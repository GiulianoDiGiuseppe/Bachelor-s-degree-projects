package Entity;




public class TitoloFinanziario {

    String ID_titolo;                    //Identificativo titolo
    double Valore_titolo;            //Valore titolo
    String Nome_titolo;                //Nome del titolo
    String Borsa;                        //Borsa in cui è quotato

//Costruttore
    public  TitoloFinanziario(String a , double b , String c ,String d){
    this.ID_titolo=a;
    this.Valore_titolo=b;
    this.Nome_titolo=c;
    this.Borsa =d;
    }


//Inserimento valori attraverso oggetto titolo finanziario

//Costruttore di copia
    public TitoloFinanziario(TitoloFinanziario a)
    {
        this.ID_titolo=a.ID_titolo;
        this.Valore_titolo=a.Valore_titolo;
        this.Nome_titolo=a.Nome_titolo;
        this.Borsa=a.Borsa;
    }

//costruttore default
    public TitoloFinanziario()
    {
        this.ID_titolo=null;
        this.Valore_titolo=0;
        this.Nome_titolo=null;
        this.Borsa=null;
    }

//Inserimento valori attraverso oggetto titolo finanziario
    public void insert_titolo(TitoloFinanziario a)
    {
        this.ID_titolo=a.ID_titolo;
        this.Valore_titolo=a.Valore_titolo;
        this.Nome_titolo=a.Nome_titolo;
        this.Borsa=a.Borsa;
    }

// Funzioni GET
    public String get_ID_Titolo() {
        return ID_titolo ;
    }

    public Double get_Valore_Titolo() {
        return Valore_titolo ;
    }

    public String get_Nome_Titolo() {
        return Nome_titolo ;
    }

    public String get_Borsa() {
        return Borsa ;
    }

    
    
    
    
//Funzione stampa
    public void Stampa_titolo() {
        System.out.print("\tID_TITOLO = "+ this.ID_titolo +"\tVALORE_TITOLO = "+ this.Valore_titolo +"\tNOME_TITLO= "+this.Nome_titolo+"\tBORSA = "+this.Borsa);
    }

}