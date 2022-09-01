
const Header = { template: '<header></header>'}
const SportsObjects = { template: '<sports-objects></sports-objects>'}
const StartPage = { template: '<start-page></start-page>' }
const RegisterPage = { template: '<register-page></register-page>' }
const LoginPage = { template: '<login-page></login-page>' }
const CustomerStartPage = { template: '<customer-SP></customer-SP>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/', component: StartPage },
	    { path: '/h', component: Header},
	    { path: '/rp', component: RegisterPage },
	    { path: '/lp', component: LoginPage },
	    { path: '/csp', component: CustomerStartPage },
	    { path: '/so', component: SportsObjects }
	    
	  ]
});


var app = new Vue({
	router,
	el: '#sports'
});

