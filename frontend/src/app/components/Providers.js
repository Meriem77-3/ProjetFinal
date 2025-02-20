"use client";
import { CartProvider } from '../context/CartContext.js';
import  StoreContextProvider from '../context/StoreContext.jsx';

export default function Providers({ children }) {
  return <CartProvider> <StoreContextProvider>{children}</StoreContextProvider></CartProvider>;

}