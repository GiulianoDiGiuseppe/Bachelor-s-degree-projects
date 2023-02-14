package Controller;

public interface Stock_Exchange_Manager {

//Verifica Apertura della Borsa
public  boolean Verifica_Apertura(String ID_Borsa, String ID_Titolo);

//Restituisce il Valore del Titolo per il numero di Azioni del Titolo presente nel Pacchetto Finanziario
public  double Get_Actions(String ID_Pacchetto, String ID_Titolo);
	
}
