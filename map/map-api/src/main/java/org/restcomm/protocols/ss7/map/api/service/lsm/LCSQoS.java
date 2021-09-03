package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 * LCS-QoS ::= SEQUENCE { horizontal-accuracy [0] Horizontal-Accuracy OPTIONAL, verticalCoordinateRequest [1] NULL OPTIONAL,
 * vertical-accuracy [2] Vertical-Accuracy OPTIONAL, responseTime [3] ResponseTime OPTIONAL, extensionContainer [4]
 * ExtensionContainer OPTIONAL, ...}
 *
 * @author amit bhayani
 *
 */
public interface LCSQoS extends Serializable {

    /**
     * Horizontal-Accuracy ::= OCTET STRING (SIZE (1)) -- bit 8 = 0 -- bits 7-1 = 7 bit Uncertainty Code defined in 3GPP TS
     * 23.032. The horizontal location -- error should be less than the error indicated by the uncertainty code with 67% --
     * confidence.
     *
     * @return
     */
    Integer getHorizontalAccuracy();

    /**
     * NULL
     *
     * @return
     */
    boolean getVerticalCoordinateRequest();

    /**
     * Vertical-Accuracy ::= OCTET STRING (SIZE (1)) -- bit 8 = 0 -- bits 7-1 = 7 bit Vertical Uncertainty Code defined in 3GPP
     * TS 23.032. -- The vertical location error should be less than the error indicated -- by the uncertainty code with 67%
     * confidence.
     *
     * @return
     */
    Integer getVerticalAccuracy();

    ResponseTime getResponseTime();

    MAPExtensionContainer getExtensionContainer();
}
