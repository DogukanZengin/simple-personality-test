import React from 'react'
import { fetchQuestions, fetchCategories, sendAnswers, checkUsername, uploadImage } from '../../actions'
import App from '../../App'
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
            email: ''
        };
        this.usernameTimeout = null;

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

        fetchQuestions()
            .then(
                questions => this.setState({ questions })
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

    testCompleted(email){
        sendAnswers({ username: email, answers: this.state.answers});
    }

    onEmailProvided(email){
        this.setState({ email });
        console.log(email)
    }

    onCategoryChange(categoryAnswers){
        let answers = [...this.state.answers];
        const { categories, email } = this.state;
        answers = answers.concat(categoryAnswers);
        let activeCategory = this.state.activeCategory;
        if(activeCategory + 1 === this.state.categories.length){
            // that's it
            this.setState({ finished: true, answers });
            sendAnswers({ username: email, answers });

        }
        else{
            activeCategory++;
            this.setState({
                answers,
                activeCategory,
                steps: this.generateSteps(categories, activeCategory)
            });
        }

    }

    checkUsername(username){
        return new Promise((resolve, reject)=>{
            clearTimeout(this.usernameTimeout);
            this.usernameTimeout = setTimeout(()=>{
                checkUsername(username)
                    .then(
                        response => resolve(response)
                    )
                    .catch(
                        error => reject(error)
                    );
            }, 500);
        });
    }

    render(){
        let categoryQuestions = [];
        const { categories, questions, activeCategory, finished } = this.state;
        if(activeCategory >= 0 && Object.keys(questions).length){
            categoryQuestions = questions[categories[activeCategory].name];
        }
        return <App steps={this.state.steps}
                        questions={categoryQuestions}
                        onCategoryChange={this.onCategoryChange}
                        lastCategory={activeCategory + 1 === categories.length}
                        finished={finished}
                        onEmailProvided={this.onEmailProvided.bind(this)}
                        checkUsername={ this.checkUsername.bind(this) }
                        uploadImage={uploadImage} />
    }
}