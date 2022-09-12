Vue.component("add-object", {
	data: function(){
		return{
		title: "Dodavanje objekta",
		object:null,
		admin:null,
		managers:{},
		error: '',
		newManager:false,
		username:"",
		password:"",
		name:"",
		mName:"",
		last_name:"",
		birthDate:"",
		logo:"",
		chosenM:null,
		selectedOld:null,
		addedManager:null,
		types:{},
		type:"",
		location:""
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
			            <th align="left"  class="header_item"><button class="barButton" v-on:click="homePage()"><p class="active">Naši Objekti</p></button></th>
	            		<th align="left"  class="header_item"><button class="barButton" v-on:click="showUsers()"><p class="inactive">Korisnici</p></button></th>
			        </tr>
			    </table>
		    </div>
		    
		    
			<div class="addObj_container">
		        <div class="backBtn3_grid">
		            <button style="position:relative;left:200px;border:none;background: transparent;" v-on:click="goBack()"><img src="images/back.png" class="back_img"></img></button>
		        </div>
	        <div class="objInfo3_grid">
	            <div class="objectView_container" style="width:50%;">
	            
	                <div class="grid_name"><input  type="text" class="name_input" v-model="name"  placeholder="Naziv"/></div>
	                <div class="headers">
	                    <ul style="list-style:none">
	                        <li>Tip:</li>
	                        <li>Logo:</li>
	                        <li>Lokacija:</li>
	                        <li>Menadžer:</li>
	                    </ul>
	                </div>
	                <div class="values">
	                    <ul style="list-style:none">
	                        <li><select class="selectBox" v-model="type" > 
					                <option disabled value="">Odaberite</option>
									<option v-for="type in types" :value="type">{{type}}</option>
					            </select>  
					        </li>
	                        <li ><input type="image" v-model="logo" /></li>
	                        <li>
	                        	<input type="text" v-model="location"/>
	                        </li>
	                        <li>
	                        	<input type="text" disabled v-model="chosenManager"/>
	                            <p style="font-size: 15px;">Postojeći</p>
	                            <input type="radio" style="margin-top: -8% ;" id="e" name="managerRG" value="Existing" v-on:click="oldManagerBttn()">
	                            
	                        </li>
	                        <li>
	                            <p style="font-size: 15px;">Novi</p>
	                            <input type="radio" style="margin-top: -8% ;"  id="n" name="managerRG" value="New" v-on:click="newManagerBttn()">
	                            
	                            
	                        </li>
	                    </ul>
	                </div>
	            </div>
	        </div>


         <div class="oldManager_grid" v-show="!newManager" style="margin-top:3%;">
            <select class="selectBox" style="margin-top:-65%;margin-left:-20%;width:300px" @change="selectManager($event)"> 
                <option value="">Odaberite</option>
				<option v-for="manager in managers" :value="manager.fullName" v-on:click="oldManagerChosen(manager)">{{manager.fullName}}</option>
            </select>  
         </div>

        <div class="manager_grid" name="addNewManager" v-show="newManager">
            <table class="register_container">
                <tr>
                    <td class="credential_labels" align="center">Korisničko ime</td>
                </tr>
                <tr>
                    <td align="center"><input class="credential_inputs"  type="text" v-model = "username" ></td>
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
                    <td align="center"><input class="credential_inputs"  type="text" v-model = "mName"></td>
                </tr>
                <tr>
                    <td class="credential_labels" align="center">Prezime</td>
                </tr>
                <tr>
                    <td align="center"><input class="credential_inputs"  type="text" v-model = "last_name"></td>
                </tr>
                <tr>
                    <td class="credential_labels" align="center">Pol</td>
                </tr>
                <tr>
                    <td class="credential_inputs">
                        <input type="radio" name="gender" id="m" value="FEMALE" v-model = "gender">
                        <label for="f" style="color:white">Ženski</label>
                        <input type="radio" name="gender" id="f" value="MALE" v-model = "gender">
                        <label for="m" style="color: white;">Muški</label>
                    </td>
                </tr>
                <tr style="margin-top:2%;">
                    <td class="credential_labels" align="center">Datum rođenja</td>
                </tr>
                <tr>
                    <td align="center"><input class="credential_inputs" type="date" v-model = "birthDate"></td>
                </tr>
                <tr>
                    <td  align="center"><button class="Button" style="width: 30%;height:50%" v-on:click="addManager()">Dodeli menadžera</button></td>
                </tr>
            </table>
        </div>
        
        <div class="addObjBtn_grid" style="margin-top:5%;">
            <button class="button2" style="margin-top:-30%;margin-left:48%; width:35%;" v-on:click="addObj()">Dodaj objekat</button>
        </div>
    </div>  
    </div>
    	`,
	mounted() {
		axios
		     .get('rest/user/activeUser')
		     .then(response => this.admin = response.data);
		
		axios
		     .get('rest/user/getFreeManagers')
		     .then(response => this.managers = response.data);
		     
		axios
		     .get('rest/sportsobjects/getTypes')
		     .then(response => this.types = response.data);
			
				},
	methods: {
		
		
		logOut: function(){
			router.push(`/`);
		},
		
		homePage: function(){
			router.push(`/asp`);
		},
		showUsers: function(){
			router.push(`/au`);
		},
		addObj: function(){
			axios.post('rest/sportsobjects/addNew',{
				"name":this.name,
				"type":this.type,
				"location":this.location	
			}).then(response => {
				this.object=response.data;
			});
			
			
			
			axios
				.post('rest/sportsobjects/assignManager',this.chosenM.username)
				.then(response => {
					toast(response.data);
			});
			
			
		},
		addManager: function(){
			axios.post('rest/user/registerManager', {
				 username: this.username,
			 	 password: this.password,
			 	 name: this.name,
			 	 last_name: this.last_name,
			 	 gender: this.gender,
			 	 birthDate: this.birthDate,}).then(response => {
						if(response.status===400) toast(response.data);
						else {
							this.addedManager=response.data;
							this.chosenM=this.addedManager;
						}
					});
			
		},
		
		oldManagerChosen:function(manager){
			this.addedManager=manager;
		},
		
		goBack: function(){
			router.push(`/asp`);
			
		},
		
		newManagerBttn: function(){
			this.newManager=true;
		},
		oldManagerBttn: function(){
			this.newManager=false;
		},
		
		selectManager:function(event) {
	      this.selectedOld=event.target.value;
	      this.chosenM=this.selectedOld;
	     },
	     selectCategory:function(event) {
	      this.type=event.target.value;
	    }
			
		
		
	},
	computed:{
		chosenManager(){
			if (this.chosenM===null) return "";
			else return this.chosenM.fullName;
		}
	}
	
});