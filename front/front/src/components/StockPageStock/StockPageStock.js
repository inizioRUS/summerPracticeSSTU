import React, {useEffect, useState} from 'react';
import styles from "./StockPageStock.module.css"
import {useNavigate} from "react-router";

const StockPageStock = ({id, img, name, cost, count, change}) => {
    const navigate = useNavigate()
    const [changeColor, setChangeColor] = useState()

    useEffect(() => {
        change >= 0 ? setChangeColor("#359C41") : setChangeColor("#D83636")
    })

    return (
        <div className={styles.account_stock}
                onClick={() => {navigate(`/stock/${id}`)}}>
            <div className={styles.left_container}>
                <div className={styles.img_container} style={{
                    backgroundImage: `url(${img})`
                }}>
                </div>
                <div className={styles.small_container}>
                    <div>{name}</div>
                    <div>{count}шт · {cost}$</div>
                </div>
            </div>
            <div className={styles.small_container}>
                <div>{(count * cost).toFixed(2)}$</div>
                <div style={{
                    color: `${changeColor}`
                }}>{change.toFixed(2)}$ · {(change/cost).toFixed(2)}%</div>
            </div>

        </div>
    );
};

export default StockPageStock;