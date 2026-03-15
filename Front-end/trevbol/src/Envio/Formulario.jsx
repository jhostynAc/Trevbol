import './Formulario.css';
import logo from '../Img/Logo.png'
import { Link, useNavigate } from 'react-router'; 
import axios from 'axios';
import { useState } from 'react';
import { useContext } from "react";
import { Cartcontext } from "../Carcontex/CartProvider";
import Swal from 'sweetalert2'; 

export default function Formulario() {
    const { allproducts, total, cleanCart } = useContext(Cartcontext);
    const navigate = useNavigate(); 

    const [Formpedido, setFormpedido] = useState({
        nombre: "",
        apellidos: "",
        cedula: "",
        correo: "",
        telefono: "",
        direccion: "",
        especificaciones: "",
        fecha: new Date().toISOString().split('T')[0] 
    });

   const guardarProducto = async (e) => {
    e.preventDefault();

    if (!Formpedido.nombre || !Formpedido.correo || !Formpedido.direccion) {
        Swal.fire({
            title: 'Campos incompletos',
            text: 'Por favor, llena los campos obligatorios',
            icon: 'warning'
        });
        return;
    }

    try {
        Swal.fire({
            title: 'Procesando pedido...',
            allowOutsideClick: false,
            didOpen: () => Swal.showLoading()
        });

        const productos = allproducts.map(p => ({
            productoId: p.id,
            cantidad: p.quantity
        }));

        const pedidoCompleto = {
            pedido: { ...Formpedido, total: total },
            productos: productos
        };

        await axios.post("http://localhost:8080/formpedido", pedidoCompleto);
        
        Swal.close();

    } catch (error) {
        console.error("Error en el servidor:", error);
        Swal.fire({
            title: 'Error al enviar',
            text: 'Hubo un problema con el servidor. Inténtalo más tarde.',
            icon: 'error'
        });
        return; 
    }

   
   await Swal.fire({
            title: '¡Pedido enviado correctamente!',
            text: 'La guía de compra ha sido enviada a tu correo.',
            icon: 'success',
            confirmButtonColor: '#28a745',
            confirmButtonText: 'Genial'
        });

    try {
        cleanCart(); 
        navigate("/catalogo"); 
    } catch (navigationError) {
        console.error("Error al navegar:", navigationError);
        window.location.href = "/catalogo"; 
    }
};

    return (
        <>
            <nav>
                <div className="logo"><img src={logo} alt="logo" /></div>
                <div className="botones">
                    <ul>
                        <li><Link to="/carrito">Volver</Link></li>
                    </ul>
                </div>
            </nav>

            <div className="container-form">
                <div className="form">
                    <div className="campo">
                        <label>Nombre</label>
                        <input type="text" placeholder='Nombre' value={Formpedido.nombre} onChange={(e) => setFormpedido({ ...Formpedido, nombre: e.target.value })} />
                    </div>
                    <div className="campo">
                        <label>Apellidos</label>
                        <input type="text" placeholder='Apellidos' value={Formpedido.apellidos} onChange={(e) => setFormpedido({ ...Formpedido, apellidos: e.target.value })} />
                    </div>
                    <div className="campo">
                        <label>Cedula</label>
                        <input type="number" placeholder='Cedula' value={Formpedido.cedula} onChange={(e) => setFormpedido({ ...Formpedido, cedula: e.target.value })} />
                    </div>
                    <div className="campo">
                        <label>Correo</label>
                        <input type="email" placeholder='Correo' value={Formpedido.correo} onChange={(e) => setFormpedido({ ...Formpedido, correo: e.target.value })} />
                    </div>
                    <div className="campo">
                        <label>Telefono</label>
                        <input type="tel" placeholder='Telefono' value={Formpedido.telefono} onChange={(e) => setFormpedido({ ...Formpedido, telefono: e.target.value })} />
                    </div>
                    <div className="campo">
                        <label>Direccion</label>
                        <input type="text" placeholder='Direccion' value={Formpedido.direccion} onChange={(e) => setFormpedido({ ...Formpedido, direccion: e.target.value })} />
                    </div>
                    <div className="campo full">
                        <label>Especificaciones</label>
                        <input type="text" placeholder='Especificaciones' value={Formpedido.especificaciones} onChange={(e) => setFormpedido({ ...Formpedido, especificaciones: e.target.value })} />
                    </div>
                    <div className="campo-btn">
                        <button className="btn-sendform" onClick={guardarProducto}>
                            Finalizar Compra
                        </button>
                    </div>
                </div>
            </div>
        </>
    );
}