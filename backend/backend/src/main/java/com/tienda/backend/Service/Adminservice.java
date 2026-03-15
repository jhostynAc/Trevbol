package com.tienda.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.tienda.backend.Model.Admin;
import com.tienda.backend.Repository.Adminrepository;

@Service
public class Adminservice {
    @Autowired
    private Adminrepository adminrepository;
    private BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();


    public Admin login(String username, String password) {
        Admin admin = adminrepository.findByUsername(username);

        if (admin != null && encoder.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
       
    }
}
