import React from 'react'
import PropTypes from 'prop-types'
import { Button } from 'semantic-ui-react'
import Question from './Question'
import UnansweredQuestionModal from './UnansweredQuestions'

export default class QuestionsInCategory extends React.Component {
    constructor(props){
        super(props);
        let questions = this.processQuestions(this.props.questions);
        this.state = {
            answers: this.prepareAnswers(questions),
            questions,
            finished: false,
            showModal: false,
            markUnanswered: false
        };
        this.nextCategory = this.nextCategory.bind(this);
    }

    saveAnswer(id, answer){
        let answers = [...this.state.answers];
        let questions = [...this.state.questions];
        console.log(typeof answer)
        if (typeof answer === "number") {
            console.log("Setting range");
            answers[id].range = answer;
            console.log(answers[id])
        }else{
            answers[id].answer = answer;
        }
        if(questions[id].hasBonus){
            questions[id + 1].visible = this.processCondition(answer);
        }
        this.setState({ answers, questions });
    }

    prepareAnswers(questions){
        return questions.map( (question) => {
            return {
                question: question,
                answer: null,
                category: question.category
            };
        } );
    }

    processQuestions(questions){
        let allQuestions = [];
        questions.forEach( (question, key) => {
            if(question.type === 'SINGLE_CHOICE_CONDITIONAL'){
                allQuestions.push(Object.assign({}, question, { visible: true, hasBonus: true }));
                question.options.forEach( (option, key) => {
                    if(option.followUp){
                        allQuestions.push(Object.assign({}, option.followUp, { visible: false, hasBonus: false }));
                    }
                });
            }
            else{
                allQuestions.push(Object.assign({}, question, { visible: true, hasBonus: false }));
            }
        });
        console.log(allQuestions)
        return allQuestions;
    }

    processCondition(answer){
        if(answer.followUp){
            return true;
        }
        return false;
    }

    canMoveToNextCategory(){
        return this.state.answers.filter( (answer, key) => {
            if(!this.state.questions[key].visible){
                return false
            }
            return answer.range == null && answer.answer == null;
        }).length === 0;
    }

    nextCategory(){
        if(!this.canMoveToNextCategory()){
            this.setState({ showModal: true, markUnanswered: true });
        }
        else{
            this.setState({ markUnanswered: false });
            const { answers, questions } = this.state;
            let filteredAnswers = answers.filter( (answer, key) => questions[key].visible);
            this.props.onCategoryChange(filteredAnswers);
        }
    }

    componentWillReceiveProps(nextProps){
        if(JSON.stringify(nextProps.questions) !== JSON.stringify(this.props.questions)){
            let questions = this.processQuestions(nextProps.questions);
            this.setState({ answers: this.prepareAnswers(questions), questions });
            setTimeout(function() {window.scrollTo(0, 0);},50);
        }
    }

    closeModal(){
        this.setState({ showModal: false });
    }

    render(){
        const { lastCategory } = this.props;
        const { questions, showModal, markUnanswered } = this.state;
        let button, uaModal;
        if(!lastCategory){
            button = <Button primary
                             content='Next'
                             icon='right arrow'
                             labelPosition='right'
                             floated='right'

                             onClick={this.nextCategory} />
        }
        else{
            button = <Button color={'green'}
                             content='Finish'
                             icon='check'
                             labelPosition='right'
                             floated='right'
                             onClick={this.nextCategory} />
        }
        if(showModal){
            uaModal = <UnansweredQuestionModal onClose={this.closeModal.bind(this)} />
        }
        return (
            <div>
                {
                    questions.map( (question, key) => <Question question={question.text}
                                                                type={question.type}
                                                                options={question.options}
                                                                range={question.range}
                                                                key={question.id + key}
                                                                visible={question.visible}
                                                                onChange={this.saveAnswer.bind(this, key)}
                                                                markRed={markUnanswered}/> )
                }
                { button }
                { uaModal }
            </div>
        );
    }
}

QuestionsInCategory.propTypes = {
    questions: PropTypes.array.isRequired,
    onCategoryChange: PropTypes.func.isRequired
}