package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Input;
import Entity.PacchettoFinanziario;
import Entity.Broker;

public class PacchettoFinanziarioDAO {
	
	

	public static void insert_pacchettofinanziarioDAO(PacchettoFinanziario p) throws SQLException{
		
		Connection conn = DBManager.getConnection();
		PreparedStatement s = null;
		
		try { 
			
			s = conn.prepareStatement("INSERT INTO PACCHETTI_FINANZIARI (ID_PACCHETTO, FATTORE_DI_RISCHIO , STIMA_DI_RENDIMENTO , NOME_PACCHETTO , STATO , VALORE_PACCHETTO , N_REG_CAM_COMMERCIO , DESCRIZIONE , N_TITOLI ) " +
					"VALUES (?,?,?,?,?,?,?,?,?)");
			
		

			s.setString(1, p.get_id());
			s.setDouble(2, p.get_Fattore_di_rischio());
			s.setDouble(3, p.get_Stima_di_rendimento());
			s.setString(4, p.get_Nome_Pacchetto());
			s.setString(5, p.get_Stato());
			s.setDouble(6, p.get_Valore_pacchetto());
			s.setString(7, p.get_N_Cam_commercio());
			s.setString(8, p.get_Descrizione());
			s.setInt(9, p.get_N_titoli());

			s.executeUpdate();
			s.close();
		
		}
		finally {
			if (s != null) { s.close(); }
		}
	}

	
	
	
	

	public static void read_all_pacchettifinanziari() {
		try {
			
			Connection conn = DBManager.getConnection();

			Statement stmnt = conn.createStatement();
			ResultSet r = stmnt.executeQuery("SELECT * FROM PACCHETTI_FINANZIARI");
			
					
			while (r.next()){
			
			System.out.print("\n ID:");
			String ID_pacchetto = r.getString("ID_PACCHETTO");
			System.out.print("\t"+ID_pacchetto);
			if (r.wasNull()) System.out.println("id is null"); 
				
			
			System.out.print("\t FATTORE DI RISCHIO:");
			double fatt = r.getDouble("FATTORE_DI_RISCHIO");
			System.out.print("\t "+fatt+"%");
			if (r.wasNull()) System.out.println("id is null"); 
			
			System.out.print("\t STIMA DI RENDIMENTO:");
			double stim = r.getDouble("STIMA_DI_RENDIMENTO");
			System.out.print("\t "+stim+"%");
			if (r.wasNull()) System.out.println("id is null"); 
			
			System.out.print("\n NOME PACCHETTO:");
			String nome = r.getString("NOME_PACCHETTO");
			System.out.print("\t"+nome);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\n STATO:");
			String stato = r.getString("STATO");
			System.out.print("\t"+stato);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\t VALORE_PACCHETTO:");
			double valore = r.getDouble("VALORE_PACCHETTO");
			System.out.print("\t "+valore);
			if (r.wasNull()) System.out.println("id is null"); 
	
			System.out.print("\n N_REG_CAM_COMMERCIO:");
			String n_reg = r.getString("N_REG_CAM_COMMERCIO");
			System.out.print("\t"+n_reg);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\n DESCRIZIONE:");
			String descriz = r.getString("DESCRIZIONE");
			System.out.print("\t"+descriz);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\n N_TITOLI:");
			int num = r.getInt("N_TITOLI");
			System.out.print("\t"+num);
			if (r.wasNull()) System.out.println("id is null");
		
			}
			

			stmnt.close();

			
		} catch (SQLException e) {
			System.out.print("\n\nC'è stato un errore con la comunicazione col database\n");
			e.printStackTrace();
		}		
	}

	
	
	
//VISUALIZZA I PACCHETTI CREATI DA UN DETERMINATO BROKER	
	public static void read_pacchetti_creati(String n_reg_cam_commercio) throws SQLException{
		Connection conn = DBManager.getConnection();
		Statement stmt = null;
		
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet r = stmt.executeQuery("SELECT * FROM PACCHETTI_FINANZIARI WHERE N_REG_CAM_COMMERCIO = '"+n_reg_cam_commercio.toString()+"';");
			
			if (r.next() == false){
				throw new SQLException("NON HAI CREATO ANCORA NESSUN PACCHETTO!!");
				}
			r.beforeFirst();	
			
			while (r.next()){
			
			System.out.print("\n ID:");
			String ID_pacchetto = r.getString("ID_PACCHETTO");
			System.out.print(""+ID_pacchetto);
			if (r.wasNull()) System.out.println("id is null"); 
				
			System.out.print("\n FATTORE DI RISCHIO:");
			double fatt = r.getDouble("FATTORE_DI_RISCHIO");
			System.out.print(" "+fatt+"%");
			if (r.wasNull()) System.out.println("id is null"); 
			
			System.out.print("\t\t STIMA DI RENDIMENTO:");
			double stim = r.getDouble("STIMA_DI_RENDIMENTO");
			System.out.print(" "+stim+"%");
			if (r.wasNull()) System.out.println("id is null"); 
			
			System.out.print("\n NOME PACCHETTO:");
			String nome = r.getString("NOME_PACCHETTO");
			System.out.print(""+nome);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\t\t STATO:");
			String stato = r.getString("STATO");
			System.out.print(""+stato);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\n VALORE_PACCHETTO:");
			double valore = r.getDouble("VALORE_PACCHETTO");
			System.out.print(" "+valore);
			if (r.wasNull()) System.out.println("id is null"); 
	
			System.out.print("\t\t N_REG_CAM_COMMERCIO:");
			String n_reg = r.getString("N_REG_CAM_COMMERCIO");
			System.out.print(""+n_reg);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\n DESCRIZIONE:");
			String descriz = r.getString("DESCRIZIONE");
			System.out.print(""+descriz);
			if (r.wasNull()) System.out.println("id is null");
			
			System.out.print("\n N_TITOLI:");
			int num = r.getInt("N_TITOLI");
			System.out.print(""+num+"\n\n");
			if (r.wasNull()) System.out.println("id is null");
		
			}

			stmt.close();
			
			
		}
		finally {
			if (stmt != null) { stmt.close(); }
		}
	}
	
	

	
		
    
//RESTITUISCE UN PACCHETTO DATO L'ID			
	public static PacchettoFinanziario get_pacchetto(String id) throws SQLException{
		if(id == null) return null;
		Statement stmt = null;
		
		PacchettoFinanziario p = null;
		Connection conn = DBManager.getConnection();
		try{
		
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PACCHETTI_FINANZIARI WHERE ID_PACCHETTO = '"+id.toString()+"';");
			if (rs.next() == false){
				throw new SQLException("ID NON VALIDO!!");
				}
				
				
				final String ID_pacchetto = rs.getString("ID_PACCHETTO");
		        final double Fattore_di_rischio = rs.getDouble("FATTORE_DI_RISCHIO");
		        final double Stima_di_rendimento = rs.getDouble("STIMA_DI_RENDIMENTO");
		        final String Nome = rs.getString("NOME_PACCHETTO");
		        final String Stato = rs.getString("STATO");
		        final double Valore = rs.getDouble("VALORE_PACCHETTO");
		        final String N_reg = rs.getString("N_REG_CAM_COMMERCIO");
		        final String Descrizione = rs.getString("DESCRIZIONE");
		        final int N_titoli = rs.getInt("N_TITOLI");
		
		      
		      p = new PacchettoFinanziario(ID_pacchetto, Fattore_di_rischio, Stima_di_rendimento, Nome, Stato, Valore, N_reg, Descrizione, N_titoli);
		   
				
		//	p = new pacchettofinanziario(rs.getString("ID_PACCHETTO"), rs.getDouble("FATTORE_DI_RISCHIO"), rs.getDouble("STIMA_DI_RENDIMENTO"), rs.getString("NOME_PACCHETTO"), rs.getString("STATO"), rs.getDouble("VALORE_PACCHETTO"), rs.getString("N_REG_CAM_COMMERCIO"), rs.getString("DESCRIZIONE"), rs.getInt("N_TITOLI"));
			
			stmt.close();
		      
			} finally {
				if (stmt != null) { stmt.close(); }
			}
		return p;
		}
	
			
		
		

	
	
	//MODIFICARE INFORMAZIONI DEL PACCHETTO FINANZIARIO
	public static void update_pacchetto_finanziario(String id, double fattore_rischio, double stima_rend, String nome, String stato, String n_reg, String descrizione) throws SQLException{
		
		Connection conn = DBManager.getConnection();
		PreparedStatement s = null;
		
		try {
			
			s = conn.prepareStatement("UPDATE PACCHETTI_FINANZIARI SET FATTORE_DI_RISCHIO = ?, STIMA_DI_RENDIMENTO = ?, NOME_PACCHETTO = ?, STATO = ?, N_REG_CAM_COMMERCIO = ?, DESCRIZIONE = ?   WHERE ID_PACCHETTO = ?;");
			s.setDouble(1, fattore_rischio);
			s.setDouble(2, stima_rend);
			s.setString(3, nome);
			s.setString(4,  stato);
			s.setString(5,  n_reg);
			s.setString(6,  descrizione);
			s.setString(7,  id);
			
			
			s.executeUpdate();
			s.close();
			
		}
		finally {
			if (s != null) { s.close(); }
		}
	}
	
	
	
	
	
//CANCELLAZIONI PACCHETTO	
	public static void delete_pacchettofinaziarioDAO(String id) throws SQLException{
		Connection conn = DBManager.getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("DELETE PACCHETTI_FINANZIARI WHERE ID_PACCHETTO = ?");
			s.setString(1, id);
			
			s.executeUpdate();
			
		}
		finally {
			if (s != null) { s.close(); }	
	}
	}
		

	
	
		
		
		
//FUNZIONE CHE PRENDE GLI ID DEI TITOLI PRESENTI IN N PACCHETTO E LI METTE IN UN ARRAY
	public static String[] id_titolo_into_array (PacchettoFinanziario p) throws SQLException{
			
			String [] array = new String[p.get_N_titoli()];
			int i = 0;
			
			
			if(p.get_id() == null) {
				throw new SQLException ("ID VALUE NOT FOUND!!");
			}
			
			Connection conn = DBManager.getConnection();
			Statement stmt = null;
				
			try{
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("SELECT ID_TITOLO FROM AZIONI WHERE ID_PACCHETTO = '" +p.get_id().toString()+ "';");
				
				if (rs.next() == false){
					throw new SQLException("NESSUN TITOLO NEL PACCHETTO!!");
					}
				rs.beforeFirst();
			
				
				while(rs.next()) {
					array[i] = rs.getString("ID_TITOLO");
					i++;
				}
				
				stmt.close();
				
				
			} 
			finally {
				if (stmt != null) { stmt.close(); }	
		}
			return array;
		}
		
		
	
	
	
	//SELEZIONA UN PACCHETTO, RESTITUENDO L'ID
	public static String SelezionaPacchetto(Broker b) throws SQLException{
		//SELEZIONE DEL PACCHETTO 
		String id = null;
		System.out.print("Digitare 1 se si vuole ricercare il pacchetto tramite ID oppure digitare 2 se si vuole visionare la lista dei pacchetti:   ");
		String risposta1 = Input.readLine();
		
		if(risposta1.equals("1")) {
			System.out.print("\nDigitare l'ID del pacchetto da modificare:   ");
			id = Input.readLine();
		}
		else if(risposta1.equals("2")) {
			PacchettoFinanziarioDAO.read_pacchetti_creati(b.getN_reg_cam_commercio());		//visualizza solo i pacchetti da lui creato
			System.out.print("\n\n\nDigitare l'ID del pacchetto da modificare:   ");
			id = Input.readLine();
		}
		return id;

	
	}
	
	
}