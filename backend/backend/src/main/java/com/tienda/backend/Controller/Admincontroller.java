package com.tienda.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tienda.backend.Model.Admin;
import com.tienda.backend.Service.Adminservice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class Admincontroller {
    @Autowired
    private Adminservice adminservice;

    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin){
        return adminservice.login(admin.getUsername(),admin.getPassword());
    }
}
