import React, {useEffect, useState} from 'react';
import styles from "./Recommendations.module.css"
import MoreButton from "../MoreButton/MoreButton";
import RecommendationsStock from "../RecommendationsStock/RecommendationsStock";
import {Link} from "react-router-dom";
import {getStocks} from "../../services/StockService";
import {getPhotoById} from "../../services/PhotoService";

const Recommendations = () => {
    const [stocks, setStocks] = useState([])

    useEffect(() => {
        getStocks()
            .then(response => setStocks(response.slice(0, 2)))
            .catch(error => console.log(error))
    }, [])
    return (
        <div className={styles.recommendations}>
            <div className={styles.header}>Популярные бумаги</div>
            <div className={styles.content}>
                {stocks.map((stock) => {
                    const photoLink = getPhotoById(stock.attachmentId);
                    return <Link to={`/stock/${stock.id}`}
                                 className={styles.link}
                                 key={stock.id}>
                                <RecommendationsStock key={stock.id}
                                              img={photoLink}
                                              name={stock.name}
                                              cost={stock.price.toFixed(2)}/>
                         </Link>
                })}
            </div>
            <Link to={"/stocks"}>
                <MoreButton/>
            </Link>

        </div>
    );
};

export default Recommendations;