//import * as toast from 'toast.js';
Vue.component("select-membership", {
	data: function() {
		return{
		memberships: null,
		customer: null,
		notSelected:true,
		title: "Odabir Članarine",
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
		            <th align="left"  class="header_item"><button class="barButton"  v-on:click="homePage()"><p class="inactive">Naši Objekti</p></button></th>
		            <th align="left"  class="header_item"><button class="barButton" v-on:click="trainings()"><p class="inactive">Moji Treninzi</p></button></th>
		            <th align="left" class="header_item"><button class="barButton"><p class="active">Moje članarine</p></button></th>
		        </tr>
		    </table>
    	</div>

	<div class="mems_grid">
	    <div class="memTable_grid">
	    <table class="table">
	        <tr class="table-header" >
	            <th class="header__item">Tip</th>
	            <th class="header__item">Cena</th>
	            <th class="header__item">Broj termina</th>
	        </tr>
	        <div class="table-content">  
	        <tr class="table-row"  v-for="(m, index) in memberships" v-on:click="showMembership(m)">
	            <td class="table-data">{{m.membershipType}}</td>
	             <td class="table-data">{{m.price}}</td>
	             <td class="table-data">{{m.allowedNumber}}</td>
	        </tr>
	        </div>  
	    </table>
	    </div>  
	
	</div>

</div>

    	`,
    mounted() {
		axios 
		.get('rest/memberships/getAvailable')
		.then(response => (this.memberships = response.data));
		
		axios
         .get('rest/user/activeUser')
         .then(response => (this.customer = response.data));
	},
    	
    methods: {
		
		showMembership:function(mem){
			axios
	         .post('rest/memberships/setSelected',mem)
	         .then(response => (toast(response.data)));
	        router.push(`/shm`);
			
		},
		
		homePage: function(){
			router.push(`/csp`);
		},
		logOut: function(){
			router.push(`/`);
		},
		
		trainings: function(){
			router.push(`/ct`);
		},
		memberships: function(){
			router.push(`/cm`);
		},
		profile: function(){
			router.push(`/pro`);
		},
		
		
		
	}	
	
});
