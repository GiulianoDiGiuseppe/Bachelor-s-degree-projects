package Main;
import Boundary.Application_Console_Boundary;
import Boundary.Application_Console_Boundary.TerminationState;

public class Main {
	public static void main(String[] args) throws Exception {
	
        Application_Console_Boundary applicationConsoleBoundary = new Application_Console_Boundary();
        TerminationState exitStatus = applicationConsoleBoundary.runApplication();
       
        if (exitStatus == TerminationState.CORRECT_TERMINATION) {
            System.exit(0);
        } else {
            System.exit(-1);
        }
	}
}
