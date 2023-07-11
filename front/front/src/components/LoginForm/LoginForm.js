import React, {useState} from 'react';
import {authenticate} from "../../services/auth";
import styles from "./LoginForm.module.css"
import logo from "./Logo.png"

const LoginForm = () => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    return (
        <div className={styles.container}>
            <form className={styles.form} onSubmit={(event) => {
                event.preventDefault()
                authenticate(email, password)
            }
            }>
                <div>
                    <img src={logo} alt={"logo"}/>
                </div>
                <div className={styles.heading}>
                    Вход
                </div>
                <input className={styles.input}
                       placeholder={"Email"}
                       onChange={e => setEmail(e.target.value)}/>
                <input className={styles.input}
                       placeholder={"Пароль"}
                       onChange={e => setPassword(e.target.value)}/>
                <button className={styles.login}>
                    Войти
                </button>
            </form>
            <button className={styles.signup}>
                Зарегистрироваться
            </button>
        </div>
    );
};

export default LoginForm;