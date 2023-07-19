import React, {useEffect, useState} from 'react';
import styles from "./IncreaseBalancePage.module.css"
import Header from "../../components/Header/Header";
import BackButton from "../../components/BackButton/BackButton";
import {useNavigate} from "react-router";
import {getCurrentUser, getUserById, updateUserById} from "../../services/UserService";
import {increaseBalance} from "../../services/TransactionService";

const IncreaseBalancePage = () => {
    const navigate = useNavigate()

    const [user, setUser] = useState({})
    const [money, setMoney] = useState(0)

    useEffect(() => {
        setUser(getCurrentUser())
    }, [])

    const handleIncrease = () => {
        increaseBalance(getCurrentUser(), money)
            .then(response => console.log(response))
            .catch(error => console.log(error))
        getUserById(getCurrentUser().id)
            .catch(error => console.log(error))
    }

    return (
        <div className={styles.container}>
            <Header />
            <div className={styles.container_small}>
                <div className={styles.back}>
                    <BackButton onclick={() => navigate(-1)}/>
                </div>
                <div className={styles.increase}>
                    <div className={styles.header}>Баланс</div>
                    <div className={styles.money}>{user.balance}₽</div>
                    <form className={styles.form}
                          onSubmit={(event) => {
                              event.preventDefault()
                              handleIncrease()
                          }}>
                        <input className={styles.input}
                               type={"number"}
                               placeholder={"0₽"}
                               onChange={(event) => {
                                   setMoney(parseInt(event.target.value))
                               }}/>
                        <button className={styles.login}>
                            Пополнить
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default IncreaseBalancePage;