Vue.component("view-object", {
	data: function() {
		return{
		title: "Pregled objekta",
		object:null,
		user:null,
		error: ''
		}
	},
	 template: ` 
    	<div class="bodyStyle">
    	
		    	<div class="header_container">
			        <div class="Img">
			            <img src="images/logo.png"style="height: 115px; width: 115px;"/>
			        </div>
			        <div class="Name"><h1> Fitness </h1></div>
		
				</div>
		
		
		<div class="objectInfo_grid">
		    <div class="content_grid">
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
		    
		    <div class="comments_grid">
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
		                    <li>{{object.status}}</li>
		                    <li>{{object.grade}}</li>
		                </ul>
		            </div>
		        </div>
		    </div>
		    
	    <div class="back_Btn2_grid">
	        <button v-on:click="goBack()" class="images/back.png" style="margin-top:10%;margin-left:80%;"><img src="back.png" class="back_img"></img></button>
	    </div>
 	 </div>
 	 
    </div>    
    	`,
	mounted() {
			axios
         .get('rest/user/activeUser')
         .then(response => { 
			this.user = response.data;
			});
				axios
         .get('rest/sportsobjects/getSelectedObject')
         .then(response => { 
			this.object = response.data;
			});
			
				},
	methods: {
		
		logOut: function(){
			router.push(`/`);
		},
		
		goBack: function(){
			if (user==null) router.push(`/`);
			switch(user.type){
				case "ADMINISTRATOR": 
					router.push(`/asp`);
					break;
				case "CUSTOMER": 
					router.push(`/csp`);
					break;
				case "MANAGER": 
					router.push(`/msp`);
					break;
				case "TRAINER": 
					router.push(`/tsp`);
					break;
			}
		}
	}
});