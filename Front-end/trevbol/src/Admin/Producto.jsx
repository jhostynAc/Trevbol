import './Producto.css';
import logo from '../Img/Logo.png';
import { Link, useNavigate } from 'react-router';
import axios from "axios";
import { useState, useEffect } from "react";
import Swal from 'sweetalert2'; 

export default function Producto() {
    const navigate = useNavigate();

    const admin = JSON.parse(localStorage.getItem("admin"));
    useEffect(() => {
        if (!admin) {
            navigate("/admin");
        }
    }, [admin, navigate]);

    const logout = () => {
        Swal.fire({
            title: '¿Cerrar sesión?',
            text: "Tendrás que autenticarte de nuevo.",
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
    }

    const [productos, setProductos] = useState([]);
    const [editando, setEditando] = useState(false);
    const [idEditar, setIdEditar] = useState(null);

    const [producto, setProducto] = useState({
        imagenUrl: "",
        nombre: "",
        categoria: "",
        descripcion: "",
        precio: "",
        estado: ""
    });

    const cargarProductos = async () => {
        try {
            const response = await axios.get("http://localhost:8080/producto");
            setProductos(response.data);
        } catch (error) {
            console.error("Error al cargar productos", error);
        }
    };

    useEffect(() => {
        cargarProductos();
    }, []);

    const handleChange = (e) => {
        setProducto({
            ...producto,
            [e.target.name]: e.target.value
        });
    };

    const editarProducto = (p) => {
        setProducto({
            imagenUrl: p.imagenUrl,
            nombre: p.nombre,
            categoria: p.categoria,
            descripcion: p.descripcion,
            precio: p.precio,
            estado: p.estado
        });
        setIdEditar(p.id);
        setEditando(true);
        window.scrollTo({ top: 0, behavior: 'smooth' }); 
    };

    const guardarProducto = async () => {
        if (!producto.nombre || !producto.precio) {
            Swal.fire('Atención', 'Nombre y Precio son obligatorios', 'info');
            return;
        }

        try {
            await axios.post("http://localhost:8080/producto", producto);
            
            Swal.fire({
                title: '¡Guardado!',
                text: 'El producto se creó correctamente.',
                icon: 'success',
                timer: 1500,
                showConfirmButton: false
            });

            cargarProductos();
            setProducto({
                imagenUrl: "", nombre: "", categoria: "",
                descripcion: "", precio: "", estado: "",
            });
        } catch (error) {
            Swal.fire('Error', 'No se pudo guardar el producto', 'error');
        }
    };

    const actualizarProducto = async () => {
        try {
            await axios.put(`http://localhost:8080/producto/${idEditar}`, producto);

            Swal.fire({
                title: '¡Actualizado!',
                text: 'Los cambios se guardaron con éxito.',
                icon: 'success',
                timer: 1500,
                showConfirmButton: false
            });

            cargarProductos();
            setEditando(false);
            setIdEditar(null);
            setProducto({
                imagenUrl: "", nombre: "", categoria: "",
                descripcion: "", precio: "", estado: ""
            });
        } catch (error) {
            Swal.fire('Error', 'No se pudo actualizar', 'error');
        }
    };

    return (
        <>
            <nav>
                <div className="logo"><img src={logo} alt="logo" /></div>
                <div className="botones">
                    <ul>
                        <li><Link id='activa' to="/producto">Productos</Link></li>
                        <li><Link to="/ventas">Ventas</Link></li>
                        <li><button className="btn-logout" onClick={logout}>Cerrar sesion</button></li>
                    </ul>
                </div>
            </nav>

            <div className="container-producto">
                <div className="forms-prodcuto">
                    <div className="prodcto-ingreso">
                        <span className="info-productos">Imagen</span>
                        <input type="text" placeholder='URL de imagen' name='imagenUrl' value={producto.imagenUrl} onChange={handleChange} />
                    </div>
                    <div className="prodcto-ingreso">
                        <span className="info-productos">Nombre producto</span>
                        <input type="text" placeholder='Nombre' name='nombre' value={producto.nombre} onChange={handleChange} />
                    </div>
                    <div className="prodcto-ingreso">
                        <span className="info-productos">Categoría</span>
                        <input list='Categoria' placeholder='Seleccionar...' name='categoria' value={producto.categoria} onChange={handleChange} onFocus={(e) => { const temp = e.target.value; e.target.value = ''; setTimeout(() => e.target.value = temp, 0); }} />
                        <datalist id='Categoria'>
                            <option value="Sublimacion"></option>
                            <option value="Serigrafia"></option>
                        </datalist>
                    </div>
                    <div className="prodcto-ingreso">
                        <span className="info-productos">Info producto</span>
                        <input type="text" placeholder='Descripción' name='descripcion' value={producto.descripcion} onChange={handleChange} />
                    </div>
                    <div className="prodcto-ingreso">
                        <span className="info-productos">Precio</span>
                        <input type="number" placeholder='0.00' min="0" name='precio' value={producto.precio} onChange={handleChange} />
                    </div>
                    <div className="prodcto-ingreso">
                        <span className="info-productos">Estado</span>
                        <input list="estado" placeholder='Estado' name='estado' value={producto.estado} onChange={handleChange} onFocus={(e) => { const temp = e.target.value; e.target.value = ''; setTimeout(() => e.target.value = temp, 0); }} />
                        <datalist id='estado'>
                            <option value="Disponible"></option>
                            <option value="Agotado"></option>
                        </datalist>
                    </div>
                </div>

                {editando ? (
                    <div className='btns-editar'>
                        <button className="btn-actualizar" onClick={actualizarProducto}>Actualizar</button>
                        <button className='btn-limpiar' onClick={() => {
                            setEditando(false);
                            setProducto({ imagenUrl: "", nombre: "", categoria: "", descripcion: "", precio: "", estado: "" });
                        }}>Cancelar</button>
                    </div>
                ) : (
                    <button className="btn-guardar" onClick={guardarProducto}>Guardar</button>
                )}
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Imagen</th>
                        <th>Nombre</th>
                        <th>Categoria</th>
                        <th>Información</th>
                        <th>Precio</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {productos.map((p) => (
                        <tr key={p.id}>
                            <td className="td-img">{p.imagenUrl}</td>
                            <td>{p.nombre}</td>
                            <td>{p.categoria}</td>
                            <td>{p.descripcion}</td>
                            <td>${p.precio}</td>
                            <td>{p.estado}</td>
                            <td>
                                <button className='btn-editar' onClick={() => editarProducto(p)}>Editar</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}