Vue.component("header", {
	data: {
		title: "Header",
		error: '',
	},
	 template: ` 
    	<div>
			    <div class="header_container">
			        <div class="Img">
			            <img src="logo.png"style="height: 115px; width: 115px;"/>
			        </div>
			        <div class="Name"><h1> Fitness </h1></div>
			        <div class="Login"><button class="Button"   v-on:click = "login" v-bind:hidden="mode=='LOGGED'" >Prijavite se</button></div>
			        <div class="Register"><button class="Button"  v-on:click = "register" v-bind:hidden="mode=='LOGGED'" >Registrujte se</button></div>
			        <div class="Register"><button class="Button"  v-on:click = "logout" v-bind:hidden="mode!=='LOGGED'" >Odjavite se</button></div>

			      </div>
			
			        
    	`,
	mounted() {

				},
	methods: {
		login: function () {
				router.push("/lp");
		},
		
		register: function () {
			router.push("/rp");
		},
		
		logout: function() {
			router.push("/");
		}
		
		
	}
});