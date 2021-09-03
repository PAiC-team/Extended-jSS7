
package org.restcomm.protocols.ss7.tools.simulator.level2;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SccpConfigurationData_OldFormat extends SccpConfigurationData {

    protected static final String EXTRA_LOCAL_ADDRESS_DIGITS = "extraLocalAddressDigits";

    // private String extraLocalAddressDigits = "";

    protected static final XMLFormat<SccpConfigurationData_OldFormat> XML = new XMLFormat<SccpConfigurationData_OldFormat>(
            SccpConfigurationData_OldFormat.class) {

        public void write(SccpConfigurationData_OldFormat sccp, OutputElement xml) throws XMLStreamException {
        }

        public void read(InputElement xml, SccpConfigurationData_OldFormat sccp) throws XMLStreamException {
            sccp.setRouteOnGtMode(xml.getAttribute(REMOTE_ON_GT_MODE).toBoolean());
            sccp.setRemoteSpc(xml.getAttribute(REMOTE_SPC).toInt());
            sccp.setLocalSpc(xml.getAttribute(LOCAL_SPC).toInt());
            sccp.setNi(xml.getAttribute(NI).toInt());
            sccp.setRemoteSsn(xml.getAttribute(REMOTE_SSN).toInt());
            sccp.setLocalSsn(xml.getAttribute(LOCAL_SSN).toInt());
            sccp.setTranslationType(xml.getAttribute(TRANSLATION_TYTE).toInt());

            String gtt = (String) xml.get(GLOBAL_TITLE_TYPE, String.class);
            sccp.setGlobalTitleType(GlobalTitleType.createInstance(gtt));
            String an = (String) xml.get(ADDRESS_NATURE, String.class);
            sccp.setNatureOfAddress(NatureOfAddress.valueOf(an));
            String np = (String) xml.get(NUMBERING_PLAN, String.class);
            sccp.setNumberingPlan(NumberingPlan.valueOf(np));
            sccp.setCallingPartyAddressDigits((String) xml.get(CALLING_PARTY_ADDRESS_DIGITS, String.class));
            // for skipping previous data
            String extraLocalAddressDigits = (String) xml.get(EXTRA_LOCAL_ADDRESS_DIGITS, String.class);
        }
    };

}
