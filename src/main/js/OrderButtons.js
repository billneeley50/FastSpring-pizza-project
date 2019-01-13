import React from 'react';


const OrderButtons = (props) => (

    <div className="AllDivs OrderButtons">

        <h3>Submit your order</h3>

        <br/>Enter Promo Code<input type="text" id="promocode"/>
        <input type="button" onClick={props.getPromo} value="Get Promo"/>

        <p>Total price:  ${props.orderPrice.toFixed(2)}</p>
        <p>Discount:  {props.discountPercent}%</p>

        <span className="CenterButtons">
        <input type="button" onClick={props.placeOrder} value="Order Pizza"/>
        <button type="reset" onClick={props.resetForm}>Reset</button>
        </span>

        <p>{props.orderMessage}</p>

    </div>

);

export default OrderButtons;