package Entity;
import java.util.*;

public class Pacchetti_Acquistati extends Pacchetti_Finanziari {

//Costruttore 
public Pacchetti_Acquistati(String ID_Pacchetto, int N_Pacchetti, double Valore_di_Acquisto, Date Data) {
 this.ID_Pacchetto = ID_Pacchetto;
 this.N_Pacchetti = N_Pacchetti;
 this.Valore_di_Acquisto = Valore_di_Acquisto;
 this.Data = Data;
 
}
	
	
public void Filtra(String Nominativo, Date Data) {}

public String ID_Pacchetto;
public int N_Pacchetti;
public double Valore_di_Acquisto;
public Date Data;


}
