package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.restcomm.protocols.ss7.map.smstpdu.ParameterIndicatorImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ParameterIndicatorTest {
    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        // ParameterIndicatorImpl ind = new ParameterIndicatorImpl(boolean TP_UDLPresence, boolean getTP_DCSPresence, boolean
        // getTP_PIDPresence);
        ParameterIndicatorImpl ind = new ParameterIndicatorImpl(true, false, false);
        assertTrue(ind.getTP_UDLPresence());
        assertFalse(ind.getTP_DCSPresence());
        assertFalse(ind.getTP_PIDPresence());

        ind = new ParameterIndicatorImpl(false, true, false);
        assertFalse(ind.getTP_UDLPresence());
        assertTrue(ind.getTP_DCSPresence());
        assertFalse(ind.getTP_PIDPresence());

        ind = new ParameterIndicatorImpl(false, false, true);
        assertFalse(ind.getTP_UDLPresence());
        assertFalse(ind.getTP_DCSPresence());
        assertTrue(ind.getTP_PIDPresence());
    }
}
