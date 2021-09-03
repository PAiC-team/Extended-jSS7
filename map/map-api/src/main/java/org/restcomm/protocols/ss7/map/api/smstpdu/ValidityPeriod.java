package org.restcomm.protocols.ss7.map.api.smstpdu;

import java.io.Serializable;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface ValidityPeriod extends Serializable {

    ValidityPeriodFormat getValidityPeriodFormat();

    Integer getRelativeFormatValue();

    /**
     * @return Return the relative format period in hours
     */
    Double getRelativeFormatHours();

    AbsoluteTimeStamp getAbsoluteFormatValue();

    ValidityEnhancedFormatData getEnhancedFormatValue();

}
