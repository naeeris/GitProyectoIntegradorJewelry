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
import java.util.ArrayList;

import dao.DaoUsuarios;

/**
 * Servlet implementation class Sv_ListarUsuarios
 */
public class Sv_ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sv_ListarUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		//ESTO LO DEJAMOS COMENTADO PORQUE ES LO QUE HACE QUE NOS SAQUE EL LISTAR DESDE LA BD A ECLIPSE
		try {
			ArrayList<Usuario> listaEnObjetos = DaoUsuarios.getInstance().listarUsuarios();
			
			for(Usuario u : listaEnObjetos) {
				
				System.out.println(u.toString());	
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al listar");
		}
		*/
		
		
		String respuestaJson;
		try {
			respuestaJson = DaoUsuarios.getInstance().listarJson();
			System.out.println("El listado ha llegado al servlet.");
			
			PrintWriter out = response.getWriter();
			
			out.print(respuestaJson);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
