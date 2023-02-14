package Entity;


import java.time.LocalDate;

public class Broker extends Utente {

	private String Nome;
	private String Cognome;
	private LocalDate Data_nascita;
	private String N_reg_cam_commercio;		//Id del broker che corrisponde alla sua registrazione alla camera di commercio
	
	//Costruttore
    public Broker(String nome, String cognome, LocalDate data_nascita, String n_reg_cam_comm) {
        super("username", "password", UserRole.BROKER);
        this.Nome = nome;
        this.Cognome = cognome;
        this.Data_nascita = data_nascita;
        this.N_reg_cam_commercio = n_reg_cam_comm;
    }

    //Costruttore di copia
    public Broker(Broker b) {
        this(b.Nome, b.Cognome, b.Data_nascita, b.N_reg_cam_commercio);
    }

    //Costruttore di default
        public Broker() {
            super("username", "password", UserRole.BROKER);
            this.Nome = null;
            this.Cognome = null;
            this.Data_nascita = null;
            this.N_reg_cam_commercio = null;
        }
	
	
	//FUNZIONI GET
	public String getNome() {
		return Nome;
	}
	public String getCognome(){
		return Cognome;
	}
	public java.time.LocalDate getData_nascita(){
		return Data_nascita;
	}
	public String getN_reg_cam_commercio() {
		return N_reg_cam_commercio;
	}
	
	//FUNZIONI SET
	public void set_Nome(String nome) {
		this.Nome = nome;
	}
	public void set_Cognome(String cognome) {
		this.Cognome = cognome;
	}
	public void setData_nascita(LocalDate data_nascita) {
		this.Data_nascita = data_nascita;
	}
	public void setN_reg_cam_commercio(String n_reg_cam_comm) {
		this.N_reg_cam_commercio = n_reg_cam_comm;
	}

	
	public void inserimento_id() {
		String n_reg;
	do {
		System.out.print("\n\nInserisci il numero di registrazione alla camera di commercio valido:    ");
		n_reg = Input.readLine();
		this.setN_reg_cam_commercio(n_reg);
	}
	while(n_reg.equals(""));

	}
	
	
	
}

