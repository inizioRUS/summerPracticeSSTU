import React, {useEffect, useState} from 'react';
import styles from "./UserData.module.css"
import {getUserById, updateUserById} from "../../services/UserService";
import camera from "./icons/camera.png"
import dag from "../RecommendationsStock/dag_test.png"


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
                    <div className={styles.ava}
                         style={{
                             backgroundImage: `url(${dag})`
                         }}>
                    </div>
                    <div className={styles.photo_input_container}>
                        <input className={styles.photo_input}
                               id={"input_btn"}
                               name={"input_btn"}
                               type={"file"}
                               accept={"image/*"}
                               onChange={(event) => {
                                   console.log(event.target.files[0])
                               }}/>
                        <label className={styles.label}
                               htmlFor={"input_btn"}
                               style={{
                                   backgroundImage: `url(${camera})`
                               }}>
                        </label>
                        <div>Изменить</div>
                    </div>
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