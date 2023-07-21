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

export const authenticate = async (email) => {
    console.log("start")
    const options = {
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
    };
    return await fetch(`http://31.184.253.4:8080/users/email?email=${email}`, options)
        .then(response => {
            console.log(response.json());
            return response
        })
        .then(response => {
            console.log("end")
            localStorage.setItem("user", JSON.stringify(response))
            return response
        })
        .catch(error => console.log(error))
}