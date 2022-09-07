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
const ObjectGeneral = {template:'<view-object></view-object>'}
const UpdateObject = {template:'<object-update></object-update>' }
const Profile = {template:'<profile></profile>' }
const InfoChange = {template:'<info-change></info-change>' }
const PasswordChange = {template:'<password-change></password-change>' }
const AdminStartPage = { template: '<admin-SP></admin-SP>'}
const UsersAdmin = {template: '<users-admin></users-admin>'}
const AddObjectAdmin = {template: '<add-object></add-object>'}
const ManagerTrainings = {template: '<trainings-manager></trainings-manager>'}

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
	    { path: '/mt', component: ManagerTrainings },
	    { path: '/msp', component: ManagerStartPage },
	    { path: '/ovm', component: ObjectViewManager },
	    { path: '/ovg', component: ObjectGeneral },
	    { path: '/uo', component: UpdateObject },
	    { path: '/pro', component: Profile },
	    { path: '/ic', component: InfoChange },
	    { path: '/pc', component: PasswordChange },
	    { path: '/asp', component: AdminStartPage },
	    { path: '/ua', component: UsersAdmin },
	    { path: '/aoa', component: AddObjectAdmin },


	   
	  ]
});


var app = new Vue({
	router,
	el: '#startPage'
});

