Vue.component("visitors-object", {
	data: function() {
		return{
		title: "Posetioci",
		trainers:null,
		customers:null,
		manager:null,
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
            <th align="left"  class="header_item"><button class="barButton" v-on:click="goHome()"><p class="inactive">Naši Objekti</p></button></th>
            <th align="left"  class="header_item"><button class="barButton" v-on:click="showObject()"><p class="inactive">Moj Sportski Objekat</p></button></th>
            <th align="left"  class="header_item"><button class="barButton"  v-on:click="trainingsShow()"><p class="inactive">Treninzi</p></button></th>
            <th align="left"  class="header_item"><button class="barButton"><p class="active">Posetioci</p></button></th>
        </tr>
    </table>
</div>

<div class="visitorsObject">
    <div class="trainingsObject_grid">
        <div class="commentsM_grid">
            <table class="table" style="width:500px;margin-left:170px;margin-top:-50px">
                <tr class="table-header" >
                    <th class="header__item">Treneri</th>
                </tr>
                <div class="table-content">  
                    <tr class="table-row"  v-for="(t, index) in trainers">
                        <td class="table-data">{{t}}</td>
                    </tr>
                </div>  
            </table>
        </div>
    </div>
    <div class="customersObject_grid">
        <div class="commentsM_grid" style="margin-top:-35px;margin-left:700px">
            <table class="table" style="width:500px;">
                <tr class="table-header" >
                    <th class="header__item">Kupci</th>
                </tr>
                <div class="table-content">  
                    <tr class="table-row"  v-for="(c, index) in customers">
                        <td class="table-data">{{c.fullName}}</td>
                    </tr>
                </div>  
            </table>
        </div>
    </div>
  </div>

</div>    
    	`,
	mounted() {
		axios
			.get('rest/user/activeUser')
			.then(response => this.manager = response.data); 	
		axios
			.get('rest/trainings/getTrainers')
			.then(response => this.trainers = response.data); 	
			
		axios
			.get('rest/user/getVisitors')
			.then(response => this.customers = response.data); 		
				},
	methods: {
		
		logOut: function(){
			router.push(`/`);
		},
		
		trainingsShow: function(){
			axios.post('rest/trainings/setActiveUser',this.manager);
			router.push(`/mt`);
		},
		goHome: function(){
			router.push(`/msp`);
		}
	}
});