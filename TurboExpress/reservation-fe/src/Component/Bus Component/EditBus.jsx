import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styles from './editbus.module.css'; // Import the CSS module

const EditBus = () => {
    let [name, setName] = useState("");
    let [departure_date_time, setDate] = useState("");
    let [busno, setBusno] = useState("");
    let [fromLoc, setFrom] = useState("");
    let [toLoc, setTo] = useState("");
    let [noOfSeats, setNoOfSeats] = useState("");
    let [cost, setCost] = useState("")
    const [loading, setLoading] = useState(true);

    let BusData = { 
        name, 
        departure_date_time, 
        fromLoc, 
        toLoc, 
        noOfSeats, 
        busno,
        cost 
    };

    let params = useParams();
    console.log(typeof(params.id));
    useEffect(() => {
        axios.get(`http://localhost:8088/api/bus/${params.id}`)
            .then((res) => {
                const data = res.data.data;
                setName(data.name);
                setDate(data.departure_date_time);
                setBusno(data.busno);
                setFrom(data.fromLoc);
                setTo(data.toLoc);
                setNoOfSeats(data.noOfSeats);
                setCost(data.cost);
                setLoading(false);
            })
            .catch((err) => {
                console.log(err);
                console.log(err.response);
                alert("Error: Failed to Update Bus Details!");
              })              
    }, [params.id]);

    function editBus(e) {
        e.preventDefault();
        axios.put(`http://localhost:8088/api/bus/${params.id}`, BusData)
            .then((res) => {
                console.log(res);
                alert("Bus Details Updated Successfully");
            })
            .catch((err) => {
                alert("Error: Failed to Update Bus Details!");
            })
    }

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div className={styles.editbusContainer}>
            <form onSubmit={editBus} className={styles.editbusForm}>
                <label htmlFor="">Bus Name: </label>
                <input type="text" placeholder='Enter Bus Name' value={name} onChange={(e) => { setName(e.target.value) }} required />
                <label htmlFor="">From : </label>
                <input type="text" placeholder='From Location' value={fromLoc} onChange={(e) => { setFrom(e.target.value) }} required />
                <label htmlFor="">To: </label>
                <input type="text" placeholder='To Location' value={toLoc} onChange={(e) => { setTo(e.target.value) }} required />
                <label htmlFor="">Date Of Departure: </label>
                <input type="datetime-local" placeholder='Enter Date of Departure' value={departure_date_time} onChange={(e) => { setDate(e.target.value) }} required />
                <label htmlFor="">Number of Seats: </label>
                <input type="number" placeholder='Enter Bus Seats' value={noOfSeats} onChange={(e) => { setNoOfSeats(e.target.value) }} required />
                <label htmlFor="">Bus Number: </label>
                <input type="text" placeholder='Enter Bus Number' value={busno} onChange={(e) => { setBusno(e.target.value) }} required />
                <label htmlFor="">Price</label>
                <input type="number" name="" id="" placeholder='Enter Price per Ticket' value={cost} onChange={(e) => { setCost(e.target.value) }} />
                <input type="submit" value="Update" />
            </form>
        </div>
    )
}

export default EditBus;
