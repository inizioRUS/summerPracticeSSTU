import React from 'react';
import LoginForm from "../../components/LoginForm/LoginForm";
import styles from "./LoginPage.module.css"
const LoginPage = () => {
    return (
        <div className={styles.login}>
            <LoginForm />
        </div>
    );
};

export default LoginPage;