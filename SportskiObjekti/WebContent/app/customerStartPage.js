Vue.component("start-page", {
	
	data: {
		title: "StartPage",
		error: '',
	},
	 template: ` 
    	<div>
    	<ul>
			<li><a class="active" href="#/">Home</a></li>
			 <li><a class="active" href="#/h"></a></li>
			 <li><a class="active" href="#/so">Naši objekti</a></li>
		</ul>
		</div>       
    	`,
	mounted() {

				},
	methods: {
		
		
		
	},
	mounted () {
         axios
         .get('rest/users/activeCustomer')
         .then(response => this.customer = response.data);
    }
});