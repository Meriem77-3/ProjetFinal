"use client"
import axios from 'axios';
import { useState, useEffect } from 'react';

function Welcome() {
  const [welcomeData, setWelcomeData] = useState({
    message: '',
    welcomeMessage: '',
    backgroundImageUrl: ''
  });

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    console.log(token);
    
    const config = {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    };

    axios.get('http://localhost:8083/artsphere/welcome', config)
      .then(response => {
        setWelcomeData(response.data);
      })
      .catch(error => {
        console.error('Error fetching welcome data:', error);
      });
  }, []);

  return (
    <div style={{ backgroundImage: `url(${welcomeData.backgroundImageUrl})` }}>
      <h1>{welcomeData.welcomeMessage}</h1>
      <p>{welcomeData.message}</p>
    </div>
  );
}

export default Welcome;