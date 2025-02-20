"use client"
import React, { useState } from "react";

const Users = () => {
  const [users, setUsers] = useState([]);
  const [user, setUser] = useState({ name: "", email: "" });

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleAddUser = () => {
    setUsers([...users, user]);
    setUser({ name: "", email: "" });
  };

  return (
    <div className="container">
      <h2>Users</h2>
      <div className="form">
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={user.name}
          onChange={handleChange}
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={user.email}
          onChange={handleChange}
        />
        <button onClick={handleAddUser}>Add User</button>
      </div>
      <table className="table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
          {users.map((u, index) => (
            <tr key={index}>
              <td>{u.name}</td>
              <td>{u.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Users;
