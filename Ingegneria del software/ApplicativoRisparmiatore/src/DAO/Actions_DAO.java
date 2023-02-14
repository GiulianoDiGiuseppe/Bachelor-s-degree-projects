package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actions_DAO {
	
//Restituisce alore del Titolo per singola azione
public static double Read_Action_Value(String ID_Titolo) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	
	try { 
		s = conn.prepareStatement("SELECT VALORE_TITOLO FROM TITOLI_FINANZIARI WHERE ID_TITOLO = ? ");
		s.setString(1, ID_Titolo);
		
		ResultSet rs = s.executeQuery();
		rs.next();  //Scorro la tupla con il cursore
		
		return rs.getDouble("VALORE_TITOLO");

		}
	   catch(SQLException e) {
		   System.out.println("TITOLO INESISTENTE ");
		   
	} finally {
		if (s != null) {  DB_Manager.getInstance().closeConnection();}
	}
	return 0;
}
 
//Restituisce l'id della Borsa a cui il titolo appartiene
public static String Read_ID_Stock_Exchange(String ID_Titolo) {
	
	PreparedStatement s = null;
	Connection conn = DB_Manager.getInstance().getConnection();
	
	try { 
		s = conn.prepareStatement("SELECT ID_BORSA FROM TITOLI_FINANZIARI WHERE ID_TITOLO = ?");
		s.setString(1, ID_Titolo);
		
		ResultSet rs = s.executeQuery();
		rs.next(); //Scorro la tupla con il cursore
		
		return  rs.getString("ID_BORSA");
		
		}
	   catch(SQLException e) {
		   System.out.println("TITOLO INESISTENTE");
		   
	} finally {
		if (s != null) {  DB_Manager.getInstance().closeConnection();}
	}
	return null;	
}

}

