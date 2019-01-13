import React from 'react';

import classes from './Ingredients.css';
import Ingredient from './Ingredient';

const Ingredients = (props) => (
    <div className="Ingredients">

        <h3>Select one or more ingredients</h3>

        {props.ingredients.map(ingredient => (
            <Ingredient
                name={ingredient.name.toLowerCase()}
                value={ingredient.name.toLowerCase()}
                price={ingredient.price}
                key={ingredient.name.toLowerCase()}
            />

        ))}

    </div>

);

export default Ingredients;
