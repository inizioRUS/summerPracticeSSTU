import React, {useEffect, useState} from 'react';
import styles from "./StockPage.module.css"
import {useParams, useNavigate} from "react-router";
import Header from "../../components/Header/Header";
import {
    getStockById,
    getUserStocks,
    getWeekPredictChart,
    getWeekStocksChart,
} from "../../services/StockService";
import BackButton from "../../components/BackButton/BackButton";
import FavouritePressed from "../../components/Favourites/FavouritePressed";
import Favourite from "../../components/Favourites/Favourite";
import {Line} from "react-chartjs-2";
import Chart from 'chart.js/auto';
import {
    addUserStocks,
    addUserWishlistStock,
    decreaseUserBalance, deleteUserStocks,
    deleteUserWishlistStock,
    getCurrentUser,
    getUserById, increaseUserBalance
} from "../../services/UserService";
import {getPhotoById} from "../../services/PhotoService";
import {buy, sell} from "../../services/TransactionService";
import StockPageStock from "../../components/StockPageStock/StockPageStock";


const StockPage = () => {

    const navigate = useNavigate()
    const {id} = useParams();
    const [stock, setStock] = useState({})
    const [advancedStock, setAdvancedStock] = useState({})
    const [dataSet, setDataSet] = useState([])
    const [dataSetML, setDataSetML] = useState([])
    const [labels, setLabels] = useState([])
    const [labelsML, setLabelsML] = useState([])
    const [favourite, setFavourite] = useState(false)
    const [advancedStockComponent, setAdvancedStockComponent] = useState(<div></div>)
    const [favouriteComponent, setFavouriteComponent] = useState(<Favourite/>)
    const [commonLabels, setCommonLabels] = useState([])
    const [advancedStockId, setAdvancedStockId] = useState()

    useEffect(() => {
            getStockById(id)
                .then(result => {
                        setStock(result)
                        getWeekStocksChart(result.shortName)
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
            getStockById(id)
                .then(result => {
                    getWeekPredictChart(result.shortName)
                        .then(response => {
                            if (labelsML.length === 0) {
                                response.map(item => {
                                    labelsML.push(item[0])
                                    dataSetML.push(item[1])
                                })
                            }
                            setCommonLabels(labels.concat(labelsML))
                        })
                        .catch(error => console.log(error))
                })
                .catch(error => console.log(error))

            getUserById(getCurrentUser().id)
                .then(response => {
                    response.advancedStocks.map(advancedStock => {
                        if (advancedStock.stock.id == id) {
                            setAdvancedStock(advancedStock)
                            setAdvancedStockId(advancedStock.id)
                            handleAdvancedStock(
                                advancedStock.id,
                                advancedStock.stock.attachmentId,
                                advancedStock.stock.name,
                                advancedStock.stock.price,
                                advancedStock.count,
                                0)
                        }
                    })
                    response.wishlist.map((wish) => {
                        if (wish.id == id) {
                            setFavourite(true)
                            setFavouriteComponent(<FavouritePressed/>)
                        }
                    })
                })
                .catch(error => console.log(error))
        },
        [])


    const handleFavourite = () => {
        if (!favourite) {
            addUserWishlistStock(getCurrentUser().id, id)
                .catch(error => console.log(error))
            handleUpdateComponent()
        } else {
            deleteUserWishlistStock(getCurrentUser().id, id)
                .catch(error => console.log(error))
            handleUpdateComponent()
        }
    }
    const handleUpdateComponent = () => {
        if (favourite) {
            setFavouriteComponent(<FavouritePressed/>)
        }
        setFavouriteComponent(<Favourite/>)

    }
    const handleAdvancedStock = (id, photoId, name, cost, count, change) => {
        console.log(change)
        const photoLink = getPhotoById(photoId);
        setAdvancedStockComponent(<StockPageStock key={id}
                                                  id={id}
                                                  img={photoLink}
                                                  name={name}
                                                  cost={cost.toFixed(2)}
                                                  count={count}
                                                  change={change}/>)
    }

    const handleBuy = () => {
        // buy(getCurrentUser().id, id)
        //     .then(response => response)
        //     .catch(error => console.log(error))
        decreaseUserBalance(getCurrentUser().id, stock.price)
            .catch(error => console.log(error))
        addUserStocks(getCurrentUser().id, stock.id)
            .catch(error => console.log(error))
    }

    const handleSell = () => {
        increaseUserBalance(getCurrentUser().id, stock.price)
            .catch(error => console.log(error))
        deleteUserStocks(getCurrentUser().id, advancedStockId)
            .catch(error => console.log(error))
        // let tempAdvStock;
        // getUserById(getCurrentUser().id)
        //     .then(response => {
        //         response.advancedStocks.forEach(item => {
        //             if (item.stock.id == id) {
        //                 console.log(item)
        //                 tempAdvStock = item
        //                 const price = stock.price
        //                 const newAdvancedStock = {
        //                     "id": tempAdvStock.id,
        //                     "stock": stock,
        //                     "count": 1,
        //                     "buyPrice": tempAdvStock.buyPrice,
        //                 }
        //                 sell(getCurrentUser().id, newAdvancedStock, id, price)
        //                     .then(response => response)
        //                     .catch(error => console.log(error))
        //             }
        //         })
        //     })
    }

    return (
        <div>
            <Header/>
            <div className={styles.container}>
                <div className={styles.container_small}>
                    <div className={styles.heading}>
                        <div className={styles.back}>
                            <BackButton onclick={() => {
                                navigate(-1)
                            }}/>
                        </div>
                        <div className={styles.heading_container}>
                            <div className={styles.name}>{stock.name}</div>
                            <button className={styles.like}
                                    onClick={() => {
                                        handleFavourite()
                                        setFavourite(!favourite)
                                    }}>
                                {favouriteComponent}
                            </button>
                        </div>
                    </div>
                    <div className={styles.chart}>
                        <Line datasetIdKey='id' data={{
                            labels: labels.concat(labelsML),
                            datasets: [
                                {
                                    id: 1,
                                    label: 'Week',
                                    data: dataSet,
                                    borderColor: '#FF6384',
                                    backgroundColor: '#FFB1C1',
                                },
                                {
                                    id: 2,
                                    label: "Predict",
                                    data: dataSet.concat(dataSetML),
                                    borderColor: '#36A2EB',
                                    backgroundColor: '#9BD0F5',
                                }
                            ],
                        }}/>
                    </div>

                    <div>
                        <div className={styles.text}>В портфеле</div>
                        <div className={styles.in_account}>
                            {advancedStockComponent}
                        </div>
                    </div>
                    <div className={styles.in_account_container}>
                        <button className={styles.buy} onClick={handleBuy}>
                            <div className={styles.txt}>Купить</div>
                        </button>
                        <button className={styles.sell} onClick={handleSell}>
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