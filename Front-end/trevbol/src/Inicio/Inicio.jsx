import './inicio.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartShopping } from '@fortawesome/free-solid-svg-icons';
import { faFacebook, faInstagram } from '@fortawesome/free-brands-svg-icons';
import camisafon from '../Img/machape.png';
import logo from '../Img/Logo.png';
import { Link } from 'react-router';
function inicio() {
    return (
        <body>
             <nav>
                    <div className="logo"><img src={logo} alt="logo" /></div>
                    <div className="botones">
                        <ul>
                            <li id='activa'><Link to="/inicio">Inicio</Link></li>
                            <li><Link to="">Sublimacion</Link></li>
                            <li><Link to="">Serigrafia</Link></li>
                            <li><Link to="/catalogo">Catalogo</Link></li>
                            <li id='carrito'><link rel="stylesheet" href="" /><FontAwesomeIcon icon={faCartShopping} /></li>
                        </ul>
                    </div>
                </nav>
            <div className="contenedor_principal">
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
            <div className="titulo">
                <h3>Collage de productos</h3>
            </div>
            <div className="container_collage">
                <div class="parent">
                    <div class="div1"></div>
                    <div class="div2"></div>
                    <div class="div3"></div>
                    <div class="div4"></div>
                    <div class="div5"></div>
                </div>
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
        </body>
      
    );
}

window.addEventListener('scroll', function () {
    var nav = this.document.querySelector('nav');
    nav.classList.toggle('bajar_1', window.scrollY > 0);
});

export default inicio;