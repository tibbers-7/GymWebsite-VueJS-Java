Vue.component("memberships-customer", {
	data: function() {
		return{
		customer: null,
		title: "Članarine",
		text: "",
		error: '',
		show:true,
		membership:{membershipType:"",payDateString:"",validUntilString:"",price:0,allowedNumber:0},
		points:0
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
                Broj poena: {{points}}
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
    	
    mounted(){
		axios
	         .get('rest/user/activeUser')  //dobavi korisnika
	         .then(response =>  
				this.customer = response.data);
		axios
			.get('rest/memberships/getMembership') //dobavi njegovu clanarinu
			.then(response => {
				if (response.data!=null) this.membership=response.data;
				});
		axios
				.get('rest/user/checkMembership') //posalji original na proveru jel validna
				.then(response => {
					if(response.data===null){
						this.membership={membershipType:"NEMA AKTIVNE",payDateString:"",validUntilString:"",price:"",allowedNumber:""}
					}
				});
				
		axios
         .get('rest/user/getPoints')  //dobavi korisnika opet za refresh poena
         .then(response => { 
			this.points = response.data;
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
