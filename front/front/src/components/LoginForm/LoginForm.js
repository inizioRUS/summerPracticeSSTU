import React, {useState} from 'react';
import {authenticate} from "../../services/AuthService";
import styles from "./LoginForm.module.css"
import logo from "./Logo.png"
import {Link, useNavigate} from "react-router-dom";

const LoginForm = () => {
    const navigate = useNavigate()
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const handleSubmit = (event) => {
        event.preventDefault()
        authenticate(email, password)
            .then(result => {
                navigate("/main")
            })
            .catch(error => {
                alert("Неверный логин или пароль")
                console.log(error)
            })
    }

    return (
        <div className={styles.container}>
            <form className={styles.form}
                  onSubmit={(event) => handleSubmit(event)}>
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
            <Link to={"/registration"}>
                <button className={styles.signup}>
                    Зарегистрироваться
                </button>
            </Link>
        </div>
    );
};

export default LoginForm;