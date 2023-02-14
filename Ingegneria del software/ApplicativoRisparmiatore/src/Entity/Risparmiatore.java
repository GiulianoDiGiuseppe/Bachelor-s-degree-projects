package Entity;
import java.util.Date;

public class Risparmiatore {

	
//Costruttore
public Risparmiatore(char Nome, char Cognome, String Indirizzo, String Codice_Fiscale, Date Data_Nascita) {
	this.Nome = Nome;
	this.Cognome = Cognome;
	this.Indirizzo = Indirizzo;
	this.Codice_Fiscale = Codice_Fiscale; 
	this.Data_Nascita = Data_Nascita;
	
}
	
	
char Nome;
char Cognome;
String Indirizzo; 
String Codice_Fiscale;
Date Data_Nascita;


}
