package Boundary;

import java.sql.SQLException;


import Entity.Input;

public class Application_Console_Boundary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			int option = 0;
			System.out.print("BENVENUTO IN BLOOMFIN");
		
			System.out.print(" \n SEI UN BROKER O UN RISPARMIATORE");

			do {
				System.out.println("\n \n Seleziona operazione: \n" +
						"\t1) BROKER \n" +
						"\t2) RISPARMIATORE \n" +
						"\t3) Esci");
				
				System.out.print("\n\n Inserisci il comando  ");
					option=Input.readInt();
		
					switch (option) {
					case 1: {Broker_Console_Boundary.BrokerConsoleBoundary(); break;}
					case 2: {System.out.print("\n Non ancora implementato !!!"); break; }		
					case 3: {System.out.print("\n ARRIVEDERCI !!!"); break; }
					default: { System.out.print("Numero non riconosciuto\n");}
		}
		}
			
		while(option!=3);	
	} catch(SQLException e) {
		System.err.print("\n\nSpiacenti c'è stato un errore con la comunicazione col database\n");
		e.printStackTrace();
	}
		
	}
		
		
		
		
		
		
}
