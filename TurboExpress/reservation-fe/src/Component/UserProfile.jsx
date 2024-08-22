import axios from 'axios';
import React, { useState, useEffect } from 'react';

import styles from './usersignup.module.css';

const UserProfile = () => {
    let [name, setName] = useState("");
    let [email, setEmail] = useState("");
    let [phone, setPhone] = useState("");
    let [age, setAge] = useState("");
    let [gender, setGender] = useState("");
    let [address, setAddress] = useState("");
    let [password, setPassword] = useState("");
    let [loading, setLoading] = useState(true);

    const data = {
        name,
        age,
        email,
        phone,
        address,
        password
    };

    let user = JSON.parse(localStorage.getItem("User")); 
    useEffect(() => {
        axios.get(`http://localhost:8088/api/users/${user.id}`)
            .then((res) => {
                const data = res.data.data;
                setName(data.name);
                setEmail(data.email);
                setPhone(data.phone);
                setAddress(data.address);
                setPassword(data.password);
                setLoading(false);
            })
            .catch((err) => {
                console.log(err);
                alert("Error: Failed to Fetch User Details!");
            });
    }, [user.id]);

    const update = (e) => {
        e.preventDefault();
        axios.put(`http://localhost:8088/api/users/${user.id}`, data)
            .then((res) => {
                alert("User Details Updated Successfully");
                console.log(res);
            })
            .catch((err) => {
                alert("Error: Failed to Update User Details!");
                console.log(err);
            });
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div>
           
            <div className={styles.usersign}>
                <form onSubmit={update} className={styles.form}>
                    <p className={styles.heading}>Update Profile</p>
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
                    <button className={styles.button}>Update Profile</button>
                </form>
            </div>
        </div>
    );
};
export default UserProfile;
