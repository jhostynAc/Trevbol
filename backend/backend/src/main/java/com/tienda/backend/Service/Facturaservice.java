package com.tienda.backend.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;

import jakarta.mail.internet.MimeMessage;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.tienda.backend.Model.Detallefactura;

@Service
public class Facturaservice {

    @Autowired
    private JavaMailSender mailSender;

    public byte[] generarFactura(String html) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, null);
        builder.toStream(out);
        builder.run();
        return out.toByteArray();
    }

    public String cargarPlantilla() throws Exception {
        ClassPathResource resource = new ClassPathResource("templates/factura.html");
        return new String(resource.getInputStream().readAllBytes());
    }

    public String llenarDatosFactura(
            String html,
            Long id,
            String fecha,
            String nombre,
            String apellidos,
            String correo,
            long telefono,
            String direccion,
            String especificaciones,
            String total,
            String productos) {

        html = html.replace("{{numeroFactura}}", "FAC-" + id);
        html = html.replace("{{id}}", String.valueOf(id));
        html = html.replace("{{fecha}}", fecha);
        html = html.replace("{{nombre}}", nombre);
        html = html.replace("{{apellidos}}", apellidos);
        html = html.replace("{{correo}}", correo);
        html = html.replace("{{telefono}}", telefono == 0 ? "N/A" : String.valueOf(telefono));
        html = html.replace("{{direccion}}", direccion);
        html = html.replace("{{especificaciones}}", especificaciones);
        html = html.replace("{{total}}", total);
        html = html.replace("{{productos}}", productos);

        return html;
    }

    public String generarFilasProductos(List<Detallefactura> detalles) {
        StringBuilder filas = new StringBuilder();
        for (Detallefactura d : detalles) {
            filas.append("<tr>")
                    .append("<td style='text-align: left;'>").append(d.getProducto().getNombre()).append("</td>")
                    .append("<td style='text-align: center;'>").append(d.getCantidad()).append("</td>")
                    .append("<td style='text-align: center;'>$").append(d.getProducto().getPrecio()).append("</td>")
                    .append("</tr>");
        }
        return filas.toString();
    }

    public void enviarFactura(String correo, byte[] pdf) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(correo);
        helper.setSubject("¡Todo listo! Aquí tienes tu guía de compra 📦");
        helper.setText("¡Hola! Muchas gracias por tu compra. Nos hace mucha ilusión que nos hayas elegido. Te adjuntamos tu guía de compra para que tengas toda la información a mano. ¡Que la disfrutes!");
        helper.addAttachment("Guia.pdf", new ByteArrayResource(pdf));
        mailSender.send(message);
    }
}