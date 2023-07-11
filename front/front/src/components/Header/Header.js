import React from 'react';
import styles from "./Header.module.css"
import Search from "../Search/Search";
import logo from "./Logo.png"
import ava from "./Ava.png"

const Header = () => {
    return (
        <div className={styles.header_container}>
            <div className={styles.logo}>
                <img src={logo}/>
            </div>
            <div>
                <Search />
            </div>
            <div>
                <img src={ava}/>
            </div>
        </div>
    );
};

export default Header;