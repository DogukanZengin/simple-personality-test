import axios from 'axios';
let proxy = "http://localhost:8080/api/v1";
export const fetchQuestions = (categoryId) => {
    return axios.get(proxy + '/questions/category/' + categoryId)
        .then(
            response => response.data.questions
        )
        .catch(
            err => console.log('Fetch failed.', err)
        )
}

export const fetchCategories = () => {
    return axios.get(proxy + '/categories')
        .then(
            response => response.data.categories
        )
        .catch(
            err => console.log('Fetch failed.', err)
        )
}

export const sendAnswers = ({username, answers}) => {
    return axios.post('/answers', { username, answers })
        .then(
            response => response.data
        )
        .catch(
            error => console.log('Submit failed.', error)
        )
}