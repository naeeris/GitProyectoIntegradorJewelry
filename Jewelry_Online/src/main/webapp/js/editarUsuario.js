/**
 * 
 */

var urlParams = new URLSearchParams(window.location.search);
var id_usuario = urlParams.get('id_usuario');

console.log(id_usuario);
 
function llamada(id_usuario) {

    fetch('Sv_ModificarUsuario?id_usuario=' + id_usuario)
        .then(response => response.json())
        .then(data => pintarFormulario(data))
}

//Función para otener el valor de un parametro en el GET 

function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	    results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

//Hacerlo con el addevent listener
function validarFormulario(){
		 
		let permiso_usuario = document.getElementById('permiso_usuario').value;   
	    let nombre = document.getElementById('nombre').value;
	    let apellidos = document.getElementById('apellidos').value;
	    let domicilio = document.getElementById('domicilio').value;
	    let cod_postal = document.getElementById('cod_postal').value;
	    let pais = document.getElementById('pais').value;
		let email = document.getElementById('email').value;
	    let telefono = document.getElementById('telefono').value;

		let ok = true;
		if(permiso_usuario == ""){
			ok = false;
			document.getElementById('permiso_usuario').style.background = "red";
		}
					
		if(nombre == ""){
			ok = false;
			document.getElementById('nombre').style.background = "red";
		}
		
		if(apellidos == ""){
			ok = false;
			document.getElementById('apellidos').style.background = "red";
		}
		
		if(domicilio == ""){
			ok = false;
			document.getElementById('domicilio').style.background = "red";
		}
		
		if(cod_postal == ""){
			ok = false;
			document.getElementById('cod_postal').style.background = "red";
		}
		
		if(pais == ""){
			ok = false;
			document.getElementById('pais').style.background = "red";
		}
		
		if(email == ""){
			ok = false;
			document.getElementById('email').style.background = "red";
		}
		
		if(telefono == ""){
			ok = false;
			document.getElementById('telefono').style.background = "red";
		}
		
		
		if(ok == true){
			
			document.getElementById("EditarUsuario").submit();
			
		}
			   
	}

//Función para pintar el formulario
function pintarFormulario(resultados){
		document.getElementById("id_usuario").value = resultados.id_usuario;
		document.getElementById("permiso_usuario").value = resultados.permiso_usuario;
		document.getElementById("nombre").value = resultados.nombre;
		document.getElementById("apellidos").value = resultados.apellidos;
		document.getElementById("domicilio").value = resultados.domicilio;
		document.getElementById("cod_postal").value = resultados.cod_postal;
		document.getElementById("pais").value = resultados.pais;
		document.getElementById("email").value = resultados.email;
		document.getElementById("telefono").value = resultados.telefono;
			



}





window.addEventListener('DOMContentLoaded', function(){
	
	let id_usuario = getParameterByName("id_usuario");
	llamada(id_usuario);
})
