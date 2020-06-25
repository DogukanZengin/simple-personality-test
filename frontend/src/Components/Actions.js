import axios from 'axios';
let proxy = "http://localhost:8080/api/v1";
export const fetchQuestions = (categoryId) => {
    return axios.get(proxy + '/questions/category/' + categoryId)
        .then(
            response => response.data
        )
        .catch(
            err => console.log('Fetch failed.', err)
        )
}

export const fetchCategories = () => {
    return axios.get(proxy + '/categories')
        .then(
            response => response.data
        )
        .catch(
            err => console.log('Fetch failed.', err)
        )
}

export const sendAnswers = ({username, answers}) => {
    return axios.post(proxy + '/answers', { username, answers })
        .then(
            response => response.data
        )
        .catch(
            error => console.log('Submit failed.', error)
        )
}