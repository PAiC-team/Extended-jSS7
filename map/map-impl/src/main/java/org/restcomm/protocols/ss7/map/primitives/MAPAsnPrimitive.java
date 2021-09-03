package org.restcomm.protocols.ss7.map.primitives;

import java.io.Serializable;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPAsnPrimitive extends Serializable {

    int getTag() throws MAPException;

    int getTagClass();

    boolean getIsPrimitive();

    /**
     * Decoding the length and the content of the primitive (the tag has already read)
     *
     * @param asnInputStream The AsnInputStream that contains the length and the content of the primitive
     * @throws MAPParsingComponentException
     */
    void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException;

    /**
     * Decoding the content of the primitive (the tag and the length have already read)
     *
     * @param asnInputStream The AsnInputStream that contains the content of the primitive
     * @param length The length of the content
     * @throws MAPParsingComponentException
     */
    void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException;

    /**
     * Encoding the tag, the length and the content. Tag and tag class are universal
     *
     * @param asnOutputStream
     * @throws MAPException
     */
    void encodeAll(AsnOutputStream asnOutputStream) throws MAPException;

    /**
     * Encoding the tag, the length and the content. Tag and tag class are defined
     *
     * @param asnOutputStream
     * @param tagClass
     * @param tag
     * @throws MAPException
     */
    void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException;

    /**
     * Encoding the content.
     *
     * @param asnOutputStream
     * @throws MAPException
     */
    void encodeData(AsnOutputStream asnOutputStream) throws MAPException;

}
