package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.Input;
import Entity.TitoloFinanziario;
import Entity.PacchettoFinanziario;




public class AzioniDAO {


	
//Inserisce nel DBMS i valori in ingresso
	public static void insert_AzioniDAO(String a, String b, int c)  throws SQLException{

		Connection conn = DBManager.getConnection();
		PreparedStatement s = null;
		
		try { 
			
			s = conn.prepareStatement("INSERT INTO AZIONI (ID_PACCHETTO, ID_TITOLO , N_AZIONI) " +
					"VALUES (?,?,?);");
			
			s.setString(1, a);
			s.setString(2, b);
			s.setInt(3, c);
			
			s.executeUpdate();
			
			s.close();
		} 
		finally {
			if (s != null) { s.close(); }	
	}
		
		
	}
	
	
	//Modificare il numero di azioni
	public static void update_AzioniDAO(String id, String id_titolo ,int num_azioni) throws SQLException{
		
		Connection conn = DBManager.getConnection();
		PreparedStatement s = null;
		
		try {
			
			s = conn.prepareStatement("UPDATE AZIONI SET N_AZIONI = ? WHERE ID_TITOLO = ? AND ID_PACCHETTO = ?;");
			s.setInt(1, num_azioni);
			s.setString(2, id_titolo);
			s.setString(3, id);
			
			s.executeUpdate();
			s.close();
			
		}
		finally {
			if (s != null) { s.close(); }	
	}
	}
	
	
	
	
	

//Stampa a video la tabella azioni con tutte le tuple
	public static void read_all_azioni() throws SQLException{
		
		Connection conn = DBManager.getConnection();
		Statement stmt = null;
		
		try {
			
			

			stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM AZIONI");
			
					
			System.out.print("\n \n ID_PACCHETTO		ID_TITOLO			NUM_AZIONI");

			while (r.next()){
				String ID_pacchetto = r.getString("ID_PACCHETTO");
				System.out.print("\n"+ID_pacchetto);
				if (r.wasNull()) System.out.println("id is null"); 
				
				double ID_titolo = r.getDouble("ID_TITOLO");
				System.out.print("\t \t \t"+ID_titolo);
				if (r.wasNull()) System.out.println("id is null"); 
				
				String N_azioni = r.getString("N_AZIONI");
				System.out.print("\t \t \t"+N_azioni);
				if (r.wasNull()) System.out.println("id is null"); 
			}
			
		
			stmt.close();
			
			
		}	
		finally {
			if (stmt != null) { stmt.close(); }	
	}
	}

	
	
	
	
	
	//stampo a video azioni where id= a;
	public static void read_azioni_di_pacchettofinanziario(String a) throws SQLException{
		
		Connection conn = DBManager.getConnection();
		Statement stmt = null;
		
		try {
			
			

			stmt = conn.createStatement();
			ResultSet r = stmt.executeQuery("SELECT * FROM AZIONI WHERE ID_PACCHETTO = '"+a+"';");
			
					
			System.out.print("\nID_PACCHETTO		ID_TITOLO			NUM_AZIONI");

			while (r.next()){
				String ID_pacchetto = r.getString("ID_PACCHETTO");
				System.out.print("\n"+ID_pacchetto);
				if (r.wasNull()) System.out.println("id is null"); 
				
				double ID_titolo = r.getDouble("ID_TITOLO");
				System.out.print("\t \t \t"+ID_titolo);
				if (r.wasNull()) System.out.println("id is null"); 
				
				String N_azioni = r.getString("N_AZIONI");
				System.out.print("\t \t \t"+N_azioni);
				if (r.wasNull()) System.out.println("id is null"); 
			}
			
		
			stmt.close();
	
		}
		finally {
			if (stmt != null) { stmt.close(); }	
	}
	}

	
	
	
	
	public static void deleteAzioniDAO(String id) throws SQLException{
		Connection conn = DBManager.getConnection();
		PreparedStatement s = null;
		
		try { 
			s = conn.prepareStatement("DELETE AZIONI WHERE ID_PACCHETTO = ?");
			s.setString(1, id);
			
			s.executeUpdate();
			
		}
		finally {
			if (s != null) { s.close(); }	
	}
	}
	
	
	public static void inserimento_valori_in_AzioniDAO(PacchettoFinanziario a)throws SQLException	{
		
		int num_azioni;
		String id_tit;
		int c;
		
		for(int i=0;i<a.get_N_titoli();i++)	{	
			
			c=i+1; //serve solo per la stampa a video altrimenti il conteggio partiva da 0
			
			//SELEZIONA TITOLO.
			System.out.print("\n\n Inserisci id del "+c+"° TITOLO  = ");
			id_tit = Input.readLine();
			
			//INSERIRE IL TITOLO SELEZIONATO NELLA CLASSE TITOLO FINANZIARIO
			TitoloFinanziario titolo_richiesto = new TitoloFinanziario(TitoloFinanziarioDAO.get_titolofinanziarioDAO(id_tit)) ;

			//NUMERO DI AZIONI DA INSERIRE
			System.out.print("\n\n Inserisci NUMERO DI AZIONI DA INSERIRE  = ");
			num_azioni = Input.readInt(); 
				
			if(num_azioni>0 && num_azioni<100) 
				{
				//SALVATAGGIO IN AZIONI
				AzioniDAO.insert_AzioniDAO(a.get_id(), titolo_richiesto.get_ID_Titolo() , num_azioni);
			
				//CALCOLO VALORE PACCHETTO
				a.Sommavalore(num_azioni, titolo_richiesto.get_Valore_Titolo());
				}	
			else {
				System.out.print("\n\n ERRORE IL NUMERO DI AZIONI DEVE ESSERE COMPRESO TRA 1 E 99 ");
				System.out.print("\n\n Inserisci di nuovo il titolo desiderato con il numero di azioni compreso tra 1 e 99 ");
					i--;
				}
			
			
			}
	}
	
	
	
	
	public static void modifica_valori_in_AzioniDAO(String [] array, PacchettoFinanziario p) throws SQLException{
		
		int num_azioni;
		int c;
		
		for(int j=0; j<array.length; j++) {
			
			c = j+1;
			
			//SELEZIONE DEL NUMERO DI AZIONI PER I TITOLI GIA' PRESENTI
			TitoloFinanziario titolo_richiesto = new TitoloFinanziario(TitoloFinanziarioDAO.get_titolofinanziarioDAO(array[j].toString()));
			System.out.print("\n\nInserisci il numero di azioni per il "+c+"° titolo, con ID pari a " +titolo_richiesto.get_ID_Titolo()+ " :   " );
			num_azioni = Input.readInt();
			
			
			if(num_azioni>0 && num_azioni<100) {
				//SALVATAGGIO IN AZIONI
				AzioniDAO.insert_AzioniDAO(p.get_id(), titolo_richiesto.get_ID_Titolo(), num_azioni);
			
				//CALCOLO VALORE PACCHETTO
				p.Sommavalore(num_azioni, titolo_richiesto.get_Valore_Titolo());
				}
				
			else {
				System.out.print("\n\n ERRORE IL NUMERO DI AZIONI DEVE ESSERE COMPRESO TRA 0 E 99 ");
				System.out.print("\n\n Inserisci di nuovo il titolo desiderato con il numero di azioni compreso tra 1 e 99 ");									
				j--;
				}
			
		}
	}
	
	
	
	
	public static void inserimento_titoli_aggiuntivi_in_AzioniDAO(int c, int aggiunte,  PacchettoFinanziario p ) throws SQLException{
		
		String id_tit;
		int num_azioni;
		
		for(int j=0; j<aggiunte; j++) {
			
			//SELEZIONE DEL NUMERO DI AZIONI PER I TITOLI NUOVI
				c++;
				//SELEZIONE TITOLO
				System.out.print("\n\n Inserisci id del "+c+"° TITOLO:    ");
				id_tit = Input.readLine();
				
				
				//INSERIRE IL TITOLO SELEZIONATO NELLA CLASSE TITOLO FINANZIARIO
				TitoloFinanziario titolo_richiesto = new TitoloFinanziario(TitoloFinanziarioDAO.get_titolofinanziarioDAO(id_tit)) ;
				
				
				//NUMERO DI AZIONI DA INSERIRE
				System.out.print("\n\n Inserisci NUMERO DI AZIONI DA INSERIRE  = ");
				num_azioni = Input.readInt(); 
				
				if(num_azioni>0 && num_azioni<100) {
					//SALVATAGGIO IN AZIONI
					AzioniDAO.insert_AzioniDAO(p.get_id(), titolo_richiesto.get_ID_Titolo(), num_azioni);
				
					//CALCOLO VALORE PACCHETTO
					p.Sommavalore(num_azioni, titolo_richiesto.get_Valore_Titolo());
					}
					
				else {
					System.out.print("\n\n ERRORE IL NUMERO DI AZIONI DEVE ESSERE COMPRESO TRA 0 E 99 ");
					System.out.print("\n\n Inserisci di nuovo il titolo desiderato con il numero di azioni comppreso tra 0 e 99 ");
							j--;
					}
				
			}
	}
	
}