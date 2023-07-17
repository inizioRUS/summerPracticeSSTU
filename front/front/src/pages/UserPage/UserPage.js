import React from 'react';
import styles from "./UserPage.module.css"
import Header from "../../components/Header/Header";
import IndexAccount from "../../components/IndexAccount/IndexAccount";
import UserData from "../../components/UserData/UserData";
import img from "../../components/Header/Ava.png"
import BackButton from "../../components/BackButton/BackButton";
import {useNavigate} from "react-router";

const UserPage = () => {
    const navigate = useNavigate();

    return (
        <div className={styles.user_page}>
            <div className={styles.back}>
                <BackButton onclick={() => {
                    navigate("/main")
                }} />
            </div>
            <Header />
            <div className={styles.top_container}>
                <UserData img={img}
                          name={"Олег"}
                          surname={"Штырев"}
                          email={"oleg@gmail.com"}/>
                <IndexAccount />
            </div>
        </div>
    );
};

export default UserPage;