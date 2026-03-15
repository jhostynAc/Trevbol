package com.tienda.backend.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.backend.Model.Producto;
import com.tienda.backend.Repository.Productorepository;

@Service
public class Productoservice {
    @Autowired
    private Productorepository productorepository;

    public List<Producto> getAllProductos(){
        return productorepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id){
        return productorepository.findById(id);
    }

    public Producto saveProducto(Producto producto){
        return productorepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto){

        Producto productoExistente = productorepository.findById(id).orElse(null);

        if(productoExistente != null){

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

    public void deleteProducto(Long id){
        productorepository.deleteById(id);
    }
}
