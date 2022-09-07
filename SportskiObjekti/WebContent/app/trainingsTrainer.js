//import * as toast from 'toast.js';
Vue.component("trainings-trainer", {
	data: function() {
		return{
		personalTrainings: null,
		groupTrainings:null,
		selected:false,
		selectedTraining:null,
		trainer:null,
		title: "Treninzi",
		text: "",
		error: '',
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
	             <th align="left"  class="header_item"><button class="barButton" ><p class="inactive">Naši Objekti</p></button></th>
		            <th align="left"  class="header_item"><button class="barButton" v-on:click="trainings()"><p class="active" >Moji Treninzi</p></button></th>
	        </tr>
	    </table>
	</div>
		
		
<div class="trainingsTrainer_grid">
    <div class="personalTrainings_grid">
        <h1 class="training_header">Personalni</h1>
        <table class="table">
            <tr class="table-header" >
                <th class="header__item">Naziv</th>
                <th class="header__item">Objekat</th>
                <th class="header_item">Datum</th>
            </tr>
            <div class="table-content">  
            <tr class="table-row"  v-for="(t, index) in personalTrainings" v-on:click="cancelTraining(t)">
                <td class="table-data">{{t.training}}</td>
                 <td class="table-data">{{t.sObject}}</td>
                 <td class="table-data">{{t.dateTimeString}}</td>
            </tr>
        </table>
    </div>
    <div class="groupTrainings_grid">
        <h1 class="training_header">Grupni</h1>
        <table class="table">
            <tr class="table-header" >
                <th class="header__item">Naziv</th>
                <th class="header__item">Objekat</th>
                <th class="header_item">Datum</th>
            </tr>
            <div class="table-content">  
            <tr class="table-row"  v-for="(t, index) in groupTrainings">
                <td class="table-data">{{t.training}}</td>
                 <td class="table-data">{{t.sObject}}</td>
                 <td class="table-data">{{t.dateTimeString}}</td>
            </tr>
        </table>
    </div>
    <div class="cancelTraining_grid">
        <button class="button2" style="margin-top:1%; margin-left:5%">Otkaži trening</button>
    </div>
  </div>
</div>

    </div>

    	`,
	mounted() {
		axios
         .get('rest/user/activeUser')
         .then(response => { 
			this.trainer = response.data;
			axios
			.post('rest/trainings/getByTrainerPersonal', this.trainer)
			.then(response => this.personalTrainings = response.data); 
			axios
			.post('rest/trainings/getByTrainerGroup', this.trainer)
			.then(response => this.groupTrainings = response.data); 
			});
	},
	
	methods: {
		cancelTraining: function(training){
			this.selectedTraining=training;
			if (selected==true){
				axios
			.post('rest/trainings/cancelTraining', { id: this.customer.id });
			//.then(response => this.trainings = response.data); 
			
			} else toast("Niste odabrali trening!");
		}
	}
		
		
	
});
