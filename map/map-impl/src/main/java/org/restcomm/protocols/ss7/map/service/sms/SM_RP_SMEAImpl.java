
package org.restcomm.protocols.ss7.map.service.sms;

import java.io.ByteArrayInputStream;

import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_SMEA;
import org.restcomm.protocols.ss7.map.api.smstpdu.AddressField;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;
import org.restcomm.protocols.ss7.map.smstpdu.AddressFieldImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SM_RP_SMEAImpl extends OctetStringBase implements SM_RP_SMEA {

    public SM_RP_SMEAImpl() {
        super(1, 12, "SM_RP_SMEA");
    }

    public SM_RP_SMEAImpl(byte[] data) {
        super(1, 12, "SM_RP_SMEA", data);
    }

    public SM_RP_SMEAImpl(AddressField addressField) throws MAPException {
        super(1, 12, "SM_RP_SMEA");

        if (addressField == null) {
            throw new MAPException("addressField field must not be equal null");
        }

        AsnOutputStream res = new AsnOutputStream();
        addressField.encodeData(res);
        this.data = res.toByteArray();
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public AddressField getAddressField() throws MAPException {

        ByteArrayInputStream stm = new ByteArrayInputStream(data);
        AddressField res = AddressFieldImpl.createMessage(stm);
        return res;
    }
}
