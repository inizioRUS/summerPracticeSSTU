import React, {useEffect, useState} from 'react';
import styles from "../StocksPage/StocksPage.module.css"
import Header from "../../components/Header/Header";
import BackButton from "../../components/BackButton/BackButton";
import {useNavigate} from "react-router";
import {getCurrentUser, getUserById} from "../../services/UserService";
import {getPhotoById} from "../../services/PhotoService";
import {Link} from "react-router-dom";
import Stock from "../../components/Stock/Stock";

const FavouritePage = () => {
    const navigate = useNavigate()
    const [stocks, setStocks] = useState([])

    useEffect(() => {
        getUserById(getCurrentUser().id)
            .then(response => setStocks(response.wishlist))
            .catch(error => console.log(error))
    }, [])

    return (
        <div className={styles.stocks_page}>
            <div className={styles.back}>
                <BackButton onclick={() => {
                    navigate("/main")
                }}/>
            </div>
            <Header/>
            <div className={styles.container}>
                <div className={styles.stocks_container}>
                    {
                        stocks ? stocks.map((stock) => {
                            const photoLink = getPhotoById(stock.attachmentId)
                            return <Link to={`/stock/${stock.id}`}
                                         className={styles.link}
                                         key={stock.id}>
                                <Stock key={stock.id}
                                       img={photoLink}
                                       name={stock.name}
                                       cost={stock.price}/>
                            </Link>
                        }) : <div></div>
                    }
                </div>
            </div>
        </div>
    );
};

export default FavouritePage;