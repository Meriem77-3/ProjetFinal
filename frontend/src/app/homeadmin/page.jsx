"use client";
import React, { useState } from 'react';
import { Layout, MenuIcon, Plus, Edit, Trash2, Layers } from 'lucide-react';

const DashboardPage = () => {
  const [selectedContent, setSelectedContent] = useState('products');

  const handleContentChange = (content) => {
    setSelectedContent(content);
  };

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Left Sidebar */}
      <div className="w-64 bg-gray-900 text-white">
        <div className="p-4">
          <div className="flex items-center space-x-2">
            <Layout className="w-8 h-8" />
            <span className="text-xl font-bold">AdminPanel</span>
          </div>
        </div>

        <nav className="mt-8">
          <div className="px-4 py-2 text-gray-400 uppercase text-sm">Menu</div>
          <div className="space-y-2">
           
            <button 
              onClick={() => handleContentChange('artists')} 
              className="w-full px-4 py-2 text-left hover:bg-gray-800 flex items-center space-x-2"
            >
              <Edit className="w-5 h-5" />
              <span>Artistes</span>
            </button>
            <button 
              onClick={() => handleContentChange('categories')} 
              className="w-full px-4 py-2 text-left hover:bg-gray-800 flex items-center space-x-2"
            >
              <Layers className="w-5 h-5" />
              <span>Catégories</span>
            </button>
          </div>
        </nav>
      </div>

      {/* Main Content */}
      <div className="flex-1 flex flex-col">
        {/* Top Navigation */}
        <div className="bg-white shadow">
          <div className="px-8 py-4 flex justify-between items-center">
            <h1 className="text-2xl font-bold">Dashboard</h1>
          </div>
        </div>

        {/* Content Area */}
        <div className="flex-1 p-8">
          {selectedContent === 'products' && (
            <div className="bg-white rounded-lg shadow p-6">
              <h2 className="text-2xl font-bold mb-4">Gestion des Produits</h2>
              <p>Formulaire d'ajout ou modification des produits...</p>
            </div>
          )}
          {selectedContent === 'artists' && (
            <div className="bg-white rounded-lg shadow p-6">
              <h2 className="text-2xl font-bold mb-4">Gestion des Artistes</h2>
              <button className="px-4 py-2 bg-blue-500 text-white rounded-lg">Ajouter un Artiste</button>
              <div className="mt-4 space-y-2">
                <div className="flex justify-between items-center bg-gray-100 p-4 rounded-lg">
                  <span>Artiste 1</span>
                  <div className="space-x-2">
                    <button className="px-4 py-2 bg-yellow-500 text-white rounded-lg">Modifier</button>
                    <button className="px-4 py-2 bg-red-500 text-white rounded-lg">Supprimer</button>
                  </div>
                </div>
                <div className="flex justify-between items-center bg-gray-100 p-4 rounded-lg">
                  <span>Artiste 2</span>
                  <div className="space-x-2">
                    <button className="px-4 py-2 bg-yellow-500 text-white rounded-lg">Modifier</button>
                    <button className="px-4 py-2 bg-red-500 text-white rounded-lg">Supprimer</button>
                  </div>
                </div>
              </div>
            </div>
          )}
          {selectedContent === 'categories' && (
            <div className="bg-white rounded-lg shadow p-6">
              <h2 className="text-2xl font-bold mb-4">Gestion des Catégories</h2>
              <button className="px-4 py-2 bg-blue-500 text-white rounded-lg">Ajouter une Catégorie</button>
              <div className="mt-4 space-y-2">
                <div className="flex justify-between items-center bg-gray-100 p-4 rounded-lg">
                  <span>Catégorie 1</span>
                  <div className="space-x-2">
                    <button className="px-4 py-2 bg-yellow-500 text-white rounded-lg">Modifier</button>
                    <button className="px-4 py-2 bg-red-500 text-white rounded-lg">Supprimer</button>
                  </div>
                </div>
                <div className="flex justify-between items-center bg-gray-100 p-4 rounded-lg">
                  <span>Catégorie 2</span>
                  <div className="space-x-2">
                    <button className="px-4 py-2 bg-yellow-500 text-white rounded-lg">Modifier</button>
                    <button className="px-4 py-2 bg-red-500 text-white rounded-lg">Supprimer</button>
                  </div>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default DashboardPage;
