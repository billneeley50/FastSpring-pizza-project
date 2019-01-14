import React from 'react';

const Ingredient = ( props ) => {

    let ingredientId = "ingredient" + props.name;

    return (
        <div className="Ingredient">

            <input
                type="checkbox"
                id={ingredientId}
                name={props.name}
                key={ingredientId}
                onChange={props.calculatePrice}
                />
            {props.name} - ${props.price.toFixed(2)}

        </div>
    );
};

export default Ingredient;