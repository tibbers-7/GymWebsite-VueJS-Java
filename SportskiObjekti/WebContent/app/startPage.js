Vue.component("start-page", {
	data: {
		  
	},
	template: ` 
<div>
	{{title}}
	<form>
		<label>Ime</label>
				<label>Cena</label>
	</form>
</div>		  
`
	, 
	methods : {
		
	},
	mounted () {
		toast("uspeo");
    }
});