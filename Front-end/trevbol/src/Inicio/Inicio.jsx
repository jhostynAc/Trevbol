import './inicio.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCartShopping } from '@fortawesome/free-solid-svg-icons';
import camisafon from '../Img/fondocamis.png';
function inicio() {
    return (
        <body>
            <div className="contenedor_principal">
                <nav className="navbar">
                    <div className="logo"><h3>Trevbol</h3></div>
                    <div className="botones">
                        <ul>
                            <li id='activa'><link rel="stylesheet" href="" />Inicio</li>
                            <li><link rel="stylesheet" href="" />Sublimacion</li>
                            <li><link rel="stylesheet" href="" />Serigrafia</li>
                            <li><link rel="stylesheet" href="" />Catalogo</li>
                            <li id='carrito'><link rel="stylesheet" href="" /><FontAwesomeIcon icon={faCartShopping} /></li>
                            <li> </li>
                        </ul>
                    </div>
                </nav>
                <div className="container1">
                    <div className="container-img"><img src={camisafon} alt="Camisas" /></div>
                    <div className="container">
                        <h2>Dark mode</h2>
                        <p>
                            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Consequuntur, obcaecati facilis nam dicta consequatur hic fugiat tempora pariatur sed ipsam aliquid suscipit molestias veritatis rerum corrupti possimus quidem repellat nihil?
                        </p>
                        <button><link rel="stylesheet" href="" />Ver Catalogo</button>
                    </div>
                </div>
            </div>
        </body>

    );
}
export default inicio;