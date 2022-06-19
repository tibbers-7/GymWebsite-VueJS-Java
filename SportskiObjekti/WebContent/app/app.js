var app = new Vue({
	el: '#products',
	data: {
		products: null,
		title: "Vue.js tehnologija za letove",
		mode: "BROWSE",
		selectedProduct: {},
		error: '',
	},
	mounted() {
		axios.get('rest/sportsobjects/')
			.then(response => (this.products = response.data))
	},
	methods: {
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
		createOrEditProduct: function (event) {
			this.error = ""
			if (this.selectedProduct.polaziste.toLowerCase() === this.selectedProduct.odrediste.toLowerCase()) {
				this.error = "Polaziste i odrediste ne mogu biti isti";
				event.preventDefault();
				return;
			}
			
			if (this.mode == 'CREATE') {
				for(p of this.products){
					if(this.selectedProduct.sifra.toLowerCase() === p.sifra.toLowerCase()){
						this.error = "Sifra vec postoji";
						event.preventDefault();
						return;
					}
				}
				axios.post('rest/products', this.selectedProduct)
					.then((response) => {
						alert('Novi proizvod uspešno kreiran')
						this.mode = 'BROWSE'
						this.products.push(response.data)
					})

			} else {
				axios.put('rest/products/' + this.selectedProduct.sifra, this.selectedProduct)
					.then((response) => {
						alert('Proizvod je uspešno izmenjen ')
						this.mode = 'BROWSE'
						this.products = this.products.filter((p) => p.sifra !== this.selectedProduct.sifra)
						this.products.push(response.data)
					})
			}

			event.preventDefault();
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
