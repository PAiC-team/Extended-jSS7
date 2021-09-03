package org.restcomm.protocols.ss7.sccp.impl.parameter;

import org.restcomm.protocols.ss7.indicator.GlobalTitleIndicator;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.NumberingPlan;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.parameter.EncodingScheme;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle0001;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle0010;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle0011;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle0100;
import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCause;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccp.parameter.Segmentation;

/**
 * @author kulikov
 * @author baranowb
 */
public class ParameterFactoryImpl implements ParameterFactory {

    public SccpAddress createSccpAddress(RoutingIndicator ri, GlobalTitle gt, int dpc, int ssn) {
        return new SccpAddressImpl(ri, gt, dpc, ssn);
    }

    public Importance createImportance(int value) {
        return new ImportanceImpl((byte) value);
    }

    public HopCounter createHopCounter(int hopCount) {
        return new HopCounterImpl(hopCount);
    }

    public ProtocolClass createProtocolClass(int pClass, boolean returnMessageOnError) {
        return new ProtocolClassImpl(pClass, returnMessageOnError);
    }

    public Segmentation createSegmentation() {
        return new SegmentationImpl();
    }

    public ReturnCause createReturnCause(ReturnCauseValue cause) {
        return new ReturnCauseImpl(cause);
    }

    @Override
    public EncodingScheme createEncodingScheme(byte i) {
        switch (i) {
            case DefaultEncodingScheme.SCHEMA_CODE:
                return DefaultEncodingScheme.INSTANCE;
            case BCDOddEncodingScheme.SCHEMA_CODE:
                return BCDOddEncodingScheme.INSTANCE;
            case BCDEvenEncodingScheme.SCHEMA_CODE:
                return BCDEvenEncodingScheme.INSTANCE;
            default:
                return DefaultEncodingScheme.INSTANCE;
        }
    }

    @Override
    public GlobalTitle createGlobalTitle(GlobalTitleIndicator globalTitleIndicator) {
        switch (globalTitleIndicator) {
            case GLOBAL_TITLE_INCLUDES_NATURE_OF_ADDRESS_INDICATOR_ONLY:
                return new GlobalTitle0001Impl();
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_AND_ENCODING_SCHEME:
                return new GlobalTitle0011Impl();
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_NUMBERING_PLAN_ENCODING_SCHEME_AND_NATURE_OF_ADDRESS:
                return new GlobalTitle0100Impl();
            case GLOBAL_TITLE_INCLUDES_TRANSLATION_TYPE_ONLY:
                return new GlobalTitle0010Impl();
            case NO_GLOBAL_TITLE_INCLUDED:
                //return new NoGlobalTitle();
                return null;
            default:
                throw new IllegalArgumentException();

        }
    }

    @Override
    public GlobalTitle0001 createGlobalTitle(String digits, NatureOfAddress natureOfAddress) {
        return new GlobalTitle0001Impl(digits, natureOfAddress);
    }

    @Override
    public GlobalTitle0010 createGlobalTitle(String digits, int translationType) {
        return new GlobalTitle0010Impl(digits, translationType);
    }

    @Override
    public GlobalTitle0011 createGlobalTitle(String digits, int translationType, NumberingPlan numberingPlan, EncodingScheme encodingScheme) {
        return new GlobalTitle0011Impl(digits, translationType, getEncodingScheme(encodingScheme, digits), numberingPlan);
    }

    @Override
    public GlobalTitle0100 createGlobalTitle(String digits, int translationType, NumberingPlan numberingPlan,
            EncodingScheme encodingScheme, NatureOfAddress natureOfAddress) {
        return new GlobalTitle0100Impl(digits, translationType, getEncodingScheme(encodingScheme, digits), numberingPlan, natureOfAddress);
    }

    @Override
    public GlobalTitle createGlobalTitle(String digits) {
        return new NoGlobalTitle(digits);
    }

    protected EncodingScheme getEncodingScheme(EncodingScheme encodingScheme, String digits) {
        // Rules don't support ES yet.
        if (encodingScheme != null) {
            return encodingScheme;
        }
        if (digits.length() % 2 == 0) {
            return BCDEvenEncodingScheme.INSTANCE;
        } else {
            return BCDOddEncodingScheme.INSTANCE;
        }
    }

}
