package Entity;

public class Pacchetti_Finanziari {

//Costruttore
public Pacchetti_Finanziari() {}
	

public String ID_Pacchetto;
public double Fattore_di_Rischio;
public double Stima_Rendimento;
public String Nome_Pacchetto;
public enum Stato {Disponibile, Non_Disponibile,In_Elaborazione};
public double Valore_Pacchetto;
public String N_Reg_Cam_Commercio;
public String Descrizione;
public int N_Titoli;

}
