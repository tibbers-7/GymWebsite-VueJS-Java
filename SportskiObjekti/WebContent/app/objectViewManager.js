Vue.component("manager-object", {
	data: function() {
		return{
		title: "managerObjectView",
		object:null,
		selected:false,
		manager:null,
		error: '',
		}
	},
	 template: ` 
    	<div style="bodyStyle">
    	
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
		                    
		                    <div class="grid_name">{{o.name}}</div>
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
		                            <li>{{object.status}}</li>
		                            <li>{{object.grade}}</li>
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
			            <tr class="table-row"  v-for="(c, index) in object.contents">
			                <td class="table-data">{{c.name}}</td>
			            </tr>
		            </div>  
		        </table>
		    </div>
		    
		    <div class="addNewContent_grid">
		        <a href="#/nc"><button class="button2">Dodaj novi sadržaj</button></a>
		    </div>
		    
		    <div class="commentsM_grid">
		        <table class="table" style="width:60%;">
		            <tr class="table-header" >
		                <th class="header__item">Komentari</th>
		            </tr>
		            <div class="table-content">  
		                <tr class="table-row"  v-for="(c, index) in object.comments">
		                    <td class="table-data">{{c.text}}</td>
		                </tr>
		            </div>  
		        </table>
		    </div>
		  </div>
    </div>    
    	`,
	mounted() {
		axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data;
			axios
			.post('rest/sportsObjects/getObjectByManager', { id: this.manager.id })
			.then(response => this.object = response.data); 
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