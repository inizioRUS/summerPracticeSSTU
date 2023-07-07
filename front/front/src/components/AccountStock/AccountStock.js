import React from 'react';
import styles from "./AccountStock.module.css"
import test_icon from "./Ellipse 19.png"

const AccountStock = () => {
    return (
        <button className={styles.account_stock}>
            <div className={styles.left_container}>
                <div className={styles.img_container}>
                    <img className={styles.img} src={test_icon} alt={"icon"}/>
                </div>
                <div className={styles.small_container}>
                    <div>Газпром</div>
                    <div>6шт · 166,75₽</div>
                </div>
            </div>
            <div className={styles.small_container}>
                <div>1000,14₽</div>
                <div>+5₽ · 1,1%</div>
            </div>

        </button>
    );
};

export default AccountStock;