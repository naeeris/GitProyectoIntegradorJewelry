package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//Creo esta clase para poder crear la conexión con la base de datos y poder reutilizarla desde cada dao (usuario, producto etc)
public class ConexionDB {
	
	
	//Creo este método porque quiero poder llamar a esta clase desde cualquier sitio sin tener que instanciarla. Con el static está visible a los demás
	public static Connection instance = null;
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/jewelry_online";
	
	
	private ConexionDB() {
		
	}
	
	//Quiero que esta clase me de acceso a un método publico pero sea estatic y me va a devolver una conexion
	public  static Connection getConnection() throws SQLException {
		
		//Aquí indico que si no está conectado, es decir la conexión es null, que haga lo siguiente:
		if (instance == null) {
			/*Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			//props.put("charset", "UTF-8);
			*/
			instance = DriverManager.getConnection(JDBC_URL, "root", "");
			
		}
		return instance;
		
	}

}
