import React, {useEffect, useState} from 'react';
import styles from "./StockPage.module.css"
import {useParams, useNavigate, useLocation} from "react-router";
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
import {buy} from "../../services/TransactionService";


const StockPage = () => {
    const navigate = useNavigate()
    const {id} = useParams();
    const [name, setName] = useState('');
    const [stock, setStock] = useState({})
    const [advancedStock, setAdvancedStock] = useState({})

    const [dataSet, setDataSet] = useState([])
    const [labels, setLabels] = useState([])

    const [favourite, setFavourite] = useState(false)

    useEffect(() => {
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

        getCurrentUser().advancedStocks.map(advancedStock => {
            if (advancedStock.stock.id == id) {
                localStorage
                    .setItem("advancedStock", JSON.stringify(advancedStock))
            }
        })

        setStock(getLocalStock())
        setAdvancedStock(getLocalAdvancedStock())

        console.log(stock)
        console.log(advancedStock)

        getCurrentUser().wishlist.forEach(wish => {
            if (wish.id == id) {
                setFavourite(true)
            }
        })

        setName(getLocalStock().name)
    }, [id])


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

    const getLocalStock = () => JSON.parse(localStorage.getItem("stock"))

    const getLocalAdvancedStock = () => JSON.parse(localStorage.getItem("advancedStock"))

    const handleAdvancedStock = () => {
        const photoId = getLocalStock().attachmentId
        const photoLink = getPhotoById(photoId);
        const change = getLocalStock().price - getLocalAdvancedStock().buyPrice
        return (<AccountStock key={id}
                              id={id}
                              img={photoLink}
                              name={getLocalStock().name}
                              cost={getLocalStock().price.toFixed(2)}
                              count={getLocalAdvancedStock().count}
                              change={change.toFixed(4)}/>)
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
                            <div className={styles.name}>{name}</div>
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
                            {}
                        </div>
                    </div>
                    <div className={styles.in_account_container}>
                        <button className={styles.buy} onClick={() => {
                            console.log(1)
                            buy(JSON.parse(localStorage.getItem("advancedStock")))
                                .then(response => console.log(response))
                                .catch(error => console.log(error))
                        }}>
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