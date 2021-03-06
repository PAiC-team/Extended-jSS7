package org.restcomm.protocols.ss7.isup.message.parameter;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.isup.ParameterException;


/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public enum GeneralProblemType {

    /**
     * The component type is not recognized as being one of those defined in 3.1. (Invoke, ReturnResult, ReturnResultLast,
     * ReturnError, Reject) This code is generated by the TCAP layer.
     */
    UnrecognizedComponent(0),

    /**
     * The elemental structure of a component does not conform to the structure of that component as defined in 3.1/Q.773. This
     * code is generated by the TCAP layer.
     */
    MistypedComponent(1),

    /**
     * The contents of the component do not conform to the encoding rules defined in 4.1/Q.773. This code is generated by the
     * TCAP layer.
     */
    BadlyStructuredComponent(2);

    private long type = -1;

    public static final int _TAG_CLASS = Tag.CLASS_APPLICATION;
    public static final boolean _TAG_PC_PRITIMITIVE = true;

    GeneralProblemType(long l) {
        this.type = l;
    }

    /**
     * @return the type
     */
    public long getType() {
        return type;
    }

    public static GeneralProblemType getFromInt(long t) throws ParameterException {
        if (t == 0) {
            return UnrecognizedComponent;
        } else if (t == 1) {
            return MistypedComponent;
        } else if (t == 2) {
            return BadlyStructuredComponent;
        }

        throw new ParameterException("Wrong value of type: " + t);
    }
}
