//import * as toast from 'toast.js';
var app = new Vue({
	el: '#sports',
	data: {
		products: null,
		title: "Sportski objekti",
		mode: "BROWSE",
		text: "",
		error: '',
	},
	 template: ` 
    	<div>
    		<h3>Prikaz objekata</h3>
    		<table border="1">
	    		<tr bgcolor="lightgrey">
	    			<th>Naziv</th>
	    			<th>Tip</th>
	    			<th>Ponuda</th>
	    			<th>Otvoreno</th>
	    			<th>Adresa</th>
	    			<th>Ocena</th>
	    			<th>Logo</th>
	    			<th>Radno vreme</th>
	    		</tr>
	    			
	    		<tr v-for="(p, index) in products">
	    			<td>{{p.name}}</td>
	    		 	<td>{{p.type}}</td>
	    		 	<td>{{p.services}}</td>
	    		 	<td>{{p.isOpen}}</td>
	    		 	<td>{{p.location}}</td>
	    		 	<td>{{p.avgScore}}</td>
	    		 	<td><img src={{p.logoPath}}></td>
	    		 	<td>{{p.openHours}}</td>
	    		</tr>
	    	</table>
	    	<input type="text" name="name" v-model = "text">
	    	<button v-on:click = "filterName">Filter po imenu</button>
	    	<button v-on:click = "filterType">Filter po tipu</button>
	    	<button v-on:click = "filterAddress">Filter po adresi</button>
	    	<button v-on:click = "filterScore">Filter po oceni</button>
    	</div>		  
    	`,
	mounted() {
		axios.get('rest/sportsobjects/')
			.then(response => (this.products = response.data))
	},
	
	
	methods: {
		filterName: function() {
    return this.products.filter((product) => {
      product.name.contains(text);
    })
  },
  filterType: function() {
    return this.products.filter((product) => {
      product.type.contains(text);
    })
  },
  filterAddress: function() {
    return this.products.filter((product) => {
      product.location.contains(text);
    })
  },
  filterScore: function() {
    return this.products.filter((product) => {
      product.avgScore.contains(text);
    })
  }
  
		
		
	}
});
