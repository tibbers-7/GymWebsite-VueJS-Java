const StartPage = { template: '<start-page></start-page>' }
const RegisterPage = { template: '<register-page></register-page>' }
const LoginPage = { template: '<login-page></login-page>' }
const CustomerStartPage = { template: '<customer-SP></customer-SP>'}
const TrainingsCustomer = {template: '<trainings-customer></trainings-customer>'}
const MembershipsCustomer = {template: '<memberships-customer></memberships-customer>'}
const TrainerStartPage = { template: '<trainer-SP></trainer-SP>'}
const TrainingsTrainer = {template: '<trainings-trainer></trainings-trainer>'}
const ManagerStartPage = {template: '<manager-SP></manager-SP>'}
const ObjectViewManager = {template:'<manager-object></manager-object>'}
const UpdateObject = {template:'<object-update></object-update>' }

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
	    { path: '/msp', component: ManagerStartPage },
	    { path: '/ov', component: ObjectViewManager },
	    { path: '/uo', component: UpdateObject }
	    
	  ]
});


var app = new Vue({
	router,
	el: '#startPage'
});

