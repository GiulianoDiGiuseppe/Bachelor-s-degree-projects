package Controller;
import java.sql.SQLException;

import DAO.AzioniDAO;
import DAO.PacchettoFinanziarioDAO;
import DAO.TitoloFinanziarioDAO;
import Entity.Broker;
import Entity.Input;
import Entity.PacchettoFinanziario;
import Entity.TitoloFinanziario;

public class Bloomfin_Management_System {


	
	public static void Crea_PacchettoFinanziario(Broker b)throws SQLException {
		//GUIDA
				System.out.print("Benvenuto alla creazione del tuo pacchetto finanziario");
				System.out.print("\n Dovrai effettuare le seguenti operazioni:");
				System.out.print("\n \t Inserisci le informazioni riguardanti al pacchetto");
				System.out.print("\n \t Inserisci il numero esatto dei titoli nel pacchetto");
				System.out.print("\n \t Inserisci il numero di azioni per ogni titolo desiderato");

		//CREAZIONE PACCHETTO E CARICAMENTO INFORMAZIONI
				PacchettoFinanziario a = new PacchettoFinanziario();
				a.Inserimento_informazioni(b.getN_reg_cam_commercio());
				
				a.Stampa();
		//STAMPA A VIDEO TUTTI I TITOLI			
				TitoloFinanziarioDAO.read_all_titolifinanziari();
		
				AzioniDAO.inserimento_valori_in_AzioniDAO(a);	
				
		
		System.out.print("\n\n Stampa a video delle azioni  e il loro numero");
		AzioniDAO.read_azioni_di_pacchettofinanziario(a.get_id());

		//SALVATAGGIO PACCHETTO
		System.out.print("\n\n Sto creando il pacchetto ....... \n");
		PacchettoFinanziarioDAO.insert_pacchettofinanziarioDAO(a);
		System.out.print("\n\n Il pacchetto è stato creato complimenti ");
	
		//STAMPA
		System.out.print("\n\n Ecco il TUO PACCHETTO \n ");
		a.Stampa();	
			
	}
	
	
	public static void Crea_PacchettoFinanziarioTEST(PacchettoFinanziario a, String reg_cam,String[] id_tit,int[] num_azioni)throws SQLException {
		//GUIDA
				System.out.print("Benvenuto alla creazione del tuo pacchetto finanziario");
				System.out.print("\n Dovrai effettuare le seguenti operazioni:");
				System.out.print("\n \t Inserisci le informazioni riguardanti al pacchetto");
				System.out.print("\n \t Inserisci il numero esatto dei titoli nel pacchetto");
				System.out.print("\n \t Inserisci il numero di azioni per ogni titolo desiderato");

		//CREAZIONE PACCHETTO E CARICAMENTO INFORMAZIONI
				
				a.Stampa();
		//STAMPA A VIDEO TUTTI I TITOLI			
				TitoloFinanziarioDAO.read_all_titolifinanziari();
						

		//INIZIO CICLO FOR		
		for(int i=0;i<a.get_N_titoli();i++)	{	
			
				//INSERIRE IL TITOLO SELEZIONATO NELLA CLASSE TITOLO FINANZIARIO
				TitoloFinanziario titolo_richiesto = new TitoloFinanziario(TitoloFinanziarioDAO.get_titolofinanziarioDAO(id_tit[i])) ;
					
				if(num_azioni[i]>0 && num_azioni[i]<100) 
					{
					//SALVATAGGIO IN AZIONI
					AzioniDAO.insert_AzioniDAO(a.get_id(), titolo_richiesto.get_ID_Titolo() , num_azioni[i]);
				
					//CALCOLO VALORE PACCHETTO
					a.Sommavalore(num_azioni[i], titolo_richiesto.get_Valore_Titolo());
					}	
				else {
					System.out.print("\n\n ERRORE IL NUMERO DI AZIONI DEVE ESSERE COMPRESO TRA 1 E 99 ");
					System.out.print("\n\n Inserisci di nuovo il titolo desiderato con il numero di azioni compreso tra 1 e 99 ");
					
					}
				
				}	

		System.out.print("\n\n Stampa a video delle azioni  e il loro numero");
		AzioniDAO.read_azioni_di_pacchettofinanziario(a.get_id());

		//SALVATAGGIO PACCHETTO
		System.out.print("\n\n Sto creando il pacchetto ....... \n");
		PacchettoFinanziarioDAO.insert_pacchettofinanziarioDAO(a);
		System.out.print("\n\n Il pacchetto è stato creato complimenti ");
	
		//STAMPA
		System.out.print("\n\n Ecco il TUO PACCHETTO \n ");
		a.Stampa();	
			
	}
	
	public static void  Modifica_PacchettoFinanziario(Broker b) throws SQLException{
		
		
		//GUIDA
				System.out.print("\nBenvenuto alla modifica del tuo pacchetto finanziario");
		
	
				
		//SELEZIONE DEL PACCHETTO DA MODIFICARE
				String id = PacchettoFinanziarioDAO.SelezionaPacchetto(b);

				PacchettoFinanziario p = new PacchettoFinanziario(PacchettoFinanziarioDAO.get_pacchetto(id));				
				
				//STAMPA DEL PACCHETTO
				System.out.print("\n\nQuesto è il pacchetto selezionato:\n\n");
				p.Stampa();
	
				
				
		//STAMPA DELLE AZIONI PRESENTI NEL PACCHETTO	
				System.out.print("\n\nQuesti sono i titoli con le rispettive azioni presenti nel pacchetto:\n\n");
				AzioniDAO.read_azioni_di_pacchettofinanziario(id.toString());
			
				
				
		//MI PRENDO GLI ID PRESENTI DEL PACCHETTO E LI METTO IN UN ARRAY	
				
				String [] array = PacchettoFinanziarioDAO.id_titolo_into_array(p);
				
				
				//INIZIO MODIFICA
				p.Modifica_id();
		
				
			//CAMBIO NUMERO DI AZIONI PER I TITOLI GIA' PRESENTI NEL PACCHETTO
				
				AzioniDAO.modifica_valori_in_AzioniDAO(array, p);

				
					
			//AGGIUNTA DI NUOVI TITOLI CON IL RISPETTIVO NUMERO DI AZIONI PER TITOLO
					
				int c = array.length;
				
				System.out.print("\n\nSi vogliono aggiungere titoli al pacchetto? Digitare 1 per risposta affermativa, digitare 2 per risposta negativa:   ");
				String risposta2 = Input.readLine();
				int titoli_totali = 0;
				
				if(risposta2.equals("1")) {
					System.out.print("\nInserire il numero di titoli che si vogliono aggiungere:   ");
					int aggiunte = Input.readInt();
					titoli_totali = p.get_N_titoli() + aggiunte;
					p.Inserimento_N_titoli(titoli_totali);
					
					//STAMPA A VIDEO TUTTI I TITOLI
					System.out.print("\nQuesti sono tutti i titoli disponibili:\n");
					TitoloFinanziarioDAO.read_all_titolifinanziari();
				
					AzioniDAO.inserimento_titoli_aggiuntivi_in_AzioniDAO(c, aggiunte, p);
					
					
				}

				
				
				System.out.print("\n\n Stampa a video delle azioni e il loro numero");


				AzioniDAO.read_azioni_di_pacchettofinanziario(p.get_id());


			//SALVATAGGIO PACCHETTO
				System.out.print("\n\n Sto modificando il pacchetto....... \n\n");
				//INSERIRE LA MODIFICA DELLE CREDENZIALI
				p.Modifica_informazioni(titoli_totali);
				PacchettoFinanziarioDAO.insert_pacchettofinanziarioDAO(p);

				//VISUALIZZA TUTTI I PACCHETTI	
				System.out.print("\n\n Il pacchetto è stato modificato correttamente. Complimenti !!");

				//STAMPA

				System.out.print("\n\n Ecco il TUO PACCHETTO \n ");

				p.Stampa();	
					
	}
	
	
	public static void  Modifica_PacchettoFinanziarioTEST(String n_reg, String risposta_01, String id_pacchetto,  int[] numero_azioni, String [] id_titoli, String risposta_02, int titoli_aggiunti, double fattore_rischio, double stima_rendimento, String nome, String descrizione) throws SQLException{
		
		
		//GUIDA
		//		System.out.print("\nBenvenuto alla modifica del tuo pacchetto finanziario");
		
		
		//INSERIMENTO ID BROKER
				Broker b = new Broker();
				b.setN_reg_cam_commercio(n_reg);

				
		//SELEZIONE DEL PACCHETTO DA MODIFICARE
				String id = null;
				System.out.print("Digitare 1 se si vuole ricercare il pacchetto tramite ID oppure digitare 2 se si vuole visionare la lista dei pacchetti:   ");
				String risposta1 =  risposta_01;    /*Input.readLine();*/
				
				if(risposta1.equals("1")) {
					System.out.print("\nDigitare l'ID del pacchetto da modificare:   ");
					id = id_pacchetto;  /*Input.readLine();*/
				}
				else if(risposta1.equals("2")) {
					PacchettoFinanziarioDAO.read_pacchetti_creati(b.getN_reg_cam_commercio());		//visualizza solo i pacchetti da lui creato
					System.out.print("\n\n\nDigitare l'ID del pacchetto da modificare:   ");
					id = id_pacchetto;   /*Input.readLine();*/
				}
			
				PacchettoFinanziario p = new PacchettoFinanziario(PacchettoFinanziarioDAO.get_pacchetto(id));
				

				
				
				
				
		//STAMPA DEL PACCHETTO
				System.out.print("\n\nQuesto è il pacchetto selezionato:\n\n");
				p.Stampa();
		//STAMPA DELLE AZIONI PRESENTI NEL PACCHETTO	
				System.out.print("\n\nQuesti sono i titoli con le rispettive azioni presenti nel pacchetto:\n\n");
				AzioniDAO.read_azioni_di_pacchettofinanziario(id.toString());
			
				
				
		//MI PRENDO GLI ID PRESENTI DEL PACCHETTO E LI METTO IN UN ARRAY	
				
				String [] array = PacchettoFinanziarioDAO.id_titolo_into_array(p);
				
				
				//INIZIO MODIFICA
				p.Modifica_id();
		
				
			//CAMBIO NUMERO DI AZIONI PER I TITOLI GIA' PRESENTI NEL PACCHETTO
				
					int num_azioni;
					String id_tit;
					int c;
					
					for(int j=0; j<array.length; j++) {
						
						c = j+1;
						
						//SELEZIONE DEL NUMERO DI AZIONI PER I TITOLI GIA' PRESENTI
						TitoloFinanziario titolo_richiesto = new TitoloFinanziario(TitoloFinanziarioDAO.get_titolofinanziarioDAO(array[j].toString()));
						System.out.print("\n\nInserisci il numero di azioni per il "+c+"° titolo, con ID pari a " +titolo_richiesto.get_ID_Titolo()+ " :   " );
						num_azioni =  numero_azioni[j];  /*Input.readInt();*/
						
						
						if(num_azioni>0 && num_azioni<100) {
							//SALVATAGGIO IN AZIONI
						//	AzioniDAO.update_AzioniDAO(id_tit, num_azioni);
							AzioniDAO.insert_AzioniDAO(p.get_id(), titolo_richiesto.get_ID_Titolo(), num_azioni);
						
							//CALCOLO VALORE PACCHETTO
							p.Sommavalore(num_azioni, titolo_richiesto.get_Valore_Titolo());
							}
							
						else {
							System.out.print("\n\n ERRORE IL NUMERO DI AZIONI DEVE ESSERE COMPRESO TRA 0 E 99 ");
							System.out.print("\n\n Inserisci di nuovo il titolo desiderato con il numero di azioni compreso tra 1 e 99 ");									j--;
							}
						
					}			
				
				
					
			//AGGIUNTA DI NUOVI TITOLI CON IL RISPETTIVO NUMERO DI AZIONI PER TITOLO
					
				c = array.length;
				System.out.print("\n\nSi vogliono aggiungere titoli al pacchetto? Digitare 1 per risposta affermativa, digitare 2 per risposta negativa:   ");
				String risposta2 = risposta_02;       /*Input.readLine();*/
				int titoli_totali = 0;
				
				if(risposta2.equals("1")) {
					System.out.print("\nInserire il numero di titoli che si vogliono aggiungere:   ");
					int aggiunte = titoli_aggiunti;     /*Input.readInt();*/
					titoli_totali = p.get_N_titoli() + aggiunte;
					p.Inserimento_N_titoli(titoli_totali);
					
					//STAMPA A VIDEO TUTTI I TITOLI
					System.out.print("\nQuesti sono tutti i titoli disponibili:\n");
					TitoloFinanziarioDAO.read_all_titolifinanziari();
					
					for(int j=0; j<aggiunte; j++) {
						
					//SELEZIONE DEL NUMERO DI AZIONI PER I TITOLI NUOVI
						c++;
						//SELEZIONE TITOLO
						System.out.print("\n\n Inserisci id del "+c+"° TITOLO:    ");
						id_tit =  id_titoli[j];          /*Input.readLine();*/
						
						
						//INSERIRE IL TITOLO SELEZIONATO NELLA CLASSE TITOLO FINANZIARIO
						TitoloFinanziario titolo_richiesto = new TitoloFinanziario(TitoloFinanziarioDAO.get_titolofinanziarioDAO(id_tit)) ;
						
						
						//NUMERO DI AZIONI DA INSERIRE
						System.out.print("\n\n Inserisci NUMERO DI AZIONI DA INSERIRE  = ");
						num_azioni = numero_azioni[c - 1]; /*Input.readInt(); */
						
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

				
				
				System.out.print("\n\n Stampa a video delle azioni e il loro numero");


				AzioniDAO.read_azioni_di_pacchettofinanziario(p.get_id());


			//SALVATAGGIO PACCHETTO
				System.out.print("\n\n Sto modificando il pacchetto....... \n\n");
				//INSERIRE LA MODIFICA DELLE CREDENZIALI
				p.Modifica_informazioniTEST(fattore_rischio, stima_rendimento, nome, descrizione, titoli_totali);
				PacchettoFinanziarioDAO.insert_pacchettofinanziarioDAO(p);

				//VISUALIZZA TUTTI I PACCHETTI	
				System.out.print("\n\n Il pacchetto è stato modificato correttamente. Complimenti !!");

				//STAMPA

				System.out.print("\n\n Ecco il TUO PACCHETTO \n ");

						p.Stampa();	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
