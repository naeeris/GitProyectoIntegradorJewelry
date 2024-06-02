/**
 * 
 */
//HAGO LA LLAMADA DESDE JAVASCRIPT
	function llamada(){
		fetch('Sv_ListarUsuarios')
		.then(response => response.json())
		.then(data => pintar(data))
	}
	
    function pintar(resultados){
		
		console.log(resultados); //Para comprobar si me saca los datos (DE MOMENTO NO ME LOS SACA)
		//Esto lo tengo que modificar y meter en el html para poder darle estilo desde el css
        let html = "<table border=1>";
        
         // Añadir fila de encabezado
	    html += "<tr>";
	    html += "<th>Nombre</th>";
	    html += "<th>Apellidos</th>";
	    html += "<th>Domicilio</th>";
	    html += "<th>Código postal</th>";
	    html += "<th>Pais</th>";
	    html += "<th>Email</th>";
	    html += "<th>Telefono</th>";
	    html += "</tr>";


        for(let i=0; i<resultados.length; i++){

            html += "<tr><td>"+resultados[i].nombre+"</td>";
            html += "<td>"+resultados[i].apellidos+"</td>";
            html += "<td>"+resultados[i].domicilio+"</td>";
            html += "<td>"+resultados[i].cod_postal+"</td>";
            html += "<td>"+resultados[i].pais+"</td>";
            html += "<td>"+resultados[i].email+"</td>";
            html += "<td>"+resultados[i].telefono+"</td>";
            html += "</tr>";
        }

        html += "</table>";

        document.getElementById("listado_usuarios").innerHTML = html;

    }

    //Gestor de eventos. Cuando el objeto window se ejecute, cuando haya leído todo, quiero que me ejecutes la siguiente función anónima
    //Quiero que cuando mi ventana (window) se cargue (onload), me ejecutes 'function'
    window.onload = function(){

        llamada();

    }
