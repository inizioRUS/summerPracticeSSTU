import React, {useEffect, useState} from 'react';
import styles from "./StockPage.module.css"
import {useParams, useNavigate} from "react-router";
import Header from "../../components/Header/Header";
import {getStockById} from "../../services/StockService";
import BackButton from "../../components/BackButton/BackButton";
import FavouritePressed from "../../components/Favourites/FavouritePressed";
import Favourite from "../../components/Favourites/Favourite";
import AnyChart from "anychart-react/src/anychart-react";

const StockPage = () => {
    const navigate = useNavigate()
    const {id} = useParams();
    const [stock, setStock] = useState({});
    const [favourite, setFavourite] = useState(false)

    useEffect(() => {
        getStockById(id)
            .then(result => setStock(result))
            .catch(error => console.log(error))

    }, [])

    const handleFavourite = () => favourite ? <FavouritePressed /> : <Favourite />

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
                            <button className={styles.like}
                                    onClick={() => setFavourite(!favourite)}>
                                {handleFavourite()}
                            </button>
                        </div>
                    </div>
                    <div className={styles.chart}>

                    </div>
                </div>
            </div>
        </div>
    );
};

export default StockPage;