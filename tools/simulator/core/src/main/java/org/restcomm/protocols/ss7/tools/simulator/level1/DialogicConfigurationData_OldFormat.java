
package org.restcomm.protocols.ss7.tools.simulator.level1;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class DialogicConfigurationData_OldFormat extends DialogicConfigurationData {

    protected static final XMLFormat<DialogicConfigurationData_OldFormat> XML = new XMLFormat<DialogicConfigurationData_OldFormat>(
            DialogicConfigurationData_OldFormat.class) {

        public void write(DialogicConfigurationData_OldFormat dial, OutputElement xml) throws XMLStreamException {
        }

        public void read(InputElement xml, DialogicConfigurationData_OldFormat dial) throws XMLStreamException {
            dial.setSourceModuleId(xml.getAttribute(SOURCE_MODULE_ID).toInt());
            dial.setDestinationModuleId(xml.getAttribute(DESTINATION_MODULE_ID).toInt());
        }
    };

}
