Vue.component("info-change", {
	data: function() {
		return{
		user: null,
		title: "Promena podataka naloga",
		text: "",
		error: '',
		newUser:null,
		genderM:false,
		genderF:false
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
                        <input class="credential_inputs" type="text" v-model = "newUser.name" v-bind:placeholder="user.name" >
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Prezime:</td>
                </tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="text" v-model = "newUser.last_name" v-bind:placeholder="user.last_name">
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Datum rođenja:</td>
                </tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="date" v-model = "newUser.birthDate" v-bind:placeholder="user.birthDate">
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Pol:</td>
                </tr>
				<tr>
					<td style="line-height: 2px ;">
                        <input type="radio" name="gender" id="f" value="FEMALE" v-model="genderM">
	                    <label for="f" style="color:white">Ženski</label>
	                    <input type="radio" name="gender" id="m" value="MALE" v-model="genderF">
	                    <label for="m" style="color: white;">Muški</label>
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
        <button style="position:relative;margin-top:10%;left:500px;border:none;background: transparent;" v-on:click="goBack()"><img src="back.png" class="back_img"></img></button>
    </div>
  </div>
    </div>
    	`,
	mounted() {
		axios
         .get('rest/user/activeUser')
         .then(response => (this.user = response.data));
         this.newUser=this.user;
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		goBack: function(){
			switch(this.user.userType){
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
			
			
			if(this.newUser.name==null | this.newUser.last_name==null | this.newUser.birthDate==null ) toast("Niste uneli sve potrebne podatke!");
			else if(this.genderM==false && this.genderF==false)  toast("Molimo vas da odaberete pol!");
			else {
				if(this.genderM) this.newUser.gender="MALE"; else this.newUser.gender="FEMALE";
				 axios.post('rest/user/editInfo', this.newUser).then(response => {
						toast(response.data);
						goBack();
					});
				
			}
		},
	}
		
		
	
});
