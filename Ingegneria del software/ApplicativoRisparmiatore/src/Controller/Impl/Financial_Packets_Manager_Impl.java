package Controller.Impl;
import java.util.*;
import Controller.Financial_Packets_Manager;
import Controller.IBloomfin_Financial_AgencyEIS;
import Controller.Method_of_Payment_Manager;
import DAO.Actions_DAO;
import DAO.Financial_Packets_DAO;
import DAO.Purchased_Packets_DAO;
import DAO.Shares_DAO;
import DAO.Stock_Exchange_DAO;
import Entity.Pacchetti_Acquistati;
import java.io.*;
import java.sql.Time;

public class Financial_Packets_Manager_Impl implements Financial_Packets_Manager{

	@Override
	public void View_My_Packets(String Codice_Fiscale){
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); 
		  Financial_Packets_Manager_Impl f_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_FPMI_Instance();
	   ArrayList<Pacchetti_Acquistati> elenco = Purchased_Packets_DAO.Read_Packets(Codice_Fiscale);
	   if(elenco.isEmpty()) {System.out.println("NON ESISTE NESSUN RISPARMIATORE REGISTRATO AL CODICE FISCALE INDICATO");
	       return; }
	   int option = 0;
	   
	   
		   Iterator<Pacchetti_Acquistati> i = elenco.iterator();
		  System.out.println(" ID PACCHETTO " + "NUMERO PACCHETTI " + "  VALORE DI ACQUISTO " + " DATA  ");
		  
		   //Scorro la lista
		   while(i.hasNext()) {
			  Pacchetti_Acquistati p = i.next();
		  System.out.println(p.ID_Pacchetto + "              " + p.N_Pacchetti + "               " + p.Valore_di_Acquisto + "              " + p.Data);
			  }
		   
		   do {
			System.out.println("SELEZIONARE L'OPZIONE DESIDERATA: \n" +
					"1) PROSEGUI CON LA RICERCA STANDARD \n" +
					"2) RICERCA AVANZATA \n" +
					"3) ESCI \n");
			
			System.out.flush();
		   
			try {
			option = Integer.parseInt(inputReader.readLine());
			}catch(IOException e) {
			  System.out.println("OPZIONE INSERITA NON CORRETTA");
			  option = 0;
			}
			
			switch(option) {
			
			case 1:{
				f_manager.Get_Information(Codice_Fiscale);
				return;
			}
			
			case 2:{
				f_manager.Filtra(Codice_Fiscale);
				break;
			}
			
			case 3:{
				System.out.println("SARAI INDIRIZZATO ALLA SCHERMATA PRECEDENTE ! ");
				break;
			}
			default: {
			     System.out.println("CARATTERE INSERITO NON RICONOSCIUTO !");
		    }
			}
		   }while(option != 3);
		   return;
	   }


	@Override
	public void Filtra(String Codice_Fiscale) {
  
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)); 
			int option = 0;
			
		do {
			System.out.println("INSERIRE STATISITCA PER LA RICERCA AVANZATA: \n" +
					"1) RICERCA PER DATA DI ACQUISTO DEL PACCHETTO \n" +
					"2) RICERCA PER ID DEL PACCHETTO \n" +
					"3) ESCI");
			
			System.out.flush();
			
			try {
				option = Integer.parseInt(inputReader.readLine());
			} catch (IOException e) {
				System.out.println("OPZIONE INSERITA NON CORRETTA");
				option = 0;
			}
			switch (option) {
			case 1: {
				java.sql.Date Data = null;
			   	System.out.println("INSERISCI LA DATA DI ACQUISTO DEL PACCHETTO DA RICERCARE ");
			   	
			   	try {
				     Data = java.sql.Date.valueOf(inputReader.readLine());
			   	}catch(IOException e) {
			   		System.out.println("DATA INSERITA NON CORRETTA");
			   	}
			   	
				ArrayList<Pacchetti_Acquistati> elenco_ordinato = Purchased_Packets_DAO.Read_Packets_By_Date(Codice_Fiscale, Data);
				    	 if(elenco_ordinato.isEmpty()) {
				    		 System.out.println("NON ESISTE NESSUN PACCHETTO PER LA DATA SPECIFICATA");
				    		 return;}
				    	 
						   Iterator<Pacchetti_Acquistati> j = elenco_ordinato.iterator();
							  System.out.println(" ID PACCHETTO " + "NUMERO PACCHETTI " + "  VALORE DI ACQUISTO " + " DATA  ");
							  
							   //Scorro la lista
							   while(j.hasNext()) {
								  Pacchetti_Acquistati p = j.next();
							  System.out.println(p.ID_Pacchetto + "                " + p.N_Pacchetti + "                " + p.Valore_di_Acquisto + "              " + p.Data);
								  }
						
				  //Il sistema mostra le informazioni aggiuntive del pacchetto, e il risparmiatore può decidere se venderlo
				  Financial_Packets_Manager_Impl f_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_FPMI_Instance();
				    f_manager.Get_Information(Codice_Fiscale);
							   
				
				
				 break; }
			
			case 2: {
				String ID_Pacchetto = null;
				System.out.println("INSERISCI L'ID DEL PACCHETTO DA RICERCARE ");
				
				try {
				   ID_Pacchetto = inputReader.readLine();
				}catch(IOException e) {
					System.out.println("ID INSERITO NON CORRETTO");
				}
				
				   Pacchetti_Acquistati p = Purchased_Packets_DAO.Read_Packets_By_ID(Codice_Fiscale, ID_Pacchetto);
				   if(p == null) {return;}
					 			    	 
						  System.out.println("  ID PACCHETTO " + "       NUMERO PACCHETTI  " + "         VALORE DI ACQUISTO  " + "       DATA  ");
						  System.out.println(p.ID_Pacchetto + "            " + p.N_Pacchetti + "                " + p.Valore_di_Acquisto + "            " + p.Data);
							  
						   
				//Il sistema mostra le informazioni aggiuntive del pacchetto, e il risparmiatore può decidere se venderlo
				 Financial_Packets_Manager_Impl f_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_FPMI_Instance();
				 f_manager.Get_Information(Codice_Fiscale);
				return; }
			
			case 3: {
				      System.out.println("SARAI INDIRIZZATO ALLA SCHERMATA PRECEDENTE ");
				          break; }
			default: {
				System.out.println("CARATTERE INSERITO NON RICONOSCIUTO !");
			}
	}
	

}while (option != 3);
	
	}

	@Override
	public double Compare_Num_Of_Packets(int N_Pacchetti, String Codice_Fiscale, String ID_Pacchetto) {
		int N_Pacchetti_posseduti = Purchased_Packets_DAO.Read_Quantity_of_Packets(Codice_Fiscale, ID_Pacchetto);
	
		if(N_Pacchetti > N_Pacchetti_posseduti) {
			System.out.println("NUMERO DI PACCHETTI INSERITO NON VALIDO");
			return 0;
		}
		else if(N_Pacchetti <= N_Pacchetti_posseduti) {
			 return Purchased_Packets_DAO.Read_Price(Codice_Fiscale, ID_Pacchetto);
		 
		}
		
		return 0;
	}


	@Override
	public void Get_Information(String Codice_Fiscale) {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("SELEZIONA L'ID DEL PACCHETTO PER VISUALIZZARE MAGGIORI INFO");
		String ID_Pacchetto = null;
		int option  = 0;
		
		try {
		     ID_Pacchetto = inputReader.readLine();
		}catch(IOException e) {
			System.out.println("ID INSERITO NON CORRETTO ");
		}
		
		boolean res = Financial_Packets_DAO.Select_Packet(ID_Pacchetto);
		if(res == false) {return;}
		
		do {
			System.out.println("SELEZIONARE L'OPZIONE VENDI PER VENDERE IL PACCHETTO: \n" +
					"1) VENDI PACCHETTO \n" +
					"2) ESCI \n");
			
			System.out.flush();
			
			try {
				option = Integer.parseInt(inputReader.readLine());
			} catch (IOException e) {
				System.out.println("OPZIONE INSERITA NON CORRETTA");
				option = 0;
			}
			switch (option) {
			
			case 1:{
				Financial_Packets_Manager_Impl f_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_FPMI_Instance();
				f_manager.Sell_Packet(Codice_Fiscale, ID_Pacchetto);
				  break; //controlla
			}
			
			case 2: {
			      System.out.println("SARAI INDIRIZZATO ALLA SCHERMATA PRECEDENTE ");
			          break; }
		   default: {
			     System.out.println("CARATTERE INSERITO NON RICONOSCIUTO !");
		    }
			
			}
		
	}while(option != 2);
	
}


	@Override
	public boolean Sell_Packet(String Codice_Fiscale, String ID_Pacchetto) {
	
		   ArrayList<String> elenco_ID = Shares_DAO.Read_ID_Actions(ID_Pacchetto);
		   Stock_Exchange_Manager_Impl s_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_SEMI_Instance();
		   Financial_Packets_Manager_Impl f_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_FPMI_Instance();
		   Iterator<String> i = elenco_ID.iterator();
	       boolean res = true;
	       
		   //Scorro la lista di titoli
		   while(i.hasNext()) {
			  String ID_Titolo = i.next();
			  String ID_Borsa = Actions_DAO.Read_ID_Stock_Exchange(ID_Titolo);
			    res = s_manager.Verifica_Apertura(ID_Borsa, ID_Titolo);
			  
			  //Se almeno una borsa in cui il titolo è registrato è chiusa il risparmiatore non può vendere il pacchetto
              if(res == false) {
            	 System.out.println("LA BORSA " + ID_Borsa + " E' CHIUSA");
            	Time Orario_Apertura =  Stock_Exchange_DAO.Read_Orario_Apertura(ID_Borsa, ID_Titolo);
            	Time Orario_Chiusura = Stock_Exchange_DAO.Read_Orario_Chiusura(ID_Borsa, ID_Titolo);
            	 System.out.println("GLI ORARI DELLA BORSA SONO : APERTURA= " + Orario_Apertura + "  CHIUSURA= " + Orario_Chiusura);
            	 break;
              }
			  }
		      
		      if(res == false) {
		    	  System.out.println("IMPOSSIBILE VENDERE IL PACCHETTO AL MOMENTO RIPROVA PIU' TARDI ");
		    	  return false;
		      }
		   
		      System.out.println("LE BORSE SONO APERTE PUOI VENDERE IL PACCHETTO SELEZIONATO ");
		      
		      //Il risparmaitore inserisce il Numero di pacchetti da vendere, la funzione li confronta con quelli da lui in possesso e se il numero corrisponde ne restituisce il valore di acquisto
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		   System.out.println("INSERISCI IL NUMERO DI PACCHETTI DA VENDERE DEL TIPO SELEZIONATO ");
			 int Num_Pacchetti  = 0;

				try {
				    Num_Pacchetti = Integer.parseInt(inputReader.readLine());
				    if(Num_Pacchetti == 0) {
				    	System.out.println("VALORE INSERITO NON CORRETTO ! ");
				    	return false;}
				}catch(IOException e) {
					System.out.println("NUMERO INSERITO NON CORRETTO ");
				}
				
		   double Valore_di_Acquisto = f_manager.Compare_Num_Of_Packets(Num_Pacchetti,Codice_Fiscale, ID_Pacchetto);
		   if(Valore_di_Acquisto == 0) {return false;}
		   double Valore_Pacchetto = f_manager.Calcola_val_Pacchetto(ID_Pacchetto);
		   
		   
		   //Calcolo la variazione percentuale 
		   double val = f_manager.Variazione_Percentuale_Valore(Valore_Pacchetto, Valore_di_Acquisto);
		   System.out.println("LA VARIAZIONE PERCENTUALE DEL VALORE E' : " + val + "%");
		   
		   //Eseguo Transazione 
		   boolean result = f_manager.Esegui_Transazione(Codice_Fiscale, ID_Pacchetto, Valore_Pacchetto, Num_Pacchetti);
		   if(result == false) {
			   System.out.println("IL PACCHETTO NON E' STATO VENDUTO");
		       return false;    }
		   
		   System.out.println("PACCHETTO VENDUTO CON SUCCESSO");

		return true;
	}


	@Override
	public double Calcola_val_Pacchetto(String ID_Pacchetto) {
		ArrayList<String> elenco_ID_Titoli = Shares_DAO.Read_ID_Actions(ID_Pacchetto);
		Stock_Exchange_Manager_Impl s_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_SEMI_Instance();
		Iterator<String> i = elenco_ID_Titoli.iterator();
		double valore = 0;
		
		//Itero la lista
		while(i.hasNext()) {
			
		 String ID_Titolo = i.next();
		  valore = valore + s_manager.Get_Actions(ID_Pacchetto, ID_Titolo);
		 }
		
		System.out.println("IL VALORE ATTUALE DEL SINGOLO PACCHETTO E': " + valore);
	   return valore;
	}


	@Override
	public double Variazione_Percentuale_Valore(double Valore_Pacchetto, double Valore_di_Acquisto) {
		double Variazione_percentuale = ((Valore_Pacchetto - Valore_di_Acquisto)/Valore_di_Acquisto)*100;
		
		return Variazione_percentuale;
	}


	@Override
	public boolean Esegui_Transazione(String Codice_Fiscale, String ID_Pacchetto, double Valore_Pacchetto, int Num_Pacchetti) {
		Method_of_Payment_Manager m_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_MoPMI();
		 BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		 
		int option = 0;
		//Verifica il metodo di pagamento
		boolean res = m_manager.Verifica_Pagamento(Codice_Fiscale);
		
		if(res == false) {
			System.out.println("TRANSAZIONE FALLITA !");
			return false;
		}
		
		System.out.println("IL VALORE COMPLESSIVO DI VENDITA E'" + Valore_Pacchetto*Num_Pacchetti);
		do {
		System.out.println("SELEZIONARE CONFERMA PER VENDERE IL NUMERO DI PACCHETTI INSERITI: \n" +
				"1) CONFERMA VENDITA \n" +
				"2) ESCI");
		
		System.out.flush();
		
		try {
			option = Integer.parseInt(inputReader.readLine());
		} catch (IOException e) {
			System.out.println("OPZIONE INSERITA NON CORRETTA");
			option = 0;
		}
		
		switch(option) {
		
		case 1:{
			
			//Restituisce il numero di pacchetti rimasti 
			int Pacchetti_tot = Purchased_Packets_DAO.Update_Num_Of_Packets(ID_Pacchetto, Num_Pacchetti, Codice_Fiscale);
			
			if(Pacchetti_tot == 0) {
				boolean result = Purchased_Packets_DAO.Delete_Packet(Codice_Fiscale, ID_Pacchetto);
				
				if(result == false) {
					System.out.println("SI E' VERIFICATO UN ERROE NEL SALVATAGGIO DEL DATO ");
					System.out.println("TRANSAZIONE ABORTITA");
					return false;
				}
			}
			 
			System.out.println("TRANSAZIONE COMPLETATA IL NUMERO DI PACCHETTI IN POSSESSO E' STATA AGGIORNATO");
			
			return true;
		}
		
		case 2:{
			System.out.println("IL PACCHETTO NON SARA' VENDUTO...");
			return false;
		}
		
		default: {
		     System.out.println("CARATTERE INSERITO NON RICONOSCIUTO !");
	    }
		}
		}while(option != 2);
      return false;
	}
	

	
	
}
