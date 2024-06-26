package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Usuario;

public class DaoUsuarios {
	
	/**
	 * Variable para conexion a la base de datos.
	 * Mantiene la conexion activa a la base de datos para realizar operaciones CRUD.
	 */
	private Connection DBconexion = null;
	
	/**
	 * Instancia unica de la clase DaoUsuarios.
	 * Utiliza el patron Singleton para asegurar que solo exista una instancia de DaoUsuarios.
	 */
	private static DaoUsuarios instance = null;
	
	/**
	 * Constructor de la clase DaoUsuarios que inicializa la conexion a la base de datos utilizando la clase ConexionDB.
	 * @throws SQLException 
	 */
	public DaoUsuarios() throws SQLException {
		DBconexion = ConexionDB.getConnection();
	}
	
	
	/**
	 * Metodo que implementa el patron Singleton para asegurar que solo exista una instancia de DaoUsuarios.
	 * @return Retorna una instancia unica de DaoUsuarios.
	 * @throws SQLException 
	 */
	public static DaoUsuarios getInstance() throws SQLException {
		
		if (instance == null) {
			instance = new DaoUsuarios();
		}
		return instance;
	
	}
	
	/**
	 * 	Metodo para insertar un usuario en la base de datos del objeto usuario.
	 * @param registro_usuario Objeto tipo usuarios.
	 * @throws SQLException
	 */
	public void insertarUsuario(Usuario registro_usuario) throws SQLException {
		
		String sql = "INSERT INTO usuarios (permiso_usuario, nombre, apellidos, domicilio, cod_postal, pais, email, telefono, contrasenya) VALUES (9, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = DBconexion.prepareStatement(sql);
		
		ps.setString(1, registro_usuario.getNombre());
		ps.setString(2, registro_usuario.getApellidos());
		ps.setString(3, registro_usuario.getDomicilio());
		ps.setInt(4, registro_usuario.getCod_postal());
		ps.setString(5, registro_usuario.getPais());
		ps.setString(6, registro_usuario.getEmail());
		ps.setInt(7, registro_usuario.getTelefono());
		ps.setString(8, registro_usuario.getContrasenya());
		
		int filas = ps.executeUpdate();
		
		ps.close();
		
	}
	
	/**
	 * 	ArrayList para poder almacenar los datos que recogemos de la base de datos.
	 * @return Devuelve una matriz ArrayList con todos los datos que tenemos insertados en la base de datos del objeto Usuario.
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
	
	
	/**
	 * Metodo que sirve para convertir los datos del ArrayList, obtenidos de la base de datos, en formato Json.
	 * @return Devuelve un String que incluye todos los datos que hemos sacado de la base de datos.
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
	 * Metodo que extrae los datos de la base de datos teniendo en cuenta el id_usuario.
	 * @param id_usuario Atributo unico que identifica al objeto Usuario.
	 * @return Devuelve el objeto Usuario con todos sus datos. 
	 * @throws SQLException
	 */
	public Usuario obtenerUsuarioPorId(int id_usuario) throws SQLException {
		
		String sql = "SELECT * FROM usuarios WHERE id_usuario=?";
		
		PreparedStatement ps = DBconexion.prepareStatement(sql);
		ps.setInt(1, id_usuario);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Usuario u = new Usuario(rs.getInt("id_usuario"), rs.getInt("permiso_usuario"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("domicilio"), rs.getInt("cod_postal"), rs.getString("pais"), rs.getString("email"), rs.getInt("telefono"), rs.getString("contrasenya"));
	
		return u;
	}
	
	/**
	 * Metodo para actualizar los datos en la base de datos teniendo en cuenta el id_usuario.
	 * @param u Obtejo tipo Usuario.
	 * @throws SQLException
	 */
	public void actualizarUsuario(Usuario u) throws SQLException {
		
		String sql = "UPDATE usuarios SET permiso_usuario=?, nombre=?, apellidos=?, domicilio=?,  cod_postal=?, pais=?, email=?, telefono=?, contrasenya=? WHERE id_usuario=?";
		
		PreparedStatement ps = DBconexion.prepareStatement(sql);
		
		ps.setInt(1, u.getPermiso_usuario());
		ps.setString(2, u.getNombre());
		ps.setString(3, u.getApellidos());
		ps.setString(4, u.getDomicilio());
		ps.setInt(5, u.getCod_postal());
		ps.setString(6, u.getPais());
		ps.setString(7, u.getEmail());
		ps.setInt(8, u.getTelefono());
		ps.setString(9, u.getContrasenya());
		ps.setInt(10, u.getId_usuario());
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	/**
	 * Metodo para eliminar un usuario de la base de datos teniendo en cuenta el id_usuario.
	 * @param id_usuario 
	 * @throws SQLException
	 */
	public void eliminarUsuario(int id_usuario) throws SQLException {
		
		String sql = "DELETE FROM usuarios WHERE id_usuario=?";
		
		PreparedStatement ps = DBconexion.prepareStatement(sql);
		ps.setInt(1, id_usuario);
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	/**
	 * Metodo para autenticar a un usuario en el sistema utilizando su correo electronico y contraseña.
	 * Realiza una consulta a la base de datos para verificar las credenciales del usuario.
	 * @param u El objeto Usuario que contiene el correo electronico del usuario que intenta iniciar sesión.
	 * @param contrasenya La contraseña proporcionada por el usuario para iniciar sesióo.
	 * @return Un objeto Usuario con los datos del usuario autenticado si la autenticacion es exitosa, o null si las credenciales son incorrectas.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL o al acceder a la base de datos.
	 */
	public Usuario logeandoUsuario(Usuario u, String contrasenya) throws SQLException {
		
		String sql = "SELECT * FROM usuarios WHERE email=? AND contrasenya=?";
		
		PreparedStatement ps = DBconexion.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		ps.setString(2, contrasenya);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Usuario aux = new Usuario(rs.getInt("id_usuario"), rs.getInt("permiso_usuario"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("domicilio"), rs.getInt("cod_postal"), rs.getString("pais"), rs.getString("email"), rs.getInt("telefono"), rs.getString("contrasenya"));
		
		return aux;
		
	}
	
}
