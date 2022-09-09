Vue.component("manager-object", {
	data: function() {
		return{
		title: "managerObjectView",
		object: {},
		selected:false,
		manager:null,
		error: '',
		newContent:''
		}
	},
	 template: ` 
    	<div style="style">
    	
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
        <table class="table" style="width:300px;">
            <tr class="table-header" >
                <th class="header__item">Sadržaj</th>
            </tr>
            <div class="table-content">  
                <tr class="table-row"  v-for="(c, index) in object.services" v-on:click=selectContent(c)>
                    <td class="table-data">{{c}}</td>
                </tr>
            </div>  
        </table>
    </div>
    <div class="deleteContent_grid">
        <button class="button2" v-on:click="deleteContent()" style="width:130px;margin-left:-20%;margin-top:3%">Obriši sadržaj</button>
    </div>
    
    <div class="inputContent_grid" style="margin-top:1%">
        <input style="color:white;margin-left:10%;width:300px;text-align:center;font-size: large;" placeholder="Novi sadržaj" type="text" v-model = "newContent"/>
    </div>
    <div class="addNewContent_grid" style="margin-top:6%">
        <button class="button2" v-on:click="addContent()">Dodaj novi sadržaj</button>
    </div>

		    
		    <div class="commentsM_grid">
		        <table class="table" style="width:60%;">
		            <tr class="table-header" >
		                <th class="header__item">Komentari</th>
		            </tr>
		            <div class="table-content">  
		                <tr class="table-row"  v-for="(c, index) in object.comments">
		                    <td class="table-data">{{c}}</td>
		                </tr>
		            </div>  
		        </table>
		    </div>
		  </div>
    </div>    
    	`,
	mounted() {
			axios
			.get('rest/sportsobjects/getByManager')
			.then(response => this.object = response.data); 			
				},
	methods: {
		
		logOut: function(){
			router.push(`/`);
		},
		
		trainingsShow: function(){
			router.push(`/mt`);
		},
		selectContent: function(selectedService){
			this.selected=selectedService;
		},
		addContent: function(){
			axios
			.post('rest/sportsobjects/addService',{string:this.newContent})
			.then(response => this.object = response.data); 			
		},
		deleteContent: function(){
			axios
			.post('rest/sportsobjects/removeService',{string:this.selected})
			.then(response => this.object = response.data); 			
		},
		goHome: function(){
			router.push(`/msp`);
		}
	}
});