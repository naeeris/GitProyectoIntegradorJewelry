/**
 * 
 */
	function validarFormulario() {
    let nombre = document.forms["AltaUsuario"]["nombre"].value;
    let apellidos = document.forms["AltaUsuario"]["apellidos"].value;
    let domicilio = document.forms["AltaUsuario"]["domicilio"].value;
    const codPostal = document.forms["AltaUsuario"]["cod_postal"].value;
    const pais = document.forms["AltaUsuario"]["pais"].value;
    const email = document.forms["AltaUsuario"]["email"].value;
    const telefono = document.forms["AltaUsuario"]["telefono"].value;
    const contrasenya = document.forms["AltaUsuario"]["contrasenya"].value;

	
    if (nombre === "" || apellidos === "" || domicilio === "" || codPostal === "" || pais === "" || email === "" || telefono === "" || contrasenya === "") {
        alert("Todos los campos deben ser completados.");
        return false;
    }

    return true;
}


window.addEventListener("DOMContentLoaded", function(){
	
	validarFormulario();
	
});