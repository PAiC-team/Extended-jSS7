
package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V2-3 absentSubscriber ERROR ::= { PARAMETER AbsentSubscriberParam -- optional -- AbsentSubscriberParam must not be used
 * in version <3 CODE local:27 }
 *
 * AbsentSubscriberParam ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ..., absentSubscriberReason [0]
 * AbsentSubscriberReason OPTIONAL}
 *
 * MAP V1 AbsentSubscriber ::= ERROR PARAMETER mwd-Set BOOLEAN -- optional -- mwd-Set must be absent in version greater 1
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageAbsentSubscriber extends MAPErrorMessage {

    // following is for MAP V3 only
    MAPExtensionContainer getExtensionContainer();

    AbsentSubscriberReason getAbsentSubscriberReason();

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

    void setAbsentSubscriberReason(AbsentSubscriberReason absentSubscriberReason);

    // following is for MAP V1 only
    Boolean getMwdSet();

    void setMwdSet(Boolean val);

}
