"use client";
import { useState } from "react";

const ProductUploadPage = () => {
  const [image, setImage] = useState(null);
  const [title, setTitle] = useState("");
  const [category, setCategory] = useState("");
  const [description, setDescription] = useState("");

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImage(URL.createObjectURL(file)); // To preview the image before uploading
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle the product upload logic here
    const productData = {
      title,
      category,
      description,
      image, // Normally, you'd send the file itself, not the URL preview
    };
    console.log("Product data:", productData);
    // You would make an API call here to submit the data
  };

  return (
    <div className="max-w-4xl mx-auto p-8 bg-white rounded-lg shadow-lg">
      <h1 className="text-3xl font-semibold text-center text-gray-800 mb-6">
        Upload Your Product
      </h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-4">
          <label
            htmlFor="title"
            className="block text-lg font-medium text-gray-700 mb-2"
          >
            Title:
          </label>
          <input
            type="text"
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="category"
            className="block text-lg font-medium text-gray-700 mb-2"
          >
            Category:
          </label>
          <select
            id="category"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          >
            <option value="">Select Category</option>
            <option value="painting">Painting</option>
            <option value="design">Design</option>
            <option value="photograph">Photograph</option>
            <option value="sculpture">Sculpture</option>
          </select>
        </div>

        <div className="mb-4">
          <label
            htmlFor="description"
            className="block text-lg font-medium text-gray-700 mb-2"
          >
            Description:
          </label>
          <textarea
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            rows="4"
            required
          ></textarea>
        </div>

        <div className="mb-6">
          <label
            htmlFor="image"
            className="block text-lg font-medium text-gray-700 mb-2"
          >
            Upload Image:
          </label>
          <input
            type="file"
            id="image"
            accept="image/*"
            onChange={handleImageChange}
            className="block w-full text-sm text-gray-500 file:border file:border-gray-300 file:py-2 file:px-4 file:rounded-md file:bg-blue-50 file:text-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          {image && (
            <img
              src={image}
              alt="Preview"
              className="mt-4 max-w-full h-auto rounded-md shadow-md"
            />
          )}
        </div>

        <button
          type="submit"
          className="w-full py-3 bg-blue-600 text-white font-semibold rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
        >
          Upload Product
        </button>
      </form>
    </div>
  );
};

export default ProductUploadPage;
