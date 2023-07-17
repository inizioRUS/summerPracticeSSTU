import React, {useEffect, useState} from 'react';
import styles from "./StockPage.module.css"
import {useParams, useNavigate} from "react-router";
import Header from "../../components/Header/Header";
import {getStockById} from "../../services/StockService";
import BackButton from "../../components/BackButton/BackButton";

const StockPage = () => {
    const navigate = useNavigate()
    const {id} = useParams();
    const [stock, setStock] = useState({});

    useEffect(() => {
        getStockById(id)
            .then(result => {
                setStock(result)
            })
            .catch(error => console.log(error))
    }, [id])

    return (
        <div>
            <Header/>
            <div className={styles.container}>
                <div className={styles.container_small}>
                    <div className={styles.heading}>
                        <div className={styles.back}>
                            <BackButton onclick={() => {
                                navigate(-1)
                            }} />
                        </div>
                        <div className={styles.heading_container}>
                            <div className={styles.name}>
                                {stock.name}
                            </div>
                            <button className={styles.like}>
                                <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 35 35"
                                     fill="none">
                                    <path d="M17.5 0C17.8249 0.000181693 18.1433 0.0950776 18.4196 0.27406C18.696 0.453042 18.9192 0.709043 19.0645 1.01339L23.5793 10.4656L33.5015 11.9756C33.8247 12.0248 34.1284 12.1677 34.3781 12.3881C34.6278 12.6086 34.8136 12.8978 34.9144 13.2231C35.0153 13.5484 35.0272 13.8968 34.9489 14.2288C34.8705 14.5609 34.705 14.8633 34.471 15.1019L27.249 22.476L28.8729 32.8738C28.9251 33.2104 28.8866 33.5556 28.7616 33.8705C28.6367 34.1854 28.4303 34.4576 28.1658 34.6564C27.9012 34.8552 27.5889 34.9727 27.264 34.9958C26.9391 35.0189 26.6145 34.9466 26.3267 34.787L17.5 29.8831L8.67325 34.787C8.38547 34.9466 8.06088 35.0189 7.73599 34.9958C7.4111 34.9727 7.09881 34.8552 6.83423 34.6564C6.56966 34.4576 6.3633 34.1854 6.23838 33.8705C6.11345 33.5556 6.0749 33.2104 6.12708 32.8738L7.75103 22.476L0.528992 15.1019C0.295018 14.8633 0.129485 14.5609 0.0511199 14.2288C-0.0272456 13.8968 -0.0153161 13.5484 0.0855589 13.2231C0.186434 12.8978 0.372229 12.6086 0.621924 12.3881C0.871619 12.1677 1.17525 12.0248 1.49846 11.9756L11.4207 10.4656L15.9355 1.01339C16.0808 0.709043 16.304 0.453042 16.5804 0.27406C16.8567 0.0950776 17.1751 0.000181693 17.5 0ZM17.5 5.93008L14.1436 12.9597C14.0176 13.2241 13.8325 13.4526 13.604 13.6259C13.3754 13.7991 13.1103 13.912 12.8311 13.9548L5.50935 15.0689L10.8467 20.5171C11.0484 20.7228 11.1997 20.9766 11.2877 21.2567C11.3758 21.5368 11.398 21.835 11.3524 22.126L10.1502 29.8172L16.6793 26.1888C16.932 26.0482 17.2138 25.9747 17.5 25.9747C17.7862 25.9747 18.068 26.0482 18.3207 26.1888L24.8498 29.8172L23.6476 22.126C23.6023 21.8353 23.6247 21.5374 23.7127 21.2577C23.8007 20.9779 23.9519 20.7245 24.1533 20.5189L29.4907 15.0689L22.1706 13.9548C21.8915 13.912 21.6263 13.7991 21.3978 13.6259C21.1692 13.4526 20.9841 13.2241 20.8582 12.9597L17.5 5.93008Z"
                                          fill="white"/>
                                </svg>
                            </button>
                        </div>
                    </div>
                    <div className={styles.chart}></div>
                </div>
            </div>
        </div>
    );
};

export default StockPage;