import React from 'react'
import UserDropDown from './UserDropDown'
import styles from './adminnavbar.module.css'
import { Link } from 'react-router-dom'
import LandingPage from './LandingPage'

const UserNavBar = () => {
  return (
    <div className={styles.navbar}>
      <div className={styles.logo}>
        <Link to={LandingPage}> <h1><i>TurboExpress</i><sup><i>.com</i></sup> </h1></Link>
      </div>
      <div className={styles.option}>
        <UserDropDown />
      </div>
    </div>
  )
}

export default UserNavBar