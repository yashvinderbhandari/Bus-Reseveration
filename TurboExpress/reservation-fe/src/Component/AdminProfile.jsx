import axios from 'axios';
import React, { useState, useEffect } from 'react';
import styles from './adminlogin.module.css';

const AdminProfile = () => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [gst_number, setGstnumber] = useState("");
    const [travels_name, setTravels] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(true);

    const data = {
        name,
        email,
        phone,
        gst_number,
        travels_name,
        password
    };
   
    let admin = JSON.parse(localStorage.getItem("Admin")); 
    useEffect(() => {
        axios.get(`http://localhost:8088/api/admins/${admin.id}`)
            .then((res) => {
                const data = res.data.data;
                setName(data.name);
                setEmail(data.email);
                setPhone(data.phone);
                setGstnumber(data.gst_number);
                setTravels(data.travels_name);
                setPassword(data.password);
                setLoading(false);
            })
            .catch((err) => {
                console.log(err);
                alert("Error: Failed to Fetch Admin Details!");
            });
    }, [admin.id]);

    const editAdmin = (e) => {
        e.preventDefault();
        axios.put(`http://localhost:8088/api/admins/${admin.id}`, data)
            .then((res) => {
                alert("Admin Details Updated Successfully");
                console.log(res);
            })
            .catch((err) => {
                alert("Error: Failed to Update Admin Details!");
                console.log(err);
            });
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <div className={styles.adminsign}>
                <form onSubmit={editAdmin} className={styles.form}>
                    <p className={styles.heading}>Admin Edit</p>

                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="name">Name: </label>
                        <input type="text" id="name" required placeholder='Enter the Name' className={styles.inputField} value={name} onChange={(e) => setName(e.target.value)} />
                    </div>

                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="phone">Phone: </label>
                        <input type="tel" id="phone" required placeholder='Enter the Phone' className={styles.inputField} value={phone} onChange={(e) => setPhone(e.target.value)} />
                    </div>

                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="email">Email-id: </label>
                        <input type="email" id="email" required placeholder='Enter the Email' className={styles.inputField} value={email} onChange={(e) => setEmail(e.target.value)} />
                    </div>

                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="gst_number">GST Number: </label>
                        <input type="text" id="gst_number" required placeholder='Enter the GST Number' className={styles.inputField} value={gst_number} onChange={(e) => setGstnumber(e.target.value)} />
                    </div>

                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="travels_name">Travels Name: </label>
                        <input type="text" id="travels_name" required placeholder='Enter the Travels Name' className={styles.inputField} value={travels_name} onChange={(e) => setTravels(e.target.value)} />
                    </div>

                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="password">Password: </label>
                        <input type="password" id="password" required placeholder='Enter the Password' className={styles.inputField} value={password} onChange={(e) => setPassword(e.target.value)} />
                    </div>

                    <button className={styles.button}>Update</button>
                </form>
            </div>
        </div>
    );
}

export default AdminProfile;
