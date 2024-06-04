/**
 * 
 */

function validarFormulario(){
		 
		let permiso_usuario = document.getElementById('permiso_usuario').value;   
	    let nombre = document.getElementById('nombre').value;
	    let apellidos = document.getElementById('apellidos').value;
	    let domicilio = document.getElementById('domicilio').value;
	    let cod_postal = document.getElementById('cod_postal').value;
	    let pais = document.getElementById('pais').value;
		let email = document.getElementById('email').value;
	    let telefono = document.getElementById('telefono').value;
	    let contrasenya = document.getElementById('contrasenya').value;

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
		
		if(contrasenya == ""){
			ok = false;
			document.getElementById('contrasenya').style.background = "red";
		}
		
		
		if(ok == true){
			
			document.getElementById("AltaUsuario").submit();
			
		}
			   
	}


window.addEventListener("DOMContentLoaded", function(){
	
	validarFormulario();
	
});