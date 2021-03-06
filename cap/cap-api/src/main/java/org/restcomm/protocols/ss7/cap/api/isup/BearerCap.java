
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.UserServiceInformation;

/**
 *
 bearerCapability: This parameter indicates the type of the bearer capability connection or the transmission medium requirements to the user.
 It is a network option to select which of the two parameters to be used:
 - bearerCap: This parameter contains the value of the ISUP User Service Information parameter.
 The parameter "bearerCapability" shall be included in the "InitialDP" operation only
 in the case the ISUP User Service Information parameter is available at the gsmSSF.
 If User Service Information and User Service Information Prime are available at the gsmSSF, then the "bearerCap"
 shall contain the value of the User Service Information Prime parameter.

 -- Indicates the type of bearer capability connection to the user. For bearerCap, the ISUP User
 -- Service Information, ETSI EN 300 356-1 [23]
 -- encoding shall be used.

 * User service information prime (1st priority) or User service information (2nd priority) (Note 3)
 *
 * NOTE 3: If User service information prime and User service information are present, then one of the following two mapping
 * rules shall be applied. The principles for the choice of mapping rule are specified in 3GPP TS 23.078 [7]. - One of User
 * service information prime or User service information is mapped to Bearer Capability. - User service information prime is
 * mapped to BearerCapability and User service information is mapped to Bearer Capability2.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BearerCap extends Serializable {

    byte[] getData();

    UserServiceInformation getUserServiceInformation() throws CAPException;

}