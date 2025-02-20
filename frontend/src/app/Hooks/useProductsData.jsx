import { useState, useEffect } from "react";
import { StoreContext } from "../context/StoreContext.jsx";
import { useContext } from "react";
import axios from "axios";
const useProductData = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { token,setToken } = useContext(StoreContext);

  const fetchProducts = async () => {
    try {
    const token2 = localStorage.getItem("token");
      console.log(token2);
      const config = {
        headers: {
          'Authorization': `Bearer ${token2}`
        }
      };
      
      console.log(token);
      setLoading(true);
      setError(null);
      const response = await axios.get("http://localhost:8083/api/produits", config);
      setProducts(response.data);
      console.log(response.data);
    } catch (err) {
      setError(
        err instanceof Error
          ? err.message
          : "Une erreur est survenue lors du chargement des produits"
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (token) {
      fetchProducts();
    }
  }, [token]);

  return {
    products,
    loading,
    error,
    fetchProducts,
  };
};

export default useProductData;
