package Controller.Impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import Controller.IBloomfin_Financial_AgencyEIS;
import Controller.Method_of_Payment_Manager;
import DAO.Method_Of_Payment_DAO;
import Entity.Metodo_di_Pagamento;

public class Method_of_Payment_Manager_Impl implements Method_of_Payment_Manager{

	@Override
	public boolean Verifica_Pagamento(String Codice_Fiscale) {
	 ArrayList<Metodo_di_Pagamento> elenco = Method_Of_Payment_DAO.Read_Method_of_Payment(Codice_Fiscale);
	 BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	 Iterator<Metodo_di_Pagamento> i = elenco.iterator();
	 long Data_attuale = System.currentTimeMillis();
	 int option = 0;
	 
	 System.out.println("METODI DI PAGAMENTO REGISTRATI PER LE TUE TRANSAZIONI");
	 System.out.println("     ID_CARTA       " + "       CIRCUITO  ");
	 
	   //Scorro la lista
	   while(i.hasNext()) {
		  Metodo_di_Pagamento m = i.next();
	  System.out.println(m.ID_Carta + "      " + m.Circuito);
		  }
	   
	  System.out.println("INSERIRE IL METODO DI PAGAMENTO \n");
	  
	  try {
		  while(true){
		String ID_Carta = inputReader.readLine();
	   long Data_scadenza_carta = Method_Of_Payment_DAO.Read_Date_of_Cart(ID_Carta).getTime();
	   
	   if(Data_attuale < Data_scadenza_carta) {
		   System.out.println("METODO DI PAGAMENTO VERIFICATO");
		   return true;
	   }
	   else {
			System.out.println("METODO DI PAGAMENTO NON VALIDO; ESCI O INSERISCINE UNO NUOVO \n");
			System.out.println("SELEZIONARE L'OPZIONE DESIDERATA: \n" +
					"1) INSERISCI NUOVO \n" +
					"2) REGISTRANE UNO NUOVO \n" +
					"3) ESCI \n");
			
			System.out.flush();
			
			try {
			option = Integer.parseInt(inputReader.readLine());
			}catch(IOException e) {
			  option = 0;
			}
			
			switch(option) {
			
			case 1:{
				System.out.println("REINSERIRE METODO DI PAGAMENTO");
				 IBloomfin_Financial_AgencyEIS.getInstance().Verifica_Pagamento(Codice_Fiscale);
				 return true;
			}
			
			case 2:{
				 System.out.println("INSERISCI IL CODICE FISCALE \n");
				   Codice_Fiscale = inputReader.readLine();
				   
				 System.out.println("INSERISCI I DATI DELLA CARTA \n");
				 System.out.println("INSERIRE ID_CARTA");
				     ID_Carta = inputReader.readLine();
				     
				 System.out.println("INSERIRE CIRCUITO");  
				 String Circuito = inputReader.readLine();
				 
				 System.out.println("INSERIRE DATA DI SCADENZA");
				 java.sql.Date Data_di_Scadenza = java.sql.Date.valueOf(inputReader.readLine());
				 
				boolean res =  Method_Of_Payment_DAO.Insert_Method_of_Payment(Codice_Fiscale, ID_Carta, Circuito, Data_di_Scadenza);
				if(res == false) {System.out.println("IL METODO DI PAGAMENTO NON E' STATO REGISTRATO");
				  return false;}
				 IBloomfin_Financial_AgencyEIS.getInstance().Verifica_Pagamento(Codice_Fiscale);
				 return true;
				
			}
			
			
			case 3:{
				return false;
			}
			default: {
			     System.out.println("CARATTERE INSERITO NON RICONOSCIUTO !");
		    }
			}
	   }
	  }
		  
	} catch (IOException e) {
		 System.out.println("METODO DI PAGAMENTO INSERITO NON CORRETTO");
	}

		return false;
	}

}
