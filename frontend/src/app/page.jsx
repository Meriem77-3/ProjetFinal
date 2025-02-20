"use client";

import Image from "next/image";
import ArtSphere from "./assets/ArtSphere.png";
import Background from "./assets/background.jpg";
import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { jwtDecode } from "jwt-decode";

function getAuthToken() {
  
  if (typeof window !== 'undefined') {
    const token = localStorage.getItem('token');
    if (!token) {
      return null;
    }
    return token;
  }
  return null;
}


export default function Home() {
  const router = useRouter();
  const handleLogout = () => {
    localStorage.removeItem('token');
    router.push('/login');
  };

  useEffect(() => {
    // Move token logic inside useEffect

      const token = getAuthToken();
     
      //const decodedToken = jwtDecode(token);
     // console.log(decodedToken);
     // console.log(decodedToken.role);
  
      
      
 
    
  }, []); // Run once on mount

  return (
    <div className="bg-white border-gray-200 dark:border-gray-600 dark:bg-gray-900">
      <nav className="bg-white border-gray-200 dark:border-gray-600 dark:bg-gray-900">
        <div className="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl p-4">
          <a
            href="/"
            className="flex items-center space-x-3 rtl:space-x-reverse"
          >
            <Image
              src={ArtSphere}
              width={32}
              height={32}
              alt="ArtSphere"
              className="h-8"
            />
            <span className="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">
              ArtSphere
            </span>
          </a>
          <div
            id="mega-menu-full"
            className="items-center justify-between font-medium hidden w-full md:flex md:w-auto md:order-1"
          >
            <ul className="flex flex-col p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
              <li>
                <a
                  href="/"
                  className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700"
                  aria-current="page"
                >
                  Home
                </a>
              </li>
              <li>
                <a
                  href="/livres"
                  className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700"
                >
                  Livres
                </a>
              </li>
              <li>
                <a
                  href="/views/AboutPage.jsp"
                  className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-blue-500 md:dark:hover:bg-transparent dark:border-gray-700"
                >
                  About
                </a>
              </li>
              <li>
                <button onClick={handleLogout} className="bg-white primary-color-text px-6 py-3 rounded-lg text-xl font-semibold primary-color-hover">Logout</button>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      {/* Section Héroïque */}
      <section
        className="bg-cover bg-center h-screen text-white"
        style={{
          backgroundImage:
            "url('https://i.postimg.cc/PJf4GRpH/Milan-Pinacotheque-Brera-11-Mantegna-blog-trace-ta-route.jpg')",
        }}
      >
        <div className="bg-black bg-opacity-50 h-full flex items-center justify-center">
          <div className="text-center max-w-2xl">
            <h1 className="text-5xl font-bold mb-6">Welcome to ArtSphere</h1>
            <p className="text-xl mb-8">
              Discover a world of creativity and inspiration on our unique
              platform. Whether you're an artist, art lover or collector, we've
              got something for you. Browse artworks
            </p>
            <a
              href="/home"
              className="bg-white primary-color-text px-6 py-3 rounded-lg text-xl font-semibold primary-color-hover"
            >
              Browse artworks
            </a>
          </div>
        </div>
      </section>
      {/* Services */}
      <section className="py-16 bg-white">
        <div className="container mx-auto text-center">
          <h2 className="text-4xl font-bold primary-color-text mb-6">
            Nos Services
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div className="bg-gray-100 p-6 rounded-lg shadow-md">
              <h3 className="text-2xl font-semibold mb-4">Grande Collection</h3>
              <p className="text-gray-700">
                Explorez des milliers de livres couvrant une variété de genres
                et de sujets.
              </p>
            </div>
            <div className="bg-gray-100 p-6 rounded-lg shadow-md">
              <h3 className="text-2xl font-semibold mb-4">
                Espaces de Lecture
              </h3>
              <p className="text-gray-700">
                Profitez d'un environnement calme et confortable pour lire et
                étudier.
              </p>
            </div>
            <div className="bg-gray-100 p-6 rounded-lg shadow-md">
              <h3 className="text-2xl font-semibold mb-4">
                Événements et Ateliers
              </h3>
              <p className="text-gray-700">
                Participez à des clubs de lecture, des discussions d'auteurs, et
                des ateliers.
              </p>
            </div>
          </div>
        </div>
      </section>

      <section className="primary-color text-white py-16">
        <div className="container mx-auto text-center">
          <h2 className="text-4xl font-bold mb-4">
            Rejoignez Notre Communauté
          </h2>
          <p className="text-xl mb-8">
            Inscrivez-vous dès aujourd'hui pour accéder à des milliers de livres
            et participer à nos événements.
          </p>
          <a
            href="/register"
            className="bg-white primary-color-text px-6 py-3 rounded-lg text-lg font-semibold primary-color-hover"
          >
            S'inscrire
          </a>
        </div>
      </section>
      
      <footer className="bg-gray-800 text-white py-6">
        <div className="container mx-auto text-center">
          <p>© 2024 ArtSphere. Tous droits réservés.</p>
          <p>
            <a href="" className="hover:underline">
              Conditions générales
            </a>{" "}
            |
            <a href="" className="hover:underline">
              Politique de confidentialité
            </a>
          </p>
        </div>
      </footer>
    </div>
  );
}
