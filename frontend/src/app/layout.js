"use client";
import "./globals.css";
import Providers from './components/Providers';
import { useRouter } from "next/navigation";


export default function RootLayout({ children }) {
  const router = useRouter();
  return (
    <html lang="fr">
    <body>
   
        
        <Providers>{children}</Providers>
   
      
    </body>
  </html>
  
  );
}