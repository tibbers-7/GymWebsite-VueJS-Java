Vue.component("add-user", {
	data: function(){
		return{
		title: "Dodaj korsinika",
		admin:null,
		error: '',
		username: "",
		password: "",
		name: "",
		last_name: "", 
		gender:"",
		birthDate: null,
		type:""
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
            
            
    <div class="barBase">
        <table style="width: 20%;">
            <tr>
                <th align="left"  class="header_item"><button class="barButton" v-on:click="homePage()"><p class="inactive">Naši Objekti</p></button></th>
                <th align="left"  class="header_item"><button class="barButton" v-on:click="showUsers()"><p class="inactive">Korisnici</p></button></th>
                <th align="left"  class="header_item"><button class="barButton"><p class="active">Dodaj korisnika</p></button></th>
                <th align="left"  class="header_item"><button class="barButton" v-on:click="profile()"><p class="inactive" >Moj profil</p></button></th>
            </tr>
        </table>
    </div>



    <div class="typeButtons">
        <div class="manager_label" style="margin-left:30%"><p>Menadžer</p></div>
        <div class="trainer_label" style="margin-left:33%"><p>Trener</p></div>
        <div class="manager_radio" style="margin-top:-8%" ><input type="radio" name="type" id="m" value="MANAGER" v-on:click="manager()"></div>
        <div class="trainer_radio" style="margin-top:-8%"><input type="radio" name="type" id="t" value="TRAINER" v-on:click="trainer()"></div>
      </div>
    
    <form id="forma" style="margin-left:35%;margin-top:2%;">
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
                    <input type="radio" name="gender" id="f" value="FEMALE" v-on:click="genderFemale()">
                    <label for="f" style="color:white">Ženski</label>
                    <input type="radio" name="gender" id="m" value="MALE" v-on:click="genderFemale()">
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
                <td  align="center"><button class="Button" v-on:click=createUser() value="Pošalji"></td>
            </tr>
        </table>
       </form>




</div>
    	`,
	mounted() {
		axios
		         .get('rest/user/activeUser')
		         .then(response => this.admin = response.data);
				},
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		homePage:function(){
			router.push(`/asp`);
		},
		
		showUsers: function(){
			router.push(`/ua`);
		},
		profile: function(){
			router.push(`/pro`);
		},
		
		
		addObject:function(){
			router.push(`/ao`);
		},
		genderFemale:function(){
			this.gender="FEMALE";
		},
		genderMale:function(){
			this.gender="MALE";
		},
		manager:function(){
			this.type="MANAGER";
		},
		trainer:function(){
			this.type="TRAINER";
		},
		createUser: function () {
			axios.post('rest/user/register', {
				userType:this.type,
				 username: this.username,
			 	 password: this.password,
			 	 name: this.name,
			 	 last_name: this.last_name,
			 	 gender: this.gender,
			 	 birthDate: this.birthDate,}).then(response => {
						toast(response.data);
					});
		},
		
		profile: function(){
			router.push(`/pro`);
		},
		
	}	
});
