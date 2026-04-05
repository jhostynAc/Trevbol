package com.tienda.backend.Dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.tienda.backend.Model.Formpedido;

class PedidorequestTest {

    private Pedidorequest pedidorequest;
    private Formpedido pedido;
    private List<Itemcarrito> productos;

    @BeforeEach
    void setUp() {
        pedidorequest = new Pedidorequest();
        
        pedido = new Formpedido();
        pedido.setNombre("Juan");
        pedido.setCorreo("juan@ejemplo.com");
        
        productos = new ArrayList<>();
        Itemcarrito item1 = new Itemcarrito();
        item1.setProductoId(1L);
        item1.setCantidad(2);
        productos.add(item1);
    }

    @Test
    void testPedidorequestCreation() {
        assertNotNull(pedidorequest);
    }

    @Test
    void testSetAndGetPedido() {
        pedidorequest.setPedido(pedido);
        assertEquals(pedido, pedidorequest.getPedido());
        assertEquals("Juan", pedidorequest.getPedido().getNombre());
    }

    @Test
    void testSetAndGetProductos() {
        pedidorequest.setProductos(productos);
        assertEquals(productos, pedidorequest.getProductos());
        assertEquals(1, pedidorequest.getProductos().size());
    }

    @Test
    void testPedidoNull() {
        pedidorequest.setPedido(null);
        assertNull(pedidorequest.getPedido());
    }

    @Test
    void testProductosNull() {
        pedidorequest.setProductos(null);
        assertNull(pedidorequest.getProductos());
    }

    @Test
    void testProductosVacio() {
        pedidorequest.setProductos(new ArrayList<>());
        assertNotNull(pedidorequest.getProductos());
        assertEquals(0, pedidorequest.getProductos().size());
    }

    @Test
    void testMultiplesAtributos() {
        pedidorequest.setPedido(pedido);
        pedidorequest.setProductos(productos);

        assertNotNull(pedidorequest.getPedido());
        assertNotNull(pedidorequest.getProductos());
        assertEquals("Juan", pedidorequest.getPedido().getNombre());
        assertEquals(1, pedidorequest.getProductos().size());
    }

    @Test
    void testMultiplesProductos() {
        List<Itemcarrito> variosProductos = new ArrayList<>();
        
        Itemcarrito item1 = new Itemcarrito();
        item1.setProductoId(1L);
        item1.setCantidad(2);
        
        Itemcarrito item2 = new Itemcarrito();
        item2.setProductoId(2L);
        item2.setCantidad(3);
        
        Itemcarrito item3 = new Itemcarrito();
        item3.setProductoId(3L);
        item3.setCantidad(1);
        
        variosProductos.add(item1);
        variosProductos.add(item2);
        variosProductos.add(item3);
        
        pedidorequest.setProductos(variosProductos);
        
        assertEquals(3, pedidorequest.getProductos().size());
        assertEquals(1L, pedidorequest.getProductos().get(0).getProductoId());
        assertEquals(2, pedidorequest.getProductos().get(0).getCantidad());
    }

    @Test
    void testActualizarProductos() {
        List<Itemcarrito> productosIniciales = new ArrayList<>();
        Itemcarrito item1 = new Itemcarrito();
        item1.setProductoId(1L);
        item1.setCantidad(1);
        productosIniciales.add(item1);
        
        pedidorequest.setProductos(productosIniciales);
        assertEquals(1, pedidorequest.getProductos().size());
        
        List<Itemcarrito> productosActualizados = new ArrayList<>();
        Itemcarrito item2 = new Itemcarrito();
        item2.setProductoId(2L);
        item2.setCantidad(5);
        productosActualizados.add(item2);
        
        pedidorequest.setProductos(productosActualizados);
        assertEquals(1, pedidorequest.getProductos().size());
        assertEquals(2L, pedidorequest.getProductos().get(0).getProductoId());
    }

    @Test
    void testPedidoConDatosVacios() {
        Formpedido pedidoVacio = new Formpedido();
        pedidoVacio.setNombre("");
        pedidoVacio.setCorreo("");
        
        pedidorequest.setPedido(pedidoVacio);
        assertEquals("", pedidorequest.getPedido().getNombre());
    }

    @Test
    void testProductosConCantidadesNegativas() {
        List<Itemcarrito> productosNegativos = new ArrayList<>();
        Itemcarrito item = new Itemcarrito();
        item.setProductoId(1L);
        item.setCantidad(-5);
        productosNegativos.add(item);
        
        pedidorequest.setProductos(productosNegativos);
        assertEquals(-5, pedidorequest.getProductos().get(0).getCantidad());
    }

    @Test
    void testProductosConIdsNull() {
        List<Itemcarrito> productosNull = new ArrayList<>();
        Itemcarrito item = new Itemcarrito();
        item.setProductoId(null);
        item.setCantidad(1);
        productosNull.add(item);
        
        pedidorequest.setProductos(productosNull);
        assertNull(pedidorequest.getProductos().get(0).getProductoId());
    }

    @Test
    void testEquals() {
        Pedidorequest otro = new Pedidorequest();
        otro.setPedido(pedido);
        otro.setProductos(productos);
        
        assertNotNull(otro);
    }
}
