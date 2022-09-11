Vue.component("memberships-customer", {
	data: function() {
		return{
		membership: null,
		customer: null,
		title: "Članarine",
		text: "",
		error: '',
		show:true
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
		        <tr>
		            <th align="left"  class="header_item"><button class="barButton"  v-on:click="homePage()"><p class="inactive">Naši Objekti</p></button></th>
		            <th align="left"  class="header_item"><button class="barButton" v-on:click="trainings()"><p class="inactive">Moji Treninzi</p></button></th>
		            <th align="left" class="header_item"><button class="barButton"><p class="active">Moje članarine</p></button></th>
		            <th align="left"  class="header_item"><button class="barButton" v-on:click="profile()"><p class="inactive" >Moj profil</p></button></th>
		        </tr>
		    </table>
    	</div>
		
		
	    <div class="membership_grid">
        <div class="active_membership">
            <div class="objectView_container" style="width:50%;margin-top:3%;margin-left:10%;">
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
                        <li>{{membership.membershipType}}</li>
                        <li>{{membership.payDateString}}</li>
                        <li>{{membership.validUntilString}}</li>
                        <li>{{membership.price}}</li>
                        <li>{{membership.allowedNumber}}</li>
                    </ul>
                </div>
              </div>
        </div>

        <div class="points">
            <div class="points_container">
                <div class="points_text">
                Broj poena: {{customer.points}}
            </div>
            </div>
        </div>
	        <div class="b1_mem">
	            <button class="button2" v-on:click="cancelMem()">Otkaži članarinu</button>
	        </div>
	
	        <div class="b2_mem">
	            <button class="button2" v-on:click="newMem()">Izaberi novu članarinu</button>
	        </div>
	    </div>

    </div>
    	`,
	mounted() {
		axios
         .get('rest/user/activeUser')  //dobavi korisnika
         .then(response => { 
			this.customer = response.data;
			axios
			.post('rest/memberships/getMembership', this.customer) //dobavi njegovu clanarinu
			.then(response => {
						this.membership = response.data;
						axios
							.post('rest/user/rememberMembership',{
								"membershipType": response.data.membershipType,
								"payDateString": response.data.payDateString,
								"validUntilString": response.data.validUntilString,
								"price": response.data.price,
								"customerID": response.data.customerID,
								"status": response.data.status,
								"allowedNumber": response.data.allowedNumber,
								"sportsObject": response.data.sportsObject
							}) //sacuvaj korisnikovu clanarinu u kontekst
							.then(response => {toast(response)});	
						}); 
			});
			
			axios
	         .get('rest/memberships/getOriginal')  //dobavi original da bi mogla provera
	         .then(response => { 
				this.ogMem=response.data;
				axios
				.post('rest/user/checkMembership',response.data) //posalji original na proveru jel validna
				.then(response => {
					this.valid=Boolean.parse(response.data);
					
					if(!this.valid){
						axios
							.post('rest/memberships/cancelMembership', this.customer) //ako nije obrisi je
							.then(response => {toast(response.status) }
							)};
						this.membership=null;
				});
			});
			
			
			
			
			
		axios
         .get('rest/user/activeUser')  //dobavi korisnika opet za refresh poena
         .then(response => { 
			this.customer = response.data;
			});
						
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		
		trainings: function(){
			axios
				.post('rest/memberships/postUser',this.customer);
			axios
				.post('rest/trainings/setActiveUser',this.customer);
			router.push(`/ct`);
		},
		homePage: function(){
			router.push(`/csp`);
		},
		
		cancelMem: function(){
			axios
			.post('rest/memberships/cancelMembership', this.customer)
			.then(response => toast(response.data)); 
			this.membership.membershipType="";
			this.membership.payDateString="";
			this.membership.validUntilString="";
			this.membership.price="";
			this.membership.allowedNumber="";
		},
		newMem: function(){
			router.push(`/sm`);
		},
		profile: function(){
			router.push(`/pro`);
		},
	}
		
		
	
});
