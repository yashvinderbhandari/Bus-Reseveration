import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from "./viewallbus.module.css"; // Import CSS module

const ViewAllBus = () => {
    let [bus, setBus] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8088/api/bus`)
            .then((res) => {
                console.log(res.data.data);
                setBus(res.data.data);
            })
            .catch((err) => {
                console.error(err);
            });
    }, []);

    let navigate = useNavigate();
    let e = JSON.parse(localStorage.getItem("Admin"));

    function removeBus(id) {
        axios.delete(`http://localhost:8088/api/bus/${e.id}/${id}`);
    }

    function editBus(id) {
        navigate(`/adminhomepage/editbus/${id}`);
    }

    return (
        <div className={styles.viewbus}>
            {bus.map((buses) => {
                return (
                    <div className={styles.busdetails} key={buses.id}>
                        <div className={styles.businfo}>
                            <h3>{buses.name}</h3>
                            <i>Total Seats: {buses.noOfSeats}</i>
                            <i>Available Seats: {buses.availableSeats}</i>
                            <p>From: {buses.fromLoc}</p>
                            <p>To: {buses.toLoc}</p>
                            <p>Date: {buses.departure_date_time}</p>
                            <span>Bus Number: {buses.busno}</span>
                        </div>
                        <div className={styles.buttonContainer}>
                            <button type="button" className={`btn btn-warning ${styles.button}`} onClick={() => { editBus(buses.id); }}>Edit</button>
                            <button className={`btn btn-danger ${styles.button}`} onClick={() => { removeBus(buses.id, buses.busno); }}>Delete</button>
                        </div>
                    </div>
                );
            })}
        </div>
    );
}

export default ViewAllBus;
