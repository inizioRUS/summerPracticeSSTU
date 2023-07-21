import React, {useEffect, useState} from 'react';
import styles from "./Header.module.css"
import Search from "../Search/Search";
import logo from "./Logo.png"
import defaultAva from "./default.jpg"
import {useNavigate} from "react-router";
import {getUserByEmail, getUserById} from "../../services/UserService";
import {getCurrentUser} from "../../services/UserService";
import {getPhotoById} from "../../services/PhotoService";

const Header = () => {
    const navigate = useNavigate()
    const [ava, setAva] = useState(defaultAva)

    useEffect(() => {
        getUserById(getCurrentUser().id)
            .then(response => {
                response.attachmentId
                    ? setAva(getPhotoById(response.attachmentId))
                    : setAva(defaultAva)
            })
            .catch(error => console.log(error))
        // getCurrentUser().attachmentId
        //     ? setAva(getPhotoById(getCurrentUser().attachmentId))
        //     : setAva(defaultAva)
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