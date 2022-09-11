Vue.component("add-training-manager", {
	data: function(){
		return{
		manager:null,
		error: '',
		name:"",
		types:null,
		chosenType:null,
		trainers:null,
		chosenTrainer:null,
		duration:null,
		objects:null,
		chosenObject:null,
		description:null,
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

         <div class="barBase">
            <table style="width: 20%;">
                <tr>
                    <th align="left"  class="header_item"><button class="barButton"><p class="active">Naši Objekti</p></button></th>
                    <th align="left"  class="header_item"><button class="barButton"  v-on:click="objectShow()"><p class="inactive">Moj Sportski Objekat</p></button></th>
                    <th align="left"  class="header_item"><button class="barButton"  v-on:click="trainingsShow()"><p class="inactive">Treninzi</p></button></th>
                    <th align="left"  class="header_item"><button class="barButton" v-on:click="profile()"><p class="inactive" >Moj profil</p></button></th>

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
                        <li>Objekat:</li>
                        <li>Trajanje:</li>
                        <li>Trener:</li>
                        <li>Opis:</li>
                    </ul>
                </div>
                <div class="values">
                    <ul style="list-style:none">
                        <li><select class="selectBox" v-model="chosenType" > 
                                <option disabled value="">Odaberite</option>
                                <option v-for="type in types" :value="type">{{type}}</option>
                            </select>  
                        </li>
                        <li >
                            <select class="selectBox" v-model="chosenObject" > 
                                <option disabled value="">Odaberite</option>
                                <option v-for="object in objects" :value="object.name">{{object.name}}</option>
                            </select>  
                        </li>
                        <li>
                            <input type="number" v-model="duration"/>
                        </li>
                        <li>
                            <select class="selectBox" v-model="chosenTrainer" > 
                                <option disabled value="">Odaberite</option>
                                <option v-for="trainer in trainers" :value="trainer.fullName">{{trainer.fullName}}</option>
                            </select>  
                            
                        </li>
                        <li>
                            <input type="text" v-model="description"  style="height:100px">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    
    
        </div>

    </div>
    	
    	`,
	mounted() {
		axios
		     .get('rest/user/activeUser')
		     .then(response => (this.manager = response.data));
		axios.get('rest/sportsobjects/getAll')
			.then(response => (this.objects = response.data));
		axios
		     .get('rest/user/getTrainers')
		     .then(response => (this.trainers = response.data));
		axios
		     .get('rest/trainings/getAllTypes')
		     .then(response => (this.types = response.data));
	},
	methods: {
		
		logOut: function(){
			router.push(`/`);
		},
		
		homePage: function(){
			router.push(`/tsp`);
		},
		trainings: function(){
			router.push(`/tt`);
		},
		profile: function(){
			router.push(`/pro`);
		},
    	
    	addTraining: function(){
				axios
					.post('rest/trainings/addNewTraining',
							{ "name": this.name,
							  "sObject": this.chosenObject,
							  "trainer":this.chosenTrainer.username,
							  "duration":this.duration,
							  "description":this.description,
							  "type":this.type
							})
					.then(response => (toast(response.data))); 
					
		}
		
	}
});