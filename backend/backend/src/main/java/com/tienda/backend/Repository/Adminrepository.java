package com.tienda.backend.Repository;
import com.tienda.backend.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Adminrepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
