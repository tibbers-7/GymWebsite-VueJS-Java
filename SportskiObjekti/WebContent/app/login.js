Vue.component("login-page", {
		title: "Login",
		mode: "LOGIN",
		user : { username: null, password: null },
		error: '',
		username: "",
		password: "",
	data: function() {
		
	},
	 template: ` 
    	<div>
			
			        <form id="formlogin" v-bind:hidden="mode!='LOGIN'">
						<table class="login_container">
			                <tr><td class="credential_labels" align="center">Korisničko ime</td></tr>
							<tr>
								<td align="center">
			                        <input class="credential_inputs" v-bind:disabled="mode!='LOGIN'" type="text" v-model = "product.username" name="username" >
			                    </td>
							</tr>
			                <tr>
			                    <td class="credential_labels" align="center">Šifra</td>
			                </tr>
							<tr>
								<td align="center">
			                        <input class="credential_inputs" type="text" name="password" v-model = "product.password">
			                    </td>
							</tr>
							<tr>
								<td align="center">
			                        <button class="Button"  v-on:click = "loginUser">
								        <a class="active" href="sportsObjects.html">Log In</a>
								    </button>
								</td>
							</tr>
						</table>
						</form>
			        
		</div>  
    	`,
	mounted() {

		axios.get('rest/user/users')
			.then(response => (this.users = response.data)) 
				},
	methods: {
		loginUser: function () {
				axios.post('rest/user/login', this.user)
					.then(response => {
						toast('Uspešno logovanje!')
						if(response.code!=400)
						this.mode='LOGGED'
						router.push("/csp");
					})
		}
	}
});