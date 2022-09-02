//import * as toast from 'toast.js';
Vue.component("sports-objects", {
	sportsObjects: null,
		title: "Sportski objekti",
		mode: "BROWSE",
		text: "",
		error: '',
	data: function (){
		
	},
	 template: ` 
    	<div>
    <div class="mainPage" style="margin-top: 60 ;">
        <h3 class="header">Naši Objekti</h3>
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
            <tr class="table-row"  v-for="(o, index) in sportsObjects">
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

    </div>
    	`,
	mounted() {
		axios.get('rest/sportsobjects/')
			.then(response => (this.sportsObjects = response.data))
	},
	
	
	methods: {
		filterName: function() {
    this.sportsObjects.filter((object) => {
      object.name.contains(text);
    })
  },
  filterType: function() {
    this.sportsObjects.filter((object) => {
      object.type.contains(text);
    })
  },
  filterAddress: function() {
    this.sportsObjects.filter((object) => {
      object.location.contains(text);
    })
  },
  filterScore: function() {
    this.sportsObjects.filter((object) => {
      object.avgScore.contains(text);
    })
  }
  
		
		
	}
});
