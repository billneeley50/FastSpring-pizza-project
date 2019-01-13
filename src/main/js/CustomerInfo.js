import React from 'react';


const CustomerInfo = ( props ) => {

    return (
        <div className="AllDivs CustomerInfo">

                <h3>Where to make the delivery</h3>
                <ul className="customer-info-list">
                    <li><span className="InfoLabel">Your name:</span><input className="CustomerInput" type="text" name="name" id="customername"/></li>
                    <li><span className="InfoLabel">Your phone number:</span><input className="CustomerInput" type="text" name="phonenumber" id="customernumber"/></li>
                    <li><span className="InfoLabel">Your address:</span><input className="CustomerInput" type="text" name="address" id="customeraddress"/></li>
                </ul>
        </div>
    );
};

export default CustomerInfo;