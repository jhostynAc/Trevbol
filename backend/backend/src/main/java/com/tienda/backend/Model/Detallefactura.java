package com.tienda.backend.Model;

import jakarta.persistence.*;

@Entity
public class Detallefactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Formpedido pedido;

    public Detallefactura() {
    }

    public Long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Formpedido getPedido() {
        return pedido;
    }

    public void setPedido(Formpedido pedido) {
        this.pedido = pedido;
    }
}