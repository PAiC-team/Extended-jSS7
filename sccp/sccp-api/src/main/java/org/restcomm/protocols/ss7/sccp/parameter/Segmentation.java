package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Segmentation parameter.
 *
 * @author baranowb
 * @author kulikov
 */
public interface Segmentation extends Parameter {

    int PARAMETER_CODE = 0x10;

    boolean isFirstSegIndication();

    boolean isClass1Selected();

    byte getRemainingSegments();

    int getSegmentationLocalRef();

}
