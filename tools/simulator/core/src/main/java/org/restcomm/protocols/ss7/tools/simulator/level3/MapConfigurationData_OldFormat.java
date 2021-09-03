
package org.restcomm.protocols.ss7.tools.simulator.level3;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MapConfigurationData_OldFormat extends MapConfigurationData {

    protected static final XMLFormat<MapConfigurationData_OldFormat> XML = new XMLFormat<MapConfigurationData_OldFormat>(
            MapConfigurationData_OldFormat.class) {

        public void write(MapConfigurationData_OldFormat map, OutputElement xml) throws XMLStreamException {
        }

        public void read(InputElement xml, MapConfigurationData_OldFormat map) throws XMLStreamException {
            int localSsn = xml.getAttribute(LOCAL_SSN).toInt();
            int remoteSsn = xml.getAttribute(REMOTE_SSN).toInt();

            map.setRemoteAddressDigits((String) xml.get(REMOTE_ADDRESS_DIGITS, String.class));
            map.setOrigReference((String) xml.get(ORIG_REFERENCE, String.class));
            map.setDestReference((String) xml.get(DEST_REFERENCE, String.class));

            String an = (String) xml.get(ORIG_REFERENCE_ADDRESS_NATURE, String.class);
            map.setOrigReferenceAddressNature(AddressNature.valueOf(an));
            String np = (String) xml.get(ORIG_REFERENCE_NUMBERING_PLAN, String.class);
            map.setOrigReferenceNumberingPlan(NumberingPlan.valueOf(np));
            an = (String) xml.get(DEST_REFERENCE_ADDRESS_NATURE, String.class);
            map.setDestReferenceAddressNature(AddressNature.valueOf(an));
            np = (String) xml.get(DEST_REFERENCE_NUMBERING_PLAN, String.class);
            map.setDestReferenceNumberingPlan(NumberingPlan.valueOf(np));
        }
    };

}
