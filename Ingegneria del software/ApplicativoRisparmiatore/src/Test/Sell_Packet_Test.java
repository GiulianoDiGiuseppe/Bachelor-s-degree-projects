package Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Controller.IBloomfin_Financial_AgencyEIS;
import Controller.Impl.Financial_Packets_Manager_Impl;

class Sell_Packet_Test {

	@Test
	void test1() {
		
		Financial_Packets_Manager_Impl f_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_FPMI_Instance();
		f_manager.View_My_Packets("VRDGS90P13F213V");
		
	}

	@Test
	void test2() {
		
		Financial_Packets_Manager_Impl f_manager = IBloomfin_Financial_AgencyEIS.getInstance().get_FPMI_Instance();
		f_manager.View_My_Packets("VRDGS90P13F213VRF");
		
	}
	
	
	
}
