Vue.component("register-page", {
	data: function() {
		return{
		title: "Registrovanje",
		mode: "REGISTER",
		user : { username: null, password: null, name: null, last_name: null, gender: null, birthDate: null },
		error: '',
		username: "",
		password: "",
		name: "",
		last_name: "", 
		genderMale:false,  
		genderFemale:false,
		birthDate: null
		}
	},
	 template: ` 
    <div class="bodyStyle">
    
    <div class="header_container">
        <div class="Img">
            <img src="logo.png"style="height: 115px; width: 115px;"/>
        </div>
        <div class="Name"><h1> Fitness </h1></div>
        <div class="Home"><button class="Button"   v-on:click = "home" >
            <a class="active" href="startPage.html">Početna strana</a>
        </button></div>
        <div class="Login" style="grid-area: Slot2;"><button class="Button"   v-on:click = "login" v-bind:hidden="mode=='LOGGED'" >Prijavite se</button></div>
    </div>

      <form id="forma" v-bind:hidden="mode!='CREATE'" @submit='createUser'>
        <table class="register_container">
            <tr>
                <td class="credential_labels" align="center">Korisničko ime</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs"  v-bind:disabled="mode!='CREATE'" type="text" v-model = "username" name="username" ></td>
            </tr>
            <tr>
                <td class="credential_labels" align="center">Šifra</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs"  type="password" name="password" v-model = "password"></td>
            </tr>
            <tr>
                <td class="credential_labels" align="center">Ime</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs"  type="text" name="name" v-model = "name"></td>
            </tr>
            <tr>
                <td class="credential_labels" align="center">Prezime</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs"  type="text" name="last_name" v-model = "last_name"></td>
            </tr>
            <tr>
                <td class="credential_labels" align="center">Pol</td>
            </tr>
            <tr>
                <td align="center" class="credential_inputs">
                    <input type="radio" name="gender" id="m" value="female" v-model = "gender">
                    <label for="f" style="color:white">Ženski</label>
                    <input type="radio" name="gender" id="f" value="male" v-model = "gender">
                    <label for="m" style="color: white;">Muški</label>
                </td>
            </tr>
            <tr>
                <td class="credential_labels" align="center">Datum rođenja</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs" type="date" name="birthDate" v-model = "birthDate"></td>
            </tr>
            <tr>
                <td  align="center"><input class="Button" type="submit" value="Pošalji"></td>
            </tr>
        </table>
        </form>



	</div>
    	`,
	mounted() {
	},
	methods: {
		
		createUser: function () {
			if (genderMale) gender='M';
			if(genderFemale) gender='F';
			
			if(username==null | password==null | name==null | lastName==null | gender==null | dateOfBirth==null){
				toast("Niste uneli sve potrebne podatke!")
			} else{
				axios.post('rest/user/register', {
				 username: this.username,
			 	 password: this.password,
			 	 name: this.name,
			 	 lastName: this.last_name,
			 	 gender: this.gender,
			 	 dateOfBirth: this.birthDate})
			 	 
					.then(response => {
						toast("Uspešno napravljen korisnički nalog!")
						router.push("/#lp");
						
					})
				}
		}
	}
});