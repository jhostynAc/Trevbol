package com.tienda.backend.Dto;

import java.util.List;
import com.tienda.backend.Model.Formpedido;

public class Pedidorequest {

    private Formpedido pedido;
    private List<Itemcarrito> productos;

    public Pedidorequest() {
    }

    public Formpedido getPedido() {
        return pedido;
    }

    public void setPedido(Formpedido pedido) {
        this.pedido = pedido;
    }

    public List<Itemcarrito> getProductos() {
        return productos;
    }

    public void setProductos(List<Itemcarrito> productos) {
        this.productos = productos;
    }
}