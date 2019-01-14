# Project Title

FastSpring pizza project by Bill Neeley

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Use git clone to pull the source code.
    "git clone https://github.com/billneeley50/FastSpring-pizza-project.git"

Run mvn to install dependencies and run the app
    "mvn spring-boot:run"

The first time you run the maven command it will download whatever dependencies are needed.  This includes
npm and spring boot.  It will then build both the React and Java code.

The server will use port 8080;


### Using the app

Access the app at {host}:8080

There are three sections to the web page.
1.  The pizza size and ingredient options.
    The size defaults to Large.  Select Small or Medium if desired.
    One or more ingredients must be selected.

2.  The user delivery info.
    Enter your name, address and phone number.  All three are required for delivery.

3.  The order placement info.
    Enter a promo code if you want a discount.
        Two promo codes exist.  "12345" gives a 10% discount.  "6789" gives a 20% discount.
    Click "Order Pizza" to place the order or click "Reset" to start over again.
        Note that the pizza price will be updated as options are selected/changed.

        If the "store" runs out of an ingredient, the order will not be accepted with a message
        indicating what ingredient we are out of.


Managing ingredients:

    The Ingredients can be managed by double clicking on the first section of the page
    (Size and Ingredients).  This will put up a list of the current ingredients; name, inventory,
    and price.  The last entry in the list ("new") can be used to create a new ingredient.  If "new" is not changed
    it will be ignored.





## App Internals

The app is built using Spring (and Spring boot) for the server and React for the UI.
The UI uses Ajax calls perform the order placement and ingredient updating.


