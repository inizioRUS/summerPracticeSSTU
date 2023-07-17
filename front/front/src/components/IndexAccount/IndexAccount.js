import React, {useState} from 'react';
import styles from "./IndexAccount.module.css"
import analyse from "./icons/analyse.png"
import fill from "./icons/fill.png"
import operations from "./icons/operations.png"
import AccountStock from "../AccountStock/AccountStock";
import MoreButton from "../MoreButton/MoreButton";
import {useNavigate} from "react-router";

const IndexAccount = () => {
    const navigate = useNavigate()

    const [money, setMoney] = useState("1500₽")
    const [changes, setChanges] = useState("+10,2₽ . 1,1%")

    return (
        <div className={styles.account}>
            <div className={styles.header}>Брокерский счёт</div>
            <div className={styles.money}>{money}</div>
            <div className={styles.changes_container}>
                <div className={styles.changes}>{changes}</div>
            </div>
            <div className={styles.functions}>
                <div className={styles.function}>
                    <button className={styles.function_button}
                            onClick={() => {navigate("/analyse")}}>
                        <img src={analyse} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Аналитика</div>
                </div>
                <div className={styles.function}>
                    <button className={styles.function_button}
                            onClick={() => {navigate("/operations")}}>
                        <img src={operations} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Операции</div>
                </div>
                <div className={styles.function}>
                    <button className={styles.function_button}
                            onClick={() => {navigate("/increase")}}>
                        <img src={fill} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Пополнить</div>
                </div>
            </div>
            <div className={styles.content}>
                <AccountStock id={1}/>
                <AccountStock id={2}/>
                <AccountStock id={3}/>
            </div>
            <div>
                <MoreButton />
            </div>
        </div>
    );
};

export default IndexAccount;