Vue.component("profile", {
	data: function() {
		return{
		user: null,
		title: "Profil",
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



<div class="profile_grid">

    <div class="profileBack_grid">
        <button style="margin-top:2%;margin-left:90%;border:none" v-on:click="goBack()"><img src="images/back.png" class="back_img"></img></button>
    </div>
    <div class="profileInfo_grid">
                <div class="objectView_container"  >
                    
                    <div class="grid_name">{{user.username}}</div>
                    <div class="headers">
                        <ul style="list-style:none">
                            <li>Ime:</li>
                            <li>Prezime:</li>
                            <li>Pol:</li>
                            <li>Datum rođenja:</li>
                        </ul>
                    </div>
                    <div class="values">
                        <ul style="list-style:none">
                            <li>{{user.name}}</li>
                            <li>{{user.last_name}}</li>
                            <li>{{user.gender}}</li>
                            <li>{{user.birthDate}}</li>
                        </ul>
                    </div>
                </div>
    </div>


    <div class="changePassBttn_grid">
        <button class="button2" style="margin-left:10%;margin-top:2%;"  v-on:click="changePass()">Promeni lozinku</button>
    </div>
    <div class="changeInfoBttn_grid">
        <button class="button2" style="margin-left:10%;margin-top:2%;"  v-on:click="changeInfo()">Promeni informacije</button>
    </div>
  </div>


    </div>
    	`,
	mounted() {
		axios
         .get('rest/users/activeUser')
         .then(response => { 
			this.user = response.data;
			});
	},
	
	methods: {
		logOut: function(){
			router.push(`/`);
		},
		changePass: function(){
			router.push(`/pc`);
		},
		changeInfo: function(){
			router.push(`/ic`);
		}, 
		goBack: function(){
			switch(this.user.userType){
					case "CUSTOMER":
						 router.push(`/csp`);
						 break;
					case "MANAGER": 
						router.push(`/msp`);
						break;
					case "ADMIN": 
						router.push(`/asp`);
						break;
					case "TRAINER": 
						router.push(`/tsp`);
						break;
					default: router.push(`/`);
				}
			}, 
	}
		
		
	
});
