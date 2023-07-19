const API_URL = "http://31.184.253.4:8080/attachments"

export const getPhotoById =  (fileId) => {
    return `${API_URL}/download/${fileId}`
}

export const uploadPhoto = async (photo) => {
    const options = {
        method: 'POST',
    };
    fetch(`${API_URL}/upload/${photo}`, options)
        .catch(error => console.log(error))
}