package com.tienda.backend.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class DetallefacturaTest {

    private Detallefactura detallefactura;
    private Producto producto;
    private Formpedido pedido;

    @BeforeEach
    void setUp() {
        detallefactura = new Detallefactura();
        
        producto = new Producto();
        producto.setNombre("Camiseta");
        producto.setPrecio(29.99);
        
        pedido = new Formpedido();
        pedido.setNombre("Juan");
    }

    @Test
    void testDetallefacturaCreation() {
        assertNotNull(detallefactura);
    }

    @Test
    void testSetAndGetCantidad() {
        int cantidad = 5;
        detallefactura.setCantidad(cantidad);
        assertEquals(cantidad, detallefactura.getCantidad());
    }

    @Test
    void testSetAndGetProducto() {
        detallefactura.setProducto(producto);
        assertEquals(producto, detallefactura.getProducto());
        assertEquals("Camiseta", detallefactura.getProducto().getNombre());
    }

    @Test
    void testSetAndGetPedido() {
        detallefactura.setPedido(pedido);
        assertEquals(pedido, detallefactura.getPedido());
        assertEquals("Juan", detallefactura.getPedido().getNombre());
    }

    @Test
    void testCantidadCero() {
        detallefactura.setCantidad(0);
        assertEquals(0, detallefactura.getCantidad());
    }

    @Test
    void testCantidadNegativa() {
        detallefactura.setCantidad(-1);
        assertEquals(-1, detallefactura.getCantidad());
    }

    @Test
    void testProductoNull() {
        detallefactura.setProducto(null);
        assertNull(detallefactura.getProducto());
    }

    @Test
    void testPedidoNull() {
        detallefactura.setPedido(null);
        assertNull(detallefactura.getPedido());
    }

    @Test
    void testMultiplesAtributos() {
        detallefactura.setCantidad(3);
        detallefactura.setProducto(producto);
        detallefactura.setPedido(pedido);

        assertEquals(3, detallefactura.getCantidad());
        assertNotNull(detallefactura.getProducto());
        assertNotNull(detallefactura.getPedido());
        assertEquals("Camiseta", detallefactura.getProducto().getNombre());
        assertEquals("Juan", detallefactura.getPedido().getNombre());
    }

    @Test
    void testCantidadGrande() {
        detallefactura.setCantidad(1000);
        assertEquals(1000, detallefactura.getCantidad());
    }
}
