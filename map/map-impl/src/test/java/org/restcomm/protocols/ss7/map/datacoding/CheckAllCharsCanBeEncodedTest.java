
package org.restcomm.protocols.ss7.map.datacoding;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.restcomm.protocols.ss7.map.datacoding.GSMCharset;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CheckAllCharsCanBeEncodedTest {

    @Test(groups = { "functional.encode", "datacoding" })
    public void testCheckAllCharsCanBeEncoded() throws Exception {

        boolean res = GSMCharset.checkAllCharsCanBeEncoded("", GSMCharset.BYTE_TO_CHAR_DefaultAlphabet,
                GSMCharset.BYTE_TO_CHAR_DefaultAlphabetExtensionTable);
        assertTrue(res);

        res = GSMCharset.checkAllCharsCanBeEncoded("aA12", GSMCharset.BYTE_TO_CHAR_DefaultAlphabet,
                GSMCharset.BYTE_TO_CHAR_DefaultAlphabetExtensionTable);
        assertTrue(res);

        res = GSMCharset.checkAllCharsCanBeEncoded("A[", GSMCharset.BYTE_TO_CHAR_DefaultAlphabet,
                GSMCharset.BYTE_TO_CHAR_DefaultAlphabetExtensionTable);
        assertTrue(res);

        res = GSMCharset.checkAllCharsCanBeEncoded("A[", GSMCharset.BYTE_TO_CHAR_DefaultAlphabet, null);
        assertFalse(res);

        res = GSMCharset.checkAllCharsCanBeEncoded("A[", null, null);
        assertFalse(res);

        res = GSMCharset.checkAllCharsCanBeEncoded("A\u0424", GSMCharset.BYTE_TO_CHAR_DefaultAlphabet,
                GSMCharset.BYTE_TO_CHAR_DefaultAlphabetExtensionTable);
        assertFalse(res);

    }
}
