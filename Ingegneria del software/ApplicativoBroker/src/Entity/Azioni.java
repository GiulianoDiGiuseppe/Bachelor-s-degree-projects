package Entity;


public class Azioni {

		public String ID_titolo;				
		public String ID_pacchetto;
		public int N_azioni;

		
		public Azioni(String a , String b , int c) {
			this.ID_pacchetto=a;
			this.ID_titolo=b;
			this.N_azioni=c;
		}		

		
		public String get_ID_titolo() {
			return ID_titolo;
		};
		
		public String get_ID_pacchetto() {
			return ID_pacchetto;
		};
		
		public int get_N_azioni() {
			return N_azioni;
		}
		
		

		
		public void Stampa_azioni(Azioni a) {
			System.out.print("\n ID_PACCHETTO = "+ a.ID_pacchetto +"\n ID_TITOLO = "+
		 a.ID_titolo+"\n NUMERO_AZIONI = "+a.N_azioni);
		}
		

}
