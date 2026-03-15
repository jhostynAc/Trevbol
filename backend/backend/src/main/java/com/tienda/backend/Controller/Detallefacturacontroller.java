package com.tienda.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tienda.backend.Model.Detallefactura;
import com.tienda.backend.Service.DetallerfacturaService;

@RestController
@RequestMapping("/detallefacturas")
@CrossOrigin("*")
public class Detallefacturacontroller {
    @Autowired
    private DetallerfacturaService detallefacturaService;
    @GetMapping
    public List<Detallefactura> getAllDetallefacturas() {
        return detallefacturaService.getAllDetallefacturas();
    }
    @PostMapping
    public Detallefactura saveDetallefactura(@RequestBody Detallefactura detallefactura) {
        return detallefacturaService.saveDetallefactura(detallefactura);
    }

}
