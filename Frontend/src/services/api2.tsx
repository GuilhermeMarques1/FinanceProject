import axios from "axios";

export const api2 = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  }
});