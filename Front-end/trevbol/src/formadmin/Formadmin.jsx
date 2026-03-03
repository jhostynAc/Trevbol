import './Formadmin.css';
/*import { Link } from 'react-router';*/

export default function Formulario() {
    return (
        <>
         <div className="container-form">
            <div className="formadmin">
                <label htmlFor="">Usuario</label>
                <input type="text" placeholder='Usuario' />
                <label htmlFor="">Contraseña</label>
                <input type="text" placeholder='Contraseña' />
                <button className="btn-sendformad">
                 Iniciar sesion
                </button>
            </div>
         </div>
        </>
    );
}