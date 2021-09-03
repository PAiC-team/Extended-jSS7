
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class TCUniMessageImpl implements TCUniMessage {

    private DialogPortion dp;
    private Component[] component;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage#getComponent()
     */
    public Component[] getComponent() {
        return component;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage#getDialogPortion()
     */
    public DialogPortion getDialogPortion() {
        return dp;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage#setComponent(org
     * .mobicents.protocols.ss7.tcap.asn.comp.Component[])
     */
    public void setComponent(Component[] component) {
        this.component = component;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage#setDialogPortion
     * (org.restcomm.protocols.ss7.tcap.asn.DialogPortion)
     */
    public void setDialogPortion(DialogPortion dp) {
        this.dp = dp;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();

            while (true) {
                if (localAis.available() == 0)
                    return;

                int tag = localAis.readTag();
                if (localAis.isTagPrimitive() || localAis.getTagClass() != Tag.CLASS_APPLICATION)
                    throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                            "Error decoding TC-Uni: DialogPortion and Component portion must be constructive and has tag class CLASS_APPLICATION");

                switch (tag) {
                    case DialogPortion._TAG:
                        this.dp = TcapFactory.createDialogPortion(localAis);
                        break;

                    case Component._COMPONENT_TAG:
                        AsnInputStream compAis = localAis.readSequenceStream();
                        List<Component> cps = new ArrayList<Component>();
                        // its iterator :)
                        while (compAis.available() > 0) {
                            Component c = TcapFactory.createComponent(compAis);
                            if (c == null) {
                                break;
                            }
                            cps.add(c);
                        }

                        this.component = new Component[cps.size()];
                        this.component = cps.toArray(this.component);
                        break;

                    default:
                        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                                "Error decoding TC-Uni: DialogPortion and Componebt parsing: bad tag - " + tag);
                }
            }
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "IOException while decoding TC-Uni: "
                    + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "AsnException while decoding TC-Uni: "
                    + e.getMessage(), e);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (this.component == null || this.component.length == 0)
            throw new EncodeException("Error encoding TC-Uni: Component portion is mandatory but not defined");

        try {
            asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, _TAG);
            int pos = asnOutputStream.StartContentDefiniteLength();

            if (this.dp != null)
                this.dp.encode(asnOutputStream);

            asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, Component._COMPONENT_TAG);
            int pos2 = asnOutputStream.StartContentDefiniteLength();
            for (Component c : this.component) {
                c.encode(asnOutputStream);
            }
            asnOutputStream.FinalizeContent(pos2);

            asnOutputStream.FinalizeContent(pos);

        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding TC-Uni: " + e.getMessage(), e);
        }
    }

}
