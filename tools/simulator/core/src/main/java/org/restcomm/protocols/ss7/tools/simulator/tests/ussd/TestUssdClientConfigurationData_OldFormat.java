
package org.restcomm.protocols.ss7.tools.simulator.tests.ussd;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TestUssdClientConfigurationData_OldFormat extends TestUssdClientConfigurationData {

    protected static final XMLFormat<TestUssdClientConfigurationData_OldFormat> XML = new XMLFormat<TestUssdClientConfigurationData_OldFormat>(
            TestUssdClientConfigurationData_OldFormat.class) {

        public void write(TestUssdClientConfigurationData_OldFormat clt, OutputElement xml) throws XMLStreamException {
        }

        public void read(InputElement xml, TestUssdClientConfigurationData_OldFormat clt) throws XMLStreamException {
            clt.dataCodingScheme = xml.getAttribute(DATA_CODING_SCHEME).toInt();
            clt.alertingPattern = xml.getAttribute(ALERTING_PATTERN).toInt();
            clt.maxConcurrentDialogs = xml.getAttribute(MAX_CONCURENT_DIALOGS).toInt();
            clt.oneNotificationFor100Dialogs = xml.getAttribute(ONE_NOTIFICATION_FOR_100_DIALOGS).toBoolean();

            clt.msisdnAddress = (String) xml.get(MSISDN_ADDRESS, String.class);
            String an = (String) xml.get(MSISDN_ADDRESS_NATURE, String.class);
            clt.msisdnAddressNature = AddressNature.valueOf(an);
            String np = (String) xml.get(MSISDN_NUMBERING_PLAN, String.class);
            clt.msisdnNumberingPlan = NumberingPlan.valueOf(np);

            String uca = (String) xml.get(USSD_CLIENT_ACTION, String.class);
            clt.ussdClientAction = UssdClientAction.createInstance(uca);
            clt.autoRequestString = (String) xml.get(AUTO_REQUEST_STRING, String.class);
        }
    };

}
