package Controller;

import Controller.Authentication_Manager.InvalidCredentials;
import Controller.Authentication_Manager.OperationNotAllowed;
import Controller.Impl.Authentication_Manager_Impl;
import Controller.Impl.Financial_Packets_Manager_Impl;
import Controller.Impl.Method_of_Payment_Manager_Impl;
import Controller.Impl.Stock_Exchange_Manager_Impl;
import Entity.Utente;

public class IBloomfin_Financial_AgencyEIS {
	private Stock_Exchange_Manager_Impl stock_manager;
	private Financial_Packets_Manager_Impl financial_manager;
	private Authentication_Manager_Impl authentication_manager;
	private Method_of_Payment_Manager_Impl M_o_P_manager;
	
	IBloomfin_Financial_AgencyEIS(){
		stock_manager = new Stock_Exchange_Manager_Impl();
		financial_manager = new Financial_Packets_Manager_Impl();
		authentication_manager = new Authentication_Manager_Impl();
		M_o_P_manager = new Method_of_Payment_Manager_Impl();	}
	
	private static IBloomfin_Financial_AgencyEIS instance = null;
	
	public static IBloomfin_Financial_AgencyEIS getInstance()  {
			if (instance == null) 
				instance = new IBloomfin_Financial_AgencyEIS();
			
		return instance;
	}
	
	public Stock_Exchange_Manager_Impl get_SEMI_Instance() {
		return stock_manager;
		
	}
	
	public Financial_Packets_Manager_Impl get_FPMI_Instance() {
		return financial_manager;
		
	}
	
	public Authentication_Manager_Impl get_AMI_Instance() {
		return authentication_manager;
		
	}
	
	public Method_of_Payment_Manager_Impl get_MoPMI() {
		return M_o_P_manager;
	}
	

	public void View_My_Packets(String Codice_Fiscale) {
		get_FPMI_Instance().View_My_Packets(Codice_Fiscale);
	}

	public void Filtra(String Codice_Fiscale) {
		get_FPMI_Instance().Filtra(Codice_Fiscale);
	}

	public double Compare_Num_Of_Packets(int N_Pacchetti, String Codice_Fiscale, String ID_Pacchetto) {
		return get_FPMI_Instance().Compare_Num_Of_Packets(N_Pacchetti, Codice_Fiscale, ID_Pacchetto);
	}

	public void Get_Information(String Codice_Fiscale) {
		get_FPMI_Instance().Get_Information(Codice_Fiscale);
	}

	public boolean Sell_Packet(String Codice_Fiscale, String ID_Pacchetto) {
		return get_FPMI_Instance().Sell_Packet(Codice_Fiscale, ID_Pacchetto);
	}

	public double Calcola_val_Pacchetto(String ID_Pacchetto) {
		return get_FPMI_Instance().Calcola_val_Pacchetto(ID_Pacchetto);
	}

	public double Variazione_Percentuale_Valore(double Valore_Pacchetto, double Valore_di_Acquisto) {
		return get_FPMI_Instance().Variazione_Percentuale_Valore(Valore_Pacchetto, Valore_di_Acquisto);
	}

	public boolean Esegui_Transazione(String Codice_Fiscale,String ID_Pacchetto, double Valore_Pacchetto, int Num_Pacchetti) {
		return get_FPMI_Instance().Esegui_Transazione(Codice_Fiscale, ID_Pacchetto, Valore_Pacchetto, Num_Pacchetti);
	}

    public Utente login(String username, String password) throws InvalidCredentials{
    	return get_AMI_Instance().login(username, password);
    }
    
    public Utente getAuthenticatedUser() {
    	return get_AMI_Instance().getAuthenticatedUser();
    }
    
    public boolean Authenticated() {
    	return get_AMI_Instance().Authenticated();
    }
    
    public void assertOperationAllowed(String operation) throws OperationNotAllowed{
    	get_AMI_Instance().assertOperationAllowed(operation);
    }
    
    public void logout() {
    	get_AMI_Instance().logout();
    }
	
    public  boolean Verifica_Apertura(String ID_Borsa, String ID_Titolo) {
    	return get_SEMI_Instance().Verifica_Apertura(ID_Borsa, ID_Titolo);
    }

    public  double Get_Actions(String ID_Pacchetto, String ID_Titolo) {
    	return get_SEMI_Instance().Get_Actions(ID_Pacchetto, ID_Titolo);
    }
	
    public boolean Verifica_Pagamento(String Codice_Fiscale) {
    	return get_MoPMI().Verifica_Pagamento(Codice_Fiscale);
    }
    
    public boolean Registrazione_Risp() {
    	return get_AMI_Instance().Registrazione_Risp();
    }
}
