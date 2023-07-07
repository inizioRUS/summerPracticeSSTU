import React, {useState} from 'react';
import styles from "./IndexAccount.module.css"
import analyse from "./icons/analyse.png"
import fill from "./icons/fill.png"
import operations from "./icons/operations.png"
import AccountStock from "../AccountStock/AccountStock";
import MoreButton from "../MoreButton/MoreButton";

const IndexAccount = () => {
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
                    <button className={styles.function_button}>
                        <img src={analyse} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Аналитика</div>
                </div>
                <div className={styles.function}>
                    <button className={styles.function_button}>
                        <img src={operations} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Операции</div>
                </div>
                <div className={styles.function}>
                    <button className={styles.function_button}>
                        <img src={fill} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Пополнить</div>
                </div>
            </div>
            <div className={styles.content}>
                <AccountStock />
                <AccountStock />
                <AccountStock />
            </div>
            <div>
                <MoreButton />
            </div>
        </div>
    );
};

export default IndexAccount;