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
	 * Atributo id del usuario
	 */
	private int id_usuario; 
	/**
	 * Atributo permiso del usuario
	 * Existen dos permisos
	 * 9 para usuario registrado y 1 para usuario administrador
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
	 * Método para llamar a la obtención de los datos de usuario de DaoUsuarios teniendo en cuenta su id_usuario y siguiendo el patrón Singleton.
	 * @param id_usuario Identificador único del usuarioS
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
	
	/**
	 * Método para convertir el objeto gson en una cadena de texto JSON.
	 * @return Retorna una cadena de texto JSON que representa el objeto actual.
	 */
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
	 * Método para llamar a la eliminación de datos de DaoUsuarios aplicando el patrón Singleton.
	 * @param id_usuario
	 * @throws SQLException
	 */
	public void eliminarUsuario(int id_usuario) throws SQLException {
		
		DaoUsuarios.getInstance().eliminarUsuario(id_usuario);
		
		
	}
	
	/**
	 * Autentica al usuario utilizando la contraseña proporcionada.
	 * Si la autenticación es exitosa, actualiza el objeto actual con los datos del usuario.
	 * @param contrasenya La contraseña del usuario para la autenticación.
	 * @return Retorna true si la autenticación es exitosa, false en caso contrario.
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
	
	/**
	 * Método para cifrar la contraseña.
	 * @param contrasenya La contraseña a cifrar.
	 * @return La contraseña cifrada en formato hexadecimal.
	 */
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
	 * Metodo de inclusion del id_usuario en el objeto.
	 * @return Retorna el id_usuario en tipo entero.
	 */
	public int getId_usuario() {
		return id_usuario;
	}

	/**
	 * Metodo para establecer el id_usuario.
	 * @param id_usuario Identificador único del usuario.
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	/**
	 * Metodo de inclusion del permiso_usuario en el objeto.
	 * @return Retorna el permiso_usuario en tipo entero.
	 */
	public int getPermiso_usuario() {
		return permiso_usuario;
	}
	
	/**
	 * Metodo para establecer el permiso_usuario.
	 * @param permiso_usuario Permiso del usuario.
	 */
	public void setPermiso_usuario(int permiso_usuario) {
		this.permiso_usuario = permiso_usuario;
	}

	/**
	 * Metodo de inclusion del nombre en el objeto.
	 * @return Retorna el nombre en tipo String.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo para establecer el nombre del usuario.
	 * @param nombre Nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo de inclusion de los apellidos en el objeto.
	 * @return Retorna los apellidos en tipo String.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo para establecer los apellidos del usuario.
	 * @param apellidos Apellidos del usuario.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Metodo de inclusion del domicilio en el objeto.
	 * @return Retorna el domicilio en tipo String.
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Metodo para establecer el domicilio del usuario.
	 * @param domicilio Domicilio del usuario.
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Metodo de inclusion del cod_postal en el objeto.
	 * @return Retorna el cod_postal en tipo int.
	 */
	public int getCod_postal() {
		return cod_postal;
	}
	
	/**
	 * Metodo para establecer el cod_postal del usuario.
	 * @param cod_postal Codigo postal del usuario.
	 */
	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}
	
	/**
	 * Metodo de inclusion del pais en el objeto.
	 * @return Retorna el pais en tipo String.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Metodo para establecer el pais del usuario.
	 * @param pais Pais del usuario.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Metodo de inclusion del email en el objeto.
	 * @return Retorna el email en tipo String.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo para establecer el email del usuario.
	 * @param email Email del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo de inclusion del telefono en el objeto.
	 * @return Retorna el telefono en tipo int.
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * Metodo para establecer el telefono del usuario.
	 * @param telefono Telefono del usuario.
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Metodo de inclusion de la contraseña en el objeto.
	 * @return Retorna la contraseña en tipo String.
	 */
	public String getContrasenya() {
		return contrasenya;
	}

	/**
	 * Metodo para establecer la contraseña del usuario.
	 * @param contrasenya Contraseña del usuario.
	 */
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
