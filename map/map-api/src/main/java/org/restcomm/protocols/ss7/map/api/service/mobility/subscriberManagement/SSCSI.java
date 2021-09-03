
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SS-CSI ::= SEQUENCE { ss-CamelData SS-CamelData, extensionContainer ExtensionContainer OPTIONAL, ..., notificationToCSE [0]
 * NULL OPTIONAL, csi-Active [1] NULL OPTIONAL -- notificationToCSE and csi-Active shall not be present when SS-CSI is sent to
 * VLR. -- They may only be included in ATSI/ATM ack/NSDC message. }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SSCSI extends Serializable {

    SSCamelData getSsCamelData();

    MAPExtensionContainer getExtensionContainer();

    boolean getNotificationToCSE();

    boolean getCsiActive();

}
