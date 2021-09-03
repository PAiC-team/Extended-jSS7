
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPTypeOrganization;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPTypeOrganizationValue;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class PDPTypeOrganizationImpl extends OctetStringLength1Base implements PDPTypeOrganization {

    public PDPTypeOrganizationImpl() {
        super("PDPTypeOrganization");
    }

    public PDPTypeOrganizationImpl(int data) {
        super("PDPTypeOrganization", data);
    }

    public PDPTypeOrganizationImpl(PDPTypeOrganizationValue value) {
        super("PDPTypeOrganization", value != null ? (value.getCode() | 0xF0) : 0);
    }

    @Override
    public PDPTypeOrganizationValue getPDPTypeOrganizationValue() {
        return PDPTypeOrganizationValue.getInstance(data & 0x0F);
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.getPDPTypeOrganizationValue() != null) {
            sb.append("PDPTypeOrganizationValue=");
            sb.append(this.getPDPTypeOrganizationValue());
        }

        sb.append("]");

        return sb.toString();
    }
}
