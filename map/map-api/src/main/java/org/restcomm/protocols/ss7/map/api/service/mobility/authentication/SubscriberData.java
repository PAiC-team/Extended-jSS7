
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.Category;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SubscriberStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ZoneCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSInfo;

/**
 *
 SubscriberData ::= SEQUENCE { msisdn [1] ISDN-AddressString OPTIONAL, category [2] Category OPTIONAL, subscriberStatus [3]
 * SubscriberStatus OPTIONAL, bearerServiceList [4] BearerServiceList OPTIONAL, teleserviceList [6] TeleserviceList OPTIONAL,
 * provisionedSS [7] SS-InfoList OPTIONAL, odb-Data [8] ODB-Data OPTIONAL, -- odb-Data must be absent in version 1
 * roamingRestrictionDueToUnsupportedFeature [9] NULL OPTIONAL, -- roamingRestrictionDueToUnsupportedFeature must be absent --
 * in version 1 regionalSubscriptionData [10] ZoneCodeList OPTIONAL -- regionalSubscriptionData must be absent in version 1 }
 *
 * BearerServiceList ::= SEQUENCE SIZE (1..50) OF BearerServiceCode
 *
 * TeleserviceList ::= SEQUENCE SIZE (1..20) OF TeleserviceCode
 *
 * SS-InfoList ::= SEQUENCE SIZE (1..30) OF SS-Info
 *
 * ZoneCodeList ::= SEQUENCE SIZE (1..10) OF ZoneCode
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SubscriberData extends Serializable {

    ISDNAddressString getMsisdn();

    Category getCategory();

    SubscriberStatus getSubscriberStatus();

    ArrayList<BearerServiceCode> getBearerServiceList();

    ArrayList<TeleserviceCode> getTeleserviceList();

    ArrayList<SSInfo> getProvisionedSS();

    ODBData getOdbData();

    boolean getRoamingRestrictionDueToUnsupportedFeature();

    ArrayList<ZoneCode> getRegionalSubscriptionData();

}
