import VueRouter from 'vue-router'
const StartPage = { template: '<start-page></start-page>' }
const RegisterPage = { template: '<register-page></register-page>' }
const LoginPage = { template: '<login-page></login-page>' }
const CustomerStartPage = { template: '<customer-SP></customer-SP>'}
const TrainingsCustomer = {template: '<trainings-customer'}
const MembershipsCustomer = {template: '<memberships-customer'}
const TrainerStartPage = { template: '<trainer-SP></trainer-SP>'}
const TrainingsTrainer = {template: '<trainings-trainer'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: StartPage },
	    { path: '/rp', component: RegisterPage },
	    { path: '/lp', component: LoginPage },
	    { path: '/csp', component: CustomerStartPage },
	    { path: '/ct', component: TrainingsCustomer },
	    { path: '/cm', component: MembershipsCustomer },
	    { path: '/tsp', component: TrainerStartPage },
	    { path: '/tt', component: TrainingsTrainer },
	    
	  ]
});


var app = new Vue({
	router,
	el: '#startPage'
});

