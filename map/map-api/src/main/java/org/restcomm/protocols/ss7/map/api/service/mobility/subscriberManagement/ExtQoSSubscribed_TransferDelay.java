
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
*
<code>
Transfer delay, octet 11 (See 3GPP TS 23.107 [81])
Bits
8 7 6 5 4 3
In MS to network direction:
0 0 0 0 0 0     Subscribed transfer delay
In network to MS direction:
0 0 0 0 0 0     Reserved
In MS to network direction and in network to MS direction:
0 0 0 0 0 1     The Transfer delay is binary coded in 6 bits, using a granularity of 10 ms
0 0 1 1 1 1     giving a range of values from 10 ms to 150 ms in 10 ms increments
0 1 0 0 0 0     The transfer delay is 200 ms + ((the binary coded value in 6 bits  010000) * 50 ms)
0 1 1 1 1 1     giving a range of values from 200 ms to 950 ms in 50ms increments
1 0 0 0 0 0     The transfer delay is 1000 ms + ((the binary coded value in 6 bits  100000) * 100 ms)
1 1 1 1 1 0     giving a range of values from 1000 ms to 4000 ms in 100ms increments
1 1 1 1 1 1     Reserved
The Transfer delay value is ignored if the Traffic Class is Interactive class or Background class.
</code>
*
* @author sergey vetyutnev
*
*/
public interface ExtQoSSubscribed_TransferDelay {

    int getSourceData();

    /**
     * @return TransferDelay (ms) value or 0 when source==0 or not specified in
     *         23.107
     */
    int getTransferDelay();

}
