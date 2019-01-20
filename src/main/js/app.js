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
		    pizzaOrders: [],
		    discountPercent: 0,
		    orderMessage: "",
		    orderPrice: 0.00,
		    discountPrice: 0.00,
		    promoCode: "",
		    admin: false
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

	loadIngredients = () => {
		client({method: 'GET', path: '/api/ingredients'}).done(response => {
		    this.setState({ingredients: response.entity._embedded.ingredients});
		});

	}

	loadPizzaOrders = () => {
		client({method: 'GET', path: '/api/pizzas'}).done(response => {
		    this.setState({pizzaOrders: response.entity._embedded.pizzas});
		});

	}


    calculatePrice = (event) => {

        let orderPrice = 0.00;
        this.state.pizzaSizes.map(pizzaSize => {
            let size = pizzaSize.size.toLowerCase() + "pizza";
            let elm = document.getElementById(size);
            if (elm && elm.checked == true) {
                orderPrice = pizzaSize.price;
            }
        });

        this.state.ingredients.map(ingredient => {
            let id = "ingredient" + ingredient.name.toLowerCase();
            let elm = document.getElementById(id);
            if (elm) {
                let checked = elm.checked;
                if (checked) {
                    orderPrice += ingredient.price;
                }
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
            if (pizzaSize.size == size) {
                pizzaSizeInfo = pizzaSize;
            }
        });

        return pizzaSizeInfo;
    }

    toggleAdmin = () => {
        this.setState({admin: !this.state.admin, orderMessage: ""}, this.loadIngredients());
    }

    fieldChange = () => {
    }

    updateIngredients = () => {

        this.state.ingredients.map(ingredient => {
            this.updateIngredient(ingredient.name.toLowerCase());
        });

        let newIngredientName = document.getElementById("ingredientnew").value;
        if (newIngredientName != "new") {
            this.updateIngredient("new");
        }

        this.loadIngredients();
        this.setState({orderMessage: "Ingredients have been updated."});

    }

    updateIngredient = (ingredient) => {

        let ingredientId = "ingredient" + ingredient;
        let nameId = ingredientId + "name";
        let inventoryId = ingredientId + "inventory";
        let priceId = ingredientId + "price";
        let ingredientName = document.getElementById(ingredientId).value;
        let ingredientInventory = document.getElementById(inventoryId).value;
        let ingredientPrice = document.getElementById(priceId).value;

        let data = {
            name: ingredientName,
            price: ingredientPrice,
            inventory: ingredientInventory
        }

        axioss.put('/updateingredient', data)
            .then(response => {
            }).catch(error => {
                console.log(error);
            });

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
        this.setState({discountPercent: newDiscount, promoCode: promoCode, orderMessage: message},
            this.calculatePrice);
    }

    placeOrder = () => {

        this.setState({orderMessage: ""});
        let orderPrice = 0.00;

        let pizzasize = 'UNKNOWN';
        let name = document.getElementById("customername").value;
        let address = document.getElementById("customeraddress").value;
        let phonenumber = document.getElementById("customernumber").value;

        this.state.pizzaSizes.map(pizzaSize => {
            let size = pizzaSize.size.toLowerCase() + "pizza";
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

            console.log("promoCode: " + this.state.promoCode);
            console.log("discountPercent: " + this.state.discountPercent);


            const data = {
                name: name,
                address: address,
                phonenumber: phonenumber,
                pizzasize: pizzasize,
                promocode: this.state.promoCode,
                discountpercent: this.state.discountPercent,
                ingredients: ingredients,
                price: this.state.discountPrice
            };

            axioss.post('/bodyorder', data)
                .then(response => {
                    this.setState({orderMessage: response.data.message});
                    this.resetForm();
                    this.loadPizzaOrders();
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

        document.getElementById("largepizza").checked = true;
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
   			    admin={this.state.admin}
   			    toggleAdmin={this.toggleAdmin.bind(this)}
   			    updateIngredients={this.updateIngredients.bind(this)}
   			    fieldChange={this.fieldChange.bind(this)}
   			    pizzaOrders={this.state.pizzaOrders}
			    />
		</div>
		)
	}
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)

