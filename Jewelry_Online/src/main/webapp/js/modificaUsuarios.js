/**
 * 
 */
	
	function llamada(){
		fetch('Sv_ListarUsuarios')
		.then(response => response.json())
		.then(data => pintar(data))
	}
	
	function eliminar(id_usuario){
		
		if(confirm("¿Seguro que deseas eliminar el usuario?")){
		fetch('Sv_EliminarUsuario?id_usuario=' + id_usuario)
		.then(response => response.json())
		.then(data => pintar(data))
		}else{
			
		}
	}
	
    function pintar(resultados){
		
		console.log(resultados); //Para comprobar si me saca los datos
		//Esto lo tengo que modificar y meter en el html para poder darle estilo desde el css
        let html = "<table >";
        
         // Añadir fila de encabezado
	    html += "<tr>";
	    html += "<th>ID</th>";
	    html += "<th>Permiso</th>";
	    html += "<th>Nombre</th>";
	    html += "<th>Apellidos</th>";
	    html += "<th>Domicilio</th>";
	    html += "<th>Código postal</th>";
	    html += "<th>Pais</th>";
	    html += "<th>Email</th>";
	    html += "<th>Telefono</th>";
	    html += "<th>Editar</th>";
	    html += "</tr>";

        for(let i=0; i<resultados.length; i++){

            html += "<tr><td>"+resultados[i].id_usuario+"</td>";
            html += "<td>"+resultados[i].permiso_usuario+"</td>";
            html += "<td>"+resultados[i].nombre+"</td>";
            html += "<td>"+resultados[i].apellidos+"</td>";
            html += "<td>"+resultados[i].domicilio+"</td>";
            html += "<td>"+resultados[i].cod_postal+"</td>";
            html += "<td>"+resultados[i].pais+"</td>";
            html += "<td>"+resultados[i].email+"</td>";
            html += "<td>"+resultados[i].telefono+"</td>";
            html += "<td><a href='editarUsuario.html?id_usuario="+resultados[i].id_usuario+"'>Editar</a></td>"
        
            html += "</tr>";
        }

        html += "</table>";

        document.getElementById("listado_usuarios_modificar").innerHTML = html;

    }
    

    //Gestor de eventos. Cuando el objeto window se ejecute, cuando haya leído todo, quiero que me ejecutes la siguiente función anónima
    //Quiero que cuando mi ventana (window) se cargue (onload), me ejecutes 'function'
   window.addEventListener("DOMContentLoaded", function(){
	   llamada();
	   
	   
	   
   })
  
