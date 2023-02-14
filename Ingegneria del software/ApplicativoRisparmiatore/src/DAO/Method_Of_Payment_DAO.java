package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entity.Metodo_di_Pagamento;

public class Method_Of_Payment_DAO {
	
	
//Crea un oggetto di tipo Pacchetto Acquistato a partire dal risultato della query	
private static Metodo_di_Pagamento restore_Metodo_di_Pagamento(ResultSet rs) throws SQLException {
	Metodo_di_Pagamento m;
			
	final String ID_Carta = rs.getString("ID_CARTA");
	final String Circuito = rs.getString("CIRCUITO");
			
	m = new Metodo_di_Pagamento(ID_Carta, Circuito);
		return m;
}

	
//Visualizza le carte registrate dal risparmiatore per eseguire le transazioni 
public static ArrayList<Metodo_di_Pagamento> Read_Method_of_Payment(String Codice_Fiscale) {
	
	ArrayList<Metodo_di_Pagamento> elenco = new ArrayList<Metodo_di_Pagamento>();
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	
	try {
		s = conn.prepareStatement("SELECT ID_CARTA,CIRCUITO FROM METODO_DI_PAGAMENTO WHERE CODICE_FISCALE=?");
		s.setString(1, Codice_Fiscale);
		ResultSet rs = s.executeQuery();
		
		while (rs.next()) {
			Metodo_di_Pagamento m = restore_Metodo_di_Pagamento(rs);
				elenco.add(m);
		}
		 return elenco;
		
	}catch(SQLException e) {
	  System.out.println("NON ESISTONO METODI DI PAGAMENTO...AGGIUNGINE UNO !");
	  
	}finally {
		if (s != null) { DB_Manager.getInstance().closeConnection(); }
	}
	
	return elenco;
}

//Restituisce la data di scadenza della carta inserita
public static java.sql.Date Read_Date_of_Cart(String ID_Carta) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	java.sql.Date Data = null;
	try {
		s = conn.prepareStatement("SELECT DATA_DI_SCADENZA FROM METODO_DI_PAGAMENTO WHERE ID_CARTA =? ");
		s.setString(1, ID_Carta);
		
		ResultSet rs = s.executeQuery();
		rs.next(); 
		Data = rs.getDate("DATA_DI_SCADENZA");
		
		return Data;
		
	}catch(SQLException e) {
		System.out.println("CARTA SPECIFICATA INESISTENTE");
		
	}finally {
		if (s != null) {DB_Manager.getInstance().closeConnection();}
	}
	return Data;
}

//Inserisce un nuovo metodo di pagamento del Risparmiatore
public static boolean Insert_Method_of_Payment(String Codice_Fiscale,String ID_Carta, String Circuito, java.sql.Date Data_di_Scadenza) {
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	
	try {
		s = conn.prepareStatement("INSERT INTO METODO_DI_PAGAMENTO(ID_CARTA,CIRCUITO,CODICE_FISCALE,DATA_DI_SCADENZA) VALUES (?,?,?,?) ");
		s.setString(1, ID_Carta);
		s.setString(2, Circuito);
		s.setString(3, Codice_Fiscale);
		s.setDate(4, Data_di_Scadenza);
		s.executeUpdate();
		
		System.out.println("METODO DI PAGAMENTO REGISTRATO CON SUCCESSO");
		return true;
	}catch(SQLException e) {
		e.printStackTrace();
	  System.out.println("ERRORE NELL'INSERIMENTO DEL METODO DI PAGAMENTO !");
	  
	}finally {
		if (s != null) { DB_Manager.getInstance().closeConnection(); }
	}
	
	return false;
	
}

}


