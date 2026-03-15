package com.tienda.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.backend.Model.Producto;

public interface Productorepository extends JpaRepository<Producto, Long> {

}
