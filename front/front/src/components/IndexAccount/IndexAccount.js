import React, {useEffect, useState} from 'react';
import styles from "./IndexAccount.module.css"
import favourite from "./icons/favorite.png"
import fill from "./icons/fill.png"
import operations from "./icons/operations.png"
import AccountStock from "../AccountStock/AccountStock";
import MoreButton from "../MoreButton/MoreButton";
import {useNavigate} from "react-router";
import {getCurrentUser, getProfitByUserID, getUserById} from "../../services/UserService";
import {getPhotoById} from "../../services/PhotoService";
import {Link} from "react-router-dom";

const IndexAccount = () => {
    const navigate = useNavigate()

    const [balance, setBalance] = useState(0)
    const [changes, setChanges] = useState(0)
    const [newAdvStocks, setNewAdvStocks] = useState(null)
    const [profitColor, setProfitColor] = useState()
    const [profitTextColor, setProfitTextColor] = useState()


    useEffect(() => {
        getUserById(getCurrentUser().id)
            .then(response => {
                setBalance(response.balance)
                setNewAdvStocks(response.advancedStocks.slice(0, 3))
            })
        getProfitByUserID(getCurrentUser().id)
            .then(response => {
                setChanges(response.toFixed(2))
                response >= 0 ? setProfitColor("#D9F4E1") : setProfitColor("#E1C6C6")
                response >= 0 ? setProfitTextColor("#359C41") : setProfitTextColor("#D83636")
            })
            .catch(error => console.log(error))
    }, [])

    return (
        <div className={styles.account}>
            <div className={styles.header}>Брокерский счёт</div>
            <div className={styles.money}>{balance ? balance.toFixed(2) : ""}$</div>
            <div className={styles.changes_container}>
                <div className={styles.changes}
                     style={{
                         backgroundColor: profitColor,
                         color: profitTextColor
                     }}>{changes}$</div>
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
                {
                    newAdvStocks ? newAdvStocks.map((advancedStock) => {
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
                        })
                        : ""
                }
            </div>
            <Link to={"/advanced"}>
                <MoreButton/>
            </Link>
        </div>
    );
};

export default IndexAccount;