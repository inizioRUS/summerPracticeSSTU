import React, {useEffect, useState} from 'react';
import styles from "./IndexAccount.module.css"
import favourite from "./icons/favorite.png"
import fill from "./icons/fill.png"
import operations from "./icons/operations.png"
import AccountStock from "../AccountStock/AccountStock";
import MoreButton from "../MoreButton/MoreButton";
import {useNavigate} from "react-router";
import {getCurrentUser} from "../../services/UserService";
import {getPhotoById} from "../../services/PhotoService";

const IndexAccount = () => {
    const navigate = useNavigate()

    const [user, setUser] = useState({})
    const [changes, setChanges] = useState("+10,2₽ . 1,1%")

    useEffect(() => {
        setUser(getCurrentUser())
    }, [])

    return (
        <div className={styles.account}>
            <div className={styles.header}>Брокерский счёт</div>
            <div className={styles.money}>{user.balance}₽</div>
            <div className={styles.changes_container}>
                <div className={styles.changes}>{changes}</div>
            </div>
            <div className={styles.functions}>
                <div className={styles.function}>
                    <button className={styles.function_button}
                            onClick={() => {
                                navigate("/favourite")
                            }}>
                        <img src={favourite} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Избраннные</div>
                </div>
                <div className={styles.function}>
                    <button className={styles.function_button}
                            onClick={() => {
                                navigate("/operations")
                            }}>
                        <img src={operations} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Операции</div>
                </div>
                <div className={styles.function}>
                    <button className={styles.function_button}
                            onClick={() => {
                                navigate("/increase")
                            }}>
                        <img src={fill} className={styles.function_icon}/>
                    </button>
                    <div className={styles.function_text}>Пополнить</div>
                </div>
            </div>
            <div className={styles.content}>
                    {user.advancedStocks ? user.advancedStocks.map((advancedStock) => {
                        const stock = advancedStock.stock
                        const photoLink = getPhotoById(stock.attachmentId);
                        const change = stock.price - advancedStock.buyPrice
                        return <AccountStock key={stock.id}
                                             id={stock.id}
                                             img={photoLink}
                                             name={stock.name}
                                             cost={stock.price.toFixed(2)}
                                             count={advancedStock.count}
                                             change={change}/>
                    }) : null}
            </div>
            <div>
                <MoreButton/>
            </div>
        </div>
    );
};

export default IndexAccount;