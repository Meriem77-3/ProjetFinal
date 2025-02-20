"use client";
import { createContext, useContext, useState } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";
import { jwtDecode } from "jwt-decode";

// Create the AuthContext
const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem("authToken") || null);
  const [user, setUser] = useState(null); // Optional: to store decoded user info
  const router = useRouter();

  const login = async (email, password) => {
    try {
      const response = await axios.post("http://localhost:8083/artsphere/auth/login", {
        email,
        password,
      });
      const { accessToken } = response.data;

      // Store the token and update context state
      localStorage.setItem("authToken", accessToken);
      setToken(accessToken);
      console.log(accessToken);

      // Decode token if needed (optional)
      const decodedUser = jwtDecode(accessToken);
      setUser(decodedUser);

      router.push("/dashboard"); // Redirect to a protected page
    } catch (error) {
      console.error("Login failed:", error);
      throw error; // Let the caller handle the error
    }
  };

  const logout = () => {
    // Remove the token and reset context state
    localStorage.removeItem("authToken");
    setToken(null);
    setUser(null);
    router.push("/login"); // Redirect to login
  };

  const isAuthenticated = !!token;

  return (
    <AuthContext.Provider value={{ token, user, isAuthenticated, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook to use the AuthContext
export const useAuth = () => useContext(AuthContext);
