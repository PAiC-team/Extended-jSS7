
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
*
<code>
Maximum SDU size, octet 7 (see 3GPP TS 23.107 [81])
In MS to network direction:
0 0 0 0 0 0 0 0     Subscribed maximum SDU size
1 1 1 1 1 1 1 1     Reserved
In network to MS direction:
0 0 0 0 0 0 0 0     Reserved
1 1 1 1 1 1 1 1     Reserved
In MS to network direction and in network to MS direction:
For values in the range 00000001 to 10010110 the Maximum SDU size value is binary coded in 8 bits, using a granularity of 10 octets, giving a range of values from 10 octets to 1500 octets.
Values above 10010110 are as below:
1 0 0 1 0 1 1 1     1502 octets
1 0 0 1 1 0 0 0     1510 octets
1 0 0 1 1 0 0 1     1520 octets
The network shall map all other values not explicitly defined onto one of the values defined in this version of the protocol.
The network shall return a negotiated value which is explicitly defined in this version of this protocol.
The MS shall consider all other values as reserved.
</code>
*
* @author sergey vetyutnev
*
*/
public interface ExtQoSSubscribed_MaximumSduSize {

    int getSourceData();

    /**
     * @return MaximumSduSize (in octets) value or 0 when source==0 or not specified in
     *         23.107
     */
    int getMaximumSduSize();

}
