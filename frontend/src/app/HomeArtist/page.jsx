"use client"
import React, { useState } from 'react';
import { Layout, MenuIcon, Plus, LineChart } from 'lucide-react';
import ProductUpload from '../components/ProductUpload';

const DashboardPage = () => {
  const [selectedContent, setSelectedContent] = useState('products');

  const handleContentChange = (content) => {
    setSelectedContent(content);
  };

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Left Sidebar */}
      <div className="w-64 bg-gray-900 text-white h-screen">
        <div className="p-4">
          <div className="flex items-center space-x-2">
            <Layout className="w-8 h-8" />
            <span className="text-xl font-bold">Artist Panel</span>
          </div>
        </div>
        
        <nav className="mt-8">
          <div className="px-4 py-2 text-gray-400 uppercase text-sm">Menu</div>
          <div className="space-y-2">
            <button className="w-full px-4 py-2 text-left hover:bg-gray-800 flex items-center space-x-2">
              <MenuIcon className="w-5 h-5" />
              <span>Dashboard</span>
            </button>
          </div>
        </nav>
      </div>

      {/* Main Content */}
      <div className="flex-1 flex flex-col">
        {/* Top Navigation */}
        <div className="bg-white shadow">
          <div className="px-8 py-4 flex justify-between items-center">
            <div className="space-x-4">
              <button 
                onClick={() => handleContentChange('products')}
                className={`px-4 py-2 rounded-lg ${
                  selectedContent === 'products' 
                    ? 'bg-blue-500 text-white' 
                    : 'bg-gray-100 text-gray-700'
                }`}
              >
                <div className="flex items-center space-x-2">
                  <Plus className="w-4 h-4" />
                  <span>Ajouter Produit</span>
                </div>
              </button>
              <button 
                onClick={() => handleContentChange('tracking')}
                className={`px-4 py-2 rounded-lg ${
                  selectedContent === 'tracking' 
                    ? 'bg-blue-500 text-white' 
                    : 'bg-gray-100 text-gray-700'
                }`}
              >
                <div className="flex items-center space-x-2">
                  <LineChart className="w-4 h-4" />
                  <span>Suivis</span>
                </div>
              </button>
            </div>
            
            <div className="flex items-center space-x-4">
              <div className="relative">
                <input
                  type="search"
                  placeholder="Rechercher..."
                  className="px-4 py-2 rounded-lg bg-gray-100 focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
            </div>
          </div>
        </div>

        {/* Content Area */}
        <div className="flex-1 p-8">
          {selectedContent === 'products' ? (
            <div className="bg-white rounded-lg shadow p-6">
              
              
                <ProductUpload />
              
            </div>
          ) : (
            <div className="bg-white rounded-lg shadow p-6">
              <h2 className="text-2xl font-bold mb-4">Suivis des Ventes</h2>
              <div className="space-y-4">
                {/* Tracking content would go here */}
                <p>Statistiques et graphiques de suivi...</p>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default DashboardPage;