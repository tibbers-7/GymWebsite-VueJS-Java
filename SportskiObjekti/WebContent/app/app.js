
const Header = { template: '<header></header>'}
const SportsObjects = { template: '<sports-objects></sports-objects>'}
const StartPage = { template: '<start-page></start-page>' }
const RegisterPage = { template: '<register-page></register-page>' }
const LoginPage = { template: '<login-page></login-page>' }
const CustomerStartPage = { template: '<customer-SP></customer-SP>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
	    { path: '/',name:'start', component: StartPage },
	    { path: '/h',name:'header', component: Header},
	    { path: '/rp',name:'register', component: RegisterPage },
	    { path: '/lp',name:'login', component: LoginPage },
	    { path: '/csp',name:'customerstart', component: CustomerStartPage },
	    { path: '/so',name:'sports', component: SportsObjects }
	    
	  ]
});


var app = new Vue({
	router,
	el: '#startPage'
});

