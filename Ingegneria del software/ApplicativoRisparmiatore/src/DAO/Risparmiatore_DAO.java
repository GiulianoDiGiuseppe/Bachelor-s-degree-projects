package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Risparmiatore_DAO {
	
public static boolean Save_credentials(String Nome, String Cognome, String Indirizzo, String Codice_Fiscale, Date Data_Nascita, String password) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	
	try {
		s = conn.prepareStatement("INSERT INTO RISPARMIATORE(NOME,COGNOME,INDIRIZZO,CODICE_FISCALE,DATA_NASCITA,PASSWORD) VALUES (?,?,?,?,?,?)");
		s.setString(1, Nome);
		s.setString(2, Cognome);
		s.setString(3, Indirizzo);
		s.setString(4, Codice_Fiscale);
		s.setDate(5, Data_Nascita);
		s.setString(6, password);
		s.executeUpdate();
		
	      return true;
		
	}catch(SQLException e) {
		e.printStackTrace();
	  System.out.println("IMPOSSIBILE INSERIRE L'UTENTE ");
	  
	}finally {
		if (s != null) { DB_Manager.getInstance().closeConnection(); }
	}
	
	return false;
}

}


