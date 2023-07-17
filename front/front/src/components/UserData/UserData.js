import React, {useEffect, useState} from 'react';
import styles from "./UserData.module.css"
import {getUserById, updateUserById} from "../../services/UserService";


const UserData = () => {
    const [user, setUser] = useState({})

    const [img, setImg] = useState(null)
    const [name, setName] = useState('')
    const [surname, setSurname] = useState('')
    const [email, setEmail] = useState('')

    useEffect(() => {
        getUserById(1)
            .then(result => {
                return setUser(result)
            })
            .catch(error => {
                console.log(error)
            })
    }, [])

    const handleUpdateUser = (event) => {
        event.preventDefault()
        const user = {
            img: img,
            name: name,
            surname: surname,
            email: email
        }
        updateUserById(user)
            .catch(error => {
                console.log(error)
            })
    }

    return (
        <div className={styles.data}>
            <div className={styles.heading}>Личный кабинет</div>
            <form className={styles.container}>
                <div className={styles.photo_container}>
                    <div className={styles.photo} style={{
                        backgroundImage: `url(${user.img})`
                    }}/>
                    <div className={styles.photo_input}></div>
                </div>
                <input className={styles.input}
                       placeholder={user.name}
                       onChange={(event) => {
                           setName(event.target.value)
                       }}/>
                <input className={styles.input}
                       placeholder={user.surname}
                       onChange={(event) => {
                           setSurname(event.target.value)
                       }}/>
                <input className={styles.input}
                       placeholder={user.email}
                       onChange={(event) => {
                           setEmail(event.target.value)
                       }}/>
                <button className={styles.button}
                        onClick={(event) => {
                            handleUpdateUser(event)
                        }}>Сохранить
                </button>
            </form>
        </div>
    );
};

export default UserData;