
package org.restcomm.protocols.ss7.map.primitives;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.primitives.AlertingCategory;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingLevel;
import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class AlertingPatternImpl extends OctetStringLength1Base implements AlertingPattern {

    private static final String ALERTING_LEVEL = "alertingLevel";
    private static final String ALERTING_CATEGORY = "alertingCategory";

    private static final String DEVAULT_STRING_VALUE = null;

    public AlertingPatternImpl() {
        super("AlertingPattern");
    }

    public AlertingPatternImpl(int data) {
        super("AlertingPattern", data);
    }

    public AlertingPatternImpl(AlertingLevel alertingLevel) {
        super("AlertingPattern", alertingLevel.getLevel());
    }

    public AlertingPatternImpl(AlertingCategory alertingCategory) {
        super("AlertingPattern", alertingCategory.getCategory());
    }

    public int getData() {
        return data;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern#getAlertingLevel()
     */
    public AlertingLevel getAlertingLevel() {
        return AlertingLevel.getInstance(this.data);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern#getAlertingCategory()
     */
    public AlertingCategory getAlertingCategory() {
        return AlertingCategory.getInstance(this.data);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        AlertingLevel al = this.getAlertingLevel();
        if (al != null) {
            sb.append("AlertingLevel=");
            sb.append(al);
        }
        AlertingCategory ac = this.getAlertingCategory();
        if (ac != null) {
            sb.append(" AlertingCategory=");
            sb.append(ac);
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<AlertingPatternImpl> ALERTING_PATTERN_XML = new XMLFormat<AlertingPatternImpl>(
            AlertingPatternImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, AlertingPatternImpl alertingPattern)
                throws XMLStreamException {
            String str = xml.getAttribute(ALERTING_LEVEL, DEVAULT_STRING_VALUE);
            if (str != null) {
                AlertingLevel al = Enum.valueOf(AlertingLevel.class, str);
                alertingPattern.data = al.getLevel();
            }

            str = xml.getAttribute(ALERTING_CATEGORY, DEVAULT_STRING_VALUE);
            if (str != null) {
                AlertingCategory cap = Enum.valueOf(AlertingCategory.class, str);
                alertingPattern.data = cap.getCategory();
            }
        }

        @Override
        public void write(AlertingPatternImpl alertingPattern, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (alertingPattern.getAlertingLevel() != null)
                xml.setAttribute(ALERTING_LEVEL, alertingPattern.getAlertingLevel().toString());
            if (alertingPattern.getAlertingCategory() != null)
                xml.setAttribute(ALERTING_CATEGORY, alertingPattern.getAlertingCategory().toString());
        }
    };

}
