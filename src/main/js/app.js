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
		    orderPrice: 0.00,
		    discountPrice: 0.00
		    };
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/pizzaSizes'}).done(response => {
			this.setState({pizzaSizes: response.entity._embedded.pizzaSizes});
    		this.calculatePrice();
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
                orderPrice += ingredient.price;
            }

        });

   	    let discountAmount = (orderPrice * this.state.discountPercent) / 100;
        let discountPrice =  orderPrice - discountAmount;
        this.setState({orderPrice: orderPrice});
        this.setState({discountPrice: discountPrice});
    }

    getPizzaSizeInfo = (size) => {

        let pizzaSizeInfo = this.state.pizzaSizes[0];
        this.state.pizzaSizes.map ( pizzaSize => {
            if (pizzaSize.pizzaSize == size) {
                pizzaSizeInfo = pizzaSize;
            }
        });

        return pizzaSizeInfo;
    }


    getPromo = () => {

        let message = "";
        let newDiscount = 0.
        let promoCode = document.getElementById("promocode").value;
        if (promoCode != "") {
            let found = false;
            this.state.promotions.map( promotion => {
                if (promotion.promotionCode == promoCode) {
                    newDiscount = promotion.discountPercent;
                    message = 'Promo code is valid.  You will receive a ' +
                               promotion.discountPercent + '% discount.'
                    found = true;
                    return;
                }
            });
            if (!found) {
                message = 'Promo code not found.';
            }
        } else {
            message = 'Please enter a promo code.';
        }
        this.setState({discountPercent: newDiscount});
        this.setState({orderMessage: message}, this.calculatePrice);
    }

    placeOrder = () => {

        this.setState({orderMessage: ""});
        let orderPrice = 0.00;

        let pizzasize = 'UNKNOWN';
        let name = document.getElementById("customername").value;
        let address = document.getElementById("customeraddress").value;
        let phonenumber = document.getElementById("customernumber").value;

        this.state.pizzaSizes.map(pizzaSize => {
            let size = pizzaSize.pizzaSize.toLowerCase() + "pizza";
            let elm = document.getElementById(size);
            if (elm.checked == true) {
                pizzasize = elm.value;
            }
        });

        let ingredients = [];
        this.state.ingredients.map(ingredient => {
            let checked = this.getIngredient(ingredient.name);
            if (checked) {
                ingredients.push(ingredient.name.toLowerCase());
            }
        });

        if (name == "" || address == "" || phonenumber == "") {
            this.setState({orderMessage: "Please provide your name, address, and phone number"});
        } else if (ingredients.length == 0) {
            this.setState({orderMessage: "Please select one or more ingredients for your pizza."});
        } else {

            const data = {
                name: name,
                address: address,
                phonenumber: phonenumber,
                pizzasize: pizzasize,
                ingredients: ingredients,
                price: this.state.discountPrice
            };

            axioss.post('/bodyorder', data)
                .then(response => {
                    console.log(response);
                    this.setState({orderMessage: response.data.message});
                }).catch(error => {
                    console.log(error);
                    this.setState({orderMessage: error});
                });
        }
    }


    getIngredient = (ingredient) => {
        let ingredientId = "ingredient" + ingredient.toLowerCase();
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
        this.setState({discountPrice: 0.00});
    }


	render() {

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
			    orderPrice={this.state.discountPrice}
			    getPromo={this.getPromo.bind(this)}
			    discountPercent={this.state.discountPercent}
			    calculatePrice={this.calculatePrice.bind(this)}
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

