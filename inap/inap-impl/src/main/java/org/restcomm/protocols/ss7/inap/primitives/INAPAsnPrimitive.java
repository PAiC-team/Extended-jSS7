package org.restcomm.protocols.ss7.inap.primitives;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface INAPAsnPrimitive {

    int getTag() throws INAPException;

    int getTagClass();

    boolean getIsPrimitive();

    /**
     * Decoding the length and the content of the primitive (the tag has already read)
     *
     * @param ansIS The AsnInputStream that contains the length and the content of the primitive
     * @throws CAPParsingComponentException
     */
    void decodeAll(AsnInputStream ansIS) throws INAPParsingComponentException;

    /**
     * Decoding the content of the primitive (the tag and the length have already read)
     *
     * @param ansIS The AsnInputStream that contains the content of the primitive
     * @param length The length of the content
     * @throws CAPParsingComponentException
     */
    void decodeData(AsnInputStream ansIS, int length) throws INAPParsingComponentException;

    /**
     * Encoding the tag, the length and the content. Tag and tag class are universal
     *
     * @param asnOs
     * @throws CAPException
     */
    void encodeAll(AsnOutputStream asnOs) throws INAPException;

    /**
     * Encoding the tag, the length and the content. Tag and tag class are defined
     *
     * @param asnOs
     * @param tagClass
     * @param tag
     * @throws CAPException
     */
    void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws INAPException;

    /**
     * Encoding the content.
     *
     * @param asnOs
     * @throws CAPException
     */
    void encodeData(AsnOutputStream asnOs) throws INAPException;

}
