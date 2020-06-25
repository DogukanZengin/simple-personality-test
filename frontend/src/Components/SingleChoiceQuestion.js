import React from 'react';
import { Form, Radio } from 'semantic-ui-react'

export default class SingleChoice extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            selected: -1
        };
    }

    componentWillMount(){
        this.handleChange = this.handleChange.bind(this);
    }
    
    handleChange(ev, {value}){
        this.setState({ selected: value }, ()=>{
            this.props.onChange(this.props.options[value]);
        });
    }

    render(){
        const { options } = this.props;
        return(
            <Form>
                {
                    options.map( (option, id) => [
                        <Form.Field key={option.id}>
                            <Radio
                                label={ option.text }
                                name='radioGroup'
                                value={id}
                                onChange={this.handleChange}
                                key = {option.id}/>
                        </Form.Field>
                    ])
                }
            </Form>
        );
    }
}