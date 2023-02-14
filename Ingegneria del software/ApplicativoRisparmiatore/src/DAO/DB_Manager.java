package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Manager {
	
	private DB_Manager() throws SQLException {
	}
	
	private static DB_Manager instance = null;
	
	public static DB_Manager getInstance()  {
		try {
			if (instance == null) {
				instance = new DB_Manager();
			}
		} catch (SQLException  e) {
			throw new RuntimeException("Impossibile caricare il Data Base", e);
		} 
		return instance;
	}
	
	public Connection getConnection()  {
		try {
			if(connection == null || connection.isClosed()) {
				this.connection = DriverManager.getConnection(url, "sa", "123");
				//System.out.println("Richiesta di connessione avvenuta con successo");
			}
		}catch(Exception exc) {
			System.out.println("IMPOSSIBILE CONNETTERSI AL DATA BASE ");
			exc.printStackTrace();
		}
		return connection;	
	}
	
	public void closeConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				//System.out.println("Connessione terminata con successo");
			}		
		}catch(Exception exc) {
			System.out.println("SI E' VERIFICATO UN ERRORE NELLA CHIUSURA DELLA CONNESSIONE ");
		}
	}
	
	protected Connection connection;
	protected final static String dbPath = "~/test";
	protected final static String url = "jdbc:h2:" + dbPath;

}
