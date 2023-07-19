import React, {useEffect, useState} from 'react';
import styles from "./UserData.module.css"
import {changeUserPhoto, getUserByEmail, getUserById, setCurrentUser, updateUserById} from "../../services/UserService";
import camera from "./icons/camera.png"
import defaultAva from "../Header/default.jpg"
import {getCurrentUser} from "../../services/UserService";
import {getPhotoById} from "../../services/PhotoService";
import {useNavigate} from "react-router";


const UserData = () => {
        const navigate = useNavigate()
        const [img, setImg] = useState(null)
        const [name, setName] = useState('')
        const [surname, setSurname] = useState('')
        const [email, setEmail] = useState('')

        useEffect(() => {
                getUserById(getCurrentUser().id)
                    .catch(error => console.log(error))
                getCurrentUser().attachmentId
                    ? setImg(getPhotoById(getCurrentUser().attachmentId))
                    : setImg(defaultAva)
                setName(getCurrentUser().name)
                setSurname(getCurrentUser().surname)
                setEmail(getCurrentUser().email)
                setImg(getPhotoById(getCurrentUser().attachmentId))
            }, []
        )

        const handleUpdateUser = (event) => {
            event.preventDefault()
            const user = {
                name: name,
                surname: surname,
                email: email
            }
            updateUserById(getCurrentUser().id, user)
                .then(response => response)
                .catch(error => {
                    console.log(error)
                })
            setCurrentUser(getUserById(getCurrentUser().id))
        }

        return (
            <div className={styles.data}>
                <div className={styles.heading}>Личный кабинет</div>
                <form className={styles.container}
                      onSubmit={(event) => {
                          event.preventDefault()
                          handleUpdateUser(event)
                      }}>
                    <div className={styles.photo_container}>
                        <div className={styles.ava}
                             style={{
                                 backgroundImage: `url(${img})`
                             }}>
                        </div>
                        <div className={styles.photo_input_container}>
                            <input className={styles.photo_input}
                                   id={"input_btn"}
                                   name={"input_btn"}
                                   type={"file"}
                                   accept={"image/*"}
                                   onChange={async (event) => {
                                       changeUserPhoto(getCurrentUser().id, event.target.files.item(0))
                                           .catch(error => console.log(error))
                                       getUserById(getCurrentUser().id)
                                           .then(response => console.log(response))
                                           .catch(error => console.log(error))
                                       await setTimeout(async () => {
                                           navigate(0)
                                       }, 6000)
                                       await setImg(getPhotoById(getCurrentUser().attachmentId))
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
                           placeholder={name}
                           onChange={(event) => {
                               setName(event.target.value)
                           }}/>
                    <input className={styles.input}
                           placeholder={surname}
                           onChange={(event) => {
                               setSurname(event.target.value)
                           }}/>
                    <input className={styles.input}
                           placeholder={email}
                           onChange={(event) => {
                               setEmail(event.target.value)
                           }}/>
                    <button className={styles.button}>Сохранить
                    </button>
                </form>
            </div>
        );
    }
;

export default UserData;