import axios from "axios";
export const baseUrl= import.meta.env.VITE_BACKEND_URL
export const httpClient=axios.create({
    baseURL:baseUrl,
});