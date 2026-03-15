package com.tienda.backend.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.tienda.backend.Model.Producto;
import com.tienda.backend.Repository.Productorepository;

@RestController
@RequestMapping("/producto")
@CrossOrigin("http://localhost:3000")

public class Productocontroller {
    private final Productorepository productorepository;

    public Productocontroller(Productorepository productorepository) {
        this.productorepository = productorepository;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productorepository.findAll();
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productorepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {

        Producto productoExistente = productorepository.findById(id).orElse(null);

        if (productoExistente != null) {
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setCategoria(producto.getCategoria());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setImagenUrl(producto.getImagenUrl());
            productoExistente.setEstado(producto.getEstado());
            return productorepository.save(productoExistente);
        }

        return null;
    }
}
