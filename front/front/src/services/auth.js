const API_URl = "http://localhost:8080/api/v1/auth"

export const register = async (email, password) => {
    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({"email": email, "password": password})
    };
    return await fetch(`${API_URl}/register`, options)
        .then(response => response.json())
        .then((response) => {
            if (response) {
                localStorage.setItem("user", JSON.stringify(response))
            }
            return response
        })
        .catch(err => console.error(err));
}

export const  authenticate = async (email, password) => {
    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        mode: "no-cors",
        body: JSON.stringify({"email": email, "password": password})
    };
    return await fetch(`${API_URl}/authenticate`, options)
        .then(function checkResponse(response) {
            response.json()
            localStorage.setItem("user", JSON.stringify(response))
        })
}

export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"))
}