package com.tienda.backend.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.backend.Model.Detallefactura;
import com.tienda.backend.Repository.Detallefacturarepository;

@Service
public class DetallerfacturaService {
    @Autowired
    private Detallefacturarepository detallefacturarepository;
    
    public List<Detallefactura> getAllDetallefacturas(){
        return detallefacturarepository.findAll();
    }
    public Detallefactura saveDetallefactura(Detallefactura detallefactura){
        return detallefacturarepository.save(detallefactura);
    }
}
