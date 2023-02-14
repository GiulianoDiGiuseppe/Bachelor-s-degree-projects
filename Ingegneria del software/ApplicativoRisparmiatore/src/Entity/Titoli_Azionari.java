package Entity;

public class Titoli_Azionari {

//Costruttore
Titoli_Azionari() {}	
	
public String ID_Titolo;         //Identificativo del Titolo
public double Valore_Titolo;     //Valore del Titolo
public String Nome_Titolo;       //Nome del Titolo
public String ID_Borsa;          //Borsa in cui il Titolo è quotato


public Titoli_Azionari(String ID_Titolo , double Valore_Titolo , String Nome_Titolo ,String ID_Borsa){
this.ID_Titolo=ID_Titolo;	
this.Valore_Titolo=Valore_Titolo;
this.Nome_Titolo=Nome_Titolo;	
this.ID_Borsa = ID_Borsa;
}

public void insert_titolo(Titoli_Azionari a)
{
	this.ID_Titolo=a.ID_Titolo;
	this.Valore_Titolo=a.Valore_Titolo;
	this.Nome_Titolo=a.Nome_Titolo;
	this.ID_Borsa=a.ID_Borsa;
}


public String get_ID_Titolo() {
return ID_Titolo ;
}

public Double get_Valore_Titolo() {
return Valore_Titolo ;
}

public String get_Nome_Titolo() {
return Nome_Titolo ;
}

public String get_Borsa() {
return ID_Borsa ;
}



	
}
