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