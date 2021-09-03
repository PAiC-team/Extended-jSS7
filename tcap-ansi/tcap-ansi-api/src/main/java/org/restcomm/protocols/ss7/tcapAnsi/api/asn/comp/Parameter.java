
/**
 *
 */

package org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.Encodable;

/**
 *
 * @author baranowb
 *
 */
public interface Parameter extends Encodable {
    int _TAG_SEQUENCE = 16;
    int _TAG_SET = 18;

    byte[] getData();

    /**
     * Sets content as raw byte[]. invocation parameter must be encoded by user.
     *
     * @param b
     */
    void setData(byte[] b);

    /**
     * Determine if this parameter is of primitive type - not constructed.
     *
     * @return
     */
    boolean isPrimitive();

    void setPrimitive(boolean b);

    /**
     * @return the tag
     */
    int getTag();

    /**
     * @param tag the tag to set
     */
    void setTag(int tag);

    /**
     * @return the tagClass
     */
    int getTagClass();

    /**
     * @param tagClass the tagClass to set
     */
    void setTagClass(int tagClass);

    Parameter[] getParameters();

    void setParameters(Parameter[] paramss);

}
