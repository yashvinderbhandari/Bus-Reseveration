import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../Component/adminlogin.module.css'; // Import CSS module
import axios from 'axios';
import UniversalNav from './UniversalNav';

const AdminLogin = () => {
    let [email, setEmail] = useState("");
    let [password, setPassword] = useState("");
    const navigate = useNavigate();

    function verify(e) {
        e.preventDefault();
        axios.post(`http://localhost:8088/api/admins/verify-by-email?email=${email}&password=${password}`)
            .then((res) => {
                navigate('/adminhomepage');
                alert("Login Successful.");
                console.log(res.data.data);
                localStorage.setItem("Admin", JSON.stringify(res.data.data));
            })
            .catch((err) => {
                alert("Login Failed.");
            });
    }

    return (
        <div><UniversalNav/>
            <form className={styles.form} onSubmit={verify}>
                <p className={styles.heading}>Admin Login</p>
                <div className={styles.field}>
                    <svg className={styles.inputIcon} xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M13.106 7.222c0-2.967-2.249-5.032-5.482-5.032-3.35 0-5.646 2.318-5.646 5.702 0 3.493 2.235 5.708 5.762 5.708.862 0 1.689-.123 2.304-.335v-.862c-.43.199-1.354.328-2.29.328-2.926 0-4.813-1.88-4.813-4.798 0-2.844 1.921-4.881 4.594-4.881 2.735 0 4.608 1.688 4.608 4.156 0 1.682-.554 2.769-1.416 2.769-.492 0-.772-.28-.772-.76V5.206H8.923v.834h-.11c-.266-.595-.881-.964-1.6-.964-1.4 0-2.378 1.162-2.378 2.823 0 1.737.957 2.906 2.379 2.906.8 0 1.415-.39 1.709-1.087h.11c.081.67.703 1.148 1.503 1.148 1.572 0 2.57-1.415 2.57-3.643zm-7.177.704c0-1.197.54-1.907 1.456-1.907.93 0 1.524.738 1.524 1.907S8.308 9.84 7.371 9.84c-.895 0-1.442-.725-1.442-1.914z"></path>
                    </svg>
                    <input type="email" autoComplete="off" placeholder="Email" className={styles.inputField} value={email} onChange={(e) => { setEmail(e.target.value) }} />
                </div>
                <div className={styles.field}>
                    <svg className={styles.inputIcon} xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"></path>
                    </svg>
                    <input placeholder="Password" className={styles.inputField} type="password" value={password} onChange={(e) => { setPassword(e.target.value) }} />
                </div>
                <div className={styles.btn}>
                    <button className={styles.button1}>Login</button>
                    <button className={styles.button2} type="button" onClick={() => navigate('/adminsignup')}>
                        SignUp
                    </button>
                </div>
                <button className={styles.button3}>Forgot Password</button>
            </form>
        </div>
    );
}

export default AdminLogin;
