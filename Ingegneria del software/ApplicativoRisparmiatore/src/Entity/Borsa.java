package Entity;
import java.util.*;

public class Borsa {
	
Borsa( String Nome, String ID_Borsa, String Stato_di_Appartenenza, Date Orario_Apertura ,Date Orario_Chiusura) {
	Nome = new String();
	ID_Borsa = new String();
	Stato_di_Appartenenza = new String();
	Orario_Apertura = new Date();
	Orario_Chiusura = new Date();	
}
	
public String ID_Borsa;
public String Stato_di_Appartenenza;
public Date Orario_Apertura;
public Date Orario_Chiusura;

}

