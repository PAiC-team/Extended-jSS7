package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

/**
 *
 * SupportedLCS-CapabilitySets ::= BIT STRING { lcsCapabilitySet1 (0), lcsCapabilitySet2 (1), lcsCapabilitySet3 (2),
 * lcsCapabilitySet4 (3) , lcsCapabilitySet5 (4) } (SIZE (2..16)) -- Core network signalling capability set1 indicates LCS
 * Release98 or Release99 version. -- Core network signalling capability set2 indicates LCS Release4. -- Core network signalling
 * capability set3 indicates LCS Release5. -- Core network signalling capability set4 indicates LCS Release6. -- Core network
 * signalling capability set5 indicates LCS Release7 or later version. -- A node shall mark in the BIT STRING all LCS capability
 * sets it supports. -- If no bit is set then the sending node does not support LCS. -- If the parameter is not sent by an VLR
 * then the VLR may support at most capability set1. -- If the parameter is not sent by an SGSN then no support for LCS is
 * assumed. -- An SGSN is not allowed to indicate support of capability set1. -- Other bits than listed above shall be
 * discarded.
 *
 * @author amit bhayani
 *
 */
public interface SupportedLCSCapabilitySets extends Serializable {

    boolean getCapabilitySetRelease98_99();

    boolean getCapabilitySetRelease4();

    boolean getCapabilitySetRelease5();

    boolean getCapabilitySetRelease6();

    boolean getCapabilitySetRelease7();

}
