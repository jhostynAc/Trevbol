package com.tienda.backend.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
    }

    @Test
    void testProductoCreation() {
        assertNotNull(producto);
    }

    @Test
    void testSetAndGetNombre() {
        String nombre = "Camiseta";
        producto.setNombre(nombre);
        assertEquals(nombre, producto.getNombre());
    }

    @Test
    void testSetAndGetDescripcion() {
        String descripcion = "Camiseta de algodón de alta calidad";
        producto.setDescripcion(descripcion);
        assertEquals(descripcion, producto.getDescripcion());
    }

    @Test
    void testSetAndGetCategoria() {
        String categoria = "Ropa";
        producto.setCategoria(categoria);
        assertEquals(categoria, producto.getCategoria());
    }

    @Test
    void testSetAndGetPrecio() {
        Double precio = 29.99;
        producto.setPrecio(precio);
        assertEquals(precio, producto.getPrecio());
    }

    @Test
    void testSetAndGetImagenUrl() {
        String imagenUrl = "https://ejemplo.com/camiseta.jpg";
        producto.setImagenUrl(imagenUrl);
        assertEquals(imagenUrl, producto.getImagenUrl());
    }

    @Test
    void testSetAndGetEstado() {
        String estado = "activo";
        producto.setEstado(estado);
        assertEquals(estado, producto.getEstado());
    }

    @Test
    void testPrecioNegativoCero() {
        Double precio = 0.0;
        producto.setPrecio(precio);
        assertEquals(precio, producto.getPrecio());
    }

    @Test
    void testNombreVacio() {
        producto.setNombre("");
        assertEquals("", producto.getNombre());
    }

    @Test
    void testNombreNull() {
        producto.setNombre(null);
        assertNull(producto.getNombre());
    }
}
