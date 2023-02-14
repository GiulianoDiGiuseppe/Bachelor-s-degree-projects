package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Financial_Packets_DAO {
	
//Mostra le informazioni aggiuntive del pacchetto selezionato dal risparmiatore prendendole da PACCHETTI FINANZIARI 
public static boolean Select_Packet(String ID_Pacchetto) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	
	try { 
		s = conn.prepareStatement("SELECT FATTORE_DI_RISCHIO,STIMA_DI_RENDIMENTO,DESCRIZIONE,NUMERO_TITOLI"
				+ " FROM PACCHETTI_FINANZIARI WHERE ID_PACCHETTO=?");
		s.setString(1, ID_Pacchetto);
		ResultSet rs = s.executeQuery();
		rs.next();
		double Fattore_di_Rischio = rs.getDouble("FATTORE_DI_RISCHIO");
		double Stima_Rendimento = rs.getDouble("STIMA_DI_RENDIMENTO");
		String Descrizione = rs.getString("DESCRIZIONE");
		int N_Titoli = rs.getInt("NUMERO_TITOLI");
		
		System.out.println("INFORMAZIONI AGGIUNTIVE DEL PACCHETTO SELEZIOANTO:\n ");
		System.out.println("FATTORE DI RISCHIO : " + Fattore_di_Rischio);
		System.out.println("STIMA DI RENDIMENTO : " + Stima_Rendimento);
		System.out.println("DESCRIZIONE : " + Descrizione);
		System.out.println("NUMERO TITOLI : " + N_Titoli);
	
}catch(SQLException e) { 	
	System.out.println("NON ESISTONO PACCHETTI FINANZIARI CON L'ID SPECIFICATO \n");
	return false;
	
}finally {
	if (s != null) { DB_Manager.getInstance().closeConnection();}
}
	 return true;
}


}


