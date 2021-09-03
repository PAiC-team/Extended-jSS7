package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.GenericDigits;
import org.restcomm.protocols.ss7.isup.message.parameter.GenericNumber;

/**
*
<code>
ISUP GenericNumber & GenericDigits wrapper

Digits {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE
bound.&minDigitsLength..bound.&maxDigitsLength))
-- Indicates the address signalling digits.
-- Refer to the ITU-T Recommendation Q.763 Generic Number & Generic Digits parameters for encoding.
-- The coding of the subfields 'NumberQualifier' in Generic Number and 'TypeOfDigits' in
-- Generic Digits are irrelevant to the INAP;
-- the ASN.1 tags are sufficient to identify the parameter.
-- The ISUP format does not allow to exclude these subfields,
-- therefore the value is network operator specific.
-- The following parameters should use Generic Number:
-- CorrelationID for AssistRequestInstructions,
-- AssistingSSPIPRoutingAddress for EstablishTemporaryConnection,
-- calledAddressValue for all occurrences,callingAddressValue for all occurrences.
-- The following parameters should use Generic Digits: prefix, all
-- other CorrelationID occurrences, dialledNumber filtering criteria,
-- callingLineID filtering criteria, lineID for ResourceID
-- type, digitResponse for ReceivedInformationArg,
-- iNServiceControlLow / iNServiceControlHighfor MidCallInfoType,
-- iNServiceControlCode for MidCallInfo.
</code>

*
* @author sergey vetyutnev
*
*/
public interface Digits extends Serializable {

    byte[] getData();

    GenericDigits getGenericDigits() throws INAPException;

    GenericNumber getGenericNumber() throws INAPException;

    void setData(byte[] data);

    void setGenericDigits(GenericDigits genericDigits) throws INAPException;

    void setGenericNumber(GenericNumber genericNumber) throws INAPException;

    boolean getIsGenericDigits();

    boolean getIsGenericNumber();

    /**
     * Set that Digits carries GenericDigits element Attention: this value must be set after primitive decoding !!!!
     */
    void setIsGenericDigits();

    /**
     * Set that Digits carries GenericNumber element Attention: this value must be set after primitive decoding !!!!
     */
    void setIsGenericNumber();

}
