import react, {Component} from 'react';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

import Order from './Order';
import axioss from './Axioss';

class App extends Component {

	constructor(props) {
		super(props);
		this.state = {
		    pizzas: [],
		    pizzaSizes: [],
		    ingredients: [],
		    promotions: [],
		    discountPercent: 0,
		    orderMessage: "",
		    orderPrice: 0.00
		    };
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/pizzaSizes'}).done(response => {
			this.setState({pizzaSizes: response.entity._embedded.pizzaSizes});
    		this.calculatePrice();
    		console.log("pizzaSizes loaded");
		});

		client({method: 'GET', path: '/api/ingredients'}).done(response => {
		    this.setState({ingredients: response.entity._embedded.ingredients});
    		this.calculatePrice();

		});

		client({method: 'GET', path: '/api/promotions'}).done(response => {
		    this.setState({promotions: response.entity._embedded.promotions});
		});

		this.calculatePrice();

	}

    calculatePrice = (event) => {

        console.log("calculatePrice: " + event);

        let orderPrice = 0.00;

        this.state.pizzaSizes.map(pizzaSize => {
            let size = pizzaSize.pizzaSize.toLowerCase() + "pizza";
            let elm = document.getElementById(size);
            if (elm.checked == true) {
                orderPrice = pizzaSize.price;
            }
        });

        this.state.ingredients.map(ingredient => {
            let id = "ingredient" + ingredient.name.toLowerCase();
            let elm = document.getElementById(id);
            let checked = elm.checked;
            if (checked) {
                console.log("Adding " + ingredient.price + " for " + ingredient.name);
                orderPrice += ingredient.price;
            }

        });


        this.setState({orderPrice: orderPrice});

    }

    getPizzaSizeInfo = (size) => {

        console.log("getPizzaSizeInfo: " + JSON.stringify(this.state.pizzaSizes));

        let pizzaSizeInfo = this.state.pizzaSizes[0];

        this.state.pizzaSizes.map ( pizzaSize => {

            console.log("pizzaSize: " + pizzaSize.pizzaSize)

            if (pizzaSize.pizzaSize == size) {
                console.log("Found: " + size);
                pizzaSizeInfo = pizzaSize;
            }

        });

        return pizzaSizeInfo;

    }

    getPromo = () => {

        let found = false;
        let promoCode = document.getElementById("promocode").value;
        if (promoCode != "") {
            this.state.promotions.map( promotion => {
                if (promotion.promotionCode == promoCode) {
                    this.setState({discountPercent: promotion.discountPercent});
                    this.setState({orderMessage: 'Promo code is valid.'});
                    this.calculatePrice();
                    found = true;
                    return;
                }
            });
        } else {
            this.setState({orderMessage: 'Please enter a promo code.'})
            return;
        }
        if (!found) {
            this.setState({orderMessage: 'Promo code not found.'})
        }

    }

    placeOrder = () => {

        this.setState({orderMessage: ""});
        let orderPrice = 0.00;

        let pizzasize = 'UNKNOWN';
        let cheese = this.getIngredient("cheese");
        let pepperoni = this.getIngredient("pepperoni");
        let sausage = this.getIngredient("sausage");
        let olives = this.getIngredient("olives");
        let mushrooms = this.getIngredient("mushrooms");

        let name = document.getElementById("customername").value;
        let address = document.getElementById("customeraddress").value;
        let phonenumber = document.getElementById("customernumber").value;

        this.state.pizzaSizes.map(pizzaSize => {
            let size = pizzaSize.pizzaSize.toLowerCase() + "pizza";
            let elm = document.getElementById(size);
            if (elm.checked == true) {
                pizzasize = elm.value;
                orderPrice = pizzaSize.price;
            }
        });

        if (name == "" || address == "" || phonenumber == "") {
            this.setState({orderMessage: "Please provide your name, address, and phone number"});
        } else if (cheese == false && pepperoni == false && sausage == false && olives == false && mushrooms == false) {
            this.setState({orderMessage: "Please select one or more ingredients for your pizza."});
        } else {
            this.state.ingredients.map(ingredient => {
                console.log("An ingredient: " + ingredient.name + " - " + ingredient.price.toFixed(2));
            });
            this.setState({orderPrice: orderPrice});

            axioss.post('/neworder', null, {params: {name, address, phonenumber,
                                        pizzasize, cheese, pepperoni, sausage, olives, mushrooms }})
                .then(response => {
                    console.log(JSON.stringify(response));
                    this.setState({orderMessage: response.data.message});
                    this.resetForm();
                }).catch(error => {
                    console.log(error);
                    this.setState({orderMessage: error});
            });

        }

    }


    getIngredient = (ingredient) => {

        let ingredientId = "ingredient" + ingredient;
        let checked = document.getElementById(ingredientId).checked;
        return checked;
    }

    getIngredientPrice = (ingredient) => {

        let ingredientId = "ingredient" + ingredient;
        let checked = document.getElementById(ingredientId).checked;
        return checked;
    }



    resetForm = () => {

        ["customername", "customeraddress", "customernumber"].map(id => {
            document.getElementById(id).value = "";
        });

        this.state.ingredients.map( ingredient => {
            document.getElementById("ingredient" + ingredient.name.toLowerCase()).checked = false;
        });

        document.getElementById("promocode").value = "";

        this.setState({discountPercent: 0});

    }



	render() {

	    let discountAmount = (this.state.orderPrice * this.state.discountPercent) / 100;
	    let discountPrice = this.state.orderPrice - discountAmount;
	    let smallPizzaPrice = 10.00
  	    let mediumPizzaPrice = 10.00
	    let largePizzaPrice = 10.00

        let smallPizzaInfo = this.getPizzaSizeInfo("SMALL");
        let mediumPizzaInfo = this.getPizzaSizeInfo("MEDIUM");
        let largePizzaInfo = this.getPizzaSizeInfo("LARGE");

		return (
		<div>
			<Order
			    ingredients={this.state.ingredients}
			    resetForm={this.resetForm.bind(this)}
			    placeOrder={this.placeOrder.bind(this)}
			    orderMessage={this.state.orderMessage}
			    orderPrice={discountPrice}
			    getPromo={this.getPromo.bind(this)}
			    discountPercent={this.state.discountPercent}
			    calculatePrice={this.calculatePrice.bind(this)}
   			    smallPizzaPrice={smallPizzaPrice}
   			    mediumPizzaPrice={mediumPizzaPrice}
   			    largePizzaPrice={largePizzaPrice}
   			    smallPizzaInfo={smallPizzaInfo}
   			    mediumPizzaInfo={mediumPizzaInfo}
   			    largePizzaInfo={largePizzaInfo}

			    />
		</div>
		)
	}
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)

