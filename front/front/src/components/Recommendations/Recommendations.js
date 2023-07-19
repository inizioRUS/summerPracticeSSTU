import React from 'react';
import styles from "./Recommendations.module.css"
import MoreButton from "../MoreButton/MoreButton";
import RecommendationsStock from "../RecommendationsStock/RecommendationsStock";
import {Link} from "react-router-dom";
import img from "../RecommendationsStock/dag_test.png"

const recommendedStocks = [
    {
        id: 1,
        img: img,
        name: "Газпром",
        cost: 1200,
        change: 5
    },
    {
        id: 2,
        img: img,
        name: "Роснефть",
        cost: 1500,
        change: 7
    }
]

const Recommendations = () => {
    return (
        <div className={styles.recommendations}>
            <div className={styles.header}>Популярные бумаги</div>
            <div className={styles.content}>
                {recommendedStocks.map((stock) => {
                    return <Link to={`/stock/${stock.id}`}
                                 className={styles.link}
                                 key={stock.id}>
                        <RecommendationsStock key={stock.id}
                                              img={stock.img}
                                              name={stock.name}
                                              cost={stock.cost}
                                              change={stock.change} />
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