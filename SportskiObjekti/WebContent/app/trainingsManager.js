//import * as toast from 'toast.js';
Vue.component("trainings-manager", {
	data: function() {
		return{
		trainings: null,
		manager: null,
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
		    <table style="width: 20%;" style="position:relative;top:-22%;">
		        <tr >
		           <th align="left"  class="header_item"><button class="barButton" v-on:click="goHome()><p class="inactive">Naši Objekti</p></button></th>
			       <th align="left"  class="header_item"><button class="barButton"  v-on:click="objectShow()"><p class="inactive">Moj Sportski Objekat</p></button></th>
			       <th align="left"  class="header_item"><button class="barButton"  ><p class="active">Treninzi</p></button></th>
		        </tr>
		    </table>
		</div>
		
		<div class="objectTable_grid" style="margin-top:2%;">
		
		    <table class="table">
		        <tr class="table-header" >
		            <th class="header__item">Naziv</th>
		            <th class="header__item">Tip</th>
		            <th class="header__item">Trajanje</th>
		            <th class="header__item">Trener</th>
		            <th class="header__item">Opis</th>
		            <th class="header__item">Slika</th>
		        </tr>
		        <div class="table-content">  
		        <tr class="table-row"  v-for="(t, index) in trainings">
		            <td class="table-data">{{t.name}}</td>
		             <td class="table-data">{{t.type}}</td>
		             <td class="table-data">{{t.duration}}</td>
		             <td class="table-data">{{t.trainer}}</td>
		             <td class="table-data">{{t.description}}</td>
		             <td class="table-data">{{t.logoPath}}</td>
		        </tr>
		    </table>
		
	    </div>

    </div>
    	`,
	mounted() {
		axios
         .get('rest/users/activeUser')
         .then(response => { 
			this.manager = response.data;
			axios
			.post('rest/trainings/getByManager', this.manager)
			.then(response => this.trainings = response.data); 
			});
			
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		goHome: function(){
			router.push(`/msp`);
		},
		showObject: function(){
			router.push(`/ovm`);
		}
	}
		
		
	
});
