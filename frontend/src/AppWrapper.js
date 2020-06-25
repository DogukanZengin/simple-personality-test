import React from 'react'
import { fetchQuestions, fetchCategories, sendAnswers } from './Components/Actions'
import App from './App'
export default class AppWrapper extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            questions: {},
            steps: [],
            isFetching: true,
            isSending: false,
            error: false,
            answers: [],
            activeCategory: -1,
            categories: [],
            finished: false,
            email: '',
        };
        this.usernameTimeout = null;
    }

    componentWillMount(){
        this.onCategoryChange = this.onCategoryChange.bind(this);
        fetchCategories()
            .then(
                categories => this.setState({
                    activeCategory: 0,
                    categories,
                    steps: this.generateSteps(categories, 0)
                })
            )
            .catch(
                () => this.setState({ error: true })
            )
        fetchQuestions(1)
            .then(
                questions => this.setState({questions})
            )
            .catch(
            () => this.setState({ error: true })
        )
    }

    generateSteps(categories, activeCategory){
        return categories.map( (category, id) => {
            return {
                completed: false,
                title: category.display,
                name: category.name,
                active: activeCategory === id
            }
        });
    }
    
    onEmailProvided(email){
        this.setState({ email });
    }

    onCategoryChange(categoryAnswers){
        let answers = [...this.state.answers];
        const { categories, email } = this.state;
        console.log("Email",email);
        answers = answers.concat(categoryAnswers);
        let activeCategory = this.state.activeCategory;
        if(activeCategory + 1 === this.state.categories.length){
            this.setState({ finished: true, answers });
            sendAnswers({ username: email, answers });
        }
        else{
            activeCategory++;
            fetchQuestions(activeCategory+1)
                .then(
                    questions => this.setState({questions})
                )
                .catch(
                    () => this.setState({ error: true })
                )
            this.setState({
                answers,
                activeCategory,
                steps: this.generateSteps(categories, activeCategory)
            });
        }

    }

    render(){
        const { categories, questions, activeCategory, finished } = this.state;
        return <App steps={this.state.steps}
                        questions={questions}
                        onCategoryChange={this.onCategoryChange}
                        lastCategory={activeCategory + 1 === categories.length}
                        finished={finished}
                        onEmailProvided={this.onEmailProvided.bind(this)}/>
    }
}