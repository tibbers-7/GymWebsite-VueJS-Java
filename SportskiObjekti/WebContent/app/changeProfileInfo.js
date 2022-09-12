Vue.component("info-change", {
	data: function() {
		return{
		user: null,
		title: "Promena podataka naloga",
		text: "",
		error: '',
		name:"",
		last_name:"",
		birthDate:"",
		gender:""
		 //gender namestiti u template
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
                <tr><td class="credential_labels" align="center">Ime:</td></tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="text" v-model = "name" v-bind:placeholder="user.name" >
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Prezime:</td>
                </tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="text" v-model = "last_name" v-bind:placeholder="user.last_name">
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Datum rođenja:</td>
                </tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="text" v-model = "birthDate" v-bind:placeholder="user.birthDate">
                    </td>
				</tr>
                <tr><td ><br></td></tr>
				<tr >
					<td align="center">
                        <button class="Button"  v-on:click = "changeInfo()">Promeni</button>
					</td>
				</tr>
                <tr><td ><br></td></tr>
			</table>
			</form>
    </div>
    <div class="passBackBtn_grid">
        <button style="position:relative;margin-top:10%;left:500px;border:none;background: transparent;" v-on:click="goBack()"><img src="back.png" class="back_img" v-on:click="goBack()"></img></button>
    </div>
  </div>
    </div>
    	`,
	mounted() {
		axios
         .get('rest/user/activeUser/')
         .then(response => { 
			this.user = response.data;
			})
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		goBack: function(){
			switch(user.userType){
								case "CUSTOMER":
									 router.push(`/csp`);
									 break;
								case "MANAGER": 
									router.push(`/msp`);
									break;
								case "ADMIN": 
									router.push(`/asp`);
									break;
								case "TRAINER": 
									router.push(`/tsp`);
									break;
									}
		},
		changeInfo: function(){
			axios.post('rest/user/editInfo', {
				 username: this.username,
			 	 password: this.password,
			 	 name: this.name,
			 	 last_name: this.last_name,
			 	 birthDate: this.birthDate}).then(response => {
						toast(response.data);
						goBack();
					})
		},
	}
		
		
	
});
