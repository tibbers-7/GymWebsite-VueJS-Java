Vue.component("add-training", {
	data: function(){
		return{
		title: "Dodavanje objekta",
		customer:null,
		error: '',
		trainings:null,
		trainers:null,
		objects:null,
		chosenObject:null,
		chosenTrainer:null,
		chosenTraining:null,
		datetime:null,
		cont:null
		
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
                <th align="left"  class="header_item"><button class="barButton"><p class="active">Naši Objekti</p></button></th>
                <th align="left"  class="header_item"><button class="barButton" v-on:click="viewTrainings()"><p class="inactive">Moji Treninzi</p></button></th>
                <th align="left" class="header_item"><button class="barButton" v-on:click="memberships()"><p class="inactive">Moje članarine</p></button></th>
                <th align="left"  class="header_item"><button class="barButton" v-on:click="profile()"><p class="inactive" >Moj profil</p></button></th>
            </tr>
        </table>
    </div>
    
    
   <div class="addObj_container">
        <div class="backBtn3_grid">
            <button style="position:relative;left:200px;border:none;background: transparent;" v-on:click="goBack()"><img src="images/back.png" class="back_img"></img></button>
        </div>
        <table class="register_container" style="margin-left:70%;line-height:5px;">
            <tr>
                <td class="credential_labels" align="center">Trener</td>
            </tr>
            <tr>
                <td align="center">
                    <select class="selectBox" v-model="chosenTrainer"  style="width:60%;">
                        <option disabled value="">Odaberite</option>
                        <option v-for="trainer in trainers" :value="trainer.fullName">{{trainer.fullName}}</option>
                    </select>  
                </td>
            </tr>
            <tr>
                <td class="credential_labels" align="center">Objekat</td>
            </tr>
            <tr>
                <td align="center">
                    <select class="selectBox" v-model="chosenObject"  style="width:60%;">
					    <option disabled value="">Odaberite</option>
					    <option v-for="object in objects" :value="object.name" v-on:click="onChange(object)">{{object.name}}</option>
					 </select> 
                </td>
            </tr>
            
            
            <tr>
                <td class="credential_labels" align="center">Tip</td>
            </tr>
            <tr>
                <td align="center">
                    <select class="selectBox" v-model="chosenTraining" style="width:60%;">
					    <option disabled value="">Odaberite</option>
					    <option v-for="content in cont" :value="content">{{content.name}}</option>
					 </select>  
                </td>
            </tr>
            <tr>
                <td align="center">
                    <button class="Button"   v-on:click="addTraining()">Dodaj trening</button>
                </td>
            </tr>
           
        </table>
	</div>
</div>    
    	`,
	mounted() {
		axios
		     .get('rest/user/activeUser')
		     .then(response => (this.customer = response.data));
		axios.get('rest/sportsobjects/getAll')
			.then(response => (this.objects = response.data));
		axios.get('rest/user/getTrainers')
			.then(response => (this.trainers = response.data));
	},
	methods: {
		
		logOut: function(){
			router.push(`/`);
		},
		
		homePage: function(){
			router.push(`/csp`);
		},
		viewTrainings: function(){
			router.push(`/ct`);
		},
		memberships: function(){
			router.push(`/cm`);
		},
		profile: function(){
			router.push(`/pro`);
		},
		
		onChange:function(chosenObject){
			axios
					.post('rest/trainings/getByObject',chosenObject)
					.then(response => this.cont=response.data); 
			
    	},
    	
    	addTraining: function(){
				axios
					.post('rest/trainings/addTraining',
							{ "user": this.customer.username,
							  "sObject": this.chosenObject.id,
							  "trainer":this.chosenTrainer.username,
							  "training": this.chosenTraining
							})
					.then(response => (toast(response.data))); 
					
		}
		
	}
});