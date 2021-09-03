package org.restcomm.protocols.ss7.sccp.parameter;

import java.io.Serializable;

import org.restcomm.protocols.ss7.indicator.GlobalTitleIndicator;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;

/**
 *
 * @author kulikov
 * @author baranowb
 */
public interface ParameterFactory extends Serializable {

    /**
     * Create SccpAddress parameter
     *
     * @param routingIndicator
     * @param globalTitle
     * @param dpc
     * @param ssn
     * @return
     */
    SccpAddress createSccpAddress(RoutingIndicator routingIndicator, GlobalTitle globalTitle, int dpc, int ssn);

    /**
     * Create Importance parameter.
     *
     * @param importance the value of this parameter
     * @return parameter
     */
    Importance createImportance(int importance);

    /**
     * Create hop counter parameter
     */
    HopCounter createHopCounter(int hopCount);

    ReturnCause createReturnCause(ReturnCauseValue returnCauseValue);

    /**
     * Creates protocol class parameter.
     *
     * @param protocolClass the value of the parameter
     * @return parameter
     */
    ProtocolClass createProtocolClass(int protocolClass, boolean returnMessageOnError);

    /**
     * Create segmentation parameter
     *
     * @return parameter.
     */
    Segmentation createSegmentation();

    /**
     * Creates encoding scheme instance by its code. Which schemes are supported, is up to implementation.
     *
     * @param encodingSchemeByte
     * @return
     */
    EncodingScheme createEncodingScheme(byte encodingSchemeByte);

    GlobalTitle createGlobalTitle(String digits);

    GlobalTitle createGlobalTitle(GlobalTitleIndicator globalTitleIndicator);

    GlobalTitle0001 createGlobalTitle(String digits, NatureOfAddress natureOfAddress);

    GlobalTitle0010 createGlobalTitle(String digits, int translationType);

    GlobalTitle0011 createGlobalTitle(String digits, int translationType, NumberingPlan numberingPlan, EncodingScheme encodingScheme);

    GlobalTitle0100 createGlobalTitle(String digits, int translationType, NumberingPlan numberingPlan,
            EncodingScheme encodingScheme, NatureOfAddress noa);

}
