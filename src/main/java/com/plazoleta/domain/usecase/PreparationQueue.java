package com.plazoleta.domain.usecase;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;

public final class PreparationQueue {

	private final PriorityQueue<Entry> heap;
	private final Map<Long, Long> versionByOrderId = new HashMap<>();
	private final Map<Long, OrderEntity> orderById = new HashMap<>();
	private final Map<Long, Boolean> urgentByOrderId = new HashMap<>();
	private final ToIntFunction<OrderEntity> estimatedMinutesFn;

	public PreparationQueue(ToIntFunction<OrderEntity> estimatedMinutesFn) {
		this.estimatedMinutesFn = estimatedMinutesFn;
		this.heap = new PriorityQueue<>(Entry.COMPARATOR);
	}

	public static PreparationQueue fromOrders(List<OrderEntity> orders) {
		PreparationQueue queue = new PreparationQueue(PreparationQueue::defaultEstimateMinutes);
		queue.rebuild(orders);
		return queue;
	}

	public void rebuild(List<OrderEntity> orders) {
		heap.clear();
		versionByOrderId.clear();
		orderById.clear();
		urgentByOrderId.clear();

		for (OrderEntity order : orders) {
			upsert(order);
		}
	}

	public int size() {
		return orderById.size();
	}

	public void upsert(OrderEntity order) {
		if (order == null || order.getId() == null) {
			throw new IllegalArgumentException("Order and order.id are required");
		}

		Long orderId = order.getId();
		orderById.put(orderId, order);
		urgentByOrderId.putIfAbsent(orderId, false);

		long nextVersion = versionByOrderId.getOrDefault(orderId, 0L) + 1L;
		versionByOrderId.put(orderId, nextVersion);

		heap.add(toEntry(order, nextVersion));
	}

	public void markUrgent(Long orderId, boolean urgent) {
		if (orderId == null) {
			throw new IllegalArgumentException("orderId is required");
		}
		OrderEntity order = orderById.get(orderId);
		if (order == null) {
			throw new IllegalArgumentException("Order not found in queue: " + orderId);
		}

		urgentByOrderId.put(orderId, urgent);
		long nextVersion = versionByOrderId.getOrDefault(orderId, 0L) + 1L;
		versionByOrderId.put(orderId, nextVersion);
		heap.add(toEntry(order, nextVersion));
	}

	public void remove(Long orderId) {
		if (orderId == null) {
			return;
		}
		orderById.remove(orderId);
		urgentByOrderId.remove(orderId);
		versionByOrderId.remove(orderId);
	}

	public Optional<OrderEntity> peek() {
		discardStaleTop();
		Entry top = heap.peek();
		if (top == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(orderById.get(top.orderId));
	}

	public Optional<OrderEntity> poll() {
		discardStaleTop();
		Entry top = heap.poll();
		if (top == null) {
			return Optional.empty();
		}

		OrderEntity order = orderById.remove(top.orderId);
		urgentByOrderId.remove(top.orderId);
		versionByOrderId.remove(top.orderId);
		return Optional.ofNullable(order);
	}

	private void discardStaleTop() {
		while (true) {
			Entry top = heap.peek();
			if (top == null) {
				return;
			}

			Long currentVersion = versionByOrderId.get(top.orderId);
			if (currentVersion == null || currentVersion.longValue() != top.version) {
				heap.poll();
				continue;
			}

			if (!orderById.containsKey(top.orderId)) {
				heap.poll();
				continue;
			}

			return;
		}
	}

	private Entry toEntry(OrderEntity order, long version) {
		boolean urgent = urgentByOrderId.getOrDefault(order.getId(), false);
		LocalDateTime date = order.getDate();
		int estimate = estimatedMinutesFn.applyAsInt(order);
		return new Entry(order.getId(), version, urgent, date, estimate);
	}

	private static int defaultEstimateMinutes(OrderEntity order) {
		if (order.getOrderPlates() == null) {
			return 0;
		}
		return order.getOrderPlates().size();
	}

	private static final class Entry {
		private static final java.util.Comparator<Entry> COMPARATOR = (a, b) -> {
			int byUrgent = Boolean.compare(b.urgent, a.urgent);
			if (byUrgent != 0) return byUrgent;

			int byDate = nullSafeDate(a.date).compareTo(nullSafeDate(b.date));
			if (byDate != 0) return byDate;

			int byEstimate = Integer.compare(a.estimatedMinutes, b.estimatedMinutes);
			if (byEstimate != 0) return byEstimate;

			return Long.compare(a.orderId, b.orderId);
		};

		private final long orderId;
		private final long version;
		private final boolean urgent;
		private final LocalDateTime date;
		private final int estimatedMinutes;

		private Entry(long orderId, long version, boolean urgent, LocalDateTime date, int estimatedMinutes) {
			this.orderId = orderId;
			this.version = version;
			this.urgent = urgent;
			this.date = date;
			this.estimatedMinutes = estimatedMinutes;
		}

		private static LocalDateTime nullSafeDate(LocalDateTime date) {
			return date == null ? LocalDateTime.MAX : date;
		}
	}
}

