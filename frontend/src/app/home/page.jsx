"use client";
import React, { useEffect } from "react";
import Navbar from "../components/navbarSearch";
import useProductData from "../Hooks/useProductsData";
import { useCart } from "../context/CartContext.js";
import { StoreContext }  from "../context/StoreContext.jsx";
import { useContext } from "react";


const Home = () => {
  const { products, loading, error } = useProductData();
  const {token} = useContext(StoreContext);
  const { addToCart } = useCart();
  const handleAddToCart = (product) => {
    console.log('Adding to cart:', product); 
    addToCart(product);
  };
  useEffect(() => {
   console.log(token);
  }, [ ]);

  if (loading) return <div className="text-center py-10">Chargement...</div>;
  if (error)
    return (
      <div className="text-center py-10 text-red-500">Erreur : {error}</div>
    );

 

  return (
    <div className="bg-white border-gray-200 min-h-screen">
      <Navbar />
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 p-5">
        {products.map((product) => (
          <div
            key={product.id}
            className="bg-white rounded-lg shadow-md overflow-hidden"
          >
            <div className="relative">
              <img
                src={product.imageURL}
                alt={product.titre}
                className="w-full h-48 object-cover"
              />
              <span className="absolute top-2 right-2 bg-red-500 text-white text-xs px-3 py-1 rounded-full uppercase">
                {product.stock > 0 ? `${product.stock} en stock` : "Rupture"}
              </span>
            </div>
            <div className="p-4">
              <h3 className="font-bold text-lg mb-2">{product.titre}</h3>
              <p className="text-gray-500 text-sm mb-2">
                {product.description}
              </p>
              <div className="flex items-center justify-between mb-4">
                <span className="text-blue-500 font-bold text-xl">
                  {product.prix} MAD
                </span>
              </div>
              <button
                onClick={() => handleAddToCart(product)}
                className="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600"
              >
                Ajouter au panier
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
