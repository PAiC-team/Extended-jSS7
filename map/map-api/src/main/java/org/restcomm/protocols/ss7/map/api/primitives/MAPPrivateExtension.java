package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * @author sergey vetyutnev
 */
public interface MAPPrivateExtension extends Serializable {

    /**
     * Get the PrivateExtension element Object identifier
     *
     * @return
     */
    long[] getOId();

    /**
     * Get the PrivateExtension element Object identifier
     *
     * @param oId
     */
    void setOId(long[] oId);

    /**
     * Get the PrivateExtension element user data - ASN.1 encoded byte array
     *
     * @return
     */
    byte[] getData();

    /**
     * Set the PrivateExtension element user data - ASN.1 encoded byte array
     *
     * @param data
     */
    void setData(byte[] data);

}
