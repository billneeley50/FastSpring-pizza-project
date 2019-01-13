import React from 'react';

import PizzaOptions from './PizzaOptions';
import Ingredients from './Ingredients';
import PizzaSizes from './PizzaSizes';
import OrderButtons from './OrderButtons';
import CustomerInfo from './CustomerInfo';
import MessageBox from './MessageBox';

const Order = ( props ) => {

    return (
        <div className="AllDivs Order">

            <h1>Place your pizza order</h1>

            <form id="orderform" action="/order" method="post">

                <PizzaOptions
                    calculatePrice={props.calculatePrice}
                    smallPizzaPrice={props.smallPizzaPrice}
                    mediumPizzaPrice={props.mediumPizzaPrice}
                    largePizzaPrice={props.largePizzaPrice}
                    ingredients={props.ingredients}
                    calculatePrice={props.calculatePrice}
                    smallPizzaInfo={props.smallPizzaInfo}
                    mediumPizzaInfo={props.mediumPizzaInfo}
                    largePizzaInfo={props.largePizzaInfo}

                />
                <CustomerInfo/>
                <OrderButtons
                    resetForm={props.resetForm}
                    placeOrder={props.placeOrder}
                    orderMessage={props.orderMessage}
                    orderPrice={props.orderPrice}
                    getPromo={props.getPromo}
       			    discountPercent={props.discountPercent}
       			    calculatePrice={props.calculatePrice}
                />

            </form>
        </div>
    );
};

export default Order;