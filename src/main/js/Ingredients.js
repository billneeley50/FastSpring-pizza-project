import React from 'react';

import Ingredient from './Ingredient';

const Ingredients = (props) => {

    if (props.admin) {

    let newingredient = {
        name: "new",
        price: 0.00,
        inventory: 0
    }
    return (
        <div className="AllDivs IngredientsUpdate col span-1-of-2" onDoubleClick={props.toggleAdmin}>

            <h3>Update ingredients</h3>

            {props.ingredients.map(ingredient => (
                <Ingredient
                    name={ingredient.name.toLowerCase()}
                    value={ingredient.name.toLowerCase()}
                    price={ingredient.price}
                    key={ingredient.name.toLowerCase()}
                    calculatePrice={props.calculatePrice}
                    admin={props.admin}
                    fullIngredient={ingredient}
                    fieldChange={props.fieldChange}
                />

            ))}

            <Ingredient
                name="new"
                value="newingredient"
                price="0.00"
                key="newingredient"
                admin={props.admin}
                fullIngredient={newingredient}
            />

            <br/><br/><input type="button" onClick={props.updateIngredients} value="Update"/>

            <p>{props.orderMessage}</p>



        </div>

        );
    } else {
    return (
        <div className="AllDivs Ingredients col span-1-of-2" onDoubleClick={props.toggleAdmin}>

            <h3>Select ingredients</h3>

            {props.ingredients.map(ingredient => (
                <Ingredient
                    name={ingredient.name.toLowerCase()}
                    value={ingredient.name.toLowerCase()}
                    price={ingredient.price}
                    key={ingredient.name.toLowerCase()}
                    calculatePrice={props.calculatePrice}
                    admin={props.admin}
                    fullIngredient={ingredient}
                    fieldChange={props.fieldChange}
                />

            ))}

        </div>

        );
    }


}

export default Ingredients;