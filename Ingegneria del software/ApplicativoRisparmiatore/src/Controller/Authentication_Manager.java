package Controller;

import Entity.Utente;

public interface Authentication_Manager {

	   
	public static class InvalidCredentials extends Exception {
		
    	public InvalidCredentials(Utente user) {
    	 super("CREDENZIALI INSERITE NON VALIDE");
    	  this.user = user;
    	}
    	final private Utente user;
    }
    

	public static class OperationNotAllowed extends Exception {
		
        public OperationNotAllowed(String operation, Utente user) {
            super("OPERAZIONE NON AUTORIZZATA");
            this.operation = operation;
            this.user = user;
        }

        public String getOperation() {
            return operation;
        }

        public Utente getUser() {
            return user;
        }
        
        final private String operation;
        final private Utente user;
    }
    
	public boolean Registrazione_Risp();
	
    public Utente login(String username, String password) throws InvalidCredentials;
    
    public Utente getAuthenticatedUser();
    
    public boolean Authenticated();
    
    public void assertOperationAllowed(String operation) throws OperationNotAllowed;
    
    public void logout() ;
	
	
	
	
}
