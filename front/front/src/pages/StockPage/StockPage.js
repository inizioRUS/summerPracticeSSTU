import React, {useEffect, useState} from 'react';
import styles from "./StockPage.module.css"
import {useParams, useNavigate} from "react-router";
import Header from "../../components/Header/Header";
import {getStockById, getYearStocksChart} from "../../services/StockService";
import BackButton from "../../components/BackButton/BackButton";
import FavouritePressed from "../../components/Favourites/FavouritePressed";
import Favourite from "../../components/Favourites/Favourite";
import {Line} from "react-chartjs-2";
import Chart from 'chart.js/auto';
import {addUserWishlistStock, deleteUserWishlistStock, getCurrentUser} from "../../services/UserService";
import AccountStock from "../../components/AccountStock/AccountStock";
import {getPhotoById} from "../../services/PhotoService";


const StockPage = () => {
    const navigate = useNavigate()
    const {id} = useParams();
    const [stock, setStock] = useState({});
    const [dataSet, setDataSet] = useState([])
    const [labels, setLabels] = useState([])
    const [favourite, setFavourite] = useState(false)

    useEffect(() => {
        //
        getStockById(id)
            .then(result => {
                    localStorage.setItem("stock", JSON.stringify(result))
                    getYearStocksChart(JSON.parse(localStorage
                        .getItem("stock")).shortName)
                        .then(response => {
                            if (labels.length === 0) {
                                response.prices.map(item => {
                                    labels.push(item[0])
                                    dataSet.push(item[1])
                                })
                            }
                        })
                        .catch(error => console.log(error))
                }
            )
            .catch(error => console.log(error))
        //
        getCurrentUser().wishlist.forEach(wish => {
            if (wish.id == id) {
                setFavourite(true)
            }
        })
        //
        getCurrentUser().advancedStocks.map(advancedStock => {
            if (advancedStock.stock.id == id) {
                localStorage
                    .setItem("advancedStock", JSON.stringify(advancedStock))
            }
        })
        //
    }, [])


    const handleFavourite = () => {
        if (!favourite) {
            addUserWishlistStock(getCurrentUser().id, id)
                .catch(error => console.log(error))
        } else {
            deleteUserWishlistStock(getCurrentUser().id, id)
                .catch(error => console.log(error))
        }
    }

    const handleUpdateComponent = () => {
        if (favourite) {
            return <FavouritePressed/>
        }
        return <Favourite/>

    }

    const handleAdvancedStock = () => {
        const advancedStock = JSON.parse(localStorage
            .getItem("advancedStock"))
        const stock = JSON.parse(localStorage.getItem("stock"))
        const stockId = stock.id
        const photoLink = getPhotoById(stock.attachmentId);
        const change = stock.price - advancedStock.buyPrice
        return <AccountStock key={stockId}
                             id={stockId}
                             img={photoLink}
                             name={stock.name}
                             cost={stock.price.toFixed(2)}
                             count={advancedStock.count}
                             change={change}/>
    }

    return (
        <div>
            <Header/>
            <div className={styles.container}>
                <div className={styles.container_small}>
                    <div className={styles.heading}>
                        <div className={styles.back}>
                            <BackButton onclick={() => {
                                localStorage.removeItem("advancedStock")
                                localStorage.removeItem("stock")
                                navigate(-1)
                            }}/>
                        </div>
                        <div className={styles.heading_container}>
                            <div className={styles.name}>
                                {stock.name}
                            </div>
                            <button className={styles.like}
                                    onClick={() => {
                                        handleFavourite()
                                        setFavourite(!favourite)
                                    }}>
                                {handleUpdateComponent()}
                            </button>
                        </div>
                    </div>
                    <div className={styles.chart}>
                        <Line datasetIdKey='id'
                              data={{
                                  labels: labels,
                                  datasets: [
                                      {
                                          id: 1,
                                          label: 'Year',
                                          data: dataSet,
                                      }],
                              }}/>
                    </div>

                    <div>
                        <div className={styles.text}>В портфеле</div>
                        <div className={styles.in_account}>
                            {localStorage.getItem("advancedStock") ? handleAdvancedStock() :
                                <div className={styles.text}>Пока нет</div>}
                        </div>
                    </div>
                    <div className={styles.in_account_container}>
                        <button className={styles.buy}>
                            <div className={styles.txt}>Купить</div>
                        </button>
                        <button className={styles.sell}>
                            <div className={styles.txt}>Продать</div>
                        </button>
                    </div>
                    <div className={styles.ml}>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default StockPage