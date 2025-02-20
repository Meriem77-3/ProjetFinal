import dynamic from "next/dynamic";

const CheckoutForm = dynamic(() => import("./CheckoutFormComponent"), {
  ssr: false,
});

export default CheckoutForm;
