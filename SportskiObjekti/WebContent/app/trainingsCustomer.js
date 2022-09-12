//import * as toast from 'toast.js';
Vue.component("trainings-customer", {
	data: function() {
		return{
		trainings: null,
		customer: null,
		title: "Treninzi",
		text: "",
		error: '',
		search:"",
		showingOpen:true,
		selectedOpen:"",
		showingType:'',
		currentSort:'name',
    	currentSortDir:'asc'
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
		    <table class="barTable" >
		        <tr >
		            <th align="left"  class="header_item"><button class="barButton"  v-on:click="homePage()"><p class="inactive">Naši Objekti</p></button></th>
			        <th align="left"  class="header_item"><button class="barButton"><p class="active">Moji Treninzi</p></button></th>
			        <th align="left" class="header_item"><button class="barButton" v-on:click="memberships()"><p class="inactive">Moje članarine</p></button></th>
			        <th align="left"  class="header_item"><button class="barButton" v-on:click="profile()"><p class="inactive" >Moj profil</p></button></th>
		        </tr>
		    </table>
		</div>
		
		<div>
			<button class="button2" v-on:click="addTraining()" style="margin-left:5%;margin-top:2%;">Dodaj trening</button>
		</div>
		
		<div class="objectTable_grid" style="margin-top:2%;">
		    <table class="table">
		        <tr class="table-header" >
		            <th class="header__item">Naziv</th>
		            <th class="header__item">Objekat</th>
		            <th class="header__item">Datum treniranja</th>
		        </tr>
		        <div class="table-content">  
		        <tr class="table-row"  v-for="(t, index) in trainings">
		            <td class="table-data">{{t.training}}</td>
		             <td class="table-data">{{t.sObject}}</td>
		             <td class="table-data">{{t.dateTimeString}}</td>
		        </tr>
		    </table>
		
	    </div>

    </div>
    	`,
	created() {
			axios
			.get('rest/trainings/getByCustomer')
			.then(response => this.trainings = response.data); 
			
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		memberships: function(){
			axios
				.post('rest/memberships/postUser',this.customer);
			router.push(`/cm`);
		},
		homePage: function(){
			router.push(`/csp`);
		},
		addTraining: function(){
			router.push(`/at`);
		},
		profile: function(){
			router.push(`/pro`);
		},
		sort:function(s) {
      //if s == current sort, reverse
      	if(s === this.currentSort) {
        this.currentSortDir = this.currentSortDir==='asc'?'desc':'asc';
      		}
      	this.currentSort = s;
    	}
		},
		computed: {
 //   filteredTrainings(){
 //     return this.trainings.filter((el) => el.isOpen === this.showingOpen&& el.type.includes(this.showingType));
//    },
    sortedTrainings:function() {
      return this.trainings.sort((a,b) => {
        let modifier = 1;
        if(this.currentSortDir === 'desc') modifier = -1;
        if(a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
        else if(a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
        return 0;
      });
    }
  }
	
		
		
	
});
