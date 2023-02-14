package Controller;

public interface Financial_Packets_Manager {

//Restituisce l'elenco dei pacchetti acquistati dal risparmiatore
public void View_My_Packets(String Codice_Fiscale);

//Restituisce l'elenco dei pacchetti acquistati in una determinata data o con un determinato ID
public void Filtra(String Codice_Fiscale);

//Restituisce il valore di acquisto del pacchetto selezionato se il numero di pacchetti inserito è consistente (cioè in linea con quelli posseduti)
public double Compare_Num_Of_Packets(int N_Pacchetti, String Codice_Fiscale, String ID_Pacchetto);

//Mostra le informazioni aggiuntive del pacchetto selezionato 
public void Get_Information(String Codice_Fiscale);

//Vende il pacchetto finanziario selezionato dal risparmiatore
public boolean Sell_Packet(String Codice_Fiscale, String ID_Pacchetto);

//Calcola il valore attuale del pacchetto 
public double Calcola_val_Pacchetto(String ID_Pacchetto);

//Calcola la variazione percentuale del valore da quando è stato acquistato all'istante in cui si dispone la vendita
public double Variazione_Percentuale_Valore(double Valore_Pacchetto, double Valore_di_Acquisto);

//Esegue il pagamento e completa il caso d'uso VENDI PACCHETTO
public boolean Esegui_Transazione(String Codice_Fiscale,String ID_Pacchetto, double Valore_Pacchetto, int Num_Pacchetti);
}
