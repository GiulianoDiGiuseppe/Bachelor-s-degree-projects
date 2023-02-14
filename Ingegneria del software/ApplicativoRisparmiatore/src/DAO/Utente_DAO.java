package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Entity.Pacchetti_Acquistati;
import Entity.Utente;
import Entity.Utente.UserRole;

public class Utente_DAO {

	
private static Utente restore_user(ResultSet rs) throws SQLException {
	Utente user;
		
	final String username = rs.getString("USERNAME");
	final String password  = rs.getString("PASSWORD");
	final UserRole UserRole = Enum.valueOf(UserRole.class, rs.getString("RUOLO"));
		
	user = new Utente(username, password, UserRole );
	return user;
}
	
	
public static boolean Save_user_login(String username, String password,String UserRole) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	
	try {
		s = conn.prepareStatement("INSERT INTO UTENTE(USERNAME,PASSWORD,RUOLO) VALUES (?,?,?)");
		s.setString(1, username);
		s.setString(2, password);
		s.setString(3, UserRole);
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
	

public static String Read_user_login(String username, String password) {
	
	Connection conn = DB_Manager.getInstance().getConnection();
	PreparedStatement s = null;
	String ruolo = null;
	
	try {
		s = conn.prepareStatement("SELECT RUOLO FROM UTENTE WHERE USERNAME=? AND PASSWORD=?");
		s.setString(1, username);
		s.setString(2, password);
		ResultSet rs = s.executeQuery();
		rs.next();
         ruolo = rs.getString("RUOLO");
		 return ruolo;

	}catch(SQLException e) {
		System.out.println("UTENTE NON REGISTRATO !");
	}finally {
		if (s != null) { DB_Manager.getInstance().closeConnection(); }
	}

	return ruolo;
}

}

