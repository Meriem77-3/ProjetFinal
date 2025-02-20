"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";


import { StoreContext } from "../context/StoreContext.jsx";

import { useContext } from "react";

const Login = () => {
  const router = useRouter();

  const [user, setUser] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  
  // Make sure storeContext has a value
  const contextValue = useContext(StoreContext);
  if (!contextValue) {
    // Handle the case where context is not available
    return <div>Loading...</div>;
  }

  const { token, login, setToken } = contextValue;

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUser((prev) => ({ ...prev, [name]: value }));
  };

  const handleClick = async (e) => {
    e.preventDefault();
    setError("");

    try {
      // Call the login method from AuthContext
     const response = await login(user.email, user.password);
     console.log(response);
     if(response.status === 200){
      setToken(response.data.accessToken);
      localStorage.setItem("token",response.data.accessToken);
      router.push("/home"); // Redirect to home page upon success
     }
     
    } catch (err) {
      console.error("Login error: ", err);
      setError("Invalid credentials");
    }
  };

  return (
    <div>
      <section className="bg-gray-50">
        <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
          <div className="w-full bg-white rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0">
            <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
              <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                Sign in to your account
              </h1>
              <form className="space-y-4 md:space-y-6" onSubmit={handleClick}>
                <div>
                  <label
                    htmlFor="email"
                    className="block mb-2 text-sm font-medium text-gray-900"
                  >
                    Your email
                  </label>
                  <input
                    type="email"
                    name="email"
                    id="email"
                    value={user.email}
                    onChange={changeHandler}
                    className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                    placeholder="name@company.com"
                    required
                  />
                </div>
                <div>
                  <label
                    htmlFor="password"
                    className="block mb-2 text-sm font-medium text-gray-900"
                  >
                    Password
                  </label>
                  <input
                    type="password"
                    name="password"
                    id="password"
                    value={user.password}
                    onChange={changeHandler}
                    placeholder="••••••••"
                    className="bg-gray-50 border border-gray-300 text-gray-900 rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                    required
                  />
                </div>
                <div className="flex items-center justify-between">
                  <div className="flex items-start">
                    <div className="flex items-center h-5">
                      <input
                        id="remember"
                        aria-describedby="remember"
                        type="checkbox"
                        className="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300"
                      />
                    </div>
                    <div className="ml-3 text-sm">
                      <label htmlFor="remember" className="text-gray-500">
                        Remember me
                      </label>
                    </div>
                  </div>
                  <a
                    href="#"
                    className="text-sm font-medium text-primary-600 hover:underline"
                  >
                    Forgot password?
                  </a>
                </div>
                <button
                  type="submit"
                  className="w-full text-white bg-sky-500 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                >
                  Sign in
                </button>
                <p className="text-sm font-light text-gray-500">
                  Don’t have an account yet?{" "}
                  <a
                    href="/register"
                    className="font-medium text-sky-500 hover:underline"
                  >
                    Sign up
                  </a>
                </p>
              </form>
              {error && <div className="text-red-500 text-sm">{error}</div>}
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Login;
