//import * as toast from 'toast.js';
Vue.component("select-membership", {
	data: function() {
		return{
		memberships: null,
		mem: null,
		customer: null,
		selected:false,
		title: "Odabir Članarine",
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
			
			
    	<div class="barBase">
		    <table style="width: 20%;">
		        <tr>
		            <th align="left"  class="header_item"><button class="barButton"  v-on:click="homePage()"><p class="inactive">Naši Objekti</p></button></th>
		            <th align="left"  class="header_item"><button class="barButton" v-on:click="trainings()"><p class="inactive">Moji Treninzi</p></button></th>
		            <th align="left" class="header_item"><button class="barButton"><p class="active">Moje članarine</p></button></th>
		        </tr>
		    </table>
    	</div>

	<div v-if="selected != true" class="mems_grid">
	    <div class="memTable_grid">
	    <table class="table">
	        <tr class="table-header" >
	            <th class="header__item">Tip</th>
	            <th class="header__item">Broj termina</th>
	            <th class="header__item">Cena</th>
	        </tr>
	        <div class="table-content">  
	        <tr class="table-row"  v-for="(m, index) in memberships">
	            <td class="table-data">{{m.type}}</td>
	             <td class="table-data">{{m.price}}</td>
	             <td class="table-data">{{m.termNumber}}</td>
	        </tr>
	        </div>  
	    </table>
	    </div>  
	
	    <div class="selectMem_Btn">
	    <button class="mem_button" v-on:click="showMembership(m)">Odaberi</button>
	    </div>
	</div>


	<div v-if="selected != false" class="selectedMem_grid" >
    <div class="back_Btn_grid">
        <button v-on:click="return" style="background: transparent; border: none;"><img src="back.png" class="back_img0"></img></button>
    </div>
    <div class="selectedMem_info">
        <div class="objectView_container" style="width:60%;" >
            
            <div class="grid_name" style="width:100%">Članarina</div>
            <div class="headers">
                <ul style="list-style:none">
                    <li>Tip:</li>
                    <li>Cena:</li>
                    <li>Broj termina:</li>
                    <li>Status:</li>
                </ul>
            </div>
            <div class="values">
                <ul style="list-style:none">
                    <li>{{mem.type}}</li>
                    <li>{{mem.price}}</li>
                    <li>{{mem.termNumber}}</li>
                    <li>{{mem.status}}</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="chooseMem_Btn">
        <button class="mem_button" v-on:click="chooseMembership(m)">Odaberi</button>
    </div>
</div>

    </div>
    	`,
    mounted() {
		axios 
		.get('rest/memberships/getAll')
		.then(response => (this.memberships = response.data));
		
		axios
         .get('rest/user/activeUser')
         .then(response => (this.customer = response.data));
	},
    	
    methods: {
		showMembership : function(membership) {
		 this.mem = membership;
		 this.selected = true;
	},
	
	chooseMembership : function() {
		
		
	},
		
	
	
	}	
	
});
