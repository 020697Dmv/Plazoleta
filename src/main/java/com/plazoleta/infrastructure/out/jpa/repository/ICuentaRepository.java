package com.plazoleta.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plazoleta.infrastructure.out.jpa.entity.Usuario;

public interface ICuentaRepository  extends JpaRepository<Usuario, Long>{

}
