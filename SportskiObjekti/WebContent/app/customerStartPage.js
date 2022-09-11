Vue.component("customer-SP", {
	data: function() {
		return{
		title: "customerStartPage",
		MODE: "LOGGED",
		sportsObjects: [],
		object:null,
		selected:false,
		customer:null,
		error: '',
		types:null,
		selectedObj:null,
		id:'',
		search:"",
		showingOpen:true,
		selectedOpen:"",
		showingType:'',
		searchTerm:'',
		currentSort:'name',
    	currentSortDir:'asc'
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
	            <th align="left"  class="header_item"><button class="barButton"><p class="active">Naši Objekti</p></button></th>
	            <th align="left"  class="header_item"><button class="barButton" v-on:click="trainings()"><p class="inactive">Moji Treninzi</p></button></th>
	            <th align="left" class="header_item"><button class="barButton" v-on:click="memberships()"><p class="inactive">Moje članarine</p></button></th>
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
                    <select class="selectBox" v-model="showingType">
					    <option disabled value="">Odaberite</option>
					    <option v-for="type in types" :value="type">{{type}}</option>
					 </select>  
            </div>
            <div class="objFilter2_grid">
                    <label style="font-size: large;"> Dostupnost </label>  
                    <select v-model="selectedOpen"  @change="filterOpen()">
					    <option disabled value="">default: Otvoreno</option>
					    <option value="Otvoreno">Otvoreno</option>
					    <option value="Zatvoreno">Zatvoreno</option>
					</select>
              </div>
             <div class="objFilter3_grid">
                    <label style="font-size: large;"> Trazi po: </label>  
                    <select v-model="searchTerm"  @change="setSearch()">
					    <option disabled value=""></option>
					    <option value="name">Naziv</option>
					    <option value="type">Tip</option>
					    <option value="location">Lokacija</option>
					    <option value="avgScore">Prosecna ocena</option>
					</select>
              </div>
            </div>
          <div class="objectSearch_grid">
                <input type="text" class="input-search" placeholder="Pretraga" v-model="search">
             
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
                <th class="header__item" >Naziv</th>
                <th class="header__item" >Tip</th>
                <th class="header__item" >Ponuda</th>
                <th class="header__item" >Otvoreno</th>
                <th class="header__item" >Adresa</th>
                <th class="header__item" >Ocena</th>
                <th class="header__item" >Logo</th>
                <th class="header__item" >Radno vreme</th>
            </tr>
            <div class="table-content">  
            <tr class="table-row" v-for="o in sortedObjects" v-on:click="selectedObject(o)" >
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
		axios
	         .get('rest/user/activeUser')
	         .then(response => this.customer = response.data);
		axios.get('rest/sportsobjects/getAll')
			.then(response => (this.sportsObjects = response.data));
			
		axios.get('rest/sportsobjects/getTypes')
		.then(response => (this.types = response.data));
			
				},
	methods: {
		selectedObject: function(selectedObj){
			axios.post('rest/sportsobjects/setSelectedObject',{
				"id": selectedObj.id,
    			"name": selectedObj.name,
    			"type": selectedObj.type,
    			"services": selectedObj.services,
    			"isOpen": selectedObj.isOpen,
    			"location": selectedObj.location,
    			"avgScore": selectedObj.avgScore,
    			"logoPath": selectedObj.logoPath,
    			"openHours": selectedObj.openHours
				
    	})
					.then(response => {
						toast(response.data);
						router.push(`/ovg`)});
		},
		
		filterOpen : function() {
			if(this.selectedOpen==="Otvoreno")
    		this.showingOpen=true;
    		else this.showingOpen=false;
    	},
		ascName: function(){
			this.sort('name');
		},
		ascLoc: function(){
			this.sort('location');
		},
		descName: function(){
			this.sort('name');
		},
		descLoc: function(){
			this.sort('location');
		},
		ascGrade: function(){
			this.sort('avgScore');
		},
		descGrade: function(){
			this.sort('avgScore');
		},
		sort:function(s) {
      //if s == current sort, reverse
      	if(s === this.currentSort) {
        this.currentSortDir = this.currentSortDir==='asc'?'desc':'asc';
      		}
      	this.currentSort = s;
    	},
		
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
		memberships: function(){
			axios
				.post('rest/memberships/postUser',this.customer);
			router.push(`/cm`);
		},
		profile: function(){
			router.push(`/pro`);
		},
		
		
	},
	computed: {
    searchObjects() {
	if(this.searchTerm==="name")
      return this.sportsObjects.filter((el) => el.name.toLowerCase().includes(this.search.toLowerCase()));
    else if(this.searchTerm==="type")
      return this.sportsObjects.filter((el) => el.type.toLowerCase().includes(this.search.toLowerCase()));
    else if(this.searchTerm==="location")
      return this.sportsObjects.filter((el) => el.location.toLowerCase().includes(this.search.toLowerCase()));
    else
      return this.sportsObjects.filter((el) => el.avgScore.toString().toLowerCase().includes(this.search.toLowerCase()));
    },
    filteredObjects(){
      return this.searchObjects.filter((el) => el.isOpen === this.showingOpen&& el.type.includes(this.showingType));
    },
    sortedObjects:function() {
      return this.filteredObjects.sort((a,b) => {
        let modifier = 1;
        if(this.currentSortDir === 'desc') modifier = -1;
        if(a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
        else if(a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
        return 0;
      });
    }
  },
});