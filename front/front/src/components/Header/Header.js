import React, {useEffect, useState} from 'react';
import styles from "./Header.module.css"
import Search from "../Search/Search";
import logo from "./Logo.png"
import defaultAva from "./default.jpg"
import {useNavigate} from "react-router";
import {getUserByEmail} from "../../services/UserService";
import {getCurrentUser} from "../../services/AuthService";

const Header = () => {
    const navigate = useNavigate()
    const [user, setUser] = useState({})
    const [ava, setAva] = useState(null)

    useEffect(() => {
        // const currentUser = getCurrentUser()
        // setUser(getUserByEmail(currentUser
        //     .then((response) => {
        //         return response.email
        //     }))
        //     .catch(error => {
        //         console.log(error)
        //     }))
        ava === null ? setAva(defaultAva) : setAva(user.ava)

    }, [])

    return (
        <div className={styles.header_container}>
            <button className={styles.logo}
                    onClick={() => navigate("/main")}>
                <img src={logo}/>
            </button>
            <div>
                <Search/>
            </div>
            <button className={styles.ava}
                    style={{
                        backgroundImage: `url(${ava})`
                    }} onClick={() => navigate("/user")}>

            </button>
        </div>
    );
};

export default Header;