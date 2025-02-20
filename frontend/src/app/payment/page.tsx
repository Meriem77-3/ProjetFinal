/*"use client";
import React from "react";

import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import PaymentForm from "../components/PaymentForm";	
import { useCart } from "../context/CartContext";

const stripePromise = loadStripe(
  "pk_test_51QaP45EKMLauj0RaSBT5Rip3OUxLih5iOWuvIDrTr0nIVde9mJmXKG15GgIjqYNGvYRxwxijkommXvNNFP9wPldo00lpTeL9Z4"
);

export default function Home() {
  const { getCartTotal, clearCart } = useCart();
  return (
    <main className="min-h-screen p-8">
      <Elements stripe={stripePromise}>
        <PaymentForm />
      </Elements>
    </main>
  );
}
*/
"use client";
import React from "react";
import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import PaymentForm from "../components/PaymentForm";
import { useCart } from "../context/CartContext";

const stripePromise = loadStripe(
  "pk_test_51QaP45EKMLauj0RaSBT5Rip3OUxLih5iOWuvIDrTr0nIVde9mJmXKG15GgIjqYNGvYRxwxijkommXvNNFP9wPldo00lpTeL9Z4"
);

export default function Home() {
  const { getCartTotal } = useCart();

  return (
    <main className="min-h-screen flex items-center justify-center bg-gray-50">
      <div className="w-full max-w-6xl grid grid-cols-1 md:grid-cols-2 gap-8 bg-white rounded-lg shadow-lg overflow-hidden">
        {/* Left Section: Image */}
        <div className="hidden md:block">
          <img
            src="https://i.postimg.cc/PJf4GRpH/Milan-Pinacotheque-Brera-11-Mantegna-blog-trace-ta-route.jpg"
            alt="Payment Illustration"
            className="w-full h-full object-cover"
          />
        </div>

        <div className="p-8 flex flex-col justify-center">
          <h1 className="text-2xl font-bold text-gray-800 mb-6">
            Complete Your Payment
          </h1>
          <Elements stripe={stripePromise}>
            <PaymentForm />
          </Elements>
          <p className="text-gray-500 text-sm mt-4">
            Total Amount:{" "}
            <span className="font-bold text-gray-800">
              {getCartTotal()} MAD
            </span>
          </p>
        </div>
      </div>
    </main>
  );
}
