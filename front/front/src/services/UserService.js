import img from "../components/Header/Ava.png"

const API_URl = "http://localhost:8080/users"

export const getUserById = async (id) => {
    // const options = {
    //     method: 'GET',
    //     headers: {'Content-Type': 'application/json'},
    // };
    // return await fetch(`${API_URl}/${id}`, options)
    //     .then(response => response.json())
    //     .then((response) => {
    //         if (response) {
    //             localStorage.setItem("user", JSON.stringify(response))
    //         }
    //         console.log(response)
    //         return response
    //     })
    //     .catch(err => console.error(err));
    return {
        id: 1,
        img: img,
        name: "Олег",
        surname: "Штырев",
        email: "oleg@mail.ru"
    }
}

export const getUserByEmail = async (email) => {
    const options = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };
    return await fetch(`${API_URl}/?email=${email}`, options)
        .then(response => response.json())
        .then((response) => {
            if (response) {
                localStorage.setItem("user", JSON.stringify(response))
            }
            console.log(response)
            return response
        })
        .catch(err => console.error(err));
}

export const updateUserById = async (id, user) => {

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

}
export const deleteUserStocks = async (userId, stockId, count) => {

}