import './Serigrafia.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartShopping, faClose, faTrash } from '@fortawesome/free-solid-svg-icons';
import { faInstagram, faFacebook } from '@fortawesome/free-brands-svg-icons';
import logo from '../Img/Logo.png';
import { Link } from 'react-router';
import { producto } from '../data';
import { useState } from 'react';
import { all } from 'axios';
import { useContext } from 'react';
import { Cartcontext } from '../Carcontex/CartProvider';

export default function Serigrafia() {
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

    return (
        <>
            <nav>
                <div className="logo"><img src={logo} alt="logo" /></div>
                <div className="botones">
                    <ul>
                        <li ><Link to="/inicio">Inicio</Link></li>
                        <li><Link to="/sublimacion">Sublimacion</Link></li>
                        <li id='activa'><Link to="serigrafia">Serigrafia</Link></li>
                        <li><Link to="/catalogo">Catalogo</Link></li>
                        <li id='carrito' onClick={() => setActiveCart(!activecart)}><FontAwesomeIcon icon={faCartShopping} /></li>
                        <div className="cont_product">
                            <span id='contador'>{contProduct}</span>
                        </div>
                        <div className={activecart ? "containercart active-cart" : "containercart hiddent-cart"}>
                            {allproducts.length ? (
                                <>
                                    {allproducts.map((product) => (
                                        <div className="cartproduct" key={product.id}>
                                            <div className="info-cart">
                                                <span className='cantidad'>{product.quantity}</span>
                                                <p className="titulo_carrito">{product.nombre}</p>
                                                <span className="tallas">{product.size}</span>
                                                <span className="preciopro">{product.precio}</span>
                                            </div>
                                            <FontAwesomeIcon className='close-cart' icon={faClose} onClick={() => removeFromCart(product)} />
                                        </div>
                                    ))}
                                    <div className="cart_total">
                                        <h3>Total: $</h3>
                                        <span className="totalpagar">{total}</span>
                                    </div>
                                    <div className="btn-clear-send">
                                  <button className="btnclearall" onClick={cleanCart}><FontAwesomeIcon icon={faTrash}/></button>
                                   <Link className='btn-send'>Ver carrito</Link>
                                    </div>
                                </>

                            ) :
                                <p className="cart-empty">El carrito esta vacio</p>
                            }
                        </div>
                    </ul>
                </div>
            </nav>
            <div className="Titulo">
                <h2>Serigrafia</h2>
            </div>
            <div className="containercompra">
                {producto.filter(val => val.tipo === 'serigrafia')
                .map((product) => (
                    <div className="containertarje" key={product.id}>
                        <><div className="cont-img">
                            <img src={product.img} alt="" />
                        </div><div className="descripciones">
                                <h3>{product.nombre}</h3>
                                <p>{product.descripcion}</p>
                                <div className="botonestalla">
                                    <button type='radio' className={Talla[product.id] === 'S' ? "size-btn active" : "size-btn"} onClick={() => setTalla({...Talla,[product.id]:"S"})}>S</button>
                                    <button type='radio' className={Talla[product.id] === 'M' ? "size-btn active" : "size-btn"}  onClick={() => setTalla({...Talla,[product.id]:"M"})}>M</button>
                                    <button type='radio' className={Talla[product.id] === 'L' ? "size-btn active" : "size-btn"}  onClick={() => setTalla({...Talla,[product.id]:"L"})}>L</button>
                                    <button type='radio' className={Talla[product.id] === 'XL' ? "size-btn active" : "size-btn"}  onClick={() => setTalla({...Talla,[product.id]:"XL"})}>XL</button>
                                </div>
                                <div className="precios">
                                    <p>$</p>
                                    <p>{product.precio}</p>
                                </div>
                                <button id='botonagregar' onClick={() => addtoCart(product,Talla[product.id])}>Agregar al carrito</button>
                            </div></>
                    </div>
                ))}
            </div>
            <footer>
                <div className="contacto">
                    <p>Contactanos:</p>
                </div>
                <div className="redes">
                    <FontAwesomeIcon icon={faInstagram} />
                    <FontAwesomeIcon icon={faFacebook} />

                </div>
                <p>© 2026 Trevbol. Todos los derechos reservados.</p>
            </footer>
        </>
    );
}

window.addEventListener('scroll', function () {
    var nav = this.document.querySelector('nav');
    nav.classList.toggle('bajar_1', window.scrollY > 0);
});



