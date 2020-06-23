import axios from 'axios';

export const fetchQuestions = (categoryId) => {
    return axios.get('/questions/category' + categoryId)
        .then(
            response => response.data.questions
        )
        .catch(
            err => console.log('Fetch failed.', err)
        )
}

export const fetchCategories = () => {
    return axios.get('/categories')
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