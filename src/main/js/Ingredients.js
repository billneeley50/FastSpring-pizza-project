import React from 'react';

import classes from './Ingredients.css';
import Ingredient from './Ingredient';

const Ingredients = (props) => (
    <div className="AllDivs Ingredients col span-1-of-2">

        <h3>Select ingredients</h3>

        {props.ingredients.map(ingredient => (
            <Ingredient
                name={ingredient.name.toLowerCase()}
                value={ingredient.name.toLowerCase()}
                price={ingredient.price}
                key={ingredient.name.toLowerCase()}
                calculatePrice={props.calculatePrice}
            />

        ))}

    </div>

);

export default Ingredients;