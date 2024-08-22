import React, { useState } from 'react';
import axios from 'axios';
import styles from './addbus.module.css';

function AddBus() {
    let [name, setName] = useState("");
    let [departure_date_time, setDate] = useState("");
    let [busno, setBusno] = useState("");
    let [fromLoc, setFrom] = useState("");
    let [toLoc, setTo] = useState("");
    let [typeofbus, setType] = useState("");
    let [noOfSeats, setNoOfSeats] = useState("");
    let [cost, setCost] = useState("");
    
    let BusData = {
        name, departure_date_time, fromLoc, typeofbus, toLoc, noOfSeats, busno, cost
    };
    
    let admin = JSON.parse(localStorage.getItem("Admin"));
    
    function addBus(e) {
        e.preventDefault();
        axios.post(`http://localhost:8088/api/bus/${admin.id}`, BusData)
            .then((res) => {
                console.log(res);
                alert("Bus Added Successfully");
            })
            .catch((err) => { alert("Error Failed to Add !") });
    }

    return (
        <div className={styles.addBus}>
            <form onSubmit={addBus} className={styles.form}>
                <div className={styles.field}>
                    <label className={styles.label}>Bus Name: </label>
                    <input type="text" placeholder='Enter Bus Name' className={styles.inputField} value={name} onChange={(e) => setName(e.target.value)} />
                </div>
                <div className={styles.field}>
                    <label className={styles.label}>From: </label>
                    <input type="text" placeholder='From Location' className={styles.inputField} value={fromLoc} onChange={(e) => setFrom(e.target.value)} />
                </div>
                <div className={styles.field}>
                    <label className={styles.label}>To: </label>
                    <input type="text" placeholder='To Location' className={styles.inputField} value={toLoc} onChange={(e) => setTo(e.target.value)} />
                </div>
                <div className={styles.field}>
                    <label className={styles.label}>Bus Type: </label>
                    <select className={styles.selectField} value={typeofbus} onChange={(e) => setType(e.target.value)}>
                        <option>AC</option>
                        <option>Non/AC</option>
                        <option>Sleeper AC</option>
                        <option>Sleeper Non/AC</option>
                    </select>
                </div>
                <div className={styles.field}>
                    <label className={styles.label}>Departure Date & Time: </label>
                    <input type="datetime-local" placeholder='Enter Date of Departure' className={styles.inputField} value={departure_date_time} onChange={(e) => setDate(e.target.value)} />
                </div>
                <div className={styles.field}>
                    <label className={styles.label}>Number of Seats: </label>
                    <input type="number" placeholder='Enter Bus Seats' className={styles.inputField} value={noOfSeats} onChange={(e) => setNoOfSeats(e.target.value)} />
                </div>
                <div className={styles.field}>
                    <label className={styles.label}>Bus Number: </label>
                    <input type="text" placeholder='Enter Bus Number' className={styles.inputField} value={busno} onChange={(e) => setBusno(e.target.value)} />
                </div>
                <div className={styles.field}>
                    <label className={styles.label}>Price: </label>
                    <input type="tel" placeholder='Enter Price per Ticket' className={styles.inputField} value={cost} onChange={(e) => setCost(e.target.value)} />
                </div>
                <button type="submit" className={styles.button}>Add</button>
            </form>
        </div>
    );
}

export default AddBus;
