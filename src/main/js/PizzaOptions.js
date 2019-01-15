import React from 'react';

import Ingredients from './Ingredients';
import PizzaSizes from './PizzaSizes';
import OrderButtons from './OrderButtons';
import CustomerInfo from './CustomerInfo';
import MessageBox from './MessageBox';

const PizzaOptions = ( props ) => {

    if (props.admin) {
    return (
        <div className="AllDivs PizzaOptions row" onDoubleClick={props.toggleAdmin}>

            <Ingredients
                ingredients={props.ingredients}
                calculatePrice={props.calculatePrice}
                admin={props.admin}
                toggleAdmin={props.toggleAdmin}
   			    updateIngredients={props.updateIngredients}
   			    fieldChange={props.fieldChange}
   			    orderMessage={props.orderMessage}
                />
        </div>
    );
    } else {
    return (
        <div className="AllDivs PizzaOptions row" onDoubleClick={props.toggleAdmin}>

            <PizzaSizes
                calculatePrice={props.calculatePrice}
                smallPizzaInfo={props.smallPizzaInfo}
                mediumPizzaInfo={props.mediumPizzaInfo}
                largePizzaInfo={props.largePizzaInfo}
                admin={props.admin}
                toggleAdmin={props.toggleAdmin}

            />
            <Ingredients
                ingredients={props.ingredients}
                calculatePrice={props.calculatePrice}
                admin={props.admin}
                toggleAdmin={props.toggleAdmin}
   			    orderMessage={props.orderMessage}
            />

        </div>
    );
    }

};

export default PizzaOptions;