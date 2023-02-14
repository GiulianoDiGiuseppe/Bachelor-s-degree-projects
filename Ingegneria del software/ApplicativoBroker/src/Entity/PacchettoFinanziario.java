package Entity;


import java.util.UUID;



public class PacchettoFinanziario {

	String ID_pacchetto	;				//Identificativo pacchetto
	double Fattore_di_rischio;			//Indica percentule di fallimento del pacchetto
	double Stima_di_rendimento ;		//Indica la probabile percentuale di gudagno 
	String Nome_pacchetto;				//Nome assegnato dal broker
	String  Stato;	//Indica se è disponibile , non disponibile o in fase di elaborazione
	double Valore_pacchetto;			//Indica il prezzo che vale il pacchetto
	String N_reg_cam_commercio;			//Id del broker che corrisponde alla sua registrazione alla camera di commercio
	String Descrizione	;				//Commento per i clienti
	int N_titoli;						//Numero totale di Titoli azionari inseriti
	
	


//costruttore 
public PacchettoFinanziario(String ID_pacchetto , double Fattore_di_rischio, double Stima_di_rendimento, String Nome_pacchetto, String  Stato, double Valore_pacchetto, String N_reg_cam_commercio, String Descrizione, int N_titoli) {
	this.ID_pacchetto = ID_pacchetto;
	this.Fattore_di_rischio = Fattore_di_rischio;
    this.Stima_di_rendimento = Stima_di_rendimento;
    this.Nome_pacchetto = Nome_pacchetto;
    this.Stato = Stato;
    this.Valore_pacchetto = Valore_pacchetto;
    this.N_reg_cam_commercio = N_reg_cam_commercio;
    this.Descrizione = Descrizione;
    this.N_titoli = N_titoli;
    while(this.N_titoli<1) {
    	System.out.print("Inserisci : NUMERI DI TITOLI:");
		this.N_titoli = Input.readInt(); 
    }

}


public void PacchettoFinanziarioTEST( double Fattore_di_rischio, double Stima_di_rendimento, String Nome_pacchetto, String Descrizione, int N_titoli,String N_reg_cam_commercio) {
	this.ID_pacchetto = new String(UUID.randomUUID().toString().substring(1,8));
	this.Fattore_di_rischio = Fattore_di_rischio;
    this.Stima_di_rendimento = Stima_di_rendimento;
    this.Nome_pacchetto = Nome_pacchetto;
    this.Stato ="DISPONIBILE";
    this.Valore_pacchetto = 0;
    this.N_reg_cam_commercio = N_reg_cam_commercio;
    this.Descrizione = Descrizione;
    this.N_titoli = N_titoli;
    while(this.N_titoli<1) {
    	System.out.print("Inserisci : NUMERI DI TITOLI:");
		this.N_titoli = Input.readInt(); 
    }

}


//costruttore di default
public PacchettoFinanziario() {
		this.ID_pacchetto = new String(UUID.randomUUID().toString().substring(1,8));        
		this.Fattore_di_rischio = 0;
        this.Stima_di_rendimento = 0;
        this.Nome_pacchetto = null;
        this.Stato = null;
        this.Valore_pacchetto = 0;
        this.N_reg_cam_commercio = null;
        this.Descrizione = null;
        this.N_titoli = 0;
    }


//costruttore di copia
public PacchettoFinanziario(PacchettoFinanziario p) {
	this.ID_pacchetto = p.ID_pacchetto;
    this.Fattore_di_rischio = p.Fattore_di_rischio;
    this.Stima_di_rendimento = p.Stima_di_rendimento;
    this.Nome_pacchetto = p.Nome_pacchetto;
    this.Stato = p.Stato;
    this.Valore_pacchetto = p.Valore_pacchetto;
    this.N_reg_cam_commercio = p.N_reg_cam_commercio;
    this.Descrizione = p.Descrizione;
    this.N_titoli = p.N_titoli;
}



//Funzioni GET			
		//get id
		public String get_id() {
			return ID_pacchetto;
		}

	//get fattore di rischio	
		public double get_Fattore_di_rischio(){			
			return Fattore_di_rischio;
		}
	//get stima	
		public double get_Stima_di_rendimento()	{		
			return Stima_di_rendimento;
		}
	//get nome pacchetto	
		public String get_Nome_Pacchetto() {
			return Nome_pacchetto;
		}
	//get valore
		public double get_Valore_pacchetto() {
			return Valore_pacchetto;
		}
	//get stato
		public String get_Stato() {
			return Stato;
		}
	//get cam-comm (il numero di registrazione alla camera di commercio del broker creatore)
		public String get_N_Cam_commercio() {
			return  N_reg_cam_commercio;
		}				
		//get descrizione
		public String get_Descrizione() {
			return Descrizione;
		}
		//get num_tit
		public int get_N_titoli() {

			return N_titoli;
		}		
		
//Inserimento ID
		public void set_Id(String id_pacchetto) {
			this.ID_pacchetto = id_pacchetto;
		}
		
//Inserimento numero titoli
		public void Inserimento_N_titoli(int a) {
			this.N_titoli = a;
		}
		
//Inserimento valori
	public void Inserimento_informazioni(String a) {
		System.out.print("\nID_PACCHETTO generato automaticamente...\n");
		this.ID_pacchetto = new String(UUID.randomUUID().toString().substring(1,8));
	
		System.out.print("Inserisci : FATTORE DI RISCHIO:");
		this.Fattore_di_rischio = Input.readDouble(); 		
		System.out.print("Inserisci la STIMA DI RENDIMENTO:");
		this.Stima_di_rendimento = Input.readDouble(); 	
		System.out.print("Inserisci il NOME del pacchetto:");
		this.Nome_pacchetto = Input.readLine(); 		
		this.Stato="Disponibile";
		this.Valore_pacchetto=0;
		
		this.N_reg_cam_commercio = a;
	
		System.out.print("inserisci la DESCRIZIONE:");
		this.Descrizione = Input.readLine(); 
		System.out.print("Inserisci : NUMERI DI TITOLI:");
		this.N_titoli = Input.readInt(); 
	    while(this.N_titoli<1) {
	    	System.out.print("Il numero dei titoli DEVE essere maggiore di 0");
	    	System.out.print("\n \t Inserisci : NUMERI DI TITOLI:");
			this.N_titoli = Input.readInt(); 
	    }

	}
	
	public void Modifica_informazioni(int titoli_totali) {
        System.out.print("Inserisci : FATTORE DI RISCHIO:");
        this.Fattore_di_rischio = Input.readDouble();
        System.out.print("Inserisci la STIMA DI RENDIMENTO:");
        this.Stima_di_rendimento = Input.readDouble();
        System.out.print("Inserisci il NOME del pacchetto:");
        this.Nome_pacchetto = Input.readLine();
        this.Stato="Disponibile";
        System.out.print("Inserisci la DESCRIZIONE:");
        this.Descrizione = Input.readLine();
        this.N_titoli = titoli_totali;
    }
	
	//FUNZIONE CHE SERVE SOLO PER IL TEST, CHIATA IN Modifica_PacchettoFinanziarioTEST
		public void Modifica_informazioniTEST(double fattore_rischio, double stima_rendimento, String nome, String descrizione, int titoli_totali) {
			this.Fattore_di_rischio = fattore_rischio;
			this.Stima_di_rendimento = 	stima_rendimento;
			this.Nome_pacchetto =  nome;
			this.Stato="Disponibile";	
			this.Descrizione = descrizione;
			this.N_titoli = titoli_totali;
		}
		

    public void Modifica_id() {
    	System.out.print("\nID_PACCHETTO cambiato automaticamente...\n");
		this.ID_pacchetto = new String(UUID.randomUUID().toString().substring(1,8));
    }
	
	
//inserimento pacchetto di prova
		public void Inserimento_informazioni_prova() {
			this.ID_pacchetto="1231243";
			this.Stima_di_rendimento = 2.4; 
			this.Fattore_di_rischio = 1.03; 
			this.Nome_pacchetto = "Ecosistema ";
			this.Stato = "DISPONIBILE"; 
			this.Valore_pacchetto=123;
			this.Descrizione = "Punta a valorizorizzare le societa ecosostenibili";
			this.N_titoli = 1; 		
			this.N_reg_cam_commercio="12312";
		}

//stampa 
	public void Stampa() {
		System.out.print("\n ID:"+ID_pacchetto);
		System.out.print("\t STIMA DI RENDIMENTO:"+this.Stima_di_rendimento+"%");
		System.out.print("\t FATTORE DI RISCHIO:"+this.Fattore_di_rischio+"%");
		System.out.print("\t NOME:"+this.Nome_pacchetto);
		System.out.print("\n STATO:"+this.Stato);
		System.out.print("\t VALORE:"+this.Valore_pacchetto);
		System.out.print("\t ID_BROKER:"+this.N_reg_cam_commercio);
		System.out.print("\n DESCRIZIONE:"+this.Descrizione);
		System.out.print("\n NUMERO DI TITOLI:"+this.N_titoli);
		
	}


//Prende in ingresso il valore del titolo e il numero di azioni e lo somma al valore attuale del pacchetto
public void Sommavalore(int a ,double b){
	this.Valore_pacchetto=this.get_Valore_pacchetto()+(b*a);
}






//END

}

		

