/*"use client";
import { useCart } from "../context/CartContext";
import Navbar from "../components/navbarSearch";
import { useNavigate } from "react-router-dom";

export default function PanierPage() {
  const navigate = useNavigate();
  function handlePayment() {
    navigate("/payment");
  }
  const { cartItems, removeFromCart, updateQuantity, getCartTotal } = useCart();

  console.log("Cart Items:", cartItems); // Pour déboguer

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <div className="max-w-7xl mx-auto px-4 py-8">
        <h1 className="text-2xl font-bold mb-8">Panier</h1>

        {cartItems.length === 0 ? (
          <p>Votre panier est vide</p>
        ) : (
          <>
            <div className="space-y-4">
              {cartItems.map((item) => (
                <div
                  key={item.id}
                  className="flex items-center gap-4 bg-white p-4 rounded-lg shadow"
                >
                  <img
                    src={item.imageURL}
                    alt={item.titre}
                    className="w-24 h-24 object-cover rounded"
                  />
                  <div className="flex-1">
                    <h3 className="font-bold">{item.titre}</h3>
                    <p>{item.prix} MAD</p>
                  </div>
                  <div className="flex items-center gap-2">
                    <button
                      onClick={() => updateQuantity(item.id, item.quantity - 1)}
                      className="px-3 py-1 bg-gray-100 rounded"
                    >
                      -
                    </button>
                    <span>{item.quantity}</span>
                    <button
                      onClick={() => updateQuantity(item.id, item.quantity + 1)}
                      className="px-3 py-1 bg-gray-100 rounded"
                    >
                      +
                    </button>
                  </div>
                  <button
                    onClick={() => removeFromCart(item.id)}
                    className="text-red-500"
                  >
                    Supprimer
                  </button>
                </div>
              ))}
            </div>
            <div className="mt-8 text-right">
              <p className="text-xl font-bold">Total: {getCartTotal()} MAD</p>
              <button
                className="mt-4 bg-blue-500 text-white px-6 py-2 rounded"
                onClick={handlePayment}
              >
                Procéder au paiement
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
*/
"use client";
import { useCart } from "../context/CartContext";
import Navbar from "../components/navbarSearch";
import { useRouter } from "next/navigation"; // Changed this line

export default function PanierPage() {
  
  const router = useRouter(); // Changed this line
  
  function handlePayment() {
    router.push("/payment"); // Changed this line
  }

  const { cartItems, getCartTotal, removeFromCart, updateQuantity } = useCart();
  console.log("Cart Items:", cartItems);

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <div className="max-w-7xl mx-auto px-4 py-8">
        <h1 className="text-2xl font-bold mb-8">Panier</h1>
        {cartItems.length === 0 ? (
          <p>Votre panier est vide</p>
        ) : (
          <>
            <div className="space-y-4">
              {cartItems.map((item) => (
                <div
                  key={item.id}
                  className="flex items-center gap-4 bg-white p-4 rounded-lg shadow"
                >
                  <img
                    src={item.imageURL}
                    alt={item.titre}
                    className="w-24 h-24 object-cover rounded"
                  />
                  <div className="flex-1">
                    <h3 className="font-bold">{item.titre}</h3>
                    <p>{item.prix} MAD</p>
                  </div>
                  <div className="flex items-center gap-2">
                    <button
                      onClick={() => updateQuantity(item.id, item.quantity - 1)}
                      className="px-3 py-1 bg-gray-100 rounded"
                    >
                      -
                    </button>
                    <span>{item.quantity}</span>
                    <button
                      onClick={() => updateQuantity(item.id, item.quantity + 1)}
                      className="px-3 py-1 bg-gray-100 rounded"
                    >
                      +
                    </button>
                  </div>
                  <button
                    onClick={() => removeFromCart(item.id)}
                    className="text-red-500"
                  >
                    Supprimer
                  </button>
                </div>
              ))}
            </div>
            <div className="mt-8 text-right">
              <p className="text-xl font-bold">Total: {getCartTotal()} MAD</p>
              <button
                className="mt-4 bg-blue-500 text-white px-6 py-2 rounded hover:bg-blue-600"
                onClick={handlePayment}
              >
                Procéder au paiement
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}