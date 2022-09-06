//import * as toast from 'toast.js';
Vue.component("choose-membership", {
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
	            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/csp">Naši Objekti</a></button></th>
	            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/ct">Moji Treninzi</a></button></th>
	            <th align="left" class="header_item"><button class="barButton"><a class="active" href="#/cm">Moje članarine</a></button></th>
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
    <div class="es01"></div>
  <div class="es02"></div>
</div>

    </div>
    	`,
    	
    methods: {
	
	showMembership : function(membership) {
		 this.mem = membership;
		 this.selected = true;
	},
	
	chooseMembership : function() {
		axios
         .get('rest/users/activeCustomer')
         .then(response => (this.customer = response.data));
		axios
		.post('rest/customers/updateMembership',{
			customerId : this.customer.id,
			membershipId : this.selectMembership.id
			})
			.then(response => toast("Članarina je ažurirana!"));
		},
		
	return : function(){
		this.selected=false;
	}
	
	},
	
	
		
	mounted() {
		axios 
		.get('rest/memberships/getAll')
		.then(response => (this.memberships = response.data));
	}
		
		
	
});
