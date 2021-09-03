package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.CorrelationID;

/**
 * Start time:12:48:19 2009-04-05<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 *
 */
public class CorrelationIDImpl extends GenericDigitsImpl implements CorrelationID {

    public CorrelationIDImpl() {
        super();

    }

    public CorrelationIDImpl(byte[] b) throws ParameterException {
        super(b);

    }

    public CorrelationIDImpl(int encodignScheme, int typeOfDigits, byte[] digits) {
        super(encodignScheme, typeOfDigits, digits);

    }

    // FIXME: Q.1218 -- weird document.... Oleg is this correct? or should it be
    // mix of GenericNumber and Generic digits?

    // TODO: CorrelationID should extends GenericNumber (not GenericDigits): from Q.1218:
    // Digits ::= OCTET STRING (SIZE (minDigitsLength .. maxDigitsLength))
    // -- Indicates the address signalling digits. Refer to the ETS 300 356-1 [8] Generic Number and
    // -- Generic Digits parameters for encoding. The coding of the subfields "NumberQualifier" in
    // -- Generic Number and "Type Of Digits" in Generic Digits are irrelevant to the INAP, the ASN.1
    // -- tags are sufficient to identify the parameter. The ISUP format does not allow to exclude these
    // -- subfields, therefor the value is network operator specific.
    // -- The following parameter should use Generic Number :
    // -- CorrelationID for AssistRequestInstructions,
    // -- AdditionalCallingPartyNumber for InitialDP,
    // -- AssistingSSPIPRoutingAddress for EstablishTemporaryConnection,
    // -- calledAddressValue for all occurrences,
    // -- callingAddressValue for all occurrences
    // -- The following parameters should use Generic Digits :
    // -- all other CorrelationID occurrences,
    // -- number VariablePart,
    // -- digitsResponse ReceivedInformationArg

    public int getCode() {

        return CorrelationID._PARAMETER_CODE;
    }
}
