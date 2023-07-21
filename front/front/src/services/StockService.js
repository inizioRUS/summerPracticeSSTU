const API_URl = "http://31.184.253.4:8080/stocks"
const STOCKS_API_URL = "http://80.249.145.114:8000"
const ML = "http://77.223.99.50:8000"

export const getStockById = async (id) => {
    const options = {
        method: "GET",
        headers: {"Access-Control-Allow-Origin": "*",},
    };
    return await fetch(`${API_URl}/${id}`, options)
        .then(response => response.json())
        .then(response => response)
        .catch(error => console.log(error))
}

export const getStocks = async () => {
    const options = {
        method: "GET",
        headers: {"Access-Control-Allow-Origin": "*",},
    };
    return await fetch(API_URl, options)
        .then(response => response.json())
        .catch(error => console.log(error))
}

export const getYearStocksChart = async (name) => {
    const options = {
        method: "GET",
        headers: {"Access-Control-Allow-Origin": "*",},
    };
    return await fetch(`${STOCKS_API_URL}/year/${name}`)
        .then(response => response.json())
        .then(response => response)
        .catch(error => console.log(error))
}

export const getWeekStocksChart = async (name) => {
    const options = {
        method: "GET",
        headers: {"Access-Control-Allow-Origin": "*",},
    };
    return await fetch(`${STOCKS_API_URL}/week/${name}`)
        .then(response => response.json())
        .then(response => response)
        .catch(error => console.log(error))
}

export const getWeekPredictChart = async (name) => {
    const options = {
        method: "GET",
        headers: {"Access-Control-Allow-Origin": "*",},
    };
    return await fetch(`${ML}/predict/${name}`)
        .then(response => response.json())
        .then(response => response)
        .catch(error => console.log(error))
}



export const getUserStocks = async (userId) => {
    const options = {
        method: "GET",
        headers: {"Access-Control-Allow-Origin": "*",},
    };
    return await fetch(`${API_URl}/advanced/${userId}`)
        .then(response => response.json())
        .catch(error => console.log(error))
}
