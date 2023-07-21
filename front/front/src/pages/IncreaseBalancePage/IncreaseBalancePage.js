import React, {useEffect, useState} from 'react';
import styles from "./IncreaseBalancePage.module.css"
import Header from "../../components/Header/Header";
import BackButton from "../../components/BackButton/BackButton";
import {useNavigate} from "react-router";
import {
    getCurrentUser,
    getUserById,
    increaseUserBalance,
    setCurrentUser,
    updateUserById
} from "../../services/UserService";
import {increaseBalance} from "../../services/TransactionService";

const IncreaseBalancePage = () => {
    const navigate = useNavigate()

    const [total, setTotal] = useState(0)
    const [balance, setBalance] = useState(0)

    useEffect(() => {

    }, [])

    const handleIncrease = (event) => {
        increaseUserBalance(getCurrentUser().id, total)
            .then(response => {
                getUserById(getCurrentUser().id)
                    .catch(error => console.log(error))
            })
            .catch(error => console.log(error))
        navigate(-1)
    }

    return (
        <div className={styles.container}>
            <Header/>
            <div className={styles.container_small}>
                <div className={styles.back}>
                    <BackButton onclick={() => navigate(-1)}/>
                </div>
                <div className={styles.increase}>
                    <div className={styles.header}>Баланс</div>
                    <div className={styles.money}>{getCurrentUser().balance.toFixed(2)}$</div>
                    <form className={styles.form}
                          onSubmit={(event => {
                              event.preventDefault()
                              handleIncrease(event)
                          })}>
                        <input className={styles.input}
                               type={"number"}
                               placeholder={"0$"}
                               onChange={(event) => {
                                   setTotal(parseInt(event.target.value))
                               }}/>
                        <button className={styles.login}>Пополнить</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default IncreaseBalancePage;