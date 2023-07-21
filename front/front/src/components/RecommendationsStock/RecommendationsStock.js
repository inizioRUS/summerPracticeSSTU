import React, {useEffect} from 'react';
import styles from "./RecommendationsStock.module.css"

const RecommendationsStock = ({ img, name, cost }) => {
    return (
        <button className={styles.recommendations_stock}>
                <div className={styles.img_container} style={{
                    backgroundImage: `url(${img})`
                }}/>
            <div className={styles.small_container}>
                <div>{name}</div>
                <div className={styles.text_container}>
                    <div>{cost}$</div>
                </div>
            </div>
        </button>
    );
};

export default RecommendationsStock;