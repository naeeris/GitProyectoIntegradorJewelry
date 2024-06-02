package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import dao.DaoUsuarios;

/**
 * Servlet implementation class Sv_Login
 */
public class Sv_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    HttpSession sesion;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String contrasenya = request.getParameter("contrasenya");
		
		
		Usuario u = new Usuario();
		
		u.setEmail(email);
		u.setContrasenya(Usuario.myMD5(contrasenya));
		
		//proteccion
		
		try {
			if (u.logeoUsuario(contrasenya)) {
				
				sesion = request.getSession();
				
				sesion.setAttribute("id_usuario", u.getId_usuario());
				sesion.setAttribute("permiso_usuario", u.getPermiso_usuario());
				sesion.setAttribute("nombre", u.getNombre());
				
				int permiso_sesion = (int) sesion.getAttribute("permiso_usuario");
				if(permiso_sesion == 1) {
					response.sendRedirect("indexadmin.html");
				}else {
					response.sendRedirect("index.html");
				}
				
			}else {
				response.sendRedirect("login.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de servlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
