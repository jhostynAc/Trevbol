package com.tienda.backend.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tienda.backend.Model.Producto;
import com.tienda.backend.Repository.Productorepository;

@ExtendWith(MockitoExtension.class)
class ProductoserviceTest {

    @Mock
    private Productorepository productorepository;

    @InjectMocks
    private Productoservice productoservice;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setNombre("Camiseta");
        producto.setDescripcion("Camiseta de algodón");
        producto.setCategoria("Ropa");
        producto.setPrecio(29.99);
        producto.setImagenUrl("https://ejemplo.com/camiseta.jpg");
        producto.setEstado("activo");
    }

    @Test
    void testGetAllProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);

        when(productorepository.findAll()).thenReturn(productos);

        List<Producto> resultado = productoservice.getAllProductos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Camiseta", resultado.get(0).getNombre());
        verify(productorepository, times(1)).findAll();
    }

    @Test
    void testGetProductoById() {
        Long id = 1L;
        when(productorepository.findById(id)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoservice.getProductoById(id);

        assertTrue(resultado.isPresent());
        assertEquals("Camiseta", resultado.get().getNombre());
        verify(productorepository, times(1)).findById(id);
    }

    @Test
    void testGetProductoByIdNotFound() {
        Long id = 999L;
        when(productorepository.findById(id)).thenReturn(Optional.empty());

        Optional<Producto> resultado = productoservice.getProductoById(id);

        assertFalse(resultado.isPresent());
        verify(productorepository, times(1)).findById(id);
    }

    @Test
    void testSaveProducto() {
        when(productorepository.save(producto)).thenReturn(producto);

        Producto resultado = productoservice.saveProducto(producto);

        assertNotNull(resultado);
        assertEquals("Camiseta", resultado.getNombre());
        verify(productorepository, times(1)).save(producto);
    }

    @Test
    void testUpdateProducto() {
        Long id = 1L;
        Producto productoActualizado = new Producto();
        productoActualizado.setNombre("Camiseta Premium");
        productoActualizado.setDescripcion("Camiseta premium de algodón");
        productoActualizado.setCategoria("Ropa Premium");
        productoActualizado.setPrecio(49.99);
        productoActualizado.setImagenUrl("https://ejemplo.com/camiseta-premium.jpg");
        productoActualizado.setEstado("activo");

        when(productorepository.findById(id)).thenReturn(Optional.of(producto));
        when(productorepository.save(any(Producto.class))).thenReturn(productoActualizado);

        Producto resultado = productoservice.updateProducto(id, productoActualizado);

        assertNotNull(resultado);
        assertEquals("Camiseta Premium", resultado.getNombre());
        assertEquals(49.99, resultado.getPrecio());
        verify(productorepository, times(1)).findById(id);
        verify(productorepository, times(1)).save(any(Producto.class));
    }

    @Test
    void testUpdateProductoNotFound() {
        Long id = 999L;
        when(productorepository.findById(id)).thenReturn(Optional.empty());

        Producto resultado = productoservice.updateProducto(id, producto);

        assertNull(resultado);
        verify(productorepository, times(1)).findById(id);
    }

    @Test
    void testDeleteProducto() {
        Long id = 1L;
        doNothing().when(productorepository).deleteById(id);

        productoservice.deleteProducto(id);

        verify(productorepository, times(1)).deleteById(id);
    }

    @Test
    void testGetAllProductosEmpty() {
        when(productorepository.findAll()).thenReturn(new ArrayList<>());

        List<Producto> resultado = productoservice.getAllProductos();

        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        verify(productorepository, times(1)).findAll();
    }

    @Test
    void testSaveProductoConDatosInvalidos() {
        Producto productoInvalido = new Producto();
        productoInvalido.setNombre("");
        productoInvalido.setPrecio(-10.0);
        
        when(productorepository.save(productoInvalido)).thenReturn(productoInvalido);
        
        Producto resultado = productoservice.saveProducto(productoInvalido);
        
        assertNotNull(resultado);
        assertEquals("", resultado.getNombre());
        assertEquals(-10.0, resultado.getPrecio());
    }

    @Test
    void testUpdateProductoConDatosVacios() {
        Long id = 1L;
        Producto productoVacio = new Producto();
        productoVacio.setNombre("");
        productoVacio.setPrecio(0.0);
        
        when(productorepository.findById(id)).thenReturn(Optional.of(producto));
        when(productorepository.save(any(Producto.class))).thenReturn(productoVacio);
        
        Producto resultado = productoservice.updateProducto(id, productoVacio);
        
        assertNotNull(resultado);
        assertEquals("", resultado.getNombre());
    }

    @Test
    void testDeleteProductoLanzaExcepcion() {
        Long id = 1L;
        doThrow(new RuntimeException("Error al eliminar")).when(productorepository).deleteById(id);
        
        assertThrows(RuntimeException.class, () -> productoservice.deleteProducto(id));
    }

    @Test
    void testGetProductoByIdLanzaExcepcion() {
        Long id = 1L;
        when(productorepository.findById(id)).thenThrow(new RuntimeException("Error al buscar"));
        
        assertThrows(RuntimeException.class, () -> productoservice.getProductoById(id));
    }

    @Test
    void testSaveProductoLanzaExcepcion() {
        when(productorepository.save(producto)).thenThrow(new RuntimeException("Error al guardar"));
        
        assertThrows(RuntimeException.class, () -> productoservice.saveProducto(producto));
    }

    @Test
    void testUpdateProductoLanzaExcepcion() {
        Long id = 1L;
        when(productorepository.findById(id)).thenThrow(new RuntimeException("Error al buscar"));
        
        assertThrows(RuntimeException.class, () -> productoservice.updateProducto(id, producto));
    }

    @Test
    void testGetAllProductosLanzaExcepcion() {
        when(productorepository.findAll()).thenThrow(new RuntimeException("Error al listar"));
        
        assertThrows(RuntimeException.class, () -> productoservice.getAllProductos());
    }
}
