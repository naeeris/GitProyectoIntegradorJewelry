package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class Sv_ModificarUsuario
 */
public class Sv_ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HttpSession sesion;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_ModificarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		sesion = request.getSession();
		
		int idSesion = (int) request.getAttribute("id_usuario");
		
		if (idSesion != 0) {
		
			PrintWriter out = response.getWriter();
			
			int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
			System.out.println(id_usuario);
			
			
			Usuario u = new Usuario();
			
			try {
				u.obtenerUsuarioPorId(id_usuario);
				
				out.print(u.devuelveJson());
				//System.out.println(u.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}else {
			
			System.out.println("No puedes acceder.");
			response.sendRedirect("login.html");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int permiso_usuario = Integer.parseInt(request.getParameter("permiso_usuario"));
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String domicilio = request.getParameter("domicilio");
		int cod_postal = Integer.parseInt(request.getParameter("cod_postal"));
		String pais = request.getParameter("pais");
		String email = request.getParameter("email");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String contrasenya = request.getParameter("contrasenya");
		
		
		//Creo un objeto Usuario para poder actualizar todos los datos
		
		Usuario u = new Usuario(id_usuario, permiso_usuario, nombre, apellidos, domicilio, cod_postal, pais, email, telefono, contrasenya); //modificar registrousuario
		
		System.out.println(u.toString());  //modificar registrousuario
		
		try {
			u.actualizarUsuario(); //modificar registrousuario
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al actualizar.");
		}
		
		response.sendRedirect("modificarUsuarios.html");
		
		
	}

}
