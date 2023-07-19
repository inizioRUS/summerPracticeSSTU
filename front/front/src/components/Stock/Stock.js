import React from 'react';
import styles from "./Stock.module.css"

const Stock = ({ img, name, cost}) => {
    return (
        <div className={styles.container}>
            <div className={styles.stock}>
                <div className={styles.picture} style={{
                    backgroundImage: `url(${img})`
                }}/>
                <div className={styles.text}>
                    <div>{name}</div>
                    <div className={styles.text_container}>
                        <div>{cost}₽</div>
                        {/*&nbsp;<div>·</div>&nbsp;*/}
                        {/*<div className={styles.procent}>+{change}%</div>*/}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Stock;