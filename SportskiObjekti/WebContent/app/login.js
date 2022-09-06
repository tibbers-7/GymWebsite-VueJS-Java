Vue.component("login-page", {
	data: function() {
		return{
		title: "login",
		user : { username: null, password: null },
		error: '',
		mode: '',
		
	}
	},
	 template: ` 
    	<div class="bodyStyle">
			<div class="header_container">
			        <div class="Img">
			            <img src="images/logo.png"style="height: 115px; width: 115px;"/>
			        </div>
			        <div class="Name"><h1> Fitness </h1></div>
			        <div class="Login"><button class="Button"   v-on:click="goHome()" >Početna strana</button></div>
			        <div class="Register"><button class="Button" v-on:click="register()" >Registrujte se</button></div>

			</div>
			        <form id="formlogin">
						<table class="login_container">
			                <tr><td class="credential_labels" align="center">Korisničko ime</td></tr>
							<tr>
								<td align="center">
			                        <input class="credential_inputs"  type="text" v-model = "user.username" name="username" >
			                    </td>
							</tr>
			                <tr>
			                    <td class="credential_labels" align="center">Šifra</td>
			                </tr>
							<tr>
								<td align="center">
			                        <input class="credential_inputs" type="text" name="password" v-model = "user.password">
			                    </td>
							</tr>
							<tr>
								<td align="center">
			                        <button class="Button"  v-on:click = "loginUser">Log In</button>
								</td>
							</tr>
						</table>
						</form>
			        
		</div>  
    	`,
	mounted() {
				},
	methods: {
		loginUser: function () {
				axios.post('rest/user/login', this.user)
					.then(response => {
						toast(response);
						if(response.code!=400){
							switch(this.us)
						}
					})
		},
		register : function() {
    		router.push(`/rp`);
    	},
    	
    	goHome: function () {
			router.push('/');
			}
	}
});