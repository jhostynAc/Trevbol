package com.tienda.backend.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.tienda.backend.Model.Producto;
import com.tienda.backend.Repository.Productorepository;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductocontrollerTest {

    @Mock
    private Productorepository productorepository;

    private Productocontroller controller;
    private Producto producto;

    @BeforeEach
    void setUp() {
        controller = new Productocontroller(productorepository);
        
        producto = new Producto();
        producto.setNombre("naa");
        producto.setDescripcion("Camiseta de algodón");
        producto.setCategoria("Ropa");
        producto.setPrecio(29.99);
        producto.setImagenUrl("https://ejemplo.com/camiseta.jpg");
        producto.setEstado("activo");
    }

    @Test
    void testListarProductos() {
        java.util.List<Producto> productos = new java.util.ArrayList<>();
        productos.add(producto);
        
        when(productorepository.findAll()).thenReturn(productos);
        
        java.util.List<Producto> resultado = controller.listarProductos();
        
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(productorepository, times(1)).findAll();
    }

    @Test
    void testCrearProducto() {
        when(productorepository.save(producto)).thenReturn(producto);
        
        Producto resultado = controller.crearProducto(producto);
        
        assertNotNull(resultado);
        assertEquals("naa", resultado.getNombre());
        verify(productorepository, times(1)).save(producto);
    }

    @Test
    void testActualizarProducto() {
        Long id = 1L;
        
        when(productorepository.findById(id)).thenReturn(java.util.Optional.of(producto));
        when(productorepository.save(any(Producto.class))).thenReturn(producto);
        
        Producto resultado = controller.actualizarProducto(id, producto);
        
        assertNotNull(resultado);
        assertEquals("Camiseta", resultado.getNombre());
        verify(productorepository, times(1)).findById(id);
    }

    @Test
    void testActualizarProductoNoExistente() {
        Long id = 9999L;
        
        when(productorepository.findById(id)).thenReturn(java.util.Optional.empty());
        
        Producto resultado = controller.actualizarProducto(id, producto);
        
        assertNull(resultado);
        verify(productorepository, times(1)).findById(id);
    }

    @Test
    void testListarProductosVacio() {
        when(productorepository.findAll()).thenReturn(new java.util.ArrayList<>());
        
        java.util.List<Producto> resultado = controller.listarProductos();
        
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }

    @Test
    void testCrearProductoConNombreVacio() {
        producto.setNombre("");
        when(productorepository.save(producto)).thenReturn(producto);
        
        Producto resultado = controller.crearProducto(producto);
        
        assertNotNull(resultado);
        assertEquals("", resultado.getNombre());
    }

    @Test
    void testCrearProductoConPrecioNegativo() {
        producto.setPrecio(-10.0);
        when(productorepository.save(producto)).thenReturn(producto);
        
        Producto resultado = controller.crearProducto(producto);
        
        assertNotNull(resultado);
        assertEquals(-10.0, resultado.getPrecio());
    }

    @Test
    void testCrearProductoConPrecioCero() {
        producto.setPrecio(0.0);
        when(productorepository.save(producto)).thenReturn(producto);
        
        Producto resultado = controller.crearProducto(producto);
        
        assertNotNull(resultado);
        assertEquals(0.0, resultado.getPrecio());
    }

    @Test
    void testCrearProductoConEstadoInactivo() {
        producto.setEstado("inactivo");
        when(productorepository.save(producto)).thenReturn(producto);
        
        Producto resultado = controller.crearProducto(producto);
        
        assertNotNull(resultado);
        assertEquals("inactivo", resultado.getEstado());
    }

    @Test
    void testActualizarProductoConDatosVacios() {
        Long id = 1L;
        Producto productoVacio = new Producto();
        productoVacio.setNombre("");
        productoVacio.setDescripcion("");
        productoVacio.setCategoria("");
        productoVacio.setPrecio(0.0);
        productoVacio.setImagenUrl("");
        productoVacio.setEstado("");
        
        when(productorepository.findById(id)).thenReturn(java.util.Optional.of(producto));
        when(productorepository.save(any(Producto.class))).thenReturn(productoVacio);
        
        Producto resultado = controller.actualizarProducto(id, productoVacio);
        
        assertNotNull(resultado);
        assertEquals("", resultado.getNombre());
    }

    @Test
    void testActualizarProductoConIdNull() {
        Producto resultado = controller.actualizarProducto(null, producto);
        
        assertNull(resultado);
    }

    @Test
    void testCrearProductoLanzaExcepcion() {
        when(productorepository.save(producto)).thenThrow(new RuntimeException("Error al guardar"));
        
        assertThrows(RuntimeException.class, () -> controller.crearProducto(producto));
    }

    @Test
    void testListarProductosLanzaExcepcion() {
        when(productorepository.findAll()).thenThrow(new RuntimeException("Error al listar"));
        
        assertThrows(RuntimeException.class, () -> controller.listarProductos());
    }

    @Test
    void testActualizarProductoLanzaExcepcion() {
        Long id = 1L;
        when(productorepository.findById(id)).thenThrow(new RuntimeException("Error al buscar"));
        
        assertThrows(RuntimeException.class, () -> controller.actualizarProducto(id, producto));
    }
}
