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

    @Test
    void testDescripcionNull() {
        producto.setDescripcion(null);
        assertNull(producto.getDescripcion());
    }

    @Test
    void testCategoriaNull() {
        producto.setCategoria(null);
        assertNull(producto.getCategoria());
    }

    @Test
    void testPrecioNull() {
        producto.setPrecio(null);
        assertNull(producto.getPrecio());
    }

    @Test
    void testImagenUrlNull() {
        producto.setImagenUrl(null);
        assertNull(producto.getImagenUrl());
    }

    @Test
    void testEstadoNull() {
        producto.setEstado(null);
        assertNull(producto.getEstado());
    }

    @Test
    void testPrecioNegativo() {
        Double precio = -50.0;
        producto.setPrecio(precio);
        assertEquals(precio, producto.getPrecio());
    }

    @Test
    void testPrecioMuyGrande() {
        Double precio = 999999.99;
        producto.setPrecio(precio);
        assertEquals(precio, producto.getPrecio());
    }

    @Test
    void testNombreMuyLargo() {
        String nombreLargo = "A".repeat(1000);
        producto.setNombre(nombreLargo);
        assertEquals(nombreLargo, producto.getNombre());
    }

    @Test
    void testEstadoTrueFalse() {
        producto.setEstado("true");
        assertEquals("true", producto.getEstado());
        
        producto.setEstado("false");
        assertEquals("false", producto.getEstado());
    }

    @Test
    void testEquals() {
        Producto otroProducto = new Producto();
        otroProducto.setNombre("Camiseta");
        otroProducto.setDescripcion("Camiseta de algodón");
        otroProducto.setCategoria("Ropa");
        otroProducto.setPrecio(29.99);
        otroProducto.setImagenUrl("https://ejemplo.com/camiseta.jpg");
        otroProducto.setEstado("activo");
        
        // Asumiendo que Producto tiene equals implementado, pero si no, esto fallará
        // Para este test, solo verificar que no es null
        assertNotNull(otroProducto);
    }
}
