import axios from 'axios';
import { isTestEnv } from '../config';
import { mockdata } from '../Services/mockdata';

// Create an Axios instance
const api = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: { "Content-Type": "application/json" },
});


// Add a request interceptor to include the token in headers
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});


// API service functions
export const getMe = async() =>  {
    // Return user profile from mock data if in test environment else make real API call
    if (isTestEnv) {
        return Promise.resolve(mockdata.userProfile);
    }
    try{
        const response = await api.get('/auth/me');
        return response.data;
    }
    catch(error){
        throw error.response ? error.response.data : error;
    }
};

export const login = async(credentials) => {
    // Return login response from mock data if in test environment else make real API call to login
    if (isTestEnv) {
        return Promise.resolve(mockdata.loginResponse);
    }
    try{
        const response = await api.post('/auth/login', credentials);
        return response.data;
    }   
    catch(error){
        throw error.response ? error.response.data : error;
    }   
};

export const logout = async() => {
    // Return logout response from mock data if in test environment else make real API call to logout
    if (isTestEnv) {
        return Promise.resolve({ message: "Logged out successfully" });
    }   
    try{
        const response = await api.post('/auth/logout');
        return response.data;
    }
    catch(error){
        throw error.response ? error.response.data : error;
    }
};

export const getAdminDashboard = async() => {
    // Return admin dashboard data from mock data if in test environment else make real API call to fetch dashboard data
    if (isTestEnv) {
        return Promise.resolve(mockdata.admin_dashboard);
    }
    try{
        const response = await api.get('/admin/dashboard');
        return response.data;
    }   
    catch(error){
        throw error.response ? error.response.data : error;
    }
};

export const getStaffList = async() => {
    if (isTestEnv) {
        return Promise.resolve(mockdata.staff_list);
    }
    try{
        const response = await api.get('/staff');
        return response.data;
    }
    catch(error){
        throw error.response ? error.response.data : error;
    }
};

export const getMenus = async() => {
    if (isTestEnv) {
        return Promise.resolve(mockdata.menus);
    }
    try{
        const response = await api.get('/menus');
        return response.data;
    }
    catch(error){
        throw error.response ? error.response.data : error;
    }
};



export const getLayouts = async() => {
    if (isTestEnv) {
        return Promise.resolve(mockdata.layouts);
    }
    try{
        const response = await api.get('/layouts');
        return response.data;
    }
    catch(error){
        throw error.response ? error.response.data : error;
    }
};

export const createOrder = async(orderData) => {
    if (isTestEnv) {
        return Promise.resolve(mockdata.order_created);
    }
    try{
        const response = await api.post('/orders', orderData);
        return response.data;
    }
    catch(error){
        throw error.response ? error.response.data : error;
    }
};

//Write API requests here using the pattern above
