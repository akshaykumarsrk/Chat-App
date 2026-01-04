import axios from "axios";
export const baseURL = "http://13.60.211.175:8080";
export const httpClient = axios.create({
  baseURL: baseURL,
});
