import React from 'react';
import styles from "./Recommendations.module.css"
import AccountStock from "../AccountStock/AccountStock";
import MoreButton from "../MoreButton/MoreButton";
import RecommendationsStock from "../RecommendationsStock/RecommendationsStock";

const Recommendations = () => {
    return (
        <div className={styles.recommendations}>
            <div className={styles.header}>Популярные бумаги</div>
            <div className={styles.content}>
                <RecommendationsStock />
                <RecommendationsStock />
            </div>
            <MoreButton/>
        </div>
    );
};

export default Recommendations;