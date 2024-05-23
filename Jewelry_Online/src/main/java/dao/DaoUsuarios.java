package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Usuario;

public class DaoUsuarios {
	
	
	private Connection DBconexion = null;
	private static DaoUsuarios instance = null;
	
	public DaoUsuarios() throws SQLException {
		DBconexion = ConexionDB.getConnection();
	}
	
	
	/**
	 * Este método es el que utilizo para aplicar el patrón Singleton
	 * @return
	 * @throws SQLException
	 */
	public static DaoUsuarios getInstance() throws SQLException {
		
		if (instance == null) {
			instance = new DaoUsuarios();
		}
		return instance;
	
	}
	
	//Creo un método para insertar usuarios
	/**
	 * 	Método para insertar un usuario en la base de datos del objeto usuario.
	 * @param registro_usuario Objeto tipo usuarios.
	 * @throws SQLException
	 */
	public void insertarUsuario(Usuario registro_usuario) throws SQLException {
		
		String sql = "INSERT INTO usuarios (nombre, apellidos, domicilio, cod_postal, pais, email, telefono, contrasenya) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = DBconexion.prepareStatement(sql);
		
		ps.setString(1, registro_usuario.getNombre());
		ps.setString(2, registro_usuario.getApellidos());
		ps.setString(3, registro_usuario.getDomicilio());
		ps.setInt(4, registro_usuario.getCod_postal());
		ps.setString(5, registro_usuario.getPais());
		ps.setString(6, registro_usuario.getEmail());
		ps.setInt(7, registro_usuario.getTelefono());
		ps.setString(8, registro_usuario.getContrasenya());
		
		//Tenemos el executeUpdate para realizar el envío y el execute query cuando hacemos la devolución
		int filas = ps.executeUpdate();
		
		ps.close();
		
	}
	
	/**
	 * 	ArrayList para poder almacenar los datos que recogemos de la base de datos.
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Usuario> listarUsuarios () throws SQLException {
		
		String sql = "SELECT * FROM usuarios";
		
		PreparedStatement  ps = DBconexion.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Usuario> result = null;
		
		while(rs.next()) {
			
			if(result == null) {
				result = new ArrayList<Usuario>();
			}
			
			result.add(new Usuario(rs.getInt("id_usuario"), rs.getInt("permiso_usuario"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("domicilio"), rs.getInt("cod_postal"), rs.getString("pais"), rs.getString("email"), rs.getInt("telefono"), rs.getString("contrasenya")));
			
		}
		
		return result;
	}
	
	
	//Necesito otro método para que me convierta los datos del ArrayList (o de la bbdd) en un formato Json
	/**
	 * Método que sirve para convertir los datos del ArrayList, obtenidos de la base de datos, en formato Gson.
	 * @return Devuelve un string que incluye todos los datos que hemos sacado de la base de datos.
	 * @throws SQLException
	 */
	public String listarJson() throws SQLException {
		
		String txtJSON = "";
		//Ya tengo importada la librería Gson, entonces solo tengo que crear un nuevo objeto de tipo Gson
		//Creo un objeto Gson llamado 'gson'
		Gson gson = new Gson();
		
		//Una vez tengo hecho el objeto, quiero que me metas en el 'json' lo que me devuelva el objeto gson con el método toJson, al cual lo que le voy a dar es el método y el listarUsuarios
		txtJSON = gson.toJson(this.listarUsuarios()); //Aquí yo ya tengo un String con todos los datos en formato Json
		
		//Y quiero que me devuelva el archivo json
		return txtJSON;
		
	}
	/**
	 * 
	 * @param id_usuario
	 * @return
	 * @throws SQLException
	 */
	public Usuario obtenerUsuario(int id_usuario) throws SQLException {
		
		String sql = ("SELECT*FROM usuarios WHERE id=?");
		
		PreparedStatement ps = DBconexion.prepareStatement(sql);
		ps.setInt(1, id_usuario);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Usuario u = new Usuario(rs.getInt("id_usuario"), rs.getInt("permiso_usuario"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("domicilio"), rs.getInt("cod_postal"), rs.getString("pais"), rs.getString("email"), rs.getInt("telefono"), rs.getString("contrasenya"));
	
		return u;
	}
	

}
