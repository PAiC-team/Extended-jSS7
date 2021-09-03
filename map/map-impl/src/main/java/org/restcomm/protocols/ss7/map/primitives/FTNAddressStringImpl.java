package org.restcomm.protocols.ss7.map.primitives;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.FTNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class FTNAddressStringImpl extends AddressStringImpl implements FTNAddressString {

    public FTNAddressStringImpl() {
    }

    public FTNAddressStringImpl(AddressNature addressNature, NumberingPlan numberingPlan, String address) {
        super(addressNature, numberingPlan, address);
    }

    @Override
    protected void _testLengthDecode(int length) throws MAPParsingComponentException {
        if (length > 15)
            throw new MAPParsingComponentException("Error when decoding FTNAddressString: mesage length must not exceed 15",
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    protected void _testLengthEncode() throws MAPException {

        if (this.address == null && this.address.length() > 28)
            throw new MAPException("Error when encoding FTNAddressString: address length must not exceed 28 digits");
    }

    @Override
    public String toString() {
        return "FTNAddressString[AddressNature=" + this.addressNature.toString() + ", NumberingPlan="
                + this.numberingPlan.toString() + ", Address=" + this.address + "]";
    }

}
