"use client";
import React, { createContext, useRef } from 'react';
import {useState,useEffect,useCallback} from "react"
import axios from 'axios';
import { useRouter } from "next/navigation";


export const StoreContext = createContext(null)

 const StoreContextProvider = (props) => {
 
    const [token, setToken] = useState(" ");
    const router = useRouter();
  


    const login = async (email, password) => {
        try {
            const response = await axios.post("http://localhost:8083/artsphere/auth/login", {
                email,
                password,
            });
            console.log(response);
            return response;
        } catch (error) {
            console.error("Login error:", error);
            return { status: 'error', error: error.message };
        }
    }
    const contextValue = {
        token,
        login,
      
        setToken,
       
        

    }
    useEffect(()=>
        {
            if(localStorage.getItem("token"))
                {
                    setToken(localStorage.getItem("token"));
                }
               
    
        },[])
          

    return (
        <StoreContext.Provider value={contextValue}>
            {props.children}
        </StoreContext.Provider>
    )
}

export default StoreContextProvider;
