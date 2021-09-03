package org.restcomm.protocols.ss7.m3ua.parameter;

/**
 * <p>
 * The network appearance is a number assigned by the signaling gateway and the ASP that, when used along with the signaling
 * point code, uniquely identifies an SS7 node in the SS7 domain.
 * </p>
 * <p>
 * This is used when a signaling gateway is connected to multiple networks, and those networks are in different countries, for
 * example. When this occurs, the SS7 point codes that are assigned could be duplicated. For example, if the node has an
 * appearance in France and also in the United Kingdom, the point code advertised in those two networks could be duplicated
 * because national point codes are of local significance only.
 * </p>
 *
 * @author amit bhayani
 * @author kulikov
 */
public interface NetworkAppearance extends Parameter {

    /**
     * A value in the range 1 to 4294967295 to be used in the Network Appearance
     *
     * @return
     */
    long getNetApp();

}
