import './Carrito.css';
import logo from '../Img/Logo.png'
import { Link } from 'react-router';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faClose } from '@fortawesome/free-solid-svg-icons'
import { useContext } from 'react';
import { Cartcontext } from '../Carcontex/CartProvider';

export default function Carrito() {

    const { allproducts, total, removeFromCart,updateQuantity } = useContext(Cartcontext);

    return (
        <body>
            <nav>
                <div className="logo"><img src={logo} alt="logo" /></div>
                <div className="botones">
                    <ul>
                        <li><Link to="/catalogo">Volver</Link></li>
                    </ul>
                </div>
            </nav>
            <div className="container-carrito">
                {allproducts.length === 0 ? (
                    <h2 className="mensaje-carrito">El carrito esta vacio</h2>
                ) : (allproducts.map((product) => (
                    <div className="carTarje" key={`${product.id}-${product.size}`}>
                        <img src={product.img} alt="" />
                        <p className="Nombre_Prenda">{product.nombre}</p>
                        <span className='precio'> {product.precio}</span>
                        <span className="tallaje">{product.talla}</span>
                        <div className="cantidad-carrito">
                            <button className='btn-res'onClick={() => updateQuantity(product.id, product.size, product.quantity - 1)}>-</button>
                            <span className="Cantidad_prendas">{product.quantity}</span>
                            <button className='btn-sum'  onClick={() => updateQuantity(product.id,product.size, product.quantity + 1)}>+</button>
                        </div>
                        <FontAwesomeIcon className='delete-item' icon={faClose} onClick={() => removeFromCart(product.id, product.size)} />
                    </div>
                ))
                )}
                {allproducts.length > 0 && (
                    <>
                        <h3 className='precio-final'>Total: ${total}</h3>
                        <Link className="btn-finalizar">Finalizar pedido</Link>
                    </>
                )}
            </div>
        </body>
    );
}