Vue.component("users-admin", {
	data:function() {
		return{
		users: null,
		title: "Korisnici",
		product : { username: null, password: null, name: null, last_name: null, gender: null, birthDate: null },
		error: '',
		}
	},
	 template: ` 
    	<div class="bodyStyle">
    	
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
		            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/asp">Naši Objekti</a></button></th>
			        <th align="left"  class="header_item"><button class="barButton"><a class="active" href="#/au">Korisnici</a></button></th>
		        </tr>
		    </table>
		</div>

<div >
    <div>
        <button class="button2" style="margin-left: 70%; margin-top:1%; width:20%; height:7%;">Dodaj novog korisnika</button>
    </div>
    
    <div class="objectSpec_grid" style="margin-top:1%;">
      <div class="objectFilter_grid">
        <div class="objFilter1_grid">
            <form>  
                <label style="font-size: large;"> Uloga </label>  
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
                <label style="font-size: large;"> Tip </label>  
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


  <div class="objectTable_grid" style="margin-top: -3%;">
    <div class="objectsSort_grid">
        
        <div style="position:relative;top:25px;">
        <button class="ascButton" v-on:click="ascName()" style="margin-left:10%"><img src="arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        <button class="descButton" v-on:click="descName()"><img src="arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        <button class="ascButton" v-on:click="ascLoc()" style="margin-left:8%"><img src="arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        <button class="descButton" v-on:click="descLoc()"><img src="arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        <button class="ascButton" v-on:click="ascGrade()" style="margin-left:8%"><img src="arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        <button class="descButton" v-on:click="descGrade()"><img src="arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        </div>
        <button class="ascButton" v-on:click="ascGrade()" style="margin-left:90%"><img src="arrowUp.png" style="width: 20px; height: 20px; margin:0px;"/></button>
        <button class="descButton" v-on:click="descGrade()"><img src="arrowDown.png" style="width: 20px; height: 20px; margin:0px;"/></button>
    </div>
    <table class="table">
        <tr class="table-header" >
            <th class="header__item">Ime</th>
            <th class="header__item">Prezime</th>
            <th class="header__item">Korisničko ime</th>
            <th class="header__item">Pol</th>
            <th class="header__item">Datum rođenja</th>
            <th class="header__item">Uloga</th>
            <th class="header__item">Tip kupca</th>
            <th class="header__item">Članarina</th>
            <th class="header__item">Poeni</th>
        </tr>
        <div class="table-content">  
        <tr class="table-row"  v-for="(u, index) in users">
            <td class="table-data">{{u.name}}</td>
             <td class="table-data">{{u.last_name}}</td>
             <td class="table-data">{{u.username}}</td>
             <td class="table-data">{{u.gender}}</td>
             <td class="table-data">{{u.birthDate}}</td>
             <td class="table-data">{{u.userType}}</td>
             <td class="table-data">{{u.customerType}}</td>
             <td class="table-data">{{u.membershipID}}</td>
             <td class="table-data">{{u.points}}</td>
        </tr>
        </div>  
    </table>
	</div>
		</div>		  
    	`,
	mounted() {
		axios.get('rest/user/users')
			.then(response => (this.users = response.data))
	},
	methods: {
		
		
	}
});
