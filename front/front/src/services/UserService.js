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
    return await fetch(`${API_URl}/${id}`, options)
        .then(response => response.json())
        .then((response) => {
            if (response) {
                localStorage.setItem("user", JSON.stringify(response))
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

export const updateUserStocks = async (userId, stockId, count) => {

}

export const increaseUserBalance = async (userId, amount) => {

}

export const decreaseUserBalance = async (userId, amount) => {

}

export const addUserWishlistStock = async (userId, stockId) => {

}

export const deleteUserWishlistStock = async (userId, stockId) => {

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
    setTimeout(() => {}, 10000)

}
export const deleteUserStocks = async (userId, stockId, count) => {

}