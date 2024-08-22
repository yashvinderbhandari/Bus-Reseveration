import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from "../Component/adminsignup.module.css"; // Import CSS module
import axios from 'axios';
import UniversalNav from './UniversalNav';

const AdminSignUp = () => {
    let [name, setName] = useState("");
    let [email, setEmail] = useState("");
    let [phone, setPhone] = useState("");
    let [gst_number, setGstnumber] = useState("");
    let [travels_name, setTravels] = useState("");
    let [password, setPassword] = useState("");
    // const navigate = useNavigate();

    let data = {
        name,
        email,
        phone,
        gst_number,
        travels_name,
        password
    };

    function admin(e) {
        e.preventDefault();
        axios.post('http://localhost:8088/api/admins', data)
            .then((res) => {
                alert("Admin had Successfully sign-up!");
                console.log(res);
            })
            .catch((err) => {
                alert("Failed to add.");
                console.log(err);
            });
    }

    return (
        <div><UniversalNav/>
            <div className={styles.adminsign}>
                <form onSubmit={admin} className={styles.form}>
                    <p className={styles.heading}>Admin Sign-Up</p>

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

                    <button className={styles.button}>Submit</button>
                    <p className={styles.loginText}>
                        Are you already registered? <Link to="/adminlogin" className={styles.loginLink}>Login here</Link>
                    </p>
                </form>
            </div>
        </div>
    );
}

export default AdminSignUp;