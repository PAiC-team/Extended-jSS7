
package org.restcomm.protocols.ss7.sccp.impl.router;

import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.sccp.LongMessageRule;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 *
 */
public class LongMessageRuleImpl implements LongMessageRule, XMLSerializable {

    private static final String FIRST_SPC = "firstSpc";
    private static final String LAST_SPC = "lastSpc";
    private static final String RULE_TYPE = "ruleType";

    private int firstSpc;
    private int lastSpc;
    private LongMessageRuleType ruleType;

    public LongMessageRuleImpl() {
    }

    public LongMessageRuleImpl(int firstSpc, int lastSpc, LongMessageRuleType ruleType) {
        this.firstSpc = firstSpc;
        this.lastSpc = lastSpc;
        this.ruleType = ruleType;
    }

    public LongMessageRuleType getLongMessageRuleType() {
        return this.ruleType;
    }

    public int getFirstSpc() {
        return this.firstSpc;
    }

    public int getLastSpc() {
        return this.lastSpc;
    }

    public boolean matches(int dpc) {
        if (dpc >= this.firstSpc && dpc <= this.lastSpc)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("firstSpc=").append(this.firstSpc).append(", lastSpc=").append(this.lastSpc).append(", ruleType=")
                .append(this.ruleType);
        return sb.toString();
    }

    protected static final XMLFormat<LongMessageRuleImpl> XML = new XMLFormat<LongMessageRuleImpl>(LongMessageRuleImpl.class) {

        public void write(LongMessageRuleImpl ai, OutputElement xml) throws XMLStreamException {
            xml.setAttribute(FIRST_SPC, ai.firstSpc);
            xml.setAttribute(LAST_SPC, ai.lastSpc);
            xml.setAttribute(RULE_TYPE, ai.ruleType.toString());
        }

        public void read(InputElement xml, LongMessageRuleImpl ai) throws XMLStreamException {
            ai.firstSpc = xml.getAttribute(FIRST_SPC).toInt();
            ai.lastSpc = xml.getAttribute(LAST_SPC).toInt();
            String ruleT = xml.getAttribute(RULE_TYPE).toString();
            ai.ruleType = LongMessageRuleType.valueOf(ruleT);
        }
    };
}
