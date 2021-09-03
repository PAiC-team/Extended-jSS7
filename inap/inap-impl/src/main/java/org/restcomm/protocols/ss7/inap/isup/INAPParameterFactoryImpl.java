package org.restcomm.protocols.ss7.inap.isup;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.inap.api.INAPParameterFactory;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.inap.api.isup.HighLayerCompatibilityInap;
import org.restcomm.protocols.ss7.inap.api.isup.RedirectionInformationInap;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.inap.api.primitives.LegType;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfoDpAssignment;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfoMessageType;
import org.restcomm.protocols.ss7.inap.primitives.LegIDImpl;
import org.restcomm.protocols.ss7.inap.primitives.MiscCallInfoImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyCategory;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectionInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.UserTeleserviceInformation;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class INAPParameterFactoryImpl implements INAPParameterFactory {

    @Override
    public CallingPartysCategoryInap createCallingPartysCategoryInap(byte[] data) {
        return new CallingPartysCategoryInapImpl(data);
    }

    @Override
    public CallingPartysCategoryInap createCallingPartysCategoryInap(CallingPartyCategory callingPartyCategory)
            throws INAPException {
        return new CallingPartysCategoryInapImpl(callingPartyCategory);
    }

    @Override
    public HighLayerCompatibilityInap createHighLayerCompatibilityInap(byte[] data) {
        return new HighLayerCompatibilityInapImpl(data);
    }

    @Override
    public HighLayerCompatibilityInap createHighLayerCompatibilityInap(UserTeleserviceInformation highLayerCompatibility)
            throws INAPException {
        return new HighLayerCompatibilityInapImpl(highLayerCompatibility);
    }

    @Override
    public RedirectionInformationInap createRedirectionInformationInap(byte[] data) {
        return new RedirectionInformationInapImpl(data);
    }

    @Override
    public RedirectionInformationInap createRedirectionInformationInap(RedirectionInformation redirectionInformation)
            throws INAPException {
        return new RedirectionInformationInapImpl(redirectionInformation);
    }

    @Override
    public LegID createLegID(boolean isSendingSideID, LegType legID) {
        return new LegIDImpl(isSendingSideID, legID);
    }

    @Override
    public MiscCallInfo createMiscCallInfo(MiscCallInfoMessageType messageType, MiscCallInfoDpAssignment dpAssignment) {
        return new MiscCallInfoImpl(messageType, dpAssignment);
    }
}
