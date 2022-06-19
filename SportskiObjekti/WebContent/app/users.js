var app = new Vue({
	el: '#users',
	data: {
		products: null,
		title: "Sportski objekti",
		mode: "BROWSE",
		selectedProduct: {},
		error: '',
	},
	 template: ` 
    	<div>
    		<h3>Prikaz proizvoda</h3>
    		<table border="1">
	    		<tr bgcolor="lightgrey">
	    			<th>Username</th>
	    			<th>Pass</th>
	    			<th>Ime</th>
	    			<th>Prezime</th>
	    			<th>Pol</th>
	    			<th>Datum rodjenja</th>
	    		</tr>
	    			
	    		<tr v-for="(p, index) in products">
	    			<td>{{p.username}}</td>
	    		 	<td>{{p.password}}</td>
	    		 	<td>{{p.name}}</td>
	    		 	<td>{{p.last_name}}</td>
	    		 	<td>{{p.gender}}</td>
	    		 	<td>{{p.birthDate}}</td>
	    		</tr>
	    	</table>
	    	<button v-on:click = "register">Registrujte se</button>
    	</div>
    	<form id="forma" v-bind:hidden="mode=='BROWSE'" @submit='createUser'>
			<table>
				<tr>
					<td>Sifra leta</td>
					<td><input v-bind:disabled="mode!='CREATE'" type="text" name="name" ></td>
				</tr>
				<tr>
					<td>Polaziste</td>
					<td><input type="text" name="name" ></td>
				</tr>
				<tr>
					<td>Odrediste</td>
					<td><input type="text" name="name" ></td>
				</tr>
				<tr>
					<td>Broj mesta</td>
					<td><input type="number" name="name" ></td>
				</tr>
				<tr>
					<td>Trajanje leta</td>
					<td><input type="number" name="name" ></td>
				</tr>
				<tr>
					<td>Presedanje</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Cena karte</td>
					<td><input type="number" name="name" ></td>
				</tr>
				<tr>
					<td><input type="submit" value="Pošalji"></td>
				</tr>
			</table>
		</form>		  
    	`,
	mounted() {
		axios.get('rest/user/users')
			.then(response => (this.products = response.data))
	},
	methods: {
		register : function() {
			this.mode = 'CREATE'
			
    	},
		editProduct: function (product) {
			this.selectedProduct = product
			this.mode = 'EDIT'
			/*axios.put('rest/products/' + this.selectedProduct.id, this.selectedProduct)
					.then((response) => {
						alert('Proizvod je uspešno izmenjen ')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.id !== this.selectedProduct.id)
						this.products.push(response.data)
					})*/
		},
		popustProduct: function (product) {
			this.selectedProduct = product
			axios.put('rest/products/popust/' + this.selectedProduct.sifra, this.selectedProduct)
					.then((response) => {
						alert('Proizvod je uspešno izmenjen sa popustom')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.sifra !== this.selectedProduct.sifra)
						this.products.push(response.data)
					})
		},
		showForm: function () {
			this.mode = 'CREATE'
			this.selectedProduct = { sifra: null, polaziste: null, odrediste: null, mesta: null, trajanje: null, presedanje: null, cena: null }
		},
		createUser: function (event) {
				axios.post('rest/user/register', this.selectedProduct)
					.then((response) => {
						alert('Novi nalog uspešno kreiran')
						this.mode = 'BROWSE'
						this.products.push(response.data)
					})
		},
		deleteProduct: function (product) {
			this.mode = 'BROWSE'
			axios.delete('rest/products/' + product.sifra)
				.then(() => {
					alert('Proizvod je uspesno obrisan')
					this.products = this.products.filter((p) => p.sifra !== product.sifra)
				})
		},
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
