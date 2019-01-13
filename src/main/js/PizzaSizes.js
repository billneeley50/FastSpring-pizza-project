import React from 'react';

const PizzaSizes = (props) => {

    let smallPizzaInfo = "";
    let mediumPizzaInfo = "";
    let largePizzaInfo = "";

    if (props.smallPizzaInfo) {
        smallPizzaInfo =
                props.smallPizzaInfo.width + '" - ' +
                "$" + props.smallPizzaInfo.price.toFixed(2);
    }
    if (props.mediumPizzaInfo) {
        mediumPizzaInfo =
                props.mediumPizzaInfo.width + '" - ' +
                "$" + props.mediumPizzaInfo.price.toFixed(2);
    }
    if (props.largePizzaInfo) {
        largePizzaInfo =
                props.largePizzaInfo.width + '" - ' +
                "$" + props.largePizzaInfo.price.toFixed(2);
    }


    return (

    <div className="AllDivs PizzaSizes col span-1-of-2">

        <h3>Select a Size</h3>

        <input
            className="PizzaSizeButton"
            type="radio"
            name="pizzasize"
            value="SMALL"
            key="SMALL"
            id="smallpizza"
            onChange={props.calculatePrice}
            />Small

            <span className="PizzaSizePrice">{smallPizzaInfo}</span>

        <br/>

        <input
            className="PizzaSizeButton"
            type="radio"
            name="pizzasize"
            value="MEDIUM"
            key="MEDIUM"
            id="mediumpizza"
            onChange={props.calculatePrice}
            />Medium

            <span className="PizzaSizePrice">{mediumPizzaInfo}</span>

        <br/>

        <input
            className="PizzaSizeButton"
            type="radio"
            name="pizzasize"
            value="LARGE"
            key="LARGE"
            id="largepizza"
            defaultChecked
            onChange={props.calculatePrice}
            />Large

            <span className="PizzaSizePrice">{largePizzaInfo}</span>

    </div>
    )

};

export default PizzaSizes;