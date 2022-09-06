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
		gender:"",
		birthDate: null
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
		<div class="Register"><button class="Button" v-on:click="logIn()">Ulogujte se</button></div>
        
     </div>

      <form id="forma">
        <table class="register_container">
            <tr>
                <td class="credential_labels" align="center">Korisničko ime</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs"  type="text" name="username" v-model = "username"></td>
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
                    <input type="radio" name="gender" id="m" value="female" v-on:click=genderFemale()>
                    <label for="f" style="color:white">Ženski</label>
                    <input type="radio" name="gender" id="f" value="male" v-on:click=genderMale()>
                    <label for="m" style="color: white;">Muški</label>
                </td>
            </tr>
            <tr>
                <td class="credential_labels" align="center">Datum rođenja</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs" type="text" name="birthDate" v-model = "birthDate">dd/mm/yyyyy</td>
            </tr>
            <tr>
                <td  align="center"><input class="Button" v-on:click=createUser() value="Pošalji"></td>
            </tr>
        </table>
       </form>



	</div>
    	`,
	mounted() {
	},
	methods: {
		goHome: function () {
			router.push('/');
			},
		logIn: function () {
			router.push('/lp');
			},
		genderMale: function () {
			gender='M';
			},
		genderFemale: function () {
			gender='F';
			},
		createUser: function () {
			axios.post('rest/user/register', {
				 username: this.username,
			 	 password: this.password,
			 	 name: this.name,
			 	 lastName: this.last_name,
			 	 gender: this.gender,
			 	 dateOfBirth: this.birthDate,}).then(response => {
						toast("Uspešno napravljen korisnički nalog!")
						router.push("/#lp");
						
					})
				}
		}
	
});