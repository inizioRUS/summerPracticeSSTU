import React, {useEffect, useState} from 'react';
import styles from "./AccountStock.module.css"
import test_icon from "./Ellipse 19.png"
import {useNavigate} from "react-router";

const AccountStock = ({id, img, name, cost, count, change}) => {
    const navigate = useNavigate()
    const [changeColor, setChangeColor] = useState()

    useEffect(() => {
        change >= 0 ? setChangeColor("#359C41") : setChangeColor("#D83636")
    })

    return (
        <button className={styles.account_stock}
                onClick={() => {navigate(`/stock/${id}`)}}>
            <div className={styles.left_container}>
                <div className={styles.img_container} style={{
                    backgroundImage: `url(${img})`
                }}>
                </div>
                <div className={styles.small_container}>
                    <div>{name}</div>
                    <div>{count}шт · {cost}₽</div>
                </div>
            </div>
            <div className={styles.small_container}>
                <div>{count * cost}₽</div>
                <div style={{
                    color: `${changeColor}`
                }}>+{change}₽ · {(change/cost).toFixed(2)}%</div>
            </div>

        </button>
    );
};

export default AccountStock;