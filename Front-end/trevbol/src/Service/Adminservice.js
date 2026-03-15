import axios from "axios";

const API_URL = "http://localhost:8080/admin";

export const loginadmin = (admin) => {
    return axios.post(`${API_URL}/login`, admin, {
        headers: {
            "Content-Type": "application/json"
        }
    });
};
