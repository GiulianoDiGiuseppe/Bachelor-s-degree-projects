package Test;
import Entity.Broker;
import Entity.PacchettoFinanziario;

import Controller.Bloomfin_Management_System;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;

class CreazionePacchettoTest {

	//CREAZIONE BASE
		@Test
		void test1()throws SQLException {
			PacchettoFinanziario test_pack = new PacchettoFinanziario();
			String reg_cam = new String("22");
			test_pack.PacchettoFinanziarioTEST(2, 3, "test1", "prova 1" , 3,reg_cam);
			String[] id_tit= {"1","2","3"};
			int[] num_azioni= {4,5,6};
			Bloomfin_Management_System.Crea_PacchettoFinanziarioTEST(test_pack, reg_cam, id_tit, num_azioni);
		}

		//CREAZIONE CON CARATTERI SPECIALI IN UNA STRINGA E INSERISCO UNA STRINGA COME ID DEL BORKER
		@Test
		void test2() throws SQLException{
			PacchettoFinanziario test_pack = new PacchettoFinanziario();
			String reg_cam = new String("GDG");
			test_pack.PacchettoFinanziarioTEST(24, 53, "test1-$", "prova 1 per vedere se la lunghezza influisce" , 3 ,reg_cam);
			String[] id_tit= {"1","2","3"};
			int[] num_azioni= {4,5,6};
			Bloomfin_Management_System.Crea_PacchettoFinanziarioTEST(test_pack, reg_cam, id_tit, num_azioni);
		}
		
		
		//NON INSERISCO NESSUN ID BROKER 
		@Test
		void test3() throws SQLException{
			PacchettoFinanziario test_pack = new PacchettoFinanziario();
			String reg_cam = new String("");
			test_pack.PacchettoFinanziarioTEST(2, 3, "test1", "prova 1" , 3,reg_cam);
			String[] id_tit= {"1","2","3"};
			int[] num_azioni= {4,5,6};
			Bloomfin_Management_System.Crea_PacchettoFinanziarioTEST(test_pack, reg_cam, id_tit, num_azioni);
		}
		
		//INSERISCO ID_TITOLI NON PRESENTI
		@Test
		void test4()throws SQLException {
			PacchettoFinanziario test_pack = new PacchettoFinanziario();
			String reg_cam = new String("22");
			test_pack.PacchettoFinanziarioTEST(2, 3, "test1", "prova 1" , 3,reg_cam);
			String[] id_tit= {"100","2","3"};
			int[] num_azioni= {4,5,6};
			Bloomfin_Management_System.Crea_PacchettoFinanziarioTEST(test_pack, reg_cam, id_tit, num_azioni);
		}
		
		//INSERISCO NUM_AZIONI SOPRA IL LIMITE UNA VOLTA E DOPO INSERISCO NUM_AZIONI ESATTI
		@Test
		void test5()throws SQLException {
			PacchettoFinanziario test_pack = new PacchettoFinanziario();
			String reg_cam = new String("22");
			test_pack.PacchettoFinanziarioTEST(2, 3, "test1", "prova 1" , 3,reg_cam);
			String[] id_tit= {"1","2","2","3"};
			int[] num_azioni= {4,500,20,6};
			Bloomfin_Management_System.Crea_PacchettoFinanziarioTEST(test_pack, reg_cam, id_tit, num_azioni);
		}
		

		//test6
		@Test
		void test6()throws SQLException {
			Broker b = new Broker();
			b.inserimento_id();
			System.out.print("\n\n Fai tu un test di prova");
			Bloomfin_Management_System.Crea_PacchettoFinanziario(b);
		}
		
		
}
