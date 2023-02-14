package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Entity.Pacchetti_Acquistati;

public class Purchased_Packets_DAO {
	
//Crea un oggetto di tipo Pacchetto Acquistato a partire dal risultato della query	
	private static Pacchetti_Acquistati restore_Pacchetti_Acquistati(ResultSet rs) throws SQLException {
		Pacchetti_Acquistati p;
		
		final String ID_Pacchetto = rs.getString("ID_PACCHETTO");
		final int N_Pacchetti = rs.getInt("NUMERO_PACCHETTI");
		final double Valore_di_Acquisto = rs.getDouble("VALORE_DI_ACQUISTO");
		final Date Data = rs.getDate("DATA");
		
		p = new Pacchetti_Acquistati(ID_Pacchetto, N_Pacchetti, Valore_di_Acquisto,Data);
		return p;
	}

	
//Restituisce i Pacchetti posseduti dal singolo risparmiatore 
public static ArrayList<Pacchetti_Acquistati> Read_Packets(String Codice_Fiscale){
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	ArrayList<Pacchetti_Acquistati> elenco = new ArrayList<Pacchetti_Acquistati>();
	
	try { 
		s = conn.prepareStatement("SELECT ID_PACCHETTO,NUMERO_PACCHETTI,VALORE_DI_ACQUISTO,DATA"
				+ " FROM PACCHETTI_ACQUISTATI WHERE CODICE_FISCALE=? ORDER BY DATA ASC");
		s.setString(1, Codice_Fiscale);
		ResultSet rs = s.executeQuery();

	while (rs.next()) {
		Pacchetti_Acquistati p = restore_Pacchetti_Acquistati(rs);
			elenco.add(p);
	}
	 return elenco;
	
}catch(SQLException e) { 
	System.out.println("NON ESISTONO PACCHETTI ACQUISTATI \n");
	
}finally {
	if (s != null) { DB_Manager.getInstance().closeConnection(); }
}
	return elenco;
}


//Restituisce i Pacchetti acquistati dal risparmiatore in quella data
public static ArrayList<Pacchetti_Acquistati> Read_Packets_By_Date(String Codice_Fiscale, java.sql.Date Data){
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	ArrayList<Pacchetti_Acquistati> elenco_ordinato = new ArrayList<Pacchetti_Acquistati>();
	
	try { 
		s = conn.prepareStatement("SELECT ID_PACCHETTO,NUMERO_PACCHETTI,VALORE_DI_ACQUISTO,DATA"
				+ " FROM PACCHETTI_ACQUISTATI WHERE CODICE_FISCALE=? AND DATA=? ");
		s.setString(1, Codice_Fiscale);
		s.setDate(2, Data);
		
		ResultSet rs = s.executeQuery();

	while (rs.next()) {
		Pacchetti_Acquistati p = restore_Pacchetti_Acquistati(rs);
			elenco_ordinato.add(p);
	}
	 return elenco_ordinato;
	
}catch(SQLException e) { 
	System.out.println("NON ESISTONO PACCHETTI ACQUISTATI ALLA DATA SPECIFICATA ");
	
}finally {
	if (s != null) { DB_Manager.getInstance().closeConnection(); }
}
	return elenco_ordinato;
}



//Restituisce il pacchetto posseduto dal singolo risparmiatore con l'ID indicato
public static Pacchetti_Acquistati Read_Packets_By_ID(String Codice_Fiscale, String ID_Pacchetto){
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	Pacchetti_Acquistati p = null;
	try { 
		s = conn.prepareStatement("SELECT ID_PACCHETTO,NUMERO_PACCHETTI,VALORE_DI_ACQUISTO,DATA"
				+ " FROM PACCHETTI_ACQUISTATI WHERE CODICE_FISCALE=? AND ID_PACCHETTO=?");
		s.setString(1, Codice_Fiscale);
		s.setString(2, ID_Pacchetto);
		
		ResultSet rs = s.executeQuery();
		rs.next();
		 p = restore_Pacchetti_Acquistati(rs);
	     return p;
	
}catch(SQLException e) { 
	System.out.println("NON ESISTONO PACCHETTI ACQUISTATI CON L'ID SPECIFICATO ");
	
}finally {
	if (s != null) { DB_Manager.getInstance().closeConnection();}
}
	return p;
}

//Restituisce il numero di pacchetti posseduti del tipo indicato dal risparmiatore
public static int Read_Quantity_of_Packets(String Codice_Fiscale, String ID_Pacchetto) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	int num = 0;
	
	try { 
		s = conn.prepareStatement("SELECT NUMERO_PACCHETTI FROM PACCHETTI_ACQUISTATI WHERE CODICE_FISCALE=? AND ID_PACCHETTO=?");
		s.setString(1, Codice_Fiscale);
		s.setString(2, ID_Pacchetto);
		
		ResultSet rs = s.executeQuery();
		rs.next();
		 num = rs.getInt("NUMERO_PACCHETTI");
		return num;
		
		}catch(SQLException e) {
		   System.out.println("PACCHETTO INESISTENTE");
		   
	} finally {
		if (s != null) {DB_Manager.getInstance().closeConnection();}
	}
	return 0;
}


//Restituisce il valore di acquisto del pacchetto indicato dal risparmiatore
public static double Read_Price(String Codice_Fiscale, String ID_Pacchetto) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	double valore  = 0;
	
	try { 
		s = conn.prepareStatement("SELECT VALORE_DI_ACQUISTO FROM PACCHETTI_ACQUISTATI WHERE CODICE_FISCALE=? AND ID_PACCHETTO=?");
		s.setString(1, Codice_Fiscale);
		s.setString(2, ID_Pacchetto);
		
		ResultSet rs = s.executeQuery();
		 rs.next();
		  valore = rs.getInt("VALORE_DI_ACQUISTO");
		return valore;
		
		} catch(SQLException e) {
		   System.out.println("PACCHETTO INESISTENTE");
		   
	} finally {
		if (s != null) { DB_Manager.getInstance().closeConnection(); }
	}
	return 0;
}


//Aggiorna il numero di pacchetti presenti in PACCHETTI ACQUISTATI e restituisce il numero di quelli rimasti
public static int Update_Num_Of_Packets(String ID_Pacchetto, int Num_Pacchetti, String Codice_Fiscale) {
	int N_pacchetti_posseduti = Purchased_Packets_DAO.Read_Quantity_of_Packets(Codice_Fiscale, ID_Pacchetto);
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	try {
		s = conn.prepareStatement("UPDATE PACCHETTI_ACQUISTATI SET NUMERO_PACCHETTI=? WHERE CODICE_FISCALE=? AND ID_PACCHETTO=?");
		int pacchetti_tot = N_pacchetti_posseduti - Num_Pacchetti;
		s.setInt(1, pacchetti_tot);
		s.setString(2, Codice_Fiscale);
		s.setString(3, ID_Pacchetto);
		s.executeUpdate();
		
	      return pacchetti_tot;
		
	}catch(SQLException e) {
		e.printStackTrace();
	  System.out.println("PACCHETTO INESISTENTE");
	  
	}finally {
		if (s != null) { DB_Manager.getInstance().closeConnection(); }
	}
	
	return 0;
}
	
	

//Cancella il pacchetto da PACCHETTI ACQUISTATI se il risparmiatore ha venduto tutti i pacchetti di quel tipo
public static boolean Delete_Packet(String Codice_Fiscale, String ID_Pacchetto) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	
	try {
		s = conn.prepareStatement("DELETE FROM PACCHETTI_ACQUISTATI WHERE CODICE_FISCALE=? AND ID_PACCHETTO=?");
		s.setString(1, Codice_Fiscale);
		s.setString(2, ID_Pacchetto);
		s.executeUpdate();
		
	      return true;
		
	}catch(SQLException e) {
		e.printStackTrace();
	  System.out.println("PACCHETTO INESISTENTE");
	  
	}finally {
		if (s != null) { DB_Manager.getInstance().closeConnection(); }
	}
	
	return false;
}



}
