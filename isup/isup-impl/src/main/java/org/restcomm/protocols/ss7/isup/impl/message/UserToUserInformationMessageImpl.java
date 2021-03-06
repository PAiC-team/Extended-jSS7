package org.restcomm.protocols.ss7.isup.impl.message;

import java.util.Map;
import java.util.Set;

import org.restcomm.protocols.ss7.isup.ISUPParameterFactory;
import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.MessageTypeImpl;
import org.restcomm.protocols.ss7.isup.message.UserToUserInformationMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageName;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageType;
import org.restcomm.protocols.ss7.isup.message.parameter.UserToUserInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.accessTransport.AccessTransport;

/**
 * Start time:00:13:46 2009-09-07<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class UserToUserInformationMessageImpl extends ISUPMessageImpl implements UserToUserInformationMessage {
    public static final MessageType _MESSAGE_TYPE = new MessageTypeImpl(MessageName.UserToUserInformation);
    private static final int _MANDATORY_VAR_COUNT = 1;

    private static final boolean _OPTIONAL_POSSIBLE = true;

    static final int _INDEX_F_MessageType = 0;

    static final int _INDEX_V_User2UserInformation = 0;

    static final int _INDEX_O_AccessTransport = 0;
    static final int _INDEX_O_EndOfOptionalParameters = 1;

    /**
     *
     * @param source
     * @throws ParameterException
     */
    public UserToUserInformationMessageImpl(Set<Integer> mandatoryCodes, Set<Integer> mandatoryVariableCodes,
            Set<Integer> optionalCodes, Map<Integer, Integer> mandatoryCode2Index,
            Map<Integer, Integer> mandatoryVariableCode2Index, Map<Integer, Integer> optionalCode2Index) {
        super(mandatoryCodes, mandatoryVariableCodes, optionalCodes, mandatoryCode2Index, mandatoryVariableCode2Index,
                optionalCode2Index);
        super.f_Parameters.put(_INDEX_F_MessageType, this.getMessageType());
        super.o_Parameters.put(_INDEX_O_EndOfOptionalParameters, _END_OF_OPTIONAL_PARAMETERS);
    }

    protected void decodeMandatoryVariableBody(ISUPParameterFactory parameterFactory, byte[] parameterBody, int parameterIndex)
            throws ParameterException {
        switch (parameterIndex) {
            case _INDEX_V_User2UserInformation:
                UserToUserInformation subsequentNumber = parameterFactory.createUserToUserInformation();
                ((AbstractISUPParameter) subsequentNumber).decode(parameterBody);
                this.setUserToUserInformation(subsequentNumber);
                break;
            default:
                throw new ParameterException("Unrecognized parameter index for mandatory variable part: " + parameterIndex);
        }
    }

    protected void decodeOptionalBody(ISUPParameterFactory parameterFactory, byte[] parameterBody, byte parameterCode)
            throws ParameterException {
        switch (parameterCode & 0xFF) {
            case AccessTransport._PARAMETER_CODE:
                AccessTransport at = parameterFactory.createAccessTransport();
                ((AbstractISUPParameter) at).decode(parameterBody);
                this.setAccessTransport(at);
                break;
            default:
                throw new ParameterException("Unrecognized parameter code for optional part: " + parameterCode);
        }

    }

    public MessageType getMessageType() {
        return _MESSAGE_TYPE;
    }

    protected int getNumberOfMandatoryVariableLengthParameters() {
        return _MANDATORY_VAR_COUNT;
    }

    public boolean hasAllMandatoryParameters() {
        return super.v_Parameters.get(_INDEX_V_User2UserInformation) != null;
    }

    protected boolean optionalPartIsPossible() {
        return _OPTIONAL_POSSIBLE;
    }

    @Override
    public void setAccessTransport(AccessTransport mci) {
        super.o_Parameters.put(_INDEX_O_AccessTransport, mci);
    }

    @Override
    public AccessTransport getAccessTransport() {
        return (AccessTransport) super.o_Parameters.get(_INDEX_O_AccessTransport);
    }

    @Override
    public void setUserToUserInformation(UserToUserInformation mci) {
        super.o_Parameters.put(_INDEX_V_User2UserInformation, mci);
    }

    @Override
    public UserToUserInformation getUserToUserInformation() {
        return (UserToUserInformation) super.o_Parameters.get(_INDEX_V_User2UserInformation);
    }
}