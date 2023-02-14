package Boundary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import Controller.IBloomfin_Financial_AgencyEIS;

public class Risparmiatore_Console_Boundary {
 
	
    public Risparmiatore_Console_Boundary(BufferedReader consoleReader, PrintWriter consoleWriter) {
        
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }	
	
    private SelectionOptions chooseOption() throws IOException {
        System.out.println("SALVE RISPARMIATORE, SCEGLI UNA DELLE OPZIONI OFFERTE DA BLOOMFIN:\n");
        while (true) {
        	System.out.println("0. EXIT ");
        	System.out.println("1. VISUALIZZA PACCHETTI DISPONIBILI ");
        	System.out.println("2. ACQUISTA UN PACCHETTO ");
        	System.out.println("3. VISUALIZZA PACCHETTI IN TUO POSSESSO ");

            try {
                int optionValue = Integer.parseInt(consoleReader.readLine());
                switch (optionValue) {
                    case 0: return SelectionOptions.EXIT;
                    case 1: return SelectionOptions.VIEW_AVAILABLE_PACKETS;
                    case 2: return SelectionOptions.BUY_PACKET;
                    case 3: return SelectionOptions.VIEW_MY_PACKETS;
                    default: 
                       System.out.println("NESSUNA AZIONE ASSOCIATA A QUESTO NUMERO! RIPROVA.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("VALORE INSERITO NON CORRETTO ! RIPROVA.\n");
            }
        }
    }
    
    private static enum SelectionOptions {
        VIEW_MY_PACKETS,
        VIEW_AVAILABLE_PACKETS,
        BUY_PACKET,
        EXIT
    }
	
	
    public void showBoundary() throws IOException {
        SelectionOptions selectedOption = null;
        while (true) {
            selectedOption = chooseOption();
                if (selectedOption == SelectionOptions.VIEW_MY_PACKETS) {
                	IBloomfin_Financial_AgencyEIS.getInstance().View_My_Packets(IBloomfin_Financial_AgencyEIS.getInstance().getAuthenticatedUser().get_Username());
                } else if (selectedOption == SelectionOptions.VIEW_AVAILABLE_PACKETS) {
                	View_Available_Packets();
                } else if (selectedOption == SelectionOptions.BUY_PACKET) {
                	Buy_Packet();
                } else {
                    return;
                }
        }
    }


//Acquista Pacchetto
public void Buy_Packet() {
    System.out.println("FUNZIONALITA' NON ANCORA IMPLEMENTATA !");
}

//Visualizza Pacchetti Disponibili
public void View_Available_Packets() {
	System.out.println("FUNZIONALITA' NON ANCORA IMPLEMENTATA !");
}

private final BufferedReader consoleReader;
private final PrintWriter consoleWriter;

}
