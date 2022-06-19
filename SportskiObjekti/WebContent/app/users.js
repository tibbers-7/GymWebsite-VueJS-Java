var app = new Vue({
	el: '#users',
	data: {
		products: null,
		title: "Korisnici",
		mode: "BROWSE",
		product : { username: null, password: null, name: null, last_name: null, gender: null, birthDate: null },
		error: '',
	},
	 template: ` 
    	<div>
    		<h3>Prikaz korisnika</h3>
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
    		<form id="forma" v-bind:hidden="mode=='BROWSE'" @submit='createUser'>
			<table>
				<tr>
					<td>Username</td>
					<td><input v-bind:disabled="mode!='CREATE'" type="text" v-model = "product.username" name="username" ></td>
				</tr>
				<tr>
					<td>Pass</td>
					<td><input type="text" name="password" v-model = "product.password"></td>
				</tr>
				<tr>
					<td>Ime</td>
					<td><input type="text" name="name" v-model = "product.name"></td>
				</tr>
				<tr>
					<td>Prezime</td>
					<td><input type="text" name="last_name" v-model = "product.last_name"></td>
				</tr>
				<tr>
					<td>Pol</td>
					<td><input type="text" name="gender" v-model = "product.gender"></td>
				</tr>
				<tr>
					<td>Datum rodjenja</td>
					<td><input type="text" name="birthDate" v-model = "product.birthDate"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Pošalji"></td>
				</tr>
			</table>
			</form>
		</div>		  
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
			this.product = product
			this.mode = 'EDIT'
			/*axios.put('rest/products/' + this.product.id, this.product)
					.then((response) => {
						alert('Proizvod je uspešno izmenjen ')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.id !== this.product.id)
						this.products.push(response.data)
					})*/
		},
		popustProduct: function (product) {
			this.product = product
			axios.put('rest/products/popust/' + this.product.sifra, this.product)
					.then((response) => {
						alert('Proizvod je uspešno izmenjen sa popustom')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.sifra !== this.product.sifra)
						this.products.push(response.data)
					})
		},
		showForm: function () {
			this.mode = 'CREATE'
		},
		createUser: function (event) {
				axios.post('rest/user/register', this.product)
					.then((response) => {
						alert('Novi nalog uspešno kreiran')
						this.mode = 'BROWSE'
						axios.get('rest/user/users')
							.then(response => (this.products = response.data))
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
			this.product = product
			if (this.product.mesta < 1) {
				this.error = "Nema vise karata";
				alert("Nema vise karata");
				return;
			}
			axios.put('rest/products/kupovina/' + this.product.sifra, this.product)
					.then((response) => {
						alert('Proizvod je kupljen ')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.sifra !== this.product.sifra)
						this.products.push(response.data)
					})
		}
	}
});
