Vue.component("memberships-customer", {
	data: function() {
		return{
		membership: null,
		customer: null,
		title: "Članarine",
		text: "",
		error: '',
		}
	},
	 template: ` 
    	<div class="bodyStyle">
    	
    	<div class="header_container">
			        <div class="Img">
			            <img src="logo.png"style="height: 115px; width: 115px;"/>
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
		
		
	    <div class="membership_grid">
	        <div class="active_membership">
	            <div class="am_container">
	                <div class="grid_name">Aktivna članarina</div>
	                <div class="headers">
	                    <ul style="list-style: none;">
	                        <li>Tip:</li>
	                        <li>Datum kupovine:</li>
	                        <li>Rok važenja:</li>
	                        <li>Cena:</li>
	                        <li>Broj termina:</li>
	                    </ul>
	                </div>
	                <div class="values">
	                    <ul style="list-style: none;">
	                        <li>{{membership.type}}</li>
	                        <li>{{membership.dateBought}}</li>
	                        <li>{{membership.expiry}}</li>
	                        <li>{{membership.price}}</li>
	                        <li>{{membership.termNumber}}</li>
	                    </ul>
	                </div>
	              </div>
	        </div>
	
	        <div class="points">
	            <div class="points_container">
	                <div class="points_text">Broj poena: {{customer.points}}</div>
	            </div>
	        </div>
	
	        <div class="b1_mem">
	            <button class="mem_button" v-on:click="cancelMem()">Otkaži članarinu</button>
	        </div>
	
	        <div class="b2_mem">
	            <button class="mem_button" v-on:click="newMem()">Izaberi novu članarinu</button>
	        </div>
	    </div>

    </div>
    	`,
	mounted() {
		axios
         .get('rest/users/activeCustomer')
         .then(response => { 
			this.customer = response.data;
			axios
			.post('rest/users/getMembership', { id: this.customer.id })
			.then(response => this.membership = response.data); 
			});
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		
		trainings: function(){
			router.push(`/ct`);
		},
		homePage: function(){
			router.push(`/csp`);
		},
		
		cancelMem: function(){
			router.push(`/cmem`);
		},
		newMem: function(){
			router.push(`/nmem`);
		}
	}
		
		
	
});
