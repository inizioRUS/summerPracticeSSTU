import {useNavigate} from "react-router";
import React, {useEffect, useState} from "react";
import {getStockById, getStocks, getUserStocks} from "../../services/StockService";
import styles from "../AdvancedStocksPage/AdvancedStocksPage.module.css";
import BackButton from "../../components/BackButton/BackButton";
import Header from "../../components/Header/Header";
import {getPhotoById} from "../../services/PhotoService";
import {getCurrentUser, getUserById} from "../../services/UserService";
import AccountStock from "../../components/AccountStock/AccountStock";

const AdvancedStocksPage = () => {
    const navigate = useNavigate()
    const [advancedStocks, setAdvancedStocks] = useState([])
    const [user, setUser] = useState({})

    useEffect(() => {
        setUser(getCurrentUser())
    }, [])

    return (
        <div className={styles.stocks_page}>
            <div className={styles.back}>
                <BackButton onclick={() => {
                    navigate("/main")
                }}/>
            </div>
            <Header/>
            <div className={styles.container}>
                <div className={styles.stocks_container}>
                    {
                        user.advancedStocks ? user.advancedStocks.map((advancedStock) => {
                            const stock = advancedStock.stock
                            const photoLink = getPhotoById(stock.attachmentId);
                            const change = stock.price - advancedStock.buyPrice
                            return <div className={styles.link}>
                                <AccountStock key={stock.id}
                                              id={stock.id}
                                              img={photoLink}
                                              name={stock.name}
                                              cost={stock.price.toFixed(2)}
                                              count={advancedStock.count}
                                              change={change}/>
                            </div>
                        }) : null
                    }

                </div>
            </div>
        </div>
    );
};

export default AdvancedStocksPage;