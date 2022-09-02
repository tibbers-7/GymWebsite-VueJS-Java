//import * as toast from 'toast.js';
Vue.component("trainings-customer", {
	el: '#trainingsCustomer',
	data: {
		trainings: null,
		customer: null,
		title: "Treninzi",
		text: "",
		error: '',
	},
	 template: ` 
    	<div>
    	
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
            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/csp">Naši Objekti</a></button></th>
            <th align="left"  class="header_item"><button class="barButton"><a class="active" href="#/ct">Moji Treninzi</a></button></th>
            <th align="left" class="header_item"><button class="barButton"><a class="inactive" href="#/cm">Moje članarine</a></button></th>
        </tr>
    </table>
</div>
		
		
    <table class="table">
        <tr class="table-header" >
            <th class="header__item">Naziv</th>
            <th class="header__item">Objekat</th>
            <th class="header_item">Datum treniranja</th>
        </tr>
        <div class="table-content">  
        <tr class="table-row"  v-for="(t, index) in trainings">
            <td class="table-data">{{t.name}}</td>
             <td class="table-data">{{t.sportsObject}}</td>
             <td class="table-data">{{t.date}}</td>
        </tr>
    </table>
</div>

    </div>
    	`,
	mounted() {
		axios
         .get('rest/users/activeCustomer')
         .then(response => { 
			this.customer = response.data;
			axios
			.post('rest/users/getTrainings', { id: this.customer.id })
			.then(response => this.trainings = response.data); 
			});
	}
		
		
	
});
