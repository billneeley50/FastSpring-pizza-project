import React from 'react';

import Ingredients from './Ingredients';
import PizzaSizes from './PizzaSizes';
import OrderButtons from './OrderButtons';
import CustomerInfo from './CustomerInfo';
import MessageBox from './MessageBox';

const PizzaOptions = ( props ) => {

    return (
        <div className="AllDivs PizzaOptions row">

                <PizzaSizes
                    calculatePrice={props.calculatePrice}
                    smallPizzaPrice={props.smallPizzaPrice}
                    mediumPizzaPrice={props.mediumPizzaPrice}
                    largePizzaPrice={props.largePizzaPrice}
                    smallPizzaInfo={props.smallPizzaInfo}
                    mediumPizzaInfo={props.mediumPizzaInfo}
                    largePizzaInfo={props.largePizzaInfo}

                />
                <Ingredients
                    ingredients={props.ingredients}
                    calculatePrice={props.calculatePrice}
                />

        </div>
    );
};

export default PizzaOptions;