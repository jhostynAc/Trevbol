import './Producto.css';
import logo from '../Img/Logo.png';
import { Link, useNavigate } from 'react-router';
import axios from "axios";
import { useState, useEffect } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFilePdf } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';

export default function Ventas() {
    const navigate = useNavigate();
    const [pedidos, setPedidos] = useState([]);
    const [editando, setEditando] = useState(false);
    const [idEditar, setIdEditar] = useState(null);

    const estadoInicial = {
        nombre: "",
        apellidos: "",
        cedula: "",
        correo: "",
        telefono: "",
        direccion: "",
        especificaciones: "",
        estado: ""
    };

    const [pedido, setPedido] = useState({
        nombre: "",
        apellidos: "",
        cedula: "",
        correo: "",
        telefono: "",
        direccion: "",
        especificaciones: "",
        estado: ""
    });

    const admin = JSON.parse(localStorage.getItem("admin"));
    useEffect(() => {
        if (!admin) {
            navigate("/admin");
        }
    }, [admin, navigate]);

    const cargarPedidos = async () => {
        try {
            const response = await axios.get("http://localhost:8080/formpedido");
            setPedidos(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        cargarPedidos();
    }, []);

    const logout = () => {
        Swal.fire({
            title: '¿Cerrar sesión?',
            text: "Tendrás que ingresar de nuevo para gestionar ventas.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#28a745',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, salir',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                localStorage.removeItem("admin");
                navigate("/admin");
            }
        });
    };
    const editarPedido = (p) => {
        setPedido({ ...p });
        setIdEditar(p.id);
        setEditando(true);
        window.scrollTo({ top: 0, behavior: 'smooth' });
    };

    const actualizarPedido = async () => {
        try {
            // 'pedido' ahora tiene los datos completos gracias al cambio anterior
            await axios.put(`http://localhost:8080/formpedido/${idEditar}`, pedido);

            await Swal.fire({
                title: '¡Actualizado!',
                text: 'El pedido se ha modificado con éxito.',
                icon: 'success',
                timer: 1500,
                showConfirmButton: false
            });

            setEditando(false);
            setIdEditar(null);
            setPedido(estadoInicial); 
            cargarPedidos();
        } catch (error) {
            console.error("Error en la actualización:", error.response?.data || error.message);
            Swal.fire('Error', 'No se pudo actualizar el pedido. Verifica los datos.', 'error');
        }
    };

    const generarFactura = async (pedidoId) => {
        Swal.fire({
            title: 'Generando Guía PDF...',
            didOpen: () => { Swal.showLoading() }
        });

        try {
            const response = await axios.get(`http://localhost:8080/formpedido/factura/${pedidoId}`, {
                responseType: 'blob'
            });
            const file = new Blob([response.data], { type: 'application/pdf' });
            const fileURL = URL.createObjectURL(file);
            window.open(fileURL);
            Swal.close();
        } catch (error) {
            Swal.fire('Error', 'No se pudo generar el archivo', 'error');
        }
    };

    return (
        <>
            <nav>
                <div className="logo"><img src={logo} alt="logo" /></div>
                <div className="botones">
                    <ul>
                        <li><Link to="/producto">Productos</Link></li>
                        <li><Link id='activa' to="/ventas">Ventas</Link></li>
                        <li><button className="btn-logout" onClick={logout}>Cerrar sesión</button></li>
                    </ul>
                </div>
            </nav>

            <div className="container-producto">
                <div className="forms-prodcuto">
                    <div className="prodcto-ingreso">
                        <span className="info-productos">Nombre</span>
                        <input type="text" placeholder="Nombre" value={pedido.nombre} onChange={(e) => setPedido({ ...pedido, nombre: e.target.value })} />
                    </div>

                    <div className="prodcto-ingreso">
                        <span className="info-productos">Apellidos</span>
                        <input type="text" placeholder="Apellidos" value={pedido.apellidos} onChange={(e) => setPedido({ ...pedido, apellidos: e.target.value })} />
                    </div>

                    <div className="prodcto-ingreso">
                        <span className="info-productos">Cedula</span>
                        <input type="number" placeholder="Cedula" value={pedido.cedula} onChange={(e) => setPedido({ ...pedido, cedula: e.target.value })} />
                    </div>

                    <div className="prodcto-ingreso">
                        <span className="info-productos">Correo</span>
                        <input type="email" placeholder="Correo" value={pedido.correo} onChange={(e) => setPedido({ ...pedido, correo: e.target.value })} />
                    </div>

                    <div className="prodcto-ingreso">
                        <span className="info-productos">Direccion</span>
                        <input type="text" placeholder="Direccion" value={pedido.direccion} onChange={(e) => setPedido({ ...pedido, direccion: e.target.value })} />
                    </div>

                    <div className="prodcto-ingreso">
                        <span className="info-productos">Especificaciones</span>
                        <input type="text" placeholder="Especificaciones" value={pedido.especificaciones} onChange={(e) => setPedido({ ...pedido, especificaciones: e.target.value })} />
                    </div>

                    <div className="prodcto-ingreso">
                        <span className="info-productos">Estado del pedido</span>
                        <input list="estadoPedido" placeholder="Estado" value={pedido.estado} onChange={(e) => setPedido({ ...pedido, estado: e.target.value })} />
                        <datalist id="estadoPedido">
                            <option value="Enviado"></option>
                            <option value="Entregado"></option>
                            <option value="Cancelado"></option>
                        </datalist>
                    </div>
                </div>

                {editando && (
                    <div className="btns-editar">
                        <button className="btn-actualizar" onClick={actualizarPedido}>Confirmar</button>
                        <button className="btn-limpiar" onClick={() => {
                            setEditando(false);
                            setPedido({ nombre: "", apellidos: "", cedula: "", correo: "", telefono: "", direccion: "", especificaciones: "", estado: "" });
                        }}>Cancelar</button>
                    </div>
                )}
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Cedula</th>
                        <th>Correo</th>
                        <th>Direccion</th>
                        <th>Especificaciones</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Fecha</th>
                        <th>Guia</th>
                        <th>Editar</th>
                    </tr>
                </thead>
                <tbody>
                    {pedidos.map((p) => (
                        <tr key={p.id}>
                            <td>{p.nombre}</td>
                            <td>{p.apellidos}</td>
                            <td>{p.cedula}</td>
                            <td>{p.correo}</td>
                            <td>{p.direccion}</td>
                            <td>{p.especificaciones}</td>
                            <td>{p.total}</td>
                            <td>{p.estado}</td>
                            <td>{p.fecha}</td>
                            <td>
                                <button className="btn-pdf" onClick={() => generarFactura(p.id)}>
                                    <FontAwesomeIcon icon={faFilePdf} />
                                </button>
                            </td>
                            <td>
                                <button className="btn-editar" onClick={() => editarPedido(p)}>Editar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}