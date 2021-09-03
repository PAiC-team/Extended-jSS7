package org.restcomm.protocols.ss7.cap.primitives;

import java.io.Serializable;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPAsnPrimitive extends Serializable {

    int getTag() throws CAPException;

    int getTagClass();

    boolean getIsPrimitive();

    /**
     * Decoding the length and the content of the primitive (the tag has already read)
     *
     * @param asnInputStream The AsnInputStream that contains the length and the content of the primitive
     * @throws CAPParsingComponentException
     */
    void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException;

    /**
     * Decoding the content of the primitive (the tag and the length have already read)
     *
     * @param asnInputStream The AsnInputStream that contains the content of the primitive
     * @param length The length of the content
     * @throws CAPParsingComponentException
     */
    void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException;

    /**
     * Encoding the tag, the length and the content. Tag and tag class are universal
     *
     * @param asnOs
     * @throws CAPException
     */
    void encodeAll(AsnOutputStream asnOs) throws CAPException;

    /**
     * Encoding the tag, the length and the content. Tag and tag class are defined
     *
     * @param asnOutputStream
     * @param tagClass
     * @param tag
     * @throws CAPException
     */
    void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException;

    /**
     * Encoding the content.
     *
     * @param asnOutputStream
     * @throws CAPException
     */
    void encodeData(AsnOutputStream asnOutputStream) throws CAPException;

}
