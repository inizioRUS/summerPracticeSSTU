const API_URl = "http://31.184.253.4:8080/stocks"

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
        .then(response => response)
        .catch(error => {
            console.log(error)
        })
}
