import './Formulario.css';
import logo from '../Img/Logo.png'
import { Link } from 'react-router';

export default function Formulario() {
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
                        <input type="text" placeholder='Nombre' />
                    </div>
                    <div className="campo">
                        <label>Apellidos</label>
                        <input type="text" placeholder='Apellidos' />
                    </div>
                    <div className="campo">
                        <label>Cedula</label>
                        <input type="number" placeholder='Cedula' />
                    </div>
                    <div className="campo">
                        <label>Correo</label>
                        <input type="email" placeholder='Correo' />
                    </div>
                    <div className="campo">
                        <label>Direccion</label>
                        <input type="text" placeholder='Direccion' />
                    </div>
                    <div className="campo full">
                        <label>Especificaciones</label>
                        <input type="text" placeholder='Especificaciones' />
                    </div>
                    <div className="campo-btn">
                        <button className="btn-sendform">
                            Enviar
                        </button>
                    </div>
                </div>
            </div>
        </>
    );
}