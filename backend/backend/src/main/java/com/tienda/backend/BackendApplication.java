package com.tienda.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import com.tienda.backend.Model.Admin;
import com.tienda.backend.Repository.Adminrepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class BackendApplication {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner init(Adminrepository repo) {
        return args -> {

            if (repo.findByUsername(adminUsername) == null) {

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                Admin admin = new Admin();
                admin.setUsername(adminUsername);
                admin.setRol("Admin");
                admin.setPassword(encoder.encode(adminPassword));

                repo.save(admin);

                System.out.println("Admin creado");
            }
        };
    }
}