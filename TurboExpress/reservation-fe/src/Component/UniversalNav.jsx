import React from 'react';
import styles from './universalnav.module.css';
import UniversalDropDown from './UniversalDropDown';
import { Link } from 'react-router-dom';

const UniversalNav = () => {
  return (
    <div className={styles.navbar}>
      <div className={styles.logo}>
        <Link to="/"><h1><i>TurboExpress</i><sup><i>.com</i></sup> </h1></Link>
      </div>
      <div className={styles.option}>
        <UniversalDropDown />
      </div>
    </div>
  )
}

export default UniversalNav;
