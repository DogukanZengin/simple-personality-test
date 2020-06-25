import React from 'react';
import { Form, Segment } from 'semantic-ui-react'
import Slider from 'react-rangeslider'
import 'react-rangeslider/lib/index.css';


export default class NumberRangeQuestion extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            value: props.range.from
        };
    }
    componentWillMount(){
        this.handleChange = this.handleChange.bind(this);
    }
    
    handleChange(value){
        this.setState({ value }, ()=>{
            this.props.onChange(value);
        });
    }

    render(){
        const { range } = this.props;

        return(
            <Form>
                <Slider
                    value={this.state.value}
                    min={range.min}
                    max={range.max}
                    onChange={this.handleChange}
                />
                <Segment basic textAlign={'center'}>
                    <b>{this.state.value}</b>
                </Segment>
            </Form>
        );
    }
}