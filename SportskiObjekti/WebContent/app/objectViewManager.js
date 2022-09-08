Vue.component("manager-object", {
	data: function() {
		return{
		title: "managerObjectView",
		object:null,
		selected:false,
		manager:null,
		error: '',
		services:{}
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
		            <th align="left"  class="header_item"><button class="barButton" v-on:click="goHome()"><p class="inactive">Naši Objekti</p></button></th>
					<th align="left"  class="header_item"><button class="barButton" ><p class="active">Moj Sportski Objekat</p></button></th>
					<th align="left"  class="header_item"><button class="barButton"  v-on:click="trainingsShow()"><p class="inactive">Treninzi</p></button></th>
		        </tr>
		    </table>
		</div>
		
		<div class="objManager_grid">
		    <div class="objInfoM_grid">
		            <div class="basicInfo_grid">
		                <div class="objectView_container" >
		                    
		                    <div class="grid_name">{{object.name}}</div>
		                    <div class="headers">
		                        <ul style="list-style:none">
		                            <li>Tip:</li>
		                            <li>Status:</li>
		                            <li>Ocena:</li>
		                        </ul>
		                    </div>
		                    <div class="values">
		                        <ul style="list-style:none">
		                            <li>{{object.type}}</li>
		                            <li>{{object.isOpen}}</li>
		                            <li>{{object.avgScore}}</li>
		                        </ul>
		                    </div>
		                </div>
		            </div>    
		    </div>
		
		    <div class="objContentM_grid">
		        <table class="table" style="width:60%;">
		            <tr class="table-header" >
		                <th class="header__item">Sadržaj</th>
		            </tr>
		            <div class="table-content">  
			            <tr class="table-row"  v-for="service in object.services">
			                <td class="table-data">{{service}}</td>
			            </tr>
		            </div>  
		        </table>
		    </div>
		    
		    <div class="addNewContent_grid">
		        <button class="button2">Dodaj novi sadržaj</button>
		    </div>
		    
		    
		  </div>
    </div>    
    	`,
	mounted() {
		axios
         .get('rest/user/activeUser')
         .then(response => { 
			this.manager = response.data;
			axios
			.get('rest/sportsobjects/getByManager', this.manager)
			.then(response =>{ 
				this.object = response.data;
				this.services=this.object.services;
				this.comments=this.object.services;
				}); 
			});
			
				},
	methods: {
		
		logOut: function(){
			router.push(`/`);
		},
		
		trainingsShow: function(){
			router.push(`/mt`);
		},
		
		goHome: function(){
			router.push(`/msp`);
		}
	}
});