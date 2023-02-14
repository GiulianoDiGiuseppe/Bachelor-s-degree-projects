package Boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import Controller.Authentication_Manager.InvalidCredentials;
import Controller.IBloomfin_Financial_AgencyEIS;
import Entity.Utente;

public class Application_Console_Boundary {
 
    public Application_Console_Boundary() {
        final boolean AUTO_FLUSH_FLAG = true;
        
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
        this.consoleWriter = new PrintWriter(System.out, AUTO_FLUSH_FLAG);
    }
    
    private boolean Registrazione() {
    	int option = 0;
    	do {
    		System.out.println("SEI RISPARMIATORE O BROKER ? SCEGLI IL TUO RUOLO : \n" +
    				"1) RISPARMIATORE \n" +
    				"2) BROKER \n" +
    				"3) EXIT\n");
    		System.out.flush();
    		
    		try {
    			option = Integer.parseInt(consoleReader.readLine());
    		} catch (IOException e) {
    			System.out.println("OPZIONE INSERITA NON CORRETTA");
    			option = 0;
    		}
    		
    		switch(option) {
    		
    		case 1:{
    			System.out.println("REGISTRATI !!");
    			  boolean res = get_Bloomfin_FA_EIS().Registrazione_Risp();
    			  if(res == false) {return false;}
    			     return true;
    			    
    			}

    		case 2:{
    			System.out.println("FUNZIONALITA' NON ANCORA IMPLEMENTATA !");
    			      return false;
    		}
    		
    		case 3:{
    			 System.out.println("USCIRAI DALL'APPLICAZIONE");
    			 return false;
    		}
    		
    		default: {
    		     System.out.println("CARATTERE INSERITO NON RICONOSCIUTO !");
    	    }
    	}
    }while(option != 3);
    	return false;
    	
  }
    
    
    private void doLogin() throws IOException {
        do {
            consoleWriter.format("Username: ");
            String username = consoleReader.readLine();
            consoleWriter.format("Password: ");
            String password = consoleReader.readLine();
           

            try {
            	get_Bloomfin_FA_EIS().login(username, password);
            } catch (InvalidCredentials e) {
                consoleWriter.format("CREDENZIALI INVALIDE ! RIPROVA.\n");
            }
        } while (get_Bloomfin_FA_EIS().Authenticated() == false);
    }
    
    private void doLogout() {
    	get_Bloomfin_FA_EIS().logout();
        consoleWriter.format("********** ARRIVEDERCI **********\n");
    }
    
    private Risparmiatore_Console_Boundary get_Risparmiatore_Console_Boundary() {
        return new Risparmiatore_Console_Boundary(consoleReader, consoleWriter);
    }
    
    private void handleException(Exception e) {
        consoleWriter.format("Sorry! An exception occurred during the execution!\n");
        consoleWriter.format("The program must be terminated. Cause: %s\n",  e.getMessage());
        e.printStackTrace(consoleWriter);
    }
    
    private IBloomfin_Financial_AgencyEIS get_Bloomfin_FA_EIS() {
        return IBloomfin_Financial_AgencyEIS.getInstance();
    }
    
    public TerminationState runApplication() {
  
        	   System.out.println("BENVENUTO IN BLOOMFIN, SOCIETA' DI INTERMEDIAZIONE FINANZIARIA ");
        	   int option = 0;
        	   
         try { 
        	   while(!get_Bloomfin_FA_EIS().Authenticated()) {
           		System.out.println("ESEGUI IL LOGIN O REGISTRATI : \n" +
           				"1) LOGIN \n" +
           				"2) REGISTRATI \n");
           		System.out.flush();
           		
           		try {
           			option = Integer.parseInt(consoleReader.readLine());
           		} catch (IOException e) {
           			System.out.println("OPZIONE INSERITA NON CORRETTA");
           			option = 0;
           		}
           		
           		switch(option) {
           		
           		case 1: {doLogin();break;}

           		case 2:{boolean res = Registrazione(); 
           		         if(res == false) { 
           		        	 System.out.println("**********ARRIVEDERCI***********");
           		        	 return TerminationState.CORRECT_TERMINATION;}
           		          }
           		
           		default: {
           		     System.out.println("CARATTERE INSERITO NON RICONOSCIUTO !");
           	    }
           		}
        	  }
 
            try {
                Utente.UserRole authenticatedUserRole = get_Bloomfin_FA_EIS().getAuthenticatedUser().get_UserRole();
                if (authenticatedUserRole == Utente.UserRole.RISPARMIATORE) {
                	get_Risparmiatore_Console_Boundary().showBoundary();
                } else {
                    consoleWriter.format("OPS: L'APPLICAZIONE NON E' STATA ANCORA SVILUPPATA PER QUESTO TIPO DI UTENTE !\n", authenticatedUserRole);
                }
            }finally {
                doLogout();
            }
            
            return TerminationState.CORRECT_TERMINATION;
            
        } catch (Exception e) {
            handleException(e);
            return TerminationState.ABNORMAL_TERMINATION;
        }
    }
    
    public static enum TerminationState {
        CORRECT_TERMINATION,
        ABNORMAL_TERMINATION
    };
    
    private final BufferedReader consoleReader;
    private final PrintWriter consoleWriter;
	
}
