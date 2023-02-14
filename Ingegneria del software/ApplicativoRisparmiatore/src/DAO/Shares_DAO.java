package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Shares_DAO {
	
//Restituisce il numero di azioni per Titolo
	public static int Read_Shares_Num(String ID_Pacchetto,String ID_Titolo) {
		
		Connection conn = DB_Manager.getInstance().getConnection();
		PreparedStatement s = null;
		int num = 0;
		
		try { 
			s = conn.prepareStatement("SELECT NUMERO_AZIONI FROM AZIONI WHERE ID_TITOLO=? AND ID_PACCHETTO=?");
			s.setString(1, ID_Titolo);
			s.setString(2, ID_Pacchetto);
			
			ResultSet rs = s.executeQuery();
			rs.next();
			num = rs.getInt("NUMERO_AZIONI");
			return num;
			}
		   catch(SQLException e) {
			   System.out.println("TITOLO O PACCHETTO INESISTENTI");
			   
		} finally {
			if (s != null) { DB_Manager.getInstance().closeConnection();}
		}
		return 0;
	}
	
	
//Restituisce una lista contenente i titoli presenti nel pacchetto specificato
public static ArrayList<String> Read_ID_Actions(String ID_Pacchetto){
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	ArrayList<String> elenco_ID = new ArrayList<String>();
	
	try { 
		s = conn.prepareStatement("SELECT ID_TITOLO FROM AZIONI WHERE ID_PACCHETTO=? ");
		s.setString(1, ID_Pacchetto);
		ResultSet rs = s.executeQuery();

	while (rs.next()) {
		String ID_Titolo = rs.getString("ID_TITOLO");
			elenco_ID.add(ID_Titolo);
	}
	 return elenco_ID;
	
}catch(SQLException e) {
	e.printStackTrace();
	System.out.println("NON ESISTONO PACCHETTI FINANZIARI CON L'ID SPECIFICATO ");
	
}finally {
	if (s != null) { DB_Manager.getInstance().closeConnection(); }
}
	return elenco_ID;
	
}
	
}
