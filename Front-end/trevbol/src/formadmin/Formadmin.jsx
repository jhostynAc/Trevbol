import './Formadmin.css';
import { loginadmin } from '../Service/Adminservice';
import { useState } from 'react';
import Swal from 'sweetalert2'; 

export default function Formulario() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.style.marginTop = '70px'; 
        }
    });

    const handleLogin = async () => {
        if (!username || !password) {
            Toast.fire({
                icon: 'warning',
                title: 'Ingresa usuario y contraseña'
            });
            return;
        }

        const admin = {
            username: username,
            password: password
        };

        try {
            const response = await loginadmin(admin);
            
            if (response.data) {
                localStorage.setItem("admin", JSON.stringify(response.data));
                
                await Swal.fire({
                    icon: 'success',
                    title: '¡Bienvenido!',
                    text: 'Login exitoso, redirigiendo...',
                    timer: 1500,
                    showConfirmButton: false
                });

                window.location.href = "producto";
            } else {
                Toast.fire({
                    icon: 'error',
                    title: 'Credenciales incorrectas'
                });
            }
        } catch (error) {
            console.error(error);
            Toast.fire({
                icon: 'error',
                title: 'Error de conexión con el servidor'
            });
        }
    };

    return (
        <>
            <div className="container-form">
                <div className="formadmin">
                    <label htmlFor="usuario">Usuario</label>
                    <input 
                        id="usuario"
                        type="text" 
                        placeholder='Usuario' 
                        value={username} 
                        onChange={(e) => setUsername(e.target.value)} 
                    />
                    <label htmlFor="password">Contraseña</label>
                    <input 
                        id="password"
                        type="password" 
                        placeholder='Contraseña' 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                    />
                    <button className="btn-sendformad" onClick={handleLogin}>
                        Iniciar sesión
                    </button> 
                </div>
            </div>
        </>
    );
}