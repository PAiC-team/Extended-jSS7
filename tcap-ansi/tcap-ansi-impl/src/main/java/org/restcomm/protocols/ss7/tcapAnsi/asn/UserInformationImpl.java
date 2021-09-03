
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.EncodeException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ParseException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformation;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformationElement;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.PAbortCause;

/**
 *
 * @author baranowb
 * @author amit bhayani
 *
 */
public class UserInformationImpl implements UserInformation {

    private UserInformationElement[] userInformationElements;

    @Override
    public UserInformationElement[] getUserInformationElements() {
        return userInformationElements;
    }

    @Override
    public void setUserInformationElements(UserInformationElement[] val) {
        userInformationElements = val;
    }

    @Override
    public void decode(AsnInputStream ais) throws ParseException {

        ArrayList<UserInformationElement> lst = new ArrayList<UserInformationElement>();
        try {
            AsnInputStream localAis = ais.readSequenceStream();

            while (true) {
                if (localAis.available() == 0)
                    break;

                int tag = localAis.readTag();
                if (tag != Tag.EXTERNAL || localAis.getTagClass() != Tag.CLASS_UNIVERSAL)
                    throw new AsnException("Error decoding UserInformation.sequence: wrong tag or tag class: tag=" + tag + ", tagClass="
                            + localAis.getTagClass());
                UserInformationElement elem = new UserInformationElementImpl();
                elem.decode(localAis);
                lst.add(elem);
            }
        } catch (IOException e) {
            throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion, "IOException while decoding UserInformation: " + e.getMessage(), e);

        } catch (AsnException e) {
            throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion, "AsnException while decoding UserInformation: " + e.getMessage(), e);
        }

        userInformationElements = new UserInformationElement[lst.size()];
        lst.toArray(userInformationElements);
    }

    @Override
    public void encode(AsnOutputStream aos) throws EncodeException {
        try {
            aos.writeTag(Tag.CLASS_PRIVATE, false, UserInformation._TAG_USER_INFORMATION);
            int pos = aos.StartContentDefiniteLength();

            if (userInformationElements != null) {
                for (UserInformationElement elem : userInformationElements) {
                    if (elem != null) {
                        elem.encode(aos);
                    }
                }
            }

            aos.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new EncodeException("AsnException when encoding UserInformation: " + e.getMessage(), e);
        }
    }
}
