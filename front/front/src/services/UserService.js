const API_URl = "http://31.184.253.4:8080/users"

export const setCurrentUser = (user) => {
    localStorage.setItem("user", JSON.stringify(user))
}

export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"))
}
export const getUserById = async (id) => {
    const options = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };
    return await fetch(`${API_URl}/${1}`, options)
        .then(response => response.json())
        .then((response) => {
            if (response) {
                setCurrentUser(response)
            }
            return response
        })
        .catch(err => console.error(err));
}

export const getUserByEmail = async (email) => {
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        },
    };
    return await fetch(`${API_URl}/email?email=${email}`, options)
        .then(response => response.json())
        .then((response) => {
            if (response) {
                localStorage.setItem("user", JSON.stringify(response))
            }
            return response
        })
        .catch(err => console.error(err));
}

export const updateUserById = async (id, user) => {
    const options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        },
        body: JSON.stringify(user)
    };
    return await fetch(`${API_URl}/${id}`, options)
        .catch(error => console.log(error))
}

export const addUserStocks = async (userId, stockId) => {
    const options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        },
    };
    return await fetch(`${API_URl}/${userId}/stock/${stockId}/1`, options)
        .then(response => response.json())
        .catch(error => console.log(error))
}

export const increaseUserBalance = async (userId, amount) => {
    const options = {
        method: 'PUT',
        headers: {"Access-Control-Allow-Origin": "*"},
    };
    fetch(`${API_URl}/${userId}/incBalance/${amount}`, options)
        .then(response => response.json())
        .catch(error => console.log(error))
}

export const decreaseUserBalance = async (userId, amount) => {
    const options = {
        method: 'PUT',
        headers: {"Access-Control-Allow-Origin": "*"}
    };
    fetch(`${API_URl}/${userId}/decBalance/${amount}`, options)
        .catch(error => console.log(error))
}

export const addUserWishlistStock = async (userId, stockId) => {
    const options = {
        method: 'PUT',
    };
    fetch(`${API_URl}/${userId}/wishlist/stock/${stockId}`, options)
        .then(response => response.json())
        .catch(error => console.log(error))
}

export const deleteUserWishlistStock = async (userId, stockId) => {
    const options = {
        method: 'DELETE',
    };
    fetch(`${API_URl}/${userId}/wishlist/stock/${stockId}`, options)
        .then(response => response.json())
        .catch(error => console.log(error))
}
export const changeUserPhoto = async (userId, newPhoto) => {
    const data = new FormData()
    data.append("photo", newPhoto)

    const options = {
        method: 'PUT',
        body: data
    };
    fetch(`${API_URl}/${userId}/change/photo`, options)
        .then(response => response.json())
        .then(response => console.log(response))
        .catch(error => console.log(error))
    setTimeout(() => {
    }, 10000)

}
export const deleteUserStocks = async (userId, advancedStockId) => {
    const options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        },
    };
    return await fetch(`${API_URl}/${userId}/stock/${advancedStockId}/1`, options)
        .then(response => response.json())
        .catch(error => console.log(error))
}
export const getProfitByUserID = async (userId) => {
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        },
    };
    return await fetch(`${API_URl}/profit/user/${userId}`, options)
        .then(response => response.json())
        .catch(err => console.error(err));
}
