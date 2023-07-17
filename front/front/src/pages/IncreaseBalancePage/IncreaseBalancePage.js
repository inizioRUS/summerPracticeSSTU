import React from 'react';
import styles from "./IncreaseBalancePage.module.css"
import Header from "../../components/Header/Header";
import BackButton from "../../components/BackButton/BackButton";
import {useNavigate} from "react-router";

const IncreaseBalancePage = () => {
    const navigate = useNavigate()

    return (
        <div className={styles.container}>
            <Header />
            <div className={styles.container_small}>
                <div className={styles.back}>
                    <BackButton onclick={() => navigate(-1)}/>
                </div>
                <div className={styles.increase}></div>
            </div>
        </div>
    );
};

export default IncreaseBalancePage;