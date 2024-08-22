import React, { useState, useRef, useEffect } from 'react';
import Dropdown from 'react-bootstrap/Dropdown';
import styles from "../Component/admindropdown.module.css";

function AdminDropDowns() {
    const [isOpen, setIsOpen] = useState(false);
    const dropdownRef = useRef(null);

    const handleToggle = () => setIsOpen(!isOpen);

    const handleClickOutside = (event) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
            setIsOpen(false);
        }
    };

    const handleLogout = () => {
        // Clear local storage and session storage on logout
        localStorage.clear();
        sessionStorage.clear();
        // Redirect to the login page or homepage
        window.location.href = '/';
    };

    let admin = JSON.parse(localStorage.getItem("Admin")); 
    console.log(admin.id);
    console.log(admin.id);


    useEffect(() => {
        if (isOpen) {
            document.addEventListener('mousedown', handleClickOutside);
        } else {
            document.removeEventListener('mousedown', handleClickOutside);
        }
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [isOpen]);

    useEffect(() => {
        const handleBeforeUnload = (event) => {
            if (!sessionStorage.getItem('isReloading')) {
                localStorage.clear();
            }
            sessionStorage.removeItem('isReloading');
        };

        const handlePageReload = () => {
            sessionStorage.setItem('isReloading', 'true');
        };

        window.addEventListener('beforeunload', handleBeforeUnload);
        window.addEventListener('unload', handlePageReload);

        return () => {
            window.removeEventListener('beforeunload', handleBeforeUnload);
            window.removeEventListener('unload', handlePageReload);
        };
    }, []);

    return (
        <div ref={dropdownRef}>
            <Dropdown show={isOpen} onToggle={handleToggle}>
                <Dropdown.Toggle variant="success" id="dropdown-basic" className={styles.dropdownToggle} onClick={handleToggle}>
                    <div className={styles.hamburger}>
                        <input type="checkbox" checked={isOpen} readOnly />
                        <svg viewBox="0 0 32 32">
                            <path className={`${styles.line} ${styles.lineTopBottom}`} d="M27 10 13 10C10.8 10 9 8.2 9 6 9 3.5 10.8 2 13 2 15.2 2 17 3.8 17 6L17 26C17 28.2 18.8 30 21 30 23.2 30 25 28.2 25 26 25 23.8 23.2 22 21 22L7 22"></path>
                            <path className={styles.line} d="M7 16 27 16"></path>
                        </svg>
                    </div>
                </Dropdown.Toggle>
                <Dropdown.Menu className={styles.dropdownMenu}>
                    <Dropdown.Item href="/adminhomepage/addbus" className={styles.dropdownItem}>Add Bus</Dropdown.Item>
                    <Dropdown.Item href="/adminhomepage/viewbus" className={styles.dropdownItem}>Bus Lists</Dropdown.Item>
                    <Dropdown.Item href="/adminhomepage/editadmin/:id" className={styles.dropdownItem}>Edit profile</Dropdown.Item>
                    <Dropdown.Item onClick={handleLogout} className={styles.dropdownItem}>Log-out</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </div>
    );
}

export default AdminDropDowns;
