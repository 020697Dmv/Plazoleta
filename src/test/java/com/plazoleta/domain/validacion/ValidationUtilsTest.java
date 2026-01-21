package com.plazoleta.domain.validacion;

import com.plazoleta.domain.exception.DomainExcepcion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void requeridoNoNull_null_throws() {
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requeridoNoNull(null, "campo"));
    }

    @Test
    void requeridoEmailValido_valid_returns() {
        String email = "test@example.com";
        assertEquals(email, ValidationUtils.requeridoEmailValido(email, "Correo"));
    }

    @Test
    void requeridoEmailValido_invalid_throws() {
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requeridoEmailValido("bad-email", "Correo"));
    }

    @Test
    void requeridoTelefonoValido_valid_returns() {
        String tel = "+573001234567";
        assertEquals(tel, ValidationUtils.requeridoTelefonoValido(tel, "Celular"));
    }

    @Test
    void requeridoTelefonoValido_invalid_throws() {
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requeridoTelefonoValido("abcd", "Celular"));
    }

    @Test
    void requeridoPriceValido_valid_returns() {
        assertEquals(100, ValidationUtils.requeridoPriceValido(100, "Precio"));
    }

    @Test
    void requeridoPriceValido_invalid_throws() {
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requeridoPriceValido(0, "Precio"));
    }

    @Test
    void requeridoDocumentoValido_valid_returns() {
        assertEquals(123L, ValidationUtils.requeridoDocumentoValido(123L, "Documento"));
    }

    @Test
    void requeridoDocumentoValido_invalid_throws() {
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requeridoDocumentoValido(0L, "Documento"));
    }

    @Test
    void requeridoMayorDeEdad_valid_returns() {
        LocalDate fecha = LocalDate.now().minusYears(20);
        assertEquals(fecha, ValidationUtils.requeridoMayorDeEdad(fecha, "Fecha"));
    }

    @Test
    void requeridoMayorDeEdad_underage_throws() {
        LocalDate fecha = LocalDate.now().minusYears(17);
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requeridoMayorDeEdad(fecha, "Fecha"));
    }

    @Test
    void requiredValidarNombre_valid_returns() {
        assertEquals("Mi Restaurante", ValidationUtils.requiredValidarNombre("Mi Restaurante", "Nombre"));
    }

    @Test
    void requiredValidarNombre_invalid_throws() {
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requiredValidarNombre("12345", "Nombre"));
    }

    @Test
    void requiredValidarNit_valid_returns() {
        assertEquals(123456L, ValidationUtils.requiredValidarNit(123456L, "NIT"));
    }

    @Test
    void requiredValidarNit_null_throws() {
        assertThrows(DomainExcepcion.class, () -> ValidationUtils.requiredValidarNit(null, "NIT"));
    }

}
