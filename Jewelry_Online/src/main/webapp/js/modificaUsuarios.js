/**
 * 
 */
/**
 * 
 */
//HAGO LA LLAMADA DESDE JAVASCRIPT
   
	/* COMENTO ESTO PARA AÑADIR LA ABREVIATURA
        
        //Cuando se cargue la página quiero que me saque una función
        function llamada(){

           
            let xhr = new XMLHttpRequest();  //Esto funciona en segundo plano, el cliente no se entera de que está haciendo cosas
            let resultados;

            //Este método controla el cambio de JSON
            xhr.onreadystatechange = function(){ // Ejecuta cada estado de la conexión, la última es la 4
                if (xhr.readyState === 4){ //Cuando llegues al estado 4, quiero que hagas lo siguiente:
                    if (xhr.status === 200){ //el 200 significa que todo está bien (p.ej cuando te sale un error 404, si lo quisiera manejar puedes hacerlo desde este punto)
                        try{
                            resultados = JSON.parse(xhr.responseText);
                        }catch(e){
                            // TODO: handle exception
                        }
                        
                    }
                }
            }
            xhr.open("GET", "Sv_ListarUsuarios", false); //Este método sirve para indicar a dónde va a ir
            xhr.setRequestHeader("Content-Type", "application/json"); //OPCIONAL pero está más fino
            xhr.send(); //Este sirve para que se ponga en marcha
            
            pintar(resultados);

        }
	*/  

		
	function llamada(){
		fetch('Sv_ListarUsuarios')
		.then(response => response.json())
		.then(data => pintar(data))
	}
	
    function pintar(resultados){
		
		console.log(resultados); //Para comprobar si me saca los datos (DE MOMENTO NO ME LOS SACA)
		//Esto lo tengo que modificar y meter en el html para poder darle estilo desde el css
        let html = "<table border=1>";

        for(let i=0; i<resultados.length; i++){

            html += "<tr><td>"+resultados[i].nombre+"</td>";
            html += "<td>"+resultados[i].apellidos+"</td>";
            html += "<td>"+resultados[i].domicilio+"</td>";
            html += "<td>"+resultados[i].cod_postal+"</td>";
            html += "<td>"+resultados[i].pais+"</td>";
            html += "<td>"+resultados[i].email+"</td>";
            html += "<td>"+resultados[i].telefono+"</td>";
            html += "<td><a href='Sv_ModificarUsuarios?id="+resultados[i].id_usuario+"'>Editar</td>"
        
            html += "</tr>";
        }

        html += "</table>";

        document.getElementById("listado_usuarios").innerHTML = html;

    }
     //Hacerlo con el addevent listener
    function validarFormulario(){
			   
		    let nombre = document.getElementById('nombre').value;
			let email = document.getElementById('mail').value;
   		    let tel = document.getElementById('tel').value;

			let ok = true;
			if(nombre == ""){
				ok = false;
				document.getElementById('nombre').style.background = "red";
			}
			
			if(email == ""){
				ok = false;
				document.getElementById('mail').style.background = "red";
			}
			
			if(tel == ""){
				ok = false;
				document.getElementById('tel').style.background = "red";
			}
			
			if(ok == true){
				
				document.getElementById("altas").submit();
				
			}
				   
		}

    //Gestor de eventos. Cuando el objeto window se ejecute, cuando haya leído todo, quiero que me ejecutes la siguiente función anónima
    //Quiero que cuando mi ventana (window) se cargue (onload), me ejecutes 'function'
    window.onload = function(){

        llamada();

    }
    
  
