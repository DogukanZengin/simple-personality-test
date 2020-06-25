import React from 'react'
import PropTypes from 'prop-types'
import { Header, Segment, Responsive } from 'semantic-ui-react'
import SingleChoice from './SingleChoiceQuestion'
import NumberRange from './NumberRangeQuestion'

export default class Question extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            answered: false
        };
    }

    onChange(value){
        this.setState({ answered: true });
        this.props.onChange(value);
    }

    render(){
        let content;
        const { question, options, type, range, visible, markRed } = this.props;
        const { answered } = this.state;
        switch(type){
            case 'SINGLE_CHOICE':
            case 'SINGLE_CHOICE_CONDITIONAL':
                content = <SingleChoice options={options} onChange={this.onChange.bind(this)} />
                break;
            case 'NUMBER_RANGE':
                content = <NumberRange range={range} onChange={this.onChange.bind(this)} />
                break;
            default:
                break;
        }

        return(
            <div className={"question" + (visible ? '-visible' : '-invisible')} style={{ display: (visible ? 'block' : 'none') }}>
                <Header as='h4' attached='top' textAlign={'left'}>
                    { question }
                </Header>
                <Responsive as={Segment} attached='bottom' textAlign={'left'} color={ (markRed && !answered ? 'red' : null) } >
                    { content }
                </Responsive>
                <br />
            </div>
        );
    }
}

Question.propTypes = {
    markRed: PropTypes.bool
}


