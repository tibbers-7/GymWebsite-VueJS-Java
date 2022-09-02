Vue.component("start-page", {
	title: "StartPage",
	error: '',
	data: function(){
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