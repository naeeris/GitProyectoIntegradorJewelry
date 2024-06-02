
window.addEventListener('DOMContentLoaded', function() {
	
	let searchIcon = document.getElementById('icono-buscar');
    let searchBar = document.getElementById('barra-buscar');
	
	searchIcon.addEventListener('click', function(){
		
		searchBar.classList.toggle('hidden');
        searchBar.querySelector('input').focus();  // Pone el foco en el input cuando se muestra
		
	});
	
	
	
});


 