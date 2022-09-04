Vue.component("password-change", {
	data: function() {
		return{
		user: null,
		title: "Promena lozinke",
		text: "",
		error: '',
		}
	},
	 template: ` 
    	<div class="bodyStyle">
    	
    	  <div class="header_container">
        <div class="Img">
            <img src="images/logo.png"style="height: 115px; width: 115px;"/>
        </div>
        <div class="Name"><h1> Fitness </h1></div>
        <div class="Register"><button class="Button"  v-on:click="logOut()">Odjavite se</button></div>

</div>


<div class="changePass_grid">
    <div class="passContainer_grid">
        <form >
			<table class="login_container" >
                <tr><td class="credential_labels" align="center">Stara šifra:</td></tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="password" v-model = "oldPassword" >
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Nova šifra:</td>
                </tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="password" v-model = "newPassword">
                    </td>
				</tr>
                <tr><td ><br></td></tr>
				<tr >
					<td align="center">
                        <button class="Button"  v-on:click = "changePass()">Promeni</button>
					</td>
				</tr>
                <tr><td ><br></td></tr>
			</table>
			</form>
    </div>
    <div class="passBackBtn_grid">
        <button style="position:relative;margin-top:10%;left:500px;border:none;background: transparent;" v-on:click="goBack()"><img src="back.png" class="back_img"></img></button>
    </div>
  </div>

    </div>
    	`,
	mounted() {
		axios
         .get('rest/users/activeUser')
         .then(response => { 
			this.user = response.data;
			});
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		goBack: function(){
			//  Zavisi koji je tip korisnika
			//router.push(`/`);
		},
		changePass: function(){
			
		},
	}
		
		
	
});
