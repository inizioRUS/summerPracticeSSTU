import React from 'react';
import styles from "./RecommendationsStock.module.css"
import dag from "./dag_test.png"

const RecommendationsStock = () => {
    return (
        <button className={styles.recommendations_stock}>
                <div className={styles.img_container}>
                    <img className={styles.img} src={dag} alt={"icon"}/>
                </div>
            <div className={styles.small_container}>
                <div>Дагестанские авиалинии</div>
                <div className={styles.text_container}>
                    <div>1200₽</div>
                    &nbsp;<div>·</div>&nbsp;
                    <div className={styles.procent}>+5%</div>
                </div>
            </div>
        </button>
    );
};

export default RecommendationsStock;