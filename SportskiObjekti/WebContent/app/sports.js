var app = new Vue({
	el: '#sports',
	data: {
		products: null,
		title: "Sportski objekti",
		mode: "BROWSE",
		selectedProduct: {},
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
	    		 	<td>{{p.logoPath}}</td>
	    		 	<td>{{p.openHours}}</td>
	    		</tr>
	    	</table>
    	</div>		  
    	`,
	mounted() {
		axios.get('rest/sportsobjects/')
			.then(response => (this.products = response.data))
	},
	methods: {
		
		kupovinaProduct: function(product){
			this.mode = 'BROWSE'
			this.selectedProduct = product
			if (this.selectedProduct.mesta < 1) {
				this.error = "Nema vise karata";
				alert("Nema vise karata");
				return;
			}
			axios.put('rest/products/kupovina/' + this.selectedProduct.sifra, this.selectedProduct)
					.then((response) => {
						alert('Proizvod je kupljen ')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.sifra !== this.selectedProduct.sifra)
						this.products.push(response.data)
					})
		}
	}
});
