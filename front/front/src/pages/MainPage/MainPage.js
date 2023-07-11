import React from 'react';
import styles from "./MainPage.module.css"
import Header from "../../components/Header/Header";
import IndexAccount from "../../components/IndexAccount/IndexAccount";
import Recommendations from "../../components/Recommendations/Recommendations";
import Currency from "../../components/Сurrency/Сurrency";

const MainPage = () => {
    return (
        <div className={styles.main_page}>
            <Header />
            <div className={styles.top_container}>
                <IndexAccount />
                <div className={styles.small_container}>
                    <Recommendations />
                    <Currency />
                </div>
            </div>
        </div>
    );
};

export default MainPage;