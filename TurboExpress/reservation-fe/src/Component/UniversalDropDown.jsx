import React, { useEffect, useRef, useState } from 'react'
import Dropdown from 'react-bootstrap/Dropdown';
import styles from './universaldropdown.module.css'
const UniversalDropDown = () => {
  
    const [isOpen, setIsOpen] = useState(false);
    const dropdownRef = useRef(null);

    const handleToggle = () => setIsOpen(!isOpen);

    const handleClickOutside = (event) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
            setIsOpen(false);
        }
    };

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
                    <Dropdown.Item href="#/action-1">Help-Desk</Dropdown.Item>
                    <Dropdown.Item href="#/action-2">Contact Us</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </div>
    );
}



export default UniversalDropDown