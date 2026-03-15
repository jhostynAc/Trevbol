    import axios from "axios";
    import e from "express";

    const API_URL = "http://localhost:8080/formpedido";

    export const getFormEnvios = () => {
        return axios.get(API_URL);
    }
    export const crearFormEnvio = (formpedido) => {
        return axios.post(API_URL, formpedido);
    }
    export const actualizarFormEnvio = (id, formpedido) => {
        return axios.put(`${API_URL}/${id}`, formpedido);
    }