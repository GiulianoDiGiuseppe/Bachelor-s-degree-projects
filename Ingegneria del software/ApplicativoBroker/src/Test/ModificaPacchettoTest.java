/**
 * 
 */
package Test;



import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controller.Bloomfin_Management_System;


/**
 * @author sossi
 *
 */
class ModificaPacchettoTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Entity.Broker#Modifica_PacchettoFinanziario()}.
	 */
	
	
	//TEST BASE
	@Test
	void test_1() throws SQLException{
		String n_reg1 = "1";
		String risposta_01 = "2";
		String id_pacchetto = "g";
		int[] numero_azioni = {25, 13, 45, 32, 24};
		String[] id_titoli = {"25", "30", "24"};
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_1";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg1, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
		
	}
	

	
	//TEST CON ID DEL BROKER CHE NON HA ANCORA CREATO NESSUN PACCHETTO
	@Test
	void test_2() throws SQLException{
		String n_reg2 = "gnartrahb";
		String risposta_01 = "2";
		String id_pacchetto = "g";
		int[] numero_azioni = {25, 13, 45, 32, 24};
		String[] id_titoli = {"25", "30", "24"};
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_2";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg2, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
		
	}
	
	
	//INSERISCO ID PACCHETTO NON PRESENTE NEI PACCHETTI CREATI
	@Test
	void test_3() throws SQLException{
		String n_reg2 = "1";
		String risposta_01 = "2";
		String id_pacchetto = "34h6hew8";
		int[] numero_azioni = {25, 13, 45, 32, 24};
		String[] id_titoli = {"25", "30", "24"};
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_3";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg2, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
	}
	

	
	//INSERISCO NUMERO AZIONI NEGATIVO
	@Test
	void test_4() throws SQLException{
		String n_reg2 = "1";
		String risposta_01 = "2";
		String id_pacchetto = "g";
		int[] numero_azioni = {-25, 13,-45, 32, 24, 42, 78};
		String[] id_titoli = {"50", "30", "24"};		//si dovrebbe inserire un valore String, ma la funzione Modifica_PacchettoFinanziarioTEST non lo permette. Bisogna fare questo test da console
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_4";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg2, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
	}
	
	
	
	//INSERISCO STRINGA IN NUMERO AZIONI 
	@Test
	void test_5() throws SQLException{
		String n_reg2 = "1";
		String risposta_01 = "2";
		String id_pacchetto = "g";
		int[] numero_azioni = {41, 13, 45, 32, 24};
		String[] id_titoli = {"25", "30", "24"};
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_5";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg2, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
	}
	
	
	//INSERISCO UN NUMERO AZIONI NON VALIDO, POI UN VALORE VALIDO
	void test_6() throws SQLException{
		String n_reg2 = "1";
		String risposta_01 = "2";
		String id_pacchetto = "g";
		int[] numero_azioni = {25, 500, 13, 45, 32, 24};
		String[] id_titoli = {"25", "30", "24"};
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_3";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg2, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
	}
	
	
	
	
	
	//INSERISCO STRINGA ID_TITOLI NON PRESENTE NEL DATABASE
	@Test
	void test_7() throws SQLException{
		String n_reg2 = "1";
		String risposta_01 = "2";
		String id_pacchetto = "g";
		int[] numero_azioni = {25, 13, 45, 32, 24};
		String[] id_titoli = {"50", "300", "254"};
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_4";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg2, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
	}
	
	
	//INSERISCO STRINGA NULLA COME N_REG_CAM_COMMERCIO
	void test_8() throws SQLException{
		String n_reg1 = "";
		String risposta_01 = "2";
		String id_pacchetto = "g";
		int[] numero_azioni = {25, 13, 45, 32, 24};
		String[] id_titoli = {"25", "30", "24"};
		String risposta_02 = "1";
		int titoli_aggiunti = 3;
		double fattore_rischio = 5.4;
		double stima_rendimento = 8.9;
		String nome = "nome_pack";
		String descrizione = "test_1";
		Bloomfin_Management_System.Modifica_PacchettoFinanziarioTEST(n_reg1, risposta_01, id_pacchetto, numero_azioni, id_titoli, risposta_02, titoli_aggiunti, fattore_rischio, stima_rendimento, nome, descrizione);
		
	}
	
}
