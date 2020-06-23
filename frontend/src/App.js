import React from 'react';
import PropTypes from 'prop-types'

export default class AppRoot extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      email: '',
      emailProvided: false,
      emailValid: false,
      duplicateEmail: false,
      isCheckingEmail: false
    }
  }
  validateEmail() {
    let duplicateEmail = false;
    this.props.checkUsername(this.state.email)
        .then(
            response => {
              if(response.status != 'available'){
                duplicateEmail = true;
              }
              this.setState({ duplicateEmail, isCheckingEmail: false });
            }
        )
        .catch(
            () => this.setState({ duplicateEmail: true, isCheckingEmail: false })
        );
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(re.test(this.state.email)){
      this.setState({ emailValid: true });
      if(!this.state.duplicateEmail){
        this.props.onEmailProvided(this.state.email);
      }
    }
    else{
      this.setState({ emailValid: false });
    }
  }
  emailProvided(){
    this.setState({ emailProvided: true });
  }

  pickImage(){
    this.__file.click();
  }
}

AppRoot.PropTypes = {
  steps: PropTypes.array.isRequired,
  sendAnswers: PropTypes.func.isRequired,
  checkUsername: PropTypes.func.isRequired,
  uploadImage: PropTypes.func.isRequired
}