package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoUsuarios;

/**
 * Servlet implementation class Sv_EliminarUsuario
 */
public class Sv_EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_EliminarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		System.out.println(id_usuario);
		
		Usuario u = new Usuario();
		
		try {
			u.eliminarUsuario(id_usuario);
			
			out.print(u.devuelveJson());
			System.out.println("Estoy borrando "+id_usuario);		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("listaUsuarios.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	

}
