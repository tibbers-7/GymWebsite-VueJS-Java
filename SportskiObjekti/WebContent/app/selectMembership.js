//import * as toast from 'toast.js';
Vue.component("select-membership", {
	data: function() {
		return{
		memberships: null,
		mem: null,
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

	<div v-if="notSelected" class="mems_grid">
	    <div class="memTable_grid">
	    <table class="table">
	        <tr class="table-header" >
	            <th class="header__item">Tip</th>
	            <th class="header__item">Broj termina</th>
	            <th class="header__item">Cena</th>
	        </tr>
	        <div class="table-content">  
	        <tr class="table-row"  v-for="(m, index) in memberships">
	            <td class="table-data">{{m.membershipType}}</td>
	             <td class="table-data">{{m.price}}</td>
	             <td class="table-data">{{m.allowedNumber}}</td>
	        </tr>
	        </div>  
	    </table>
	    </div>  
	
	    <div class="selectMem_Btn">
	    <button class="mem_button" v-on:click="showMembership(m)">Odaberi</button>
	    </div>
	</div>


	<div v-else="notSelected" class="selectedMem_grid" >
    
    <div class="chooseMem_Btn">
        <button class="button2" v-on:click="chooseMembership()">Odaberi</button>
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
		showMembership : function(membership) {
		 this.mem = membership;
		 this.selected = true;
		},
		
		chooseMembership : function() {
			axios
	         .post('rest/memberships/postUser',this.customer)
	         .then(response => (toast(response.data)));
			
			axios
	         .post('rest/memberships/addMembership',this.mem)
	         .then(response => (toast(response.data)));
			
		},
		goBack:function(){
			router.push(`/cm`);
		}
	
	}	
	
});
