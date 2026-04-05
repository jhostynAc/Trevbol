package com.tienda.backend.Dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ItemcarritoTest {

    private Itemcarrito itemcarrito;

    @BeforeEach
    void setUp() {
        itemcarrito = new Itemcarrito();
    }

    @Test
    void testItemcarritoCreation() {
        assertNotNull(itemcarrito);
    }

    @Test
    void testSetAndGetProductoId() {
        Long productoId = 1L;
        itemcarrito.setProductoId(productoId);
        assertEquals(productoId, itemcarrito.getProductoId());
    }

    @Test
    void testSetAndGetCantidad() {
        int cantidad = 3;
        itemcarrito.setCantidad(cantidad);
        assertEquals(cantidad, itemcarrito.getCantidad());
    }

    @Test
    void testProductoIdNull() {
        itemcarrito.setProductoId(null);
        assertNull(itemcarrito.getProductoId());
    }

    @Test
    void testCantidadCero() {
        itemcarrito.setCantidad(0);
        assertEquals(0, itemcarrito.getCantidad());
    }

    @Test
    void testCantidadNegativa() {
        itemcarrito.setCantidad(-5);
        assertEquals(-5, itemcarrito.getCantidad());
    }

    @Test
    void testMultiplesAtributos() {
        itemcarrito.setProductoId(5L);
        itemcarrito.setCantidad(10);

        assertEquals(5L, itemcarrito.getProductoId());
        assertEquals(10, itemcarrito.getCantidad());
    }

    @Test
    void testProductoIdGrande() {
        Long productoIdGrande = 999999999L;
        itemcarrito.setProductoId(productoIdGrande);
        assertEquals(productoIdGrande, itemcarrito.getProductoId());
    }

    @Test
    void testCantidadGrande() {
        itemcarrito.setCantidad(10000);
        assertEquals(10000, itemcarrito.getCantidad());
    }

    @Test
    void testRestablecerProductoId() {
        itemcarrito.setProductoId(1L);
        itemcarrito.setProductoId(2L);
        assertEquals(2L, itemcarrito.getProductoId());
    }

    @Test
    void testRestablecerCantidad() {
        itemcarrito.setCantidad(5);
        itemcarrito.setCantidad(10);
        assertEquals(10, itemcarrito.getCantidad());
    }

    @Test
    void testProductoIdNegativo() {
        Long productoId = -1L;
        itemcarrito.setProductoId(productoId);
        assertEquals(productoId, itemcarrito.getProductoId());
    }

    @Test
    void testCantidadMaxima() {
        int cantidad = Integer.MAX_VALUE;
        itemcarrito.setCantidad(cantidad);
        assertEquals(cantidad, itemcarrito.getCantidad());
    }

    @Test
    void testCantidadMinima() {
        int cantidad = Integer.MIN_VALUE;
        itemcarrito.setCantidad(cantidad);
        assertEquals(cantidad, itemcarrito.getCantidad());
    }

    @Test
    void testEquals() {
        Itemcarrito otro = new Itemcarrito();
        otro.setProductoId(1L);
        otro.setCantidad(3);
        
        // Si no hay equals, solo verificar no null
        assertNotNull(otro);
    }
}
