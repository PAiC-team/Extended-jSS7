
package org.restcomm.protocols.ss7.sccpext.impl.router;

/**
 * Class for exception concerning rule.
 *
 * @author baranowb
 *
 */
public class RuleException extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = 6024579057121898624L;
    private RuleImpl rule;

    /**
     *
     */
    public RuleException(RuleImpl rule) {
        this.rule = rule;
    }

    /**
     * @param message
     */
    public RuleException(String message, RuleImpl rule) {
        super(message);
        this.rule = rule;
    }

    /**
     * @param cause
     */
    public RuleException(Throwable cause, RuleImpl rule) {
        super(cause);
        this.rule = rule;
    }

    /**
     * @param message
     * @param cause
     */
    public RuleException(String message, Throwable cause, RuleImpl rule) {
        super(message, cause);
        this.rule = rule;
    }

    public String toString() {
        return "RuleException [rule=" + rule + ", toString()=" + super.toString() + "]";
    }

}
