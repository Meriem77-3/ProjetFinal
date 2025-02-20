import React, { useState } from "react";
import { CardElement, useStripe, useElements } from "@stripe/react-stripe-js";
import axios from "axios";
import { useCart } from "../context/CartContext";

const PaymentForm = () => {
  const { getCartTotal, clearCart } = useCart();
  const stripe = useStripe();
  const elements = useElements();
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);
  const [processing, setProcessing] = useState(false);
/*
  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    setProcessing(true);
    setError(null);

    const cardElement = elements.getElement(CardElement);

    if (!cardElement) {
      setProcessing(false);
      return;
    }

    const { error, paymentMethod } = await stripe.createPaymentMethod({
      type: "card",
      card: cardElement,
    });

    if (error) {
      setError(error.message || "An error occurred");
      setProcessing(false);
      return;
    }

    try {
      const { data } = await axios.post(
        "http://localhost:8083/api/payment/charge",
        {
          token: paymentMethod.id,
          amount: parseFloat(getCartTotal()) * 100,
          currency: "usd",
        }
      );

      setSuccess(true);
      cardElement.clear();
    } catch (err) {
      setError("Payment failed. Please try again.");
    }

    setProcessing(false);
  };
*/
const handleSubmit = async (event) => {
  
  

};
  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8 bg-white p-8 shadow-lg rounded-lg">
        <h2 className="text-2xl font-bold text-center text-gray-800">Payment</h2>
        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="p-4 border rounded-md bg-gray-50">
            <CardElement
              options={{
                style: {
                  base: {
                    fontSize: "16px",
                    color: "#424770",
                    "::placeholder": {
                      color: "#aab7c4",
                    },
                  },
                  invalid: {
                    color: "#9e2146",
                  },
                },
              }}
              className="focus:outline-none"
            />
          </div>
          <button
            type="submit"
            disabled={!stripe || processing}
            className="w-full py-2 px-4 text-white font-semibold bg-blue-600 hover:bg-blue-700 disabled:bg-gray-400 rounded-lg transition-colors"
          >
            {processing ? "Processing..." : `Pay ${getCartTotal()} MAD`}
          </button>
          {error && <div className="text-red-500 text-sm">{error}</div>}
          {success && (
            <div className="text-green-500 text-sm">
              Payment processed successfully!
            </div>
          )}
        </form>
      </div>
    </div>
  );
};

export default PaymentForm;
