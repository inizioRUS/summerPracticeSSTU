import React from 'react';
import styles from "./RecommendationsStock.module.css"

const RecommendationsStock = ({ img, name, cost, change }) => {
    return (
        <button className={styles.recommendations_stock}>
                <div className={styles.img_container} style={{
                    backgroundImage: `url(${img})`
                }}/>
            <div className={styles.small_container}>
                <div>{name}</div>
                <div className={styles.text_container}>
                    <div>{cost}₽</div>
                    &nbsp;<div>·</div>&nbsp;
                    <div className={styles.procent}>+{change}%</div>
                </div>
            </div>
        </button>
    );
};

export default RecommendationsStock;