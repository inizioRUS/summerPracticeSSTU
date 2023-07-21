const API_URl = "http://5.53.125.163:8080/transactions"

export const increaseBalance = async (user, total) => {
    const options = {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        },
        body: JSON.stringify(user)
    }
    await fetch(`${API_URl}/balance/add?total=${total}`, options)
        .then(response => response)
        .catch(error => console.log(error))
}

export const sell = async (userId, advancedStock, stockId, price) => {
    const options = {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        },
        body: JSON.stringify(advancedStock)
    }
    return await fetch(`${API_URl}/sell?userId=${userId}&stockId=${stockId}&price=${price}`, options)
        .catch(error => console.log(error))
}

export const buy = async (userId, stockId) => {
    const options = {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        }
    }
    return await fetch(`${API_URl}/buy?userId=${userId}&stockId=${stockId}`, options)
        .then(response => response)
        .then(response => response)
        .catch(error => console.log(error))
}