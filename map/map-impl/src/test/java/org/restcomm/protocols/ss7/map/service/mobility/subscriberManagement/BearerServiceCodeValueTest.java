
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.assertEquals;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.testng.annotations.Test;

/**
 * @author Amit Bhayani
 *
 */
public class BearerServiceCodeValueTest {

    /**
	 *
	 */
    public BearerServiceCodeValueTest() {
        // TODO Auto-generated constructor stub
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void test() throws Exception {
        int code = BearerServiceCodeValue.padAccessCA_9600bps.getCode();
//        int code = BearerServiceCodeValue.padAccessCA_9600bps.getBearerServiceCode();

        BearerServiceCodeValue valueFromCode = BearerServiceCodeValue.getInstance(code);

        assertEquals(valueFromCode, BearerServiceCodeValue.padAccessCA_9600bps);
    }

}
