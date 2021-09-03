package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.restcomm.protocols.ss7.map.api.smstpdu.NumberingPlanIdentification;
import org.restcomm.protocols.ss7.map.api.smstpdu.TypeOfNumber;
import org.restcomm.protocols.ss7.map.smstpdu.AddressFieldImpl;
import org.restcomm.protocols.ss7.map.smstpdu.CommandDataImpl;
import org.restcomm.protocols.ss7.map.smstpdu.CommandTypeImpl;
import org.restcomm.protocols.ss7.map.smstpdu.ProtocolIdentifierImpl;
import org.restcomm.protocols.ss7.map.smstpdu.SmsCommandTpduImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SmsCommandTpduTest {

    public byte[] getData1() {
        return new byte[] { 2, 11, 33, 44, 12, 13, -111, 17, 34, 51, 68, 85, 102, -9, 5, 115, 101, 116, 32, 65 };
    }

    @Test(groups = { "functional.decode", "smstpdu" })
    public void testDecode() throws Exception {

        SmsCommandTpduImpl impl = new SmsCommandTpduImpl(this.getData1());
        assertFalse(impl.getUserDataHeaderIndicator());
        assertFalse(impl.getStatusReportRequest());
        assertEquals(impl.getMessageReference(), 11);
        assertEquals(impl.getProtocolIdentifier().getCode(), 33);
        assertEquals(impl.getCommandType().getCode(), 44);
        assertEquals(impl.getMessageNumber(), 12);
        assertEquals(impl.getDestinationAddress().getTypeOfNumber(), TypeOfNumber.InternationalNumber);
        assertEquals(impl.getDestinationAddress().getNumberingPlanIdentification(),
                NumberingPlanIdentification.ISDNTelephoneNumberingPlan);
        assertTrue(impl.getDestinationAddress().getAddressValue().equals("1122334455667"));
        impl.getCommandData().decode();
        assertEquals(impl.getCommandDataLength(), 5);
        assertTrue(impl.getCommandData().getDecodedMessage().equals("set A"));
    }

    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        AddressFieldImpl destinationAddress = new AddressFieldImpl(TypeOfNumber.InternationalNumber,
                NumberingPlanIdentification.ISDNTelephoneNumberingPlan, "1122334455667");
        ProtocolIdentifierImpl pi = new ProtocolIdentifierImpl(33);
        CommandTypeImpl commandType = new CommandTypeImpl(44);
        CommandDataImpl commandData = new CommandDataImpl("set A");
        SmsCommandTpduImpl impl = new SmsCommandTpduImpl(false, 11, pi, commandType, 12, destinationAddress, commandData);
        byte[] enc = impl.encodeData();
        assertTrue(Arrays.equals(enc, this.getData1()));
    }
}
