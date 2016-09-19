import org.junit.Test;

import com.cpower.base.CPower;

public class CPowerTest {

	@Test
	public void cPowerTest() {
		try {
			CPower cPower = CPower.getInstance();
			
			cPower.initialize(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
