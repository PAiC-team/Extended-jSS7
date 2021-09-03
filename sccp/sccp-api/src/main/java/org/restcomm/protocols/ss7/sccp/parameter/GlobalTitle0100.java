
package org.restcomm.protocols.ss7.sccp.parameter;

import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;


/**
 * @author baranowb
 *
 */
public interface GlobalTitle0100 extends GlobalTitle {

    EncodingScheme getEncodingScheme();

    NumberingPlan getNumberingPlan();

    int getTranslationType();

    NatureOfAddress getNatureOfAddress();

}
