
package org.restcomm.protocols.ss7.oam.common.statistics.api;

/**
*
* Counter values type
*
* @author sergey vetyutnev
*
*/
public enum CounterType {
    /**
     * A simple constantly increasing long value : CounterValue.getLongValue() =
     * SourceValueObject.getValue() - SourceValueObject_prev.getValue()
     */
    Summary,
    /**
     * A simple constantly increasing long value - cumulative counter:
     * CounterValue.getLongValue() = SourceValueObject.getValue()
     */
    Summary_Cumulative,
    /**
     * A simple constantly increasing double value : CounterValue.getDoubleValue() =
     * SourceValueObject.getValueA() - SourceValueObject_prev.getValueA()
     */
    SummaryDouble,
    /**
     * A minimal long value for time duration : CounterValue.getLongValue() =
     * SourceValueObject.getValue()
     */
    Minimal,
    /**
     * A maximal long value for time duration : CounterValue.getLongValue() =
     * SourceValueObject.getValue()
     */
    Maximal,
    /**
     * An average double value for time duration : CounterValue.getDoubleValue()
     * = (SourceValueObject.getValueA() - SourceValueObject_prev.getValueA()) /
     * (SourceValueObject.getValueB() - SourceValueObject_prev.getValueB())
     */
    Average,
    /**
     * An array of pairs String-Long values: CounterValue.getComplexValue() =
     * SourceValueObject.getComplexValue()
     */
    ComplexValue,
}
