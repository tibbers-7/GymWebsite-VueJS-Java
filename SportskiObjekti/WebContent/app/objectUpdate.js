Vue.component("object-update", {
	data: function() {
		return{
		title: "objectUpdate",
		object:null,
		manager:null,
		error: '',
		updatedObj:null
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
            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/msp">Naši Objekti</a></button></th>
            <th align="left"  class="header_item"><button class="barButton"><a class="inactive" href="#/ov">Moj Objekat</a></button></th>
        </tr>
    </table>
</div>

    <div class="updateObj_container">
        <div class="backBtn2_grid">
            <a href="#/ov" class="backBtn2"><img src="back.png" class="back_img"></img></a>
        </div>
        <div class="objInfo2_grid">
            <div class="objectView_container" style="width:40%;">
            
                <div class="grid_name">{{object.name}}</div>
                <div class="headers">
                    <ul style="list-style:none">
                        <li>Tip:</li>
                        <li>Status:</li>
                        <li>Radno Vreme:</li>
                    </ul>
                </div>
                <div class="values">
                    <ul style="list-style:none">
                        <li><input type="text" placeholder="{{object.type}}" v-model:"updatedObj.type"/></li>
                        <li>
                            <form>  
                                <select class="selectBox" v-model:"updatedObj.status">  
                                <option value = "A" > Aktivno  
                                </option>  
                                <option value = "I"> Neaktivno
                                </select>  
                            </form> </li>
                        <li style="margin-top:-8%;"><input type="text" placeholder="{{object.workHours}}" v-model:"updatedObj.workHours"/></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="updateObjBtn_grid">
            <button class="button2" v-on:click="updateObj(updatedObj)">Ažuriraj objekat</button>
        </div>
    </div>
    </div>    
    	`,
	mounted() {
		axios
         .get('rest/users/activeManager')
         .then(response => { 
			this.manager = response.data;
			axios
			.post('rest/sportsObjects/getObjectByManager', { id: this.manager.id })
			.then(response => this.object = response.data); 
			});
		this.updatedObj=object;
			
				},
	methods: {
		updateObj: function(updatedObj){
			axios
			.post('rest/sportsObjects/updateObj', { obj: this.updatedObj })
		}
	}
});