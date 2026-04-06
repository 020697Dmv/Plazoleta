package com.plazoleta.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;

class PreparationQueueTest {

	@Test
	void ordersUrgentFirstThenOldestThenEstimateThenId() {
		LocalDateTime now = LocalDateTime.now();

		OrderEntity older = order(1L, now.minusMinutes(10), 3);
		OrderEntity newer = order(2L, now.minusMinutes(1), 1);

		PreparationQueue queue = PreparationQueue.fromOrders(List.of(older, newer));
		queue.markUrgent(2L, true);

		assertEquals(2L, queue.poll().orElseThrow().getId());
		assertEquals(1L, queue.poll().orElseThrow().getId());
		assertTrue(queue.poll().isEmpty());
	}

	@Test
	void tieBreaksByEstimateThenId() {
		LocalDateTime same = LocalDateTime.now().minusMinutes(5);

		OrderEntity a = order(10L, same, 1);
		OrderEntity b = order(9L, same, 2);
		OrderEntity c = order(8L, same, 1);

		PreparationQueue queue = PreparationQueue.fromOrders(List.of(a, b, c));

		assertEquals(8L, queue.poll().orElseThrow().getId());
		assertEquals(10L, queue.poll().orElseThrow().getId());
		assertEquals(9L, queue.poll().orElseThrow().getId());
	}

	private static OrderEntity order(Long id, LocalDateTime date, int plateCount) {
		List<OrderPlateEntity> plates = java.util.stream.IntStream.range(0, plateCount)
				.mapToObj(i -> OrderPlateEntity.builder().quantity(1).build())
				.toList();

		return OrderEntity.builder()
				.id(id)
				.clientId(100L)
				.date(date)
				.status("PENDIENTE")
				.restaurant(200L)
				.orderPlates(plates)
				.build();
	}
}

