package com.tienda.backend.Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin(); 
    }

    @Test
    void testAdminCreation() {
        assertNotNull(admin);
    }

    @Test
    void testSetAndGetUsername() {
        String username = "admin1";
        admin.setUsername(username);
        assertEquals(username, admin.getUsername());
    }

    @Test
    void testSetAndGetPassword() {
        String password = "password123";
        admin.setPassword(password);
        assertEquals(password, admin.getPassword());
    }

    @Test
    void testSetAndGetRol() {
        String rol = "superadmin";
        admin.setRol(rol);
        assertEquals(rol, admin.getRol());
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        admin.setId(id);
        assertEquals(id, admin.getId());
    }

    @Test
    void testUsernameNull() {
        admin.setUsername(null);
        assertNull(admin.getUsername());
    }

    @Test
    void testPasswordNull() {
        admin.setPassword(null);
        assertNull(admin.getPassword());
    }

    @Test
    void testRolNull() {
        admin.setRol(null);
        assertNull(admin.getRol());
    }

    @Test
    void testUsernameVacio() {
        admin.setUsername("");
        assertEquals("", admin.getUsername());
    }

    @Test
    void testPasswordVacio() {
        admin.setPassword("");
        assertEquals("", admin.getPassword());
    }

    @Test
    void testMultiplesAtributos() {
        admin.setId(1L);
        admin.setUsername("admin1");
        admin.setPassword("pass123");
        admin.setRol("admin");

        assertEquals(1L, admin.getId());
        assertEquals("admin1", admin.getUsername());
        assertEquals("pass123", admin.getPassword());
        assertEquals("admin", admin.getRol());
    }
}
