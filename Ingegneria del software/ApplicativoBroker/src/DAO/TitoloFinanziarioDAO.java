package DAO;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.TitoloFinanziario;


public class TitoloFinanziarioDAO {

//Preleva dal DBMS il titolo finanziario corrispondente all id in ingresso
	public static TitoloFinanziario get_titolofinanziarioDAO(String id) throws SQLException{
		
		Connection conn = DBManager.getConnection();
		Statement stmt = null;
		
		if (id == null) {
			throw new SQLException("ID VALUE NOT FOUND!!");
		}
		
		TitoloFinanziario e = null;
		try{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TITOLI_FINANZIARI WHERE ID_TITOLO =" +id.toString()+";");
			
			if (rs.next() == false){
				throw new SQLException("ID VALUE NOT FOUND!!");
			}
			
		e = new TitoloFinanziario(rs.getString("ID_TITOLO"),rs.getDouble("VALORE_TITOLO"),rs.getString("NOME_TITOLO"),rs.getString("BORSA"));
			
				
		System.out.println("\n\t ID_TITOLO :  "+rs.getString("ID_TITOLO")+"\t VALORE_TITOLO : "+rs.getDouble("VALORE_TITOLO")+"\t NOME_TITOLO : "+rs.getString("NOME_TITOLO")+"\t BORSA : "+rs.getString("BORSA"));
			
			
		stmt.close();
	
		}
		finally {
			if (stmt != null) { stmt.close(); }	
	}
		return e;
	}
	
	
//Stampa tutti i titoli finanziari presenti nel DBMS
	public static void read_all_titolifinanziari() throws SQLException{
		
		Connection conn = DBManager.getConnection();
		Statement stmt = null;
		try {
			
			

			stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM TITOLI_FINANZIARI");
			
					
			System.out.print("\n \n ID_TITOLO		VALORE			NOME			BORSA  ");

			while (r.next()){
				String id = r.getString("ID_TITOLO");
				System.out.print("\n"+id);
				if (r.wasNull()) System.out.println("id is null"); 
				double valore = r.getDouble("VALORE_TITOLO");
				System.out.print("\t \t \t"+valore);
				if (r.wasNull()) System.out.println("id is null"); 
				String nome = r.getString("NOME_TITOLO");
				System.out.print("\t \t \t"+nome);
				if (r.wasNull()) System.out.println("id is null"); 
				String borsa = r.getString("BORSA");
				System.out.print("\t \t \t"+borsa);
				if (r.wasNull()) System.out.println("id is null"); 
			}
			
			
			stmt.close();
	
			
		} 
		finally {
			if (stmt != null) { stmt.close(); }	
		}
	}
	

}
