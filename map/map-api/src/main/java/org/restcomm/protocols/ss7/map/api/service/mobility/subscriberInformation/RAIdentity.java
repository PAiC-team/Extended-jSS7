package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import org.restcomm.protocols.ss7.map.api.MAPException;

import java.io.Serializable;

/**
 <code>
 RAIdentity ::= OCTET STRING (SIZE (6))
 -- Routing Area Identity is coded in accordance with 3GPP TS 29.060 [105].
 -- It shall contain the value part defined in 3GPP TS 29.060 only. I.e. the 3GPP TS 29.060
 -- type identifier octet shall not be included.
 </code>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface RAIdentity extends Serializable {

    byte[] getData();

    int getMCC() throws MAPException;

    int getMNC() throws MAPException;

    int getLAC() throws MAPException;

    int getRAC() throws MAPException;

}
