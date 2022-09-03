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
    <div>

      <form id="forma" v-bind:hidden="mode!='CREATE'" @submit='createUser'>
        <table class="register_container">
            <tr>
                <td class="credential_labels" align="center">Korisničko ime</td>
            </tr>
            <tr>
                <td align="center"><input class="credential_inputs"  type="text" v-model = "username" name="username" ></td>
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
                    <input type="radio" name="gender" id="m" value="female" v-model = "genderMale" >
                    <label for="f" style="color:white">Ženski</label>
                    <input type="radio" name="gender" id="f" value="male" v-model = "genderMale">
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
                <td  align="center"><input class="Button" type="submit" v-on:click="createUser()" value="Pošalji"></td>
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