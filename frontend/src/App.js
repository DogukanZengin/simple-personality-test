import React from 'react';
import PropTypes from 'prop-types'
import { Header, Container, Step, Divider, Modal, Button, Input } from 'semantic-ui-react';
import Questions from './Components/QuestionsInCategory'
export default class App extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      email: '',
      emailProvided: false,
      emailValid: false,
      isCheckingEmail: false
    }
  }
  validateEmail() {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(re.test(this.state.email)){
      this.setState({ emailValid: true, isCheckingEmail: false });
      this.props.onEmailProvided(this.state.email);
    }
    else{
      this.setState({ emailValid: false });
    }
  }
  emailProvided(){
    this.setState({ emailProvided: true });
  }

  render(){
    const { steps, questions, onCategoryChange, lastCategory, finished } = this.props;
    const { emailProvided, emailValid, isCheckingEmail } = this.state;
    let categoryQuestions, emailModal, emailWarning;
    if(questions.length){
      categoryQuestions = <Questions questions={questions}
                                     onCategoryChange={onCategoryChange}
                                     lastCategory={lastCategory} />
    }

    if(!emailProvided){
      emailModal = <Modal dimmer="blurring" open={true} >
        <Modal.Header>Please enter your email address</Modal.Header>
        <Modal.Content>
          <Input fluid focus placeholder='Email'
                 onChange={(ev, props)=>{ this.setState({ email: props.value, isCheckingEmail: true }, ()=>{ this.validateEmail() }); }}
                 value={this.state.email} />
          { emailWarning }
          <br/>
        </Modal.Content>
        <Modal.Actions>
          <Button color='green' onClick={this.emailProvided.bind(this)} disabled={ !emailValid || isCheckingEmail } loading={isCheckingEmail}>
            Continue
          </Button>
        </Modal.Actions>
      </Modal>;
    }

    if(finished){
      return <Container textAlign="center">
        <Header className="thank-you" as="h1">Your Answers are saved to Data Store</Header>
      </Container>
    }
    else{
      return (
          <div id="content">
            <Container textAlign="center">
              <Header className="the-title" as="h1">What is your personality</Header>
              <Step.Group size="large" fluid ordered items={steps} stackable="tablet"/>
              <Divider horizontal>Answer the questions</Divider>
              { categoryQuestions }
            </Container>
            { emailModal }
          </div>
      );
    }
  }
}

App.propTypes = {
  steps: PropTypes.array.isRequired
}