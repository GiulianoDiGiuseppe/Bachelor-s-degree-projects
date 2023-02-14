package Boundary;

import java.sql.SQLException;


import Controller.Bloomfin_Management_System;
import DAO.PacchettoFinanziarioDAO;
import DAO.TitoloFinanziarioDAO;
import Entity.Broker;
import Entity.Input;

public class Broker_Console_Boundary {


	
	public static void BrokerConsoleBoundary() throws SQLException{
try {
		int option = 0;
		Broker b = new Broker();
		b.inserimento_id();
		
		System.out.print(" \nSCEGLI UNA DELLE OPZIONI");

		do {
			System.out.println("\n \n Seleziona operazione: \n" +
					"\t1) Visualizza titoli finanziari \n" +
					"\t2) Visualizza pacchetti creati \n" +
					"\t3) Crea pacchetto finanziario \n" +
					"\t4) Modifica pacchetto finanziario \n" +
					"\t5) Seleziona un nuovo N_REG_CAMERA_COMMERCIO \n" +
					"\t6) Esci");
			
			System.out.print("\n\n Inserisci il comando  ");
				option=Input.readInt();
	
				switch (option) {
				case 1: { TitoloFinanziarioDAO.read_all_titolifinanziari(); break; }
				case 2: { PacchettoFinanziarioDAO.read_pacchetti_creati(b.getN_reg_cam_commercio());break;}
				case 3: { Bloomfin_Management_System.Crea_PacchettoFinanziario(b); break; }	
				case 4: { Bloomfin_Management_System.Modifica_PacchettoFinanziario(b); break; }
				case 5: { b.inserimento_id();; break; }
				case 6: {System.out.print("\n ARRIVEDERCI BROKER!!!"); break; }
				default: { System.out.print("Numero non riconosciuto\n");}
	}
	}
	while(option!=6);	
} finally {
	System.out.print("\n");
}

}
	
	
}

