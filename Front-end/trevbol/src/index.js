import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter, Route,Routes} from "react-router";
import './index.css';
import Inicio from './Inicio/Inicio';
import Catalogo from './Catalogo/Catalogo';
import Serigrafia from'./serigrafia/Serigrafia';
import Sublimacion from './sublimacion/Sublimacion';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
  <Routes>
    <Route path='/' element={<Inicio/>}/>
    <Route path="/inicio" element={<Inicio />} />
    <Route path="/catalogo" element={<Catalogo />} />
    <Route path='/serigrafia' element={<Serigrafia/>}/>
    <Route path='/sublimacion' element={<Sublimacion/>}/>
  </Routes>
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
