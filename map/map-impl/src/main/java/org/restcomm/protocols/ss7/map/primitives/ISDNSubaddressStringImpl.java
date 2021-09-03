
package org.restcomm.protocols.ss7.map.primitives;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNSubaddressString;

/**
 *
 * @author daniel bichara
 *
 */
public class ISDNSubaddressStringImpl extends OctetStringBase implements ISDNSubaddressString {

    // TODO: implement SubAddress octets and check address format
    /*
     * -- It is composed of -- a) one octet for type of subaddress and odd/even indicator. -- b) 20 octets for subaddress
     * information.
     *
     * -- a) The first octet includes a one bit extension indicator, a -- 3 bits type of subaddress and a one bit odd/even
     * indicator, -- encoded as follows:
     *
     * -- bit 8: 1 (no extension)
     *
     * -- bits 765: type of subaddress -- 000 NSAP (X.213/ISO 8348 AD2) -- 010 User Specified -- All other values are reserved
     *
     * -- bit 4: odd/even indicator -- 0 even number of address signals -- 1 odd number of address signals -- The odd/even
     * indicator is used when the type of subaddress -- is "user specified" and the coding is BCD.
     *
     * -- bits 321: 000 (unused)
     *
     * -- b) Subaddress information. -- The NSAP X.213/ISO8348AD2 address shall be formatted as specified -- by octet 4 which
     * contains the Authority and Format Identifier -- (AFI). The encoding is made according to the "preferred binary --
     * encoding" as defined in X.213/ISO834AD2. For the definition -- of this type of subaddress, see ITU-T Rec I.334.
     *
     * -- For User-specific subaddress, this field is encoded according -- to the user specification, subject to a maximum
     * length of 20 -- octets. When interworking with X.25 networks BCD coding should -- be applied.
     */
    public ISDNSubaddressStringImpl() {
        super(1, 21, "ISDNSubaddressString");
    }

    public ISDNSubaddressStringImpl(byte[] data) {
        super(1, 21, "ISDNSubaddressString", data);
    }

    public byte[] getData() {
        return data;
    }
}
