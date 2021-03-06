package org.restcomm.protocols.ss7.map.smstpdu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.restcomm.protocols.ss7.map.api.smstpdu.ApplicationPortAddressing16BitAddress;
import org.restcomm.protocols.ss7.map.api.smstpdu.ConcatenatedShortMessagesIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.NationalLanguageLockingShiftIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.NationalLanguageSingleShiftIdentifier;
import org.restcomm.protocols.ss7.map.api.smstpdu.UserDataHeader;
import org.restcomm.protocols.ss7.map.api.smstpdu.UserDataHeaderElement;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class UserDataHeaderImpl implements UserDataHeader {

    private Map<Integer, byte[]> data = new HashMap<Integer, byte[]>();

    public UserDataHeaderImpl() {
    }

    public UserDataHeaderImpl(byte[] encodedData) {
        if (encodedData == null || encodedData.length < 1)
            return;
        int udhl = encodedData[0] & 0xFF;
        if (udhl > encodedData.length)
            udhl = encodedData.length;
        int ind = 1;
        while (ind <= udhl - 1) {
            int id = encodedData[ind++];
            int len = encodedData[ind++];
            if (len <= udhl + 1 - ind) {
                byte[] buf = new byte[len];
                System.arraycopy(encodedData, ind, buf, 0, len);
                ind += len;

                data.put(id, buf);
            }
        }
    }

    public byte[] getEncodedData() {

        if (data.size() == 0)
            return null;

        ByteArrayOutputStream stm = new ByteArrayOutputStream();
        stm.write(0);
        for (int id : data.keySet()) {
            byte[] buf = data.get(id);

            stm.write(id);
            if (buf == null)
                stm.write(0);
            else {
                stm.write(buf.length);
                try {
                    stm.write(buf);
                } catch (IOException e) {
                    // This should never occur
                }
            }
        }

        byte[] res = stm.toByteArray();
        res[0] = (byte) (res.length - 1);
        return res;
    }

    public Map<Integer, byte[]> getAllData() {
        return data;
    }

    public void addInformationElement(int informationElementIdentifier, byte[] encodedData) {
        this.data.put(informationElementIdentifier, encodedData);
    }

    public void addInformationElement(UserDataHeaderElement userDataHeaderElement) {
        this.data.put(userDataHeaderElement.getEncodedInformationElementIdentifier(),
            userDataHeaderElement.getEncodedInformationElementData());
    }

    public byte[] getInformationElementData(int informationElementIdentifier) {
        return this.data.get(informationElementIdentifier);
    }

    public NationalLanguageLockingShiftIdentifier getNationalLanguageLockingShift() {
        byte[] buf = this.data.get(_InformationElementIdentifier_NationalLanguageLockingShift);
        if (buf != null && buf.length == 1)
            return new NationalLanguageLockingShiftIdentifierImpl(buf);
        else
            return null;
    }

    public NationalLanguageSingleShiftIdentifier getNationalLanguageSingleShift() {
        byte[] buf = this.data.get(_InformationElementIdentifier_NationalLanguageSingleShift);
        if (buf != null && buf.length == 1)
            return new NationalLanguageSingleShiftIdentifierImpl(buf);
        else
            return null;
    }

    public ConcatenatedShortMessagesIdentifier getConcatenatedShortMessagesIdentifier() {
        byte[] buf = this.data.get(_InformationElementIdentifier_ConcatenatedShortMessages16bit);
        if (buf != null && buf.length == 4)
            return new ConcatenatedShortMessagesIdentifierImpl(buf);
        else {
            buf = this.data.get(_InformationElementIdentifier_ConcatenatedShortMessages8bit);
            if (buf != null && buf.length == 3)
                return new ConcatenatedShortMessagesIdentifierImpl(buf);
            else
                return null;
        }
    }

    @Override
    public ApplicationPortAddressing16BitAddress getApplicationPortAddressing16BitAddress() {
        byte[] buf = this.data.get(_InformationElementIdentifier_ApplicationPortAddressingScheme16BitAddress);
        if (buf != null && buf.length == 4)
            return new ApplicationPortAddressing16BitAddressImpl(buf);
        else
            return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserDataHeader [");
        boolean isFirst = true;
        for (int id : data.keySet()) {
            byte[] buf = data.get(id);

            if (isFirst)
                isFirst = false;
            else
                sb.append("\n\t");
            sb.append(id);
            sb.append(" = ");
            sb.append(printDataArr(buf));
        }

        NationalLanguageLockingShiftIdentifier nllsi = this.getNationalLanguageLockingShift();
        NationalLanguageSingleShiftIdentifier nlssi = this.getNationalLanguageSingleShift();
        ConcatenatedShortMessagesIdentifier csmi = this.getConcatenatedShortMessagesIdentifier();
        ApplicationPortAddressing16BitAddress apa16 = this.getApplicationPortAddressing16BitAddress();
        if (nllsi != null) {
            sb.append(", NationalLanguageLockingShiftIdentifier = [");
            sb.append(nllsi);
            sb.append("]");
        }
        if (nlssi != null) {
            sb.append(", NationalLanguageSingleShiftIdentifier = [");
            sb.append(nlssi);
            sb.append("]");
        }
        if (csmi != null) {
            sb.append(", ConcatenatedShortMessagesIdentifier = [");
            sb.append(csmi);
            sb.append("]");
        }
        if (apa16 != null) {
            sb.append(", ApplicationPortAddressing16BitAddress = [");
            sb.append(apa16);
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

    private String printDataArr(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int b : arr) {
            sb.append(b);
            sb.append(", ");
        }

        return sb.toString();
    }
}
