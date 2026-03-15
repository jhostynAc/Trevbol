package com.tienda.backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.tienda.backend.Model.Formpedido;
import com.tienda.backend.Dto.Pedidorequest;
import com.tienda.backend.Service.Formenvioservice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/formpedido")
@CrossOrigin("http://localhost:3000")
public class Formpedidocontroller {

    private final Formenvioservice formenvioservice;

    public Formpedidocontroller(Formenvioservice formenvioservice){
        this.formenvioservice = formenvioservice;
    }

    @GetMapping
    public List<Formpedido> listarFormpedidos(){
        return formenvioservice.getAllFormenvios();
    }

    @PostMapping
    public String crearPedido(@RequestBody Pedidorequest request){
        formenvioservice.guardarPedidoCompleto(request);
        return "Pedido guardado correctamente";
    }

     @PutMapping("/{id}")
    public Formpedido actualizarPedido(@PathVariable Long id, @RequestBody Formpedido pedido){
        return formenvioservice.updateFormenvio(id, pedido);
    }
     @GetMapping("/factura/{id}")
    public ResponseEntity<byte[]> generarFactura(@PathVariable Long id) {
        try {
            byte[] pdf = formenvioservice.generarFacturaPedido(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=factura_" + id + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}