
package org.restcomm.protocols.ss7.map.api.primitives;

/**
<code>
EMLPP-Priority ::= INTEGER (0..15)
-- The mapping from the values A,B,0,1,2,3,4 to the integer-value is
-- specified as follows where A is the highest and 4 is the lowest
-- priority level
-- the integer values 7-15 are spare and shall be mapped to value 4
priorityLevelA EMLPP-Priority ::= 6
priorityLevelB EMLPP-Priority ::= 5
priorityLevel0 EMLPP-Priority ::= 0
priorityLevel1 EMLPP-Priority ::= 1
priorityLevel2 EMLPP-Priority ::= 2
priorityLevel3 EMLPP-Priority ::= 3
priorityLevel4 EMLPP-Priority ::= 4
</code>
 *
 * @author cristian veliscu
 *
 */
public enum EMLPPPriority {

    priorityLevel0(0), priorityLevel1(1), priorityLevel2(2), priorityLevel3(3), priorityLevel4(4),
    priorityLevelB(5), priorityLevelA(6);

    private int code;

    private EMLPPPriority(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static EMLPPPriority getEMLPPPriority(int code) {
        switch (code) {
            case 0:
                return priorityLevel0;
            case 1:
                return priorityLevel1;
            case 2:
                return priorityLevel2;
            case 3:
                return priorityLevel3;
            case 4:
                return priorityLevel4;
            case 5:
                return priorityLevelB;
            case 6:
                return priorityLevelA;
            default:
                if ((code >= 7) && (code <= 15))
                    return priorityLevel4;
                return null;
        }
    }
}