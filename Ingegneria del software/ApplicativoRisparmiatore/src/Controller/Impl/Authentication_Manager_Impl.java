package Controller.Impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Controller.Authentication_Manager;
import DAO.Risparmiatore_DAO;
import DAO.Utente_DAO;
import Entity.Utente;
import Entity.Utente.UserRole;

public class Authentication_Manager_Impl implements Authentication_Manager {
    
    private Utente authenticatedUser;
    
    @Override
    public Utente login(String username, String password) throws InvalidCredentials {
        if (Authenticated()) {
            logout();
        }
 
        String ruolo = Utente_DAO.Read_user_login(username, password);
        if(ruolo != null) {authenticatedUser = new Utente(username,password,UserRole.valueOf(ruolo) );}
        return authenticatedUser;
    }
    
    @Override
    public Utente getAuthenticatedUser() {
        return authenticatedUser;
    }
    
    @Override
    public boolean Authenticated() {
        return authenticatedUser != null;
    }
    
    @Override
    public void assertOperationAllowed(String operation) throws OperationNotAllowed {
        if (Authenticated() == false) {
            throwOperationNotAllowed(operation);
        }
        if (authenticatedUser.get_UserRole() != Utente.UserRole.RISPARMIATORE) {
            throwOperationNotAllowed(operation);
        }
    }
    
    private void throwOperationNotAllowed(String operation) throws OperationNotAllowed {
        throw new OperationNotAllowed(operation, authenticatedUser);
    }
    
    @Override
    public void logout() {
        authenticatedUser = null;
    }

	@Override
	public boolean Registrazione_Risp() {
		System.out.println("INSERIRE LE CREDENZIALI INDICATE: ");
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String Nome = null;
		String Cognome = null;
		String Indirizzo = null;
		String Codice_Fiscale = null;
		java.sql.Date Data_Nascita = null;
		String password = null;
		String UserRole = "RISPARMIATORE";
		
		System.out.println("NOME:");
		try {
		      Nome = inputReader.readLine();
		}catch(IOException e) {
		  System.out.println("CARATTERE ERRATO ");
		}
		
		System.out.println("COGNOME:");
		try {
		      Cognome = inputReader.readLine();
		}catch(IOException e) {
		  System.out.println("CARATTERE ERRATO ");
		}
		
		System.out.println("INDIRIZZO:");
		try {
		    Indirizzo = inputReader.readLine();
		}catch(IOException e) {
		  System.out.println("CARATTERE ERRATO ");
		}
		
		System.out.println("CODICE_FISCALE:");
		try {
		       Codice_Fiscale = inputReader.readLine();
		}catch(IOException e) {
		  System.out.println("CARATTERE ERRATO ");
		}
		
		System.out.println("DATA_NASCITA:");
		try {
		     Data_Nascita = java.sql.Date.valueOf(inputReader.readLine());
		}catch(IOException e) {
		  System.out.println("CARATTERE ERRATO ");
		}
		
		System.out.println("PASSWORD:");
		try {
		       password = inputReader.readLine();
		}catch(IOException e) {
		  System.out.println("CARATTERE ERRATO ");
		}
		
		boolean res1 = Risparmiatore_DAO.Save_credentials(Nome, Cognome, Indirizzo, Codice_Fiscale, Data_Nascita, password);
		boolean res2 = Utente_DAO.Save_user_login(Codice_Fiscale, password, UserRole);
		
		if(res1 == false || res2 == false) {
			System.out.println("SI E' VERIFICATO UN ERRORE NELLA REGISTRAZIONE !");
			return false;
		}
		System.out.println("REGISTRAZIONE COMPLETATA CON SUCCESSO !");
		
		return true;
	}
	
}
