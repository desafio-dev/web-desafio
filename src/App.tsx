import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

//pages
import Home from "./pages/Home";
import Login from './pages/Login'


export default function App() {
    return (
        <Router>
            <Routes>
                <Route Component={Login} path='/' />
                <Route Component={Home} path='/home' />
            </Routes>
        </Router>

    );
}