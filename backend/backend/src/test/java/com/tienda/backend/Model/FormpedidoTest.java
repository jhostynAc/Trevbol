package com.tienda.backend.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class FormpedidoTest {

    private Formpedido formpedido;

    @BeforeEach
    void setUp() {
        formpedido = new Formpedido();
    }

    @Test
    void testFormpedidoCreation() {
        assertNotNull(formpedido);
    }

    @Test
    void testSetAndGetNombre() {
        String nombre = "Juan";
        formpedido.setNombre(nombre);
        assertEquals(nombre, formpedido.getNombre());
    }

    @Test
    void testSetAndGetApellidos() {
        String apellidos = "Pérez López";
        formpedido.setApellidos(apellidos);
        assertEquals(apellidos, formpedido.getApellidos());
    }

    @Test
    void testSetAndGetCedula() {
        long cedula = 12345678L;
        formpedido.setCedula(cedula);
        assertEquals(cedula, formpedido.getCedula());
    }

    @Test
    void testSetAndGetCorreo() {
        String correo = "juan@ejemplo.com";
        formpedido.setCorreo(correo);
        assertEquals(correo, formpedido.getCorreo());
    }

    @Test
    void testSetAndGetTelefono() {
        long telefono = 5551234567L;
        formpedido.setTelefono(telefono);
        assertEquals(telefono, formpedido.getTelefono());
    }

    @Test
    void testSetAndGetDireccion() {
        String direccion = "Calle Principal 123";
        formpedido.setDireccion(direccion);
        assertEquals(direccion, formpedido.getDireccion());
    }

    @Test
    void testSetAndGetEspecificaciones() {
        String especificaciones = "Especificaciones personalizadas";
        formpedido.setEspecificaciones(especificaciones);
        assertEquals(especificaciones, formpedido.getEspecificaciones());
    }

    @Test
    void testSetAndGetTotal() {
        double total = 150.50;
        formpedido.setTotal(total);
        assertEquals(total, formpedido.getTotal());
    }

    @Test
    void testSetAndGetEstado() {
        String estado = "Completado";
        formpedido.setEstado(estado);
        assertEquals(estado, formpedido.getEstado());
    }

    @Test
    void testEstadoPorDefecto() {
        Formpedido nuevoPedido = new Formpedido();
        assertEquals("Pendiente", nuevoPedido.getEstado());
    }

    @Test
    void testTotalCero() {
        formpedido.setTotal(0.0);
        assertEquals(0.0, formpedido.getTotal());
    }

    @Test
    void testTotalNegativo() {
        formpedido.setTotal(-50.0);
        assertEquals(-50.0, formpedido.getTotal());
    }

    @Test
    void testMultiplesAtributos() {
        formpedido.setNombre("Juan");
        formpedido.setApellidos("Pérez");
        formpedido.setCorreo("juan@ejemplo.com");
        formpedido.setTelefono(5551234567L);
        formpedido.setTotal(100.0);
        formpedido.setEstado("Procesando");

        assertEquals("Juan", formpedido.getNombre());
        assertEquals("Pérez", formpedido.getApellidos());
        assertEquals("juan@ejemplo.com", formpedido.getCorreo());
        assertEquals(5551234567L, formpedido.getTelefono());
        assertEquals(100.0, formpedido.getTotal());
        assertEquals("Procesando", formpedido.getEstado());
    }

    @Test
    void testNombreVacio() {
        formpedido.setNombre("");
        assertEquals("", formpedido.getNombre());
    }

    @Test
    void testApellidosNull() {
        formpedido.setApellidos(null);
        assertNull(formpedido.getApellidos());
    }
}
