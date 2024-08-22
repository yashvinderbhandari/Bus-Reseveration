import React from 'react'
import AdminDropDowns from './AdminDropDowns'
import styles from '../Component/adminnavbar.module.css' // Import the CSS module
import { Link } from 'react-router-dom'
import LandingPage from './LandingPage'

function AdminNavBar() {
  return (
    <div className={styles.navbar}>
      <div className={styles.logo}>
        <Link to={LandingPage}> <h1><i>TurboExpress</i><sup><i>.com</i></sup></h1></Link>
      </div>
      <div className={styles.dropdown}>
        <AdminDropDowns />
      </div>
    </div>
  )
}

export default AdminNavBar
