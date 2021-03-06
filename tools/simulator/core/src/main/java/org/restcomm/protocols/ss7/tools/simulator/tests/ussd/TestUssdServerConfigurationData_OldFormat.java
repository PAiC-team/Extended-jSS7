
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
public class TestUssdServerConfigurationData_OldFormat extends TestUssdServerConfigurationData {

    protected static final XMLFormat<TestUssdServerConfigurationData_OldFormat> XML = new XMLFormat<TestUssdServerConfigurationData_OldFormat>(
            TestUssdServerConfigurationData_OldFormat.class) {

        public void write(TestUssdServerConfigurationData_OldFormat srv, OutputElement xml) throws XMLStreamException {
        }

        public void read(InputElement xml, TestUssdServerConfigurationData_OldFormat srv) throws XMLStreamException {
            srv.dataCodingScheme = xml.getAttribute(DATA_CODING_SCHEME).toInt();
            srv.alertingPattern = xml.getAttribute(ALERTING_PATTERN).toInt();
            srv.oneNotificationFor100Dialogs = xml.getAttribute(ONE_NOTIFICATION_FOR_100_DIALOGS).toBoolean();

            srv.msisdnAddress = (String) xml.get(MSISDN_ADDRESS, String.class);
            srv.autoResponseString = (String) xml.get(AUTO_RESPONSE_STRING, String.class);
            srv.autoUnstructured_SS_RequestString = (String) xml.get(AUTO_UNSTRUCTURED_SS_REQUEST_STRING, String.class);

            String an = (String) xml.get(MSISDN_ADDRESS_NATURE, String.class);
            srv.msisdnAddressNature = AddressNature.valueOf(an);
            String np = (String) xml.get(MSISDN_NUMBERING_PLAN, String.class);
            srv.msisdnNumberingPlan = NumberingPlan.valueOf(np);
            String ss_act = (String) xml.get(PROCESS_SS_REQUEST_ACTION, String.class);
            srv.processSsRequestAction = ProcessSsRequestAction.createInstance(ss_act);
        }
    };

}
