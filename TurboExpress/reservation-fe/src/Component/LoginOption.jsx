import React from 'react'
import { Link } from 'react-router-dom'
import style from './loginoption.module.css';

const LoginOption = () => {
  return (
    <div className={style.landingpages}>
            <Link to="/adminlogin" >
                <h2 >Admin</h2>
            </Link>
            <Link to="/userlogin" >
                <h2>User</h2>
            </Link>
        </div>
  )
}

export default LoginOption