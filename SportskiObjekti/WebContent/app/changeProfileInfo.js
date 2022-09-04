Vue.component("info-change", {
	data: function() {
		return{
		user: null,
		title: "Promena podataka naloga",
		text: "",
		error: '',
		newUser:null //gender namestiti u template
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
                        <input class="credential_inputs" type="text" placeholder="{{user.name}}" v-model = "newUser.name" >
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Prezime:</td>
                </tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="text" placeholder="{{user.last_name}}" v-model = "newUser.last_name">
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Datum rođenja:</td>
                </tr>
				<tr>
					<td align="center">
                        <input class="credential_inputs" type="date" placeholder="{{user.birthDate}}" v-model = "newUser.birthDate">
                    </td>
				</tr>
                <tr>
                    <td class="credential_labels" align="center">Pol:</td>
                </tr>
				<tr>
					<td style="line-height: 2px ;">
                        <div style="margin-left:30%">
                        
                        <input type="radio" name="gender"  value="female" v-model = "gender"/>
                        <p style="color:white" style="margin-top: -5%;">Ženski</p>
                        
                        <input  type="radio" name="gender"  value="male" v-model = "gender"/>
                        <p style="color: white;">Muški</p>
                        </div>
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
         .get('rest/users/activeUser')
         .then(response => { 
			this.user = response.data;
			});
		newUser=user;
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		goBack: function(){
			//  Zavisi koji je tip korisnika
			//router.push(`/`);
		},
		changeInfo: function(){
			
		},
	}
		
		
	
});
