package com.tienda.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.backend.Model.Detallefactura;
import com.tienda.backend.Model.Formpedido;
import java.util.List;

public interface Detallefacturarepository extends JpaRepository<Detallefactura, Long> {
    List<Detallefactura> findByPedido(Formpedido pedido);

}
