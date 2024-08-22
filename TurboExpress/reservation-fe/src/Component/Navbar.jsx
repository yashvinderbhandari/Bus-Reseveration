import React from 'react'
import styles from './navbar.module.css'

import LoginOption from './LoginOption'
import { Link } from 'react-router-dom'
import LandingPage from './LandingPage'
const Navbar = () => {
    return (
        <div className={styles.navbar}>
            <div className={styles.logo}>
            <Link to={LandingPage}> <h1><i>TurboExpress</i><sup><i>.com</i></sup> </h1></Link>
            </div>
            <div className={styles.dropdown}>
                <LoginOption/>
            </div>
        </div>
      )
}

export default Navbar

