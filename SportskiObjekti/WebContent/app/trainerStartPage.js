Vue.component("trainer-SP", {
	data: function() {
		return{
		title: "trainerStartPage",
		MODE: "LOGGED",
		sportsObjects: null,
		object:null,
		selected:false,
		trainer:null,
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
	            <th align="left"  class="header_item"><button class="barButton" ><p class="active">Naši Objekti</p></button></th>
	            <th align="left"  class="header_item"><button class="barButton" v-on:click="trainings()"><p class="inactive" >Moji Treninzi</p></button></th>
	            <th align="left"  class="header_item"><button class="barButton" v-on:click="profile()"><p class="inactive" >Moj profil</p></button></th>
	        </tr>
	    </table>
    </div>
    
    
	<!-- TABELA SVIH OBJEKATA -->
		
		<div>
        
        <div class="objectSpec_grid">
          <div class="objectFilter_grid">
            <div class="objFilter1_grid">
                    <label style="font-size: large;"> Tip Objekta </label>  
                    <select class="selectBox" v-model="filterType">
					    <option disabled value="">Odaberite</option>
					    <option v-for="content in contents" :value="content">{{content}}</option>
					 </select>  
            </div>
            <div class="objFilter2_grid">
                    <label style="font-size: large;"> Dostupnost </label>  
                    <select v-model="filterAvailability">
					    <option disabled value="">Odaberite</option>
					    <option>Otvoreno</option>
					    <option>Zatvoreno</option>
					</select>
              </div>
            </div>
          <div class="objectSearch_grid">
                <input type="text" class="input-search" placeholder="Pretraga">
             
          </div>
        </div>
       
      </div>

      <div class="objectTable_grid">
        <div class="objectsSort_grid">
            <button class="ascButton" v-on:click="ascName()" style="margin-left:12%"><img src="images/arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="descButton" v-on:click="descName()"><img src="images/arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="ascButton" v-on:click="ascLoc()" style="margin-left:42%"><img src="images/arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="descButton" v-on:click="descLoc()"><img src="images/arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="ascButton" v-on:click="ascGrade()" style="margin-left:8%"><img src="images/arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="descButton" v-on:click="descGrade()"><img src="images/arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        </div>
        <table class="table">
            <tr class="table-header" >
                <th class="header__item">Naziv</th>
                <th class="header__item">Tip</th>
                <th class="header__item">Ponuda</th>
                <th class="header__item">Otvoreno</th>
                <th class="header__item">Adresa</th>
                <th class="header__item">Ocena</th>
                <th class="header__item">Logo</th>
                <th class="header__item">Radno vreme</th>
            </tr>
            <div class="table-content">  
            <tr class="table-row"  v-for="o in sportsObjects" v-on:click="selectedObject(o)" >
                <td class="table-data">{{o.name}}</td>
                 <td class="table-data">{{o.type}}</td>
                 <td class="table-data">{{o.services}}</td>
                 <td class="table-data">{{o.isOpen}}</td>
                 <td class="table-data">{{o.location}}</td>
                 <td class="table-data">{{o.avgScore}}</td>
                 <td class="table-data"><img v-bind:src="o.logoPath" width=50 height=50/> </td>
                 <td class="table-data">{{o.openHours}}</td>
            </tr>
            </div>  
        </table>
    </div>
    
    
    
		</div>       
    	`,
	mounted() {
		axios.get('rest/user/activeUser')
		         .then(response => this.trainer = response.data);
		axios.get('rest/sportsobjects/getAll')
			.then(response => (this.sportsObjects = response.data));
				},
	methods: {
		selectedObject: function(sportsObject){
			this.object=sportsObject;
			this.selected=true;
		},
		
		logOut: function(){
			router.push(`/`);
		},
		
		profile: function(){
			router.push(`/pro`);
		},
		
		trainings: function(){
			router.push(`/tt`);
		},
		
		
	}
});