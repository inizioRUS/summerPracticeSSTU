import React, {useEffect, useState} from 'react';
import styles from "./StocksPage.module.css"
import Header from "../../components/Header/Header";
import img from "../../components/RecommendationsStock/dag_test.png"
import Stock from "../../components/Stock/Stock";
import {Link} from "react-router-dom";
import BackButton from "../../components/BackButton/BackButton";
import {useNavigate} from "react-router";
import {getStocks} from "../../services/StockService";
import {getPhotoById} from "../../services/PhotoService";


const StocksPage = () => {
    const navigate = useNavigate()
    const [stocks, setStocks] = useState([])

    useEffect(() => {
        getStocks()
            .then(response => {
                setStocks(response)
                console.log(stocks)
            })
            .catch(error => console.log(error))
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
                        stocks.map((stock) => {
                            const photoLink = getPhotoById(stock.attachmentId)
                            return <Link to={`/stock/${stock.id}`}
                                         className={styles.link}
                                         key={stock.id}>
                                <Stock key={stock.id}
                                       img={photoLink}
                                       name={stock.name}
                                       cost={stock.price.toFixed(2)}/>
                            </Link>
                        })
                    }
                </div>
            </div>

        </div>
    );
};

export default StocksPage;