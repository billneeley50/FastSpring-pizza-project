import React from 'react';

const PizzaOrders = (props) => {

    return (

        <div className = "AllDivs PizzaOrders">

            <h3>Orders</h3>

            <table>
            <tbody>

                <tr>
                    <th>Date</th>
                    <th>Name</th>
                    <th>Promo</th>
                    <th>Discount</th>
                    <th>Price</th>
                    <th>Toppings</th>
                </tr>

                {props.pizzaOrders.map(pizzaorder => (

                    <tr>
                    <td>{pizzaorder.orderDate}</td>
                    <td>{pizzaorder.customerName}</td>
                    <td>{pizzaorder.promoCode}</td>
                    <td>{pizzaorder.discountPercent}%</td>
                    <td>${pizzaorder.totalPrice.toFixed(2)}</td>
                    <td>
                        <select>
                            {pizzaorder.ingredientList.map(ingredient => (
                                <option value={ingredient.name}>{ingredient.name}</option>
                            ))}
                        </select>
                    </td>
                    </tr>

                ))}

            </tbody>
            </table>

        </div>

    )

};


export default PizzaOrders;