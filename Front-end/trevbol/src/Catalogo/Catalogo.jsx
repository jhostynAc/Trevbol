import './Catalogo.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartShopping, faClose, faTrash } from '@fortawesome/free-solid-svg-icons';
import { faInstagram, faFacebook } from '@fortawesome/free-brands-svg-icons';
import { useContext, useState, useEffect } from 'react';
import { Cartcontext } from '../Carcontex/CartProvider';
import logo from '../Img/Logo.png';
import { Link } from 'react-router';
import axios from 'axios';
import Swal from 'sweetalert2';

export default function Catalogo() {
    const [producto, setProductos] = useState([]);
    const [activecart, setActiveCart] = useState(false);
    const [Talla, setTalla] = useState({});
    const {
        addtoCart,
        contProduct,
        allproducts,
        total,
        removeFromCart,
        cleanCart
    } = useContext(Cartcontext);

    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.style.marginTop = '70px';
        }
    });

    useEffect(() => {
        axios.get("http://localhost:8080/producto")
            .then(response => {
                setProductos(response.data);
            });
    }, []);

    const handleAddToCart = (p, tallaSeleccionada) => {
        if (!tallaSeleccionada) {
            Toast.fire({
                icon: 'warning',
                title: 'Selecciona una talla primero'
            });
            return;
        }

        addtoCart(p, tallaSeleccionada);
        
        Toast.fire({
            icon: 'success',
            title: `${p.nombre} añadido`
        });
    };

    const handleCleanCart = () => {
        Swal.fire({
            title: '¿Vaciar carrito?',
            text: "Se eliminarán todos los productos seleccionados.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#28a745',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, vaciar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                cleanCart(); // Asegúrate de que en CartProvider.js cleanCart() NO tenga otro Swal.fire
            }
        });
    };

    return (
        <>
            <nav>
                <div className="logo"><img src={logo} alt="logo" /></div>
                <div className="botones">
                    <ul>
                        <li><Link to="/inicio">Inicio</Link></li>
                        <li><Link to="/sublimacion">Sublimacion</Link></li>
                        <li><Link to="/serigrafia">Serigrafia</Link></li>
                        <li id='activa'><Link to="/catalogo">Catalogo</Link></li>
                        <li id='carrito' onClick={() => setActiveCart(!activecart)}>
                            <FontAwesomeIcon icon={faCartShopping} />
                        </li>
                        <div className="cont_product">
                            <span id='contador'>{contProduct}</span>
                        </div>
                        
                        <div className={activecart ? "containercart active-cart" : "containercart hiddent-cart"}>
                            {allproducts.length ? (
                                <>
                                    {allproducts.map((p) => (
                                        <div className="cartproduct" key={`${p.id}-${p.size}`}>
                                            <div className="info-cart">
                                                <span className='cantidad'>{p.quantity}</span>
                                                <p className="titulo_carrito">{p.nombre}</p>
                                                <span className="tallas">Talla: {p.size}</span>
                                                <span className="preciopro">${p.precio}</span>
                                            </div>
                                            <FontAwesomeIcon 
                                                className='close-cart' 
                                                icon={faClose} 
                                                onClick={() => removeFromCart(p.id, p.size)} 
                                            />
                                        </div>
                                    ))}
                                    <div className="cart_total">
                                        <h3>Total:</h3>
                                        <span className="totalpagar">${total}</span>
                                    </div>
                                    <div className="btn-clear-send">
                                        <button className="btnclearall" onClick={handleCleanCart}>
                                            <FontAwesomeIcon icon={faTrash}/>
                                        </button>
                                        <Link className='btn-send' to={'/carrito'}>Ver carrito</Link>
                                    </div>
                                </>
                            ) : (
                                <p className="cart-empty">El carrito está vacío</p>
                            )}
                        </div>
                    </ul>
                </div>
            </nav>

            <div className="Titulo">
                <h2>Catálogo</h2>
            </div>

            <div className="containercompra">
                {producto.filter(val => val.estado === 'Disponible')
                    .map((p) => (
                        <div className="containertarje" key={p.id}>
                            <div className="cont-img">
                                <img src={p.imagenUrl} alt={p.nombre} />
                            </div>
                            <div className="descripciones">
                                <h3>{p.nombre}</h3>
                                <p>{p.descripcion}</p>
                                <div className="botonestalla">
                                    {['S', 'M', 'L', 'XL'].map(t => (
                                        <button 
                                            key={t}
                                            className={Talla[p.id] === t ? "size-btn active" : "size-btn"} 
                                            onClick={() => setTalla({...Talla, [p.id]: t})}
                                        >
                                            {t}
                                        </button>
                                    ))}
                                </div>
                                <div className="precios">
                                    <p>${p.precio}</p>
                                </div>
                                <button id='botonagregar' onClick={() => handleAddToCart(p, Talla[p.id])}>
                                    Agregar al carrito
                                </button>
                            </div>
                        </div>
                    ))}
            </div>

            <footer>
                <div className="contacto">
                    <p>Contáctanos:</p>
                </div>
                <div className="redes">
                    <Link to="https://www.instagram.com/trevbol_?" target="_blank"><FontAwesomeIcon icon={faInstagram} /></Link>
                    <Link to="https://www.facebook.com/share/..." target="_blank"><FontAwesomeIcon icon={faFacebook} /></Link>
                </div>
                <p>© 2026 Trevbol. Todos los derechos reservados.</p>
            </footer>
        </>
    );
}