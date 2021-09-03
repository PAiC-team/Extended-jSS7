
package org.restcomm.protocols.ss7.tools.simulator.tests.sms;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.smstpdu.NumberingPlanIdentification;
import org.restcomm.protocols.ss7.map.api.smstpdu.TypeOfNumber;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapProtocolVersion;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TestSmsClientConfigurationData_OldFormat extends TestSmsClientConfigurationData {

    protected static final XMLFormat<TestSmsClientConfigurationData_OldFormat> XML = new XMLFormat<TestSmsClientConfigurationData_OldFormat>(
            TestSmsClientConfigurationData_OldFormat.class) {

        public void write(TestSmsClientConfigurationData_OldFormat srv, OutputElement xml) throws XMLStreamException {
        }

        public void read(InputElement xml, TestSmsClientConfigurationData_OldFormat clt) throws XMLStreamException {
            clt.smscSsn = xml.getAttribute(SMSC_SSN).toInt();

            clt.serviceCenterAddress = (String) xml.get(SERVICE_CENTER_ADDRESS, String.class);
            clt.sriResponseImsi = (String) xml.get(SRI_RESPONSE_IMSI, String.class);
            clt.sriResponseVlr = (String) xml.get(SRI_RESPONSE_VLR, String.class);

            String an = (String) xml.get(ADDRESS_NATURE, String.class);
            clt.addressNature = AddressNature.valueOf(an);
            String np = (String) xml.get(NUMBERING_PLAN, String.class);
            clt.numberingPlan = NumberingPlan.valueOf(np);
            String mpv = (String) xml.get(MAP_PROTOCOL_VERSION, String.class);
            clt.mapProtocolVersion = MapProtocolVersion.createInstance(mpv);
            String ton = (String) xml.get(TYPE_OF_NUMBER, String.class);
            clt.typeOfNumber = TypeOfNumber.valueOf(ton);
            String npi = (String) xml.get(NUMBERING_PLAN_IDENTIFICATION, String.class);
            clt.numberingPlanIdentification = NumberingPlanIdentification.valueOf(npi);
            String sct = (String) xml.get(SMS_CODING_TYPE, String.class);
            clt.smsCodingType = SmsCodingType.createInstance(sct);
        }
    };

}
