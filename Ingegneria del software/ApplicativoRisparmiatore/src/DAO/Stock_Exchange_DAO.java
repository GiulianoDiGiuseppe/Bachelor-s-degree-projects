package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Stock_Exchange_DAO {
	
//Lettura Orario di Apertua Borsa
public static Time Read_Orario_Apertura(String ID_Borsa, String ID_Titolo) {
	
	PreparedStatement s = null;
	Connection conn = DB_Manager.getInstance().getConnection();
	
	try { 
		
		s = conn.prepareStatement("SELECT ORARIO_APERTURA FROM BORSA JOIN TITOLI_FINANZIARI ON BORSA.NOME = TITOLI_FINANZIARI.ID_BORSA  "
				+ " WHERE ID_BORSA=? AND ID_TITOLO=?");
		s.setString(1, ID_Borsa);
		s.setString(2,ID_Titolo);
	
		ResultSet rs = s.executeQuery();
		 rs.next();
		 Time ora = rs.getTime("ORARIO_APERTURA");
		return ora;
	} catch(SQLException e) {
		System.out.println("IMPOSSIBILE TROVARE ORARIO DI APERTURA PER ID_TITOLO E ID_BORSA SPECIFICATI") ; 
	} finally {
		if (s != null) {DB_Manager.getInstance().closeConnection();}
	}
  return new Time(0);
}

//Lettura Orario di Chiusura Borsa
public static Time Read_Orario_Chiusura(String ID_Borsa, String ID_Titolo) {
	
	PreparedStatement s = null;
	Connection conn = DB_Manager.getInstance().getConnection();
	
	try { 
		
		s = conn.prepareStatement("SELECT ORARIO_CHIUSURA FROM BORSA JOIN TITOLI_FINANZIARI ON BORSA.NOME = TITOLI_FINANZIARI.ID_BORSA  "
				+ " WHERE ID_BORSA=? AND ID_TITOLO=?");
		s.setString(1, ID_Borsa);
		s.setString(2,ID_Titolo);
	
		ResultSet rs = s.executeQuery();
		rs.next();
		 Time ora = rs.getTime("ORARIO_CHIUSURA");
		return ora;
	} catch(SQLException e) {
		System.out.println("IMPOSSIBILE TROVARE ORARIO DI CHIUSURA PER ID_TITOLO E ID_BORSA SPECIFICATI");  
	} finally {
		if (s != null) {DB_Manager.getInstance().closeConnection();}
	}
  return new Time(0);
}


}
