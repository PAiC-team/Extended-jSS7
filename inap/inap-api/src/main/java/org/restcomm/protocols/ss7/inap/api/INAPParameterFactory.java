package org.restcomm.protocols.ss7.inap.api;

import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.inap.api.isup.HighLayerCompatibilityInap;
import org.restcomm.protocols.ss7.inap.api.isup.RedirectionInformationInap;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.inap.api.primitives.LegType;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfoDpAssignment;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfoMessageType;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyCategory;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectionInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.UserTeleserviceInformation;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface INAPParameterFactory {

    CallingPartysCategoryInap createCallingPartysCategoryInap(byte[] data);

    CallingPartysCategoryInap createCallingPartysCategoryInap(CallingPartyCategory callingPartyCategory)
            throws INAPException;

    HighLayerCompatibilityInap createHighLayerCompatibilityInap(byte[] data);

    HighLayerCompatibilityInap createHighLayerCompatibilityInap(UserTeleserviceInformation highLayerCompatibility)
            throws INAPException;

    RedirectionInformationInap createRedirectionInformationInap(byte[] data);

    RedirectionInformationInap createRedirectionInformationInap(RedirectionInformation redirectionInformation)
            throws INAPException;

    LegID createLegID(boolean isSendingSideID, LegType legID);

    MiscCallInfo createMiscCallInfo(MiscCallInfoMessageType messageType, MiscCallInfoDpAssignment dpAssignment);

}
