//import * as toast from 'toast.js';
Vue.component("memberships-customer", {
	data: function(){
		return{
		membership: null,
		customer: null,
		title: "Članarine",
		text: "",
		error: '',
		}
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
			
			
    	<div class="bar">
    	<ul>
			 <li><a class="inactive" href="#/csp">Naši objekti</a></li>
			 <li><a class="inactive" href="#/ct">Moji Treninzi</a></li>
			 <li><a class="active" href="#/cm">Moje Članarine</a></li>
		</ul>
		</div>
		
		
    <div class="membership_grid">
        <div class="active_membership">
            <div class="am_container">
                <div class="grid_name">Aktivna članarina</div>
                <div class="headers">
                    <ul style="list-style: none;">
                        <li>Tip:</li>
                        <li>Datum kupovine:</li>
                        <li>Rok važenja:</li>
                        <li>Cena:</li>
                        <li>Broj termina:</li>
                    </ul>
                </div>
                <div class="values">
                    <ul style="list-style: none;">
                        <li>{{m.type}}</li>
                        <li>{{m.dateBought}}</li>
                        <li>{{m.expiry}}</li>
                        <li>{{m.price}}</li>
                        <li>{{m.termNumber}}</li>
                    </ul>
                </div>
              </div>
        </div>

        <div class="points">
            <div class="points_container">
                <div class="points_text">
                Broj poena: {{c.points}}
            </div>
            </div>
        </div>

        <div class="b1_mem">
            <button class="mem_button" href="#/cmem">Otkaži članarinu</button>
        </div>

        <div class="b2_mem">
            <button class="mem_button" href="#/nmem">Izaberi novu članarinu</button>
        </div>
    </div>
</div>

    </div>
    	`,
	mounted() {
		axios
         .get('rest/users/activeCustomer')
         .then(response => { 
			this.customer = response.data;
			axios
			.post('rest/users/getMembership', { id: this.customer.id })
			.then(response => this.membership = response.data); 
			});
	}
		
		
	
});
