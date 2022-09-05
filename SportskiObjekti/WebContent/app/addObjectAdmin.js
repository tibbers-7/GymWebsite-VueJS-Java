Vue.component("add-object", {
	data: function(){
		return{
		title: "Dodavanje objekta",
		object:null,
		manager:null,
		error: '',
		newManager:true
		}
	},
	 template: ` 
    	<div style="bodyStyle">
    	
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
            <button style="position:relative;left:200px;border:none;background: transparent;" v-on:click="goBack()"><img src="back.png" class="back_img"></img></button>
        </div>
        <div class="objInfo3_grid">
            <div class="objectView_container" style="width:50%;">
            
                <div class="grid_name"><input  type="text" class="name_input"  placeholder="Naziv"/></div>
                <div class="headers">
                    <ul style="list-style:none">
                        <li>Tip:</li>
                        <li>Logo:</li>
                        <li>Menadžer:</li>
                    </ul>
                </div>
                <div class="values">
                    <ul style="list-style:none">
                        <li><input type="text" v-model="object.name"/></li>
                        <li ><input type="image" v-model="object.logoPath" /></li>
                        <li>
                            <p style="font-size: 15px;">Postojeći</label>
                            <input type="radio" style="margin-top: -8% ;" id="e" name="managerRG" value="Existing" >
                            
                        </li>
                        <li>
                            <p style="font-size: 15px;">Novi</label>
                            <input type="radio" style="margin-top: -8% ;"  id="n" name="managerRG" value="New" v-model="newManager">
                            
                        </li>
                    </ul>
                </div>
            </div>
        </div>


         <div class="oldManager_grid" v-mode:hidden="newManager==true">
            <select class="selectBox" style="margin-top:-65%;margin-left:10%;width:40%"> 
                <!-- Dinamicki generisati opcije -->
                </select>  
         </div>

        <div class="manager_grid" name="addNewManager" v-mode:hidden="newManager==false">
            <table class="register_container">
                <tr>
                    <td class="credential_labels" align="center">Korisničko ime</td>
                </tr>
                <tr>
                    <td align="center"><input class="credential_inputs"  type="text" v-model = "manager.username" ></td>
                </tr>
                
                <tr>
                    <td class="credential_labels" align="center">Ime</td>
                </tr>
                <tr>
                    <td align="center"><input class="credential_inputs"  type="text"v-model = "manager.name"></td>
                </tr>
                <tr>
                    <td class="credential_labels" align="center">Prezime</td>
                </tr>
                <tr>
                    <td align="center"><input class="credential_inputs"  type="text" v-model = "manager.last_name"></td>
                </tr>
                <tr>
                    <td class="credential_labels" align="center">Pol</td>
                </tr>
                <tr>
                    <td class="credential_inputs">
                        <input type="radio" name="gender" id="m" value="female" v-model = "gender">
                        <label for="f" style="color:white">Ženski</label>
                        <input type="radio" name="gender" id="f" value="male" v-model = "gender">
                        <label for="m" style="color: white;">Muški</label>
                    </td>
                </tr>
                <tr style="margin-top:2%;">
                    <td class="credential_labels" align="center">Datum rođenja</td>
                </tr>
                <tr>
                    <td align="center"><input class="credential_inputs" type="date" v-model = "manager.birthDate"></td>
                </tr>
                <tr>
                    <td  align="center"><button class="Button" style="width: 30%;height:50%" v-on:click="addManager()">Dodeli menadžera</td>
                </tr>
            </table>
        </div>
        
        <div class="addObjBtn_grid">
            <button class="button2" style="margin-top:-30%;margin-left:48%; width:35%;" v-on:click="addObj(object)">Dodaj objekat</button>
        </div>
    </div>
    </div>    
    	`,
	mounted() {
		axios
		         .get('rest/users/activeManager')
		         .then(response => this.customer = response.data);
		axios.get('rest/sportsobjects/')
			.then(response => (this.sportsObjects = response.data))
			
				},
	methods: {
		selectedObject: function(sportsObject){
			this.object=sportsObject;
			this.selected=true;
		},
		
		logOut: function(){
			router.push(`/`);
		},
		
		homePage: function(){
			router.push(`/asp`);
		},
		showUsers: function(){
			router.push(`/au`);
		},
		addObj: function(object){
			
			
		},
		goBack: function(){
			router.push(`/asp`);
			
		},
		
		addManager: function(){
			
			
		}
		
		
		
	}
});