import React from 'react';

const Ingredient = ( props ) => {

    let ingredientId = "ingredient" + props.name;
    let ingredientIdId = ingredientId + "id";
    let nameId = ingredientId + "name";
    let inventoryId = ingredientId + "inventory";
    let priceId = ingredientId + "price";

    console.log("ingredient: " + props.name)

    if (props.admin) {
        return (

            <div className="Ingredient">

                <input type="hidden" id={ingredientIdId} value={props.fullIngredient.ingredientId} />
                <input className="IngredientField" type="text" id={ingredientId} defaultValue={props.fullIngredient.name} />
                <input className="IngredientField" type="text" id={inventoryId} defaultValue={props.fullIngredient.inventory} />
                <input className="IngredientField" type="text" id={priceId}     defaultValue={props.fullIngredient.price.toFixed(2)} />

            </div>
        );
    } else {
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

    }

};

export default Ingredient;