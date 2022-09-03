Vue.component("customer-SP", {
	el: '#customerStartPage',
	data: {
		title: "customerStartPage",
		MODE: "LOGGED",
		sportsObjects: null,
		object:null,
		selected:false,
		customer:null,
		error: '',
	},
	 template: ` 
    	<div>
    	
    	<div class="header_container">
			        <div class="Img">
			            <img src="logo.png"style="height: 115px; width: 115px;"/>
			        </div>
			        <div class="Name"><h1> Fitness </h1></div>
			        <div class="Login"><button class="Button"   href="#/lp" v-bind:hidden="mode=='LOGGED'" >Prijavite se</button></div>
			        <div class="Register"><button class="Button"  href="#/rp" v-bind:hidden="mode=='LOGGED'" >Registrujte se</button></div>
			        <div class="Register"><button class="Button"  href="#/" v-bind:hidden="mode!=='LOGGED'" >Odjavite se</button></div>

			</div>
			
			
    <div class="barBase">
	    <table style="width: 20%;">
	        <tr>
	            <th align="left"  class="header_item"><button class="barButton"><a class="active" href="#/csp">Naši Objekti</a></button></th>
	            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/ct">Moji Treninzi</a></button></th>
	            <th align="left" class="header_item"><button class="barButton"><a class="inactive" href="#/cm">Moje članarine</a></button></th>
	        </tr>
	    </table>
    </div>
    
    
	<!-- TABELA SVIH OBJEKATA -->
		
		<div v-if="selected==true">
        <div class="es001"></div>
        <div class="es002"></div>
        <div class="es003"></div>
        
        <div class="objectSpec_grid">
          <div class="objectFilter_grid">
            <div class="objFilter1_grid">
                <form>  
                    <label style="font-size: large;"> Tip Objekta </label>  
                    <select class="selectBox">  
                    <option value = "Gym" > Teretana   
                    </option>  
                    <option value = "Sauna"> Sauna   
                    </option>  
                    <option value = "Spa"> Spa  
                    </select>  
                </form> 
            </div>
            <div class="objFilter2_grid">
                <form>  
                    <label style="font-size: large;"> Dostupnost </label>  
                    <select class="selectBox"> 
                    <option value = "Open"> Otvoreno   
                    </option>  
                    <option value = "Closed"> Zatvoreno   
                    </option>  
                    </select>  
                    </form>  
              </div>
            </div>
          <div class="objectSearch_grid">
                <input type="text" class="input-search" placeholder="Pretraga">
             
          </div>
        </div>
       
      </div>

      <div class="objectTable_grid">
        <div class="objectsSort_grid">
            <button class="ascButton" v-on:click="ascName()" style="margin-left:12%"><img src="arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="descButton" v-on:click="descName()"><img src="arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="ascButton" v-on:click="ascLoc()" style="margin-left:42%"><img src="arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="descButton" v-on:click="descLoc()"><img src="arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="ascButton" v-on:click="ascGrade()" style="margin-left:8%"><img src="arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
            <button class="descButton" v-on:click="descGrade()"><img src="arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
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
            <tr class="table-row"  v-for="(o, index) in sportsObjects" v-on:click="selectedObject(o)">
                <td class="table-data">{{o.name}}</td>
                 <td class="table-data">{{o.type}}</td>
                 <td class="table-data">{{o.services}}</td>
                 <td class="table-data">{{o.isOpen}}</td>
                 <td class="table-data">{{o.location}}</td>
                 <td class="table-data">{{o.avgScore}}</td>
                 <td class="table-data"><img src={{o.logoPath}}></td>
                 <td class="table-data">{{o.openHours}}</td>
            </tr>
            </div>  
        </table>
    </div>
    
    
    <!-- PRIKAZ OBJEKTA -->
    
    <div class="objectInfo_grid" v-if="selected == true">
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
	        <a href="#/csp"><img src="back.png" class="back_img"></img></a>
	    </div>
	    <div class="es1"></div>
	    <div class="es3"></div>
	    <div class="es2"></div>
	  </div>
    </div>       
    </div>    
    	`,
	mounted() {
		axios
		         .get('rest/users/activeCustomer')
		         .then(response => this.customer = response.data);
		axios.get('rest/sportsobjects/')
			.then(response => (this.sportsObjects = response.data))
			
				},
	methods: {
		selectedObject: function(sportsObject){
			this.object=sportsObject;
			this.selected=true;
		},
		
		
		
	}
});