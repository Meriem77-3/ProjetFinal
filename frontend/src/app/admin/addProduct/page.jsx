"use client";
import { useState } from "react";
import Navbar from "../../components/navbarSearch";

export default function AddProduct() {
  const [product, setProduct] = useState({
    titre: "",
    description: "",
    prix: "",
    stock: "",
    imageURL: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8083/api/produits/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(product),
      });

      if (response.ok) {
        alert("Produit ajouté avec succès!");
        setProduct({
          titre: "",
          description: "",
          prix: "",
          stock: "",
          imageURL: "",
        });
      } else {
        alert("Erreur lors de l'ajout du produit");
      }
    } catch (error) {
      console.error("Erreur:", error);
      alert("Erreur lors de l'ajout du produit");
    }
  };

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  return (
    <div>
      <Navbar />
      <div className="max-w-2xl mx-auto p-4">
        <h1 className="text-2xl font-bold mb-4">Ajouter un nouveau produit</h1>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block mb-1">Titre</label>
            <input
              type="text"
              name="titre"
              value={product.titre}
              onChange={handleChange}
              className="w-full border p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">Description</label>
            <textarea
              name="description"
              value={product.description}
              onChange={handleChange}
              className="w-full border p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">Prix</label>
            <input
              type="number"
              name="prix"
              value={product.prix}
              onChange={handleChange}
              className="w-full border p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">Stock</label>
            <input
              type="number"
              name="stock"
              value={product.stock}
              onChange={handleChange}
              className="w-full border p-2 rounded"
              required
            />
          </div>
          <div>
            <label className="block mb-1">URL de l'image</label>
            <input
              type="url"
              name="imageURL"
              value={product.imageURL}
              onChange={handleChange}
              className="w-full border p-2 rounded"
              required
            />
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
          >
            Ajouter le produit
          </button>
        </form>
      </div>
    </div>
  );
}
