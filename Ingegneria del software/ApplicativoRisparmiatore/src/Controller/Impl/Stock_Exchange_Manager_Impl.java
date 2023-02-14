package Controller.Impl;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import Controller.Stock_Exchange_Manager;
import DAO.Actions_DAO;
import DAO.Shares_DAO;
import DAO.Stock_Exchange_DAO;


public class Stock_Exchange_Manager_Impl implements Stock_Exchange_Manager  {

	
//Verifica Apertura della Borsa
	@Override
	public boolean Verifica_Apertura(String ID_Borsa, String ID_Titolo) {

			Time apertura = Stock_Exchange_DAO.Read_Orario_Apertura(ID_Borsa, ID_Titolo);
			Time chiusura = Stock_Exchange_DAO.Read_Orario_Chiusura(ID_Borsa, ID_Titolo);
			Time currentTime = java.sql.Time.valueOf(LocalTime.now());
			if(currentTime.getTime() >= apertura.getTime() && currentTime.getTime() <= chiusura.getTime()) {
				return true;
			}
       return false;
	}

	
	
//Restituisce il Valore del Titolo per il numero di Azioni del Titolo presente nel Pacchetto Finanziario	
	@Override
	public double Get_Actions(String ID_Pacchetto, String ID_Titolo){
		
	double Valore_per_Azione = Actions_DAO.Read_Action_Value(ID_Titolo);
	int Num_Azioni = Shares_DAO.Read_Shares_Num(ID_Pacchetto, ID_Titolo);
	
	double valore_tot = Valore_per_Azione*Num_Azioni;
	return valore_tot;
	
	}

}
