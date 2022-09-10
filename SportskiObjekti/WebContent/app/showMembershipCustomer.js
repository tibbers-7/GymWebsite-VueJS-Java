//import * as toast from 'toast.js';
Vue.component("show-membership", {
	data: function() {
		return{
		mem: null,
		customer: null,
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


	<div class="selectedMem_grid" >
    	<div class="back_Btn_grid">
	        <button v-on:click="goBack()" style="background: transparent; border: none;"><img src="back.png" class="back_img0"></img></button>
	    </div>
    <div class="selectedMem_info">
        <div class="objectView_container" style="width:60%;" >
            
            <div class="grid_name" style="width:100%">Članarina</div>
            <div class="headers">
                <ul style="list-style:none">
                    <li>Tip:</li>
                    <li>Cena:</li>
                    <li>Broj termina:</li>
                </ul>
            </div>
            <div class="values">
                <ul style="list-style:none">
                    <li>{{mem.membershipType}}</li>
                    <li>{{mem.price}}</li>
                    <li>{{mem.allowedNumber}}</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="chooseMem_Btn">
        <button class="button2" v-on:click="chooseMembership()">Odaberi</button>
    </div>
</div>

    </div>
    	`,
    mounted() {
		axios
         .get('rest/user/activeUser')
         .then(response => (this.customer = response.data));
        axios
         .get('rest/memberships/getSelected')
         .then(response => (this.mem = response.data));
	},
    	
    methods: {
		chooseMembership : function() {
			axios
	         .post('rest/memberships/postUser',this.customer)
	         .then(response => (toast(response.data)));
			
			axios
	         .post('rest/memberships/addMembership',this.mem)
	         .then(response => (toast(response.data)));
			
		},
		goBack:function(){
			router.push(`/sm`);
		}
	
	
	}	
	
});
