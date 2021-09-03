package org.restcomm.protocols.ss7.tcap;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;

/**
 * @author baranowb
 *
 */
public class TCAPTestUtils {

    public static void compareArrays(byte[] expected, byte[] encoded) {
        boolean same = Arrays.equals(expected, encoded);
        assertTrue(same, "byte[] dont match, expected|encoded \n" + Arrays.toString(expected) + "\n" + Arrays.toString(encoded));
    }

}
