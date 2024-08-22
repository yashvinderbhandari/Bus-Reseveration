import axios from 'axios';
import React, { useState } from 'react';
// import { Link, useNavigate } from 'react-router-dom';
import styles from './usersignup.module.css';
import UniversalNav from './UniversalNav';
import { Link } from 'react-router-dom';

const UserSignup = () => {
    let [name, setName] = useState("");
    let [age, setAge] = useState("");
    let [phone, setPhone] = useState("");
    let [email, setEmail] = useState("");
    let [gender, setGender] = useState("");
    let [password, setPassword] = useState("");
    let data = { name, age, phone, email, gender, password };

    function save(e) {
        e.preventDefault();
        axios.post('http://localhost:8088/api/users', data)
            .then((res) => {
                alert("User Added Successfully");
                console.log(res);
            })
            .catch((err) => {
                alert("Failed to Sign-up");
                console.error(err);
            });
    }

    // let navigate = useNavigate();
    return (
        <div>
            <UniversalNav />
            <div className={styles.usersign}>
                <form onSubmit={save} className={styles.form}>
                    <p className={styles.heading}>Sign up</p>
                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="name">Name: </label>
                        <input type="text" id="name" required placeholder="Enter the Name" className={styles.inputField} value={name} onChange={(e) => setName(e.target.value)} />
                    </div>
                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="phone">Phone: </label>
                        <input type="tel" id="phone" required placeholder="Enter the Phone" className={styles.inputField} value={phone} onChange={(e) => setPhone(e.target.value)} />
                    </div>
                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="email">Email: </label>
                        <input type="email" id="email" required placeholder="Enter email address" className={styles.inputField} value={email} onChange={(e) => setEmail(e.target.value)} />
                    </div>
                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="age">Age: </label>
                        <input type="tel" id="age" required placeholder="Enter your Age" className={styles.inputField} value={age} onChange={(e) => setAge(e.target.value)} />
                    </div>
                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="gender">Gender: </label>
                        <input type="text" id="gender" required placeholder="Enter your Gender" className={styles.inputField} value={gender} onChange={(e) => setGender(e.target.value)} />
                    </div>
                    <div className={styles.field}>
                        <label className={styles.label} htmlFor="password">Password: </label>
                        <input type="password" id="password" required placeholder="Enter your Password" className={styles.inputField} value={password} onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <button className={styles.button}>Sign up</button>
                    <p className={styles.loginText}>
                        Have an account? 
                        <Link className={styles.loginLink} to="/userlogin">
                            Log-in
                        </Link>
                    </p>
                </form>
            </div>
        </div>
    );
};

export default UserSignup;
