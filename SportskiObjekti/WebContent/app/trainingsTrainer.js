//import * as toast from 'toast.js';
Vue.component("trainings-trainer", {
	el: '#trainingsTrainer',
	data: {
		personalTrainings: null,
		groupTrainings:null,
		selected:false,
		selectedTraining:null,
		trainer:null,
		title: "Treninzi",
		text: "",
		error: '',
	},
	 template: ` 
    	<div class="bodyStyle">
    	
    	<div class="header_container">
			        <div class="Img">
			            <img src="logo.png"style="height: 115px; width: 115px;"/>
			        </div>
			        <div class="Name"><h1> Fitness </h1></div>
			        <div class="Login"><button class="Button"   href="#/lp" v-bind:hidden="mode=='LOGGED'" >Prijavite se</button></div>
			        <div class="Register"><button class="Button"  href="#/rp" v-bind:hidden="mode=='LOGGED'" >Registrujte se</button></div>
			        <div class="Register"><button class="Button"  href="#/" v-bind:hidden="mode!=='LOGGED'" >Odjavite se</button></div>

			</div>
			
			
    	<div class="barBase">
    <table style="width: 20%;">
        <tr>
            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/tsp">Naši Objekti</a></button></th>
            <th align="left"  class="header_item"><button class="barButton"><a class="active" href="#/tt">Moji Treninzi</a></button></th>
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
                <th class="header__item">Trajanje</th>
                <th class="header_item">Datum</th>
            </tr>
            <div class="table-content">  
            <tr class="table-row"  v-for="(t, index) in personalTrainings" v-on:click="cancelTraining(t)">
                <td class="table-data">{{t.name}}</td>
                 <td class="table-data">{{t.sportsObject}}</td>
                 <td class="table-data">{{t.length}}</td>
                 <td class="table-data">{{t.date}}</td>
            </tr>
        </table>
    </div>
    <div class="groupTrainings_grid">
        <h1 class="training_header">Grupni</h1>
        <table class="table">
            <tr class="table-header" >
                <th class="header__item">Naziv</th>
                <th class="header__item">Objekat</th>
                <th class="header__item">Trajanje</th>
                <th class="header_item">Datum</th>
            </tr>
            <div class="table-content">  
            <tr class="table-row"  v-for="(t, index) in groupTrainings">
                <td class="table-data">{{t.name}}</td>
                 <td class="table-data">{{t.sportsObject}}</td>
                 <td class="table-data">{{t.length}}</td>
                 <td class="table-data">{{t.date}}</td>
            </tr>
        </table>
    </div>
    <div class="cancelTraining_grid">
        <button class="button2" style="margin-top:1%; margin-left:5%">Otkaži trening</button>
    </div>
    <div class="tes1"></div>
    <div class="tes2"></div>
    <div class="tes3"></div>
    <div class="tes4"></div>
  </div>
</div>

    </div>

    	`,
	mounted() {
		axios
         .get('rest/users/activeTrainer')
         .then(response => { 
			this.customer = response.data;
			axios
			.post('rest/trainings/getTrainings', { id: this.trainer.id })
			.then(response => this.trainings = response.data); 
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
