import axios from 'axios';
import React, { useState } from 'react';
import styles from "./admindashboard.module.css";
import { useNavigate } from 'react-router-dom';

const UserDashBoard = () => {
  const [fromLoc, setFrom] = useState("");
  const [toLoc, setTo] = useState("");
  const [departure_date_time, setDate] = useState("");
  const [bus, setBus] = useState([]);
  var navigate = useNavigate();

  const searchBus = (x) => {
    x.preventDefault();
    console.log("Fetching bus data...");
    axios.get(`http://localhost:8088/api/bus/find-buses?fromLoc=${fromLoc}&toLoc=${toLoc}&departure_date_time=${departure_date_time}`)
      .then(res => {
        setBus(res.data.data);
        // console.log("Bus data set:", res.data.data);
      })
      .catch((err) => {
        console.error("API Error:", err);
        setBus([]); // Reset the bus list on error
      });
  }

  const switchLocations = () => {
    setFrom(toLoc);
    setTo(fromLoc);
  }

  return (
    <div className={styles.container}>
      <h1>Book Bus Tickets</h1>
      <form className={styles.form} onSubmit={searchBus}>
        <input 
          type="text" 
          placeholder="From" 
          className={styles.input} 
          required 
          value={fromLoc} 
          onChange={(e) => setFrom(e.target.value)} 
        />
        <button 
          type="button" 
          className={styles.switchButton} 
          onClick={switchLocations}
        >
          ⇆
        </button>
        <input 
          type="text" 
          placeholder="To" 
          className={styles.input} 
          required 
          value={toLoc} 
          onChange={(e) => setTo(e.target.value)} 
        />
        <input 
          type="date" 
          className={styles.input} 
          required 
          value={departure_date_time} 
          onChange={(e) => setDate(e.target.value)} 
        />
        <button type="submit">Search</button>
      </form>
      <div className={styles.results}>
        {bus.length > 0 ? (
          bus.map((buses) => (
            <div className={styles.buslist} key={buses.id}>
              <div className={styles.businfo}>
                <h3>{buses.name}</h3>
                <i>Total Seats: {buses.noOfSeats}</i>
                <i>Available Seats: {buses.availableSeats}</i>
                <p>From: {buses.fromLoc}</p>
                <p>To: {buses.toLoc}</p>
                <p>Date: {buses.departure_date_time}</p>
                <span>Bus Number: {buses.busno}</span>
                <button 
                  className='btn btn-danger' 
                  onClick={() => { navigate(`/bookbus/${buses.id}`) }}
                >
                  Book bus
                </button>
              </div>
            </div>
          ))
        ) : (
          <p>No buses found</p>
        )}
      </div>
    </div>
  );
}

export default UserDashBoard;
