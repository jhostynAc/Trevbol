import './inicio.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartShopping } from '@fortawesome/free-solid-svg-icons';
import camisafon from '../Img/machape.png';
import logo from '../Img/Logo.png';
function inicio() {
    return (
        <body>
            <div className="contenedor_principal">
                <nav className="navbar">
                    <div className="logo"><img src={logo} alt="logo" /></div>
                    <div className="botones">
                        <ul>
                            <li id='activa'><link rel="stylesheet" href="" />Inicio</li>
                            <li><link rel="stylesheet" href="" />Sublimacion</li>
                            <li><link rel="stylesheet" href="" />Serigrafia</li>
                            <li><link rel="stylesheet" href="" />Catalogo</li>
                            <li id='carrito'><link rel="stylesheet" href="" /><FontAwesomeIcon icon={faCartShopping} /></li>
                        </ul>
                    </div>
                </nav>
                <div className="container1">
                    <div className="container">
                        <h2>Trevbol</h2>
                        <h3>
                            Tu estilo no se copia. Se crea.
                        </h3>
                        <button>Ver Catalogo</button>
                    </div>
                    <div className="container-img">
                        <img src={camisafon} alt="Imagen" />
                    </div>
                </div>
            </div>
        </body>

    );
}
export default inicio;