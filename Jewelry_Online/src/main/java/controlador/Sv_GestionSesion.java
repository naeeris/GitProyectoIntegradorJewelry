package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Sv_GestionSesion
 */
public class Sv_GestionSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HttpSession sesion; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_GestionSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		sesion = request.getSession(); //Con esto abro la puerta a la sesion
		
		String nombre = "Noelia"; //Esto puede venir de un request get parameter de un formulario
		
		sesion.setAttribute("nombre", nombre); //Aquí puedo añadir todos los atributtes que necesite, así se guardan en la sesion abierta esos datos
		
		String respuesta = (String) sesion.getAttribute("nombre");
		
		System.out.println(respuesta);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
	}

}
