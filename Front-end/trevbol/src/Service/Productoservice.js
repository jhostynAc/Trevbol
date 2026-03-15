import axios from "axios";

const API_URL = "http://localhost:8080/producto";

export const getProductos = () => {
    return axios.get(API_URL);
};

export const crearProducto = (producto) => {
    return axios.post(API_URL, producto);
};

export const actualizarProducto = (id, producto) => {
    return axios.put(`${API_URL}/${id}`, producto);
};