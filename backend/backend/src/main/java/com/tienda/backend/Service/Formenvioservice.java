package com.tienda.backend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.backend.Model.Formpedido;
import com.tienda.backend.Model.Detallefactura;
import com.tienda.backend.Model.Producto;
import com.tienda.backend.Dto.Pedidorequest;
import com.tienda.backend.Dto.Itemcarrito;
import com.tienda.backend.Repository.Formpedidorepository;
import com.tienda.backend.Repository.Detallefacturarepository;
import com.tienda.backend.Repository.Productorepository;

@Service
public class Formenvioservice {

    @Autowired
    private Formpedidorepository formenviorepository;

    @Autowired
    private Detallefacturarepository detallerepository;

    @Autowired
    private Productorepository productorepository;

    @Autowired
    private Facturaservice facturaservice;

    public List<Formpedido> getAllFormenvios() {
        return formenviorepository.findAll();
    }

    public Optional<Formpedido> getFormenvioById(Long id) {
        return formenviorepository.findById(id);
    }

    public Formpedido saveFormenvio(Formpedido formenvio) {
        return formenviorepository.save(formenvio);
    }

    public Formpedido updateFormenvio(Long id, Formpedido formenvio) {
        Formpedido existente = formenviorepository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombre(formenvio.getNombre());
            existente.setApellidos(formenvio.getApellidos());
            existente.setCedula(formenvio.getCedula());
            existente.setCorreo(formenvio.getCorreo());
            existente.setTelefono(formenvio.getTelefono());
            existente.setDireccion(formenvio.getDireccion());
            existente.setEspecificaciones(formenvio.getEspecificaciones());
            existente.setTotal(formenvio.getTotal());
            existente.setEstado(formenvio.getEstado());
            existente.setFecha(formenvio.getFecha());

            return formenviorepository.save(existente);
        }

        return null;
    }

    public void guardarPedidoCompleto(Pedidorequest request) {

        if (request == null || request.getPedido() == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo");
        }

        // Guardar pedido principal
        Formpedido pedido = request.getPedido();
        Formpedido pedidoGuardado = formenviorepository.save(pedido);

        List<Detallefactura> detalles = new ArrayList<>();
        double total = 0;

        // Guardar detalles de productos
        if (request.getProductos() != null) {
            for (Itemcarrito item : request.getProductos()) {
                Producto producto = productorepository.findById(item.getProductoId()).orElse(null);
                if (producto != null) {
                    Detallefactura detalle = new Detallefactura();
                    detalle.setPedido(pedidoGuardado);
                    detalle.setProducto(producto);
                    detalle.setCantidad(item.getCantidad());

                    detallerepository.save(detalle);
                    detalles.add(detalle);

                    total += producto.getPrecio() * item.getCantidad();
                }
            }
        }

        // Actualizar total del pedido
        pedidoGuardado.setTotal(total);
        formenviorepository.save(pedidoGuardado);

        // Validar correo
        if (pedidoGuardado.getCorreo() == null || pedidoGuardado.getCorreo().isEmpty()) {
            System.err.println("El pedido no tiene correo válido, no se puede enviar factura.");
            return;
        }

        try {
            System.out.println("Generando factura para pedido ID: " + pedidoGuardado.getId());

            String plantilla = facturaservice.cargarPlantilla();
            String filas = facturaservice.generarFilasProductos(detalles);

            String htmlFinal = facturaservice.llenarDatosFactura(
                    plantilla,
                    pedidoGuardado.getId(),
                    pedidoGuardado.getFecha().toString(),
                    pedidoGuardado.getNombre(),
                    pedidoGuardado.getApellidos(),
                    pedidoGuardado.getCorreo(),
                    pedidoGuardado.getTelefono(),
                    pedidoGuardado.getDireccion(),
                    pedidoGuardado.getEspecificaciones(),
                    String.valueOf(pedidoGuardado.getTotal()),
                    filas);

            byte[] pdf = null;

            // Generar PDF
            try {
                pdf = facturaservice.generarFactura(htmlFinal);
              //  java.nio.file.Files.write(java.nio.file.Paths.get("factura_" + pedidoGuardado.getId() + ".pdf"), pdf);
                System.out.println("PDF generado correctamente: factura_" + pedidoGuardado.getId() + ".pdf");
            } catch (Exception e) {
                System.err.println("Error generando PDF para pedido ID: " + pedidoGuardado.getId());
                e.printStackTrace();
            }

            // Enviar factura solo si PDF se generó
            if (pdf != null) {
                try {
                    facturaservice.enviarFactura(pedidoGuardado.getCorreo(), pdf);
                    System.out.println("Factura enviada exitosamente a: " + pedidoGuardado.getCorreo());
                } catch (Exception e) {
                    System.err.println("Error enviando factura al correo: " + pedidoGuardado.getCorreo());
                    e.printStackTrace();
                }
            } else {
                System.err.println("No se pudo generar el PDF, por lo tanto no se envió la factura.");
            }

        } catch (Exception e) {
            System.err.println("Error generando o enviando factura. Los datos del pedido ya están guardados.");
            e.printStackTrace();
        }
    }

    public byte[] generarFacturaPedido(Long pedidoId) throws Exception {
        Formpedido pedido = formenviorepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado"));

        List<Detallefactura> detalles = detallerepository.findByPedido(pedido);

        String plantilla = facturaservice.cargarPlantilla();
        String filas = facturaservice.generarFilasProductos(detalles);

        String htmlFinal = facturaservice.llenarDatosFactura(
                plantilla,
                pedido.getId(),
                pedido.getFecha().toString(),
                pedido.getNombre(),
                pedido.getApellidos(),
                pedido.getCorreo(),
                pedido.getTelefono(),
                pedido.getDireccion(),
                pedido.getEspecificaciones(),
                String.valueOf(pedido.getTotal()),
                filas);

        System.out.println("HTML listo para PDF, length=" + htmlFinal.length());

        return facturaservice.generarFactura(htmlFinal);
    }
}