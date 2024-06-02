package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoUsuarios;

/**
 * <h2> Clase Usuario </h2>
 * Se pueden crear y leer los datos de los usuarios 
 * @author Noelia Cauqui
 * @version V2.0
 */
public class Usuario {
	
	//Declaramos las variables de la clase Usuario
	
	/**
	 * Atributo id del usuario.
	 */
	private int id_usuario; 
	/**
	 * Atributo permiso del usuario
	 * Existen dos permisos
	 * 0 para usuario registrado y 1 para usuario administrador
	 */
	private int permiso_usuario;
	
	/**
	 * Atributo nombre del usuario
	 */
	private String nombre;
	
	/**
	 * Atributo apellidos del usuario
	 */
	private String apellidos;
	
	/**
	 * Atributo domicilio del usuario
	 */
	private String domicilio;
	
	/**
	 * Atributo codigo postal del usuario
	 */
	private int cod_postal;
	
	/**
	 * Atributo pais del usuario
	 */
	private String pais;
	
	/**
	 * Atributo email del usuario
	 */
	private String email;
	
	/**
	 * Atributo telefono del usuario
	 */
	private int telefono;
	
	/**
	 * Atributo contrasenya del usuario
	 * Este atributo en el caso de que no fuera un proyecto academico no debería estar
	 */
	private String contrasenya; 
	
	
	/**
	 * Constructor por defecto para generar un objeto vacío de tipo usuario
	 */
	public Usuario() {
		
	}
	
	/**
	 * Constructor con todos los parámetros del objeto Usuario
	 * Crea un objeto Usuario con los siguientes parametros 
	 * Permite extraer y manipular los datos del usuario almacenados en la base de datos
	 * @param id_usuario Identificador único del usuario
	 * @param permiso_usuario Permiso del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param domicilio Domicilio del usuario
	 * @param cod_postal Codigo postal del domicilio del usuario
	 * @param pais Pais del usuario
	 * @param email Correo electronico del usuario
	 * @param telefono Numero de telefono del usuario
	 * @param contrasenya Contrasenya del usuario
	 */
	public Usuario(int id_usuario, int permiso_usuario, String nombre, String apellidos, String domicilio, int cod_postal, String pais, String email, int telefono, String contrasenya) {
		
		this.id_usuario = id_usuario;
		this.permiso_usuario = permiso_usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.cod_postal = cod_postal;
		this.pais = pais;
		this.email = email;
		this.telefono = telefono;
		this.contrasenya = contrasenya;
	}
	
	/**
	 * Constructor para crear un objeto usuario desde un formulario HTML
	 * No incluye los parámetros id_usuario y permiso_usuario
	 * @param nombre Nombre de usuario
	 * @param apellidos Apellidos de usuario
	 * @param domicilio Domicilio del usuario 
	 * @param cod_postal Codigo postal del domicilio del usuario
	 * @param pais Pais del usuario
	 * @param email Correo electronico del usuario
	 * @param telefono Numero de telefono del usuario
	 * @param contrasenya Contrasenya del usuario
	 */
	public Usuario(String nombre, String apellidos, String domicilio, int cod_postal, String pais, String email, int telefono, String contrasenya) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.cod_postal = cod_postal;
		this.pais = pais;
		this.email = email;
		this.telefono = telefono;
		this.contrasenya = contrasenya;
	}
	
	
	/**
	 * Método para llamar a la inserción de datos de DaoUsuarios aplicando el patrón Singleton.
	 * @throws SQLException
	 */
	public void insertarUsuario () throws SQLException {
	
		DaoUsuarios.getInstance().insertarUsuario(this);
		
	}
	
	
	/**
	 * Método para obtener los datos de usuario de la base de datos teniendo en cuenta su id_usuario.
	 * @param id_usuario
	 * @throws SQLException
	 */
	public void obtenerUsuarioPorId (int id_usuario) throws SQLException {
		
		Usuario aux = DaoUsuarios.getInstance().obtenerUsuarioPorId(id_usuario);
		
		this.setId_usuario(aux.getId_usuario());
		this.setPermiso_usuario(aux.getPermiso_usuario());
		this.setNombre(aux.getNombre());
		this.setApellidos(aux.getApellidos());
		this.setDomicilio(aux.getDomicilio());
		this.setCod_postal(aux.getCod_postal());
		this.setPais(aux.getPais());
		this.setEmail(aux.getEmail());
		this.setTelefono(aux.getTelefono());
		this.setContrasenya(aux.getContrasenya());
		
	}
	
	public String devuelveJson() {
		
		String txtJson = "";
		
		Gson gson = new Gson();
		
		txtJson = gson.toJson(this);
		
		return txtJson;
	}
	
	/**
	 * Método para llamar a la modificación de datos de DaoUsuarios aplicando el patrón Singleton.
	 * @throws SQLException
	 */
	public void actualizarUsuario() throws SQLException {
		
		DaoUsuarios.getInstance().actualizarUsuario(this);
	}
	/**
	 * 
	 * @param id_usuario
	 * @throws SQLException
	 */
	public void eliminarUsuario(int id_usuario) throws SQLException {
		
		DaoUsuarios.getInstance().eliminarUsuario(id_usuario);
		
		
	}
	
	/**
	 * 
	 * @param contrasenya
	 * @return
	 * @throws SQLException
	 */
	public boolean logeoUsuario(String contrasenya) throws SQLException {
		
		boolean ok = false;
		
		Usuario aux = DaoUsuarios.getInstance().logeandoUsuario(this, contrasenya);
		
		if (aux != null) {
			
			ok = true;
			
			this.setId_usuario(aux.getId_usuario());
			this.setPermiso_usuario(aux.getPermiso_usuario());
			this.setNombre(aux.getNombre());
			this.setApellidos(aux.getApellidos());
			this.setDomicilio(aux.getDomicilio());
			this.setCod_postal(aux.getCod_postal());
			this.setPais(aux.getPais());
			this.setEmail(aux.getEmail());
			this.setTelefono(aux.getTelefono());
			this.setContrasenya(aux.getContrasenya());
		}
		
		return ok;
	}
	
	public static String myMD5(String contrasenya) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(contrasenya.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	//GETTERS Y SETTERS
	
	/**
	 * Método de inclusión del id_usuario en el objeto.
	 * @return Retorna el id_usuario en tipo entero.
	 */
	public int getId_usuario() {
		return id_usuario;
	}

	/**
	 * 
	 * @param id_usuario
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getPermiso_usuario() {
		return permiso_usuario;
	}

	public void setPermiso_usuario(int permiso_usuario) {
		this.permiso_usuario = permiso_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getCod_postal() {
		return cod_postal;
	}

	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", permiso_usuario=" + permiso_usuario + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", domicilio=" + domicilio + ", cod_postal=" + cod_postal + ", pais="
				+ pais + ", email=" + email + ", telefono=" + telefono + ", contrasenya=" + contrasenya + "]";
	}
	
	

}
