
package org.restcomm.protocols.ss7.tools.traceparser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TraceReaderDriverHexStream extends TraceReaderDriverBase implements TraceReaderDriver {

    public TraceReaderDriverHexStream(ProcessControl processControl, String fileName) {
        super(processControl, fileName);
    }

    @Override
    public void startTraceFile() throws TraceReaderException {

        if (this.listeners.size() == 0)
            throw new TraceReaderException("TraceReaderListener list is empty");

        this.isStarted = true;

        FileInputStream fis = null;
        DataInputStream in = null;

        try {
            if (this.processControl.checkNeedInterrupt())
                return;

            fis = new FileInputStream(fileName);
            in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                if (strLine.length() > 0) {
                    if ((strLine.length() % 2) != 0)
                        throw new TraceReaderException("Odd characters count in a string");
                    byte[] buf = this.hexToBytes(strLine);

                    int si = 3;
                    int ni = 0;
                    int priority = 0;
                    int opc = 0;
                    int dpc = 0;
                    int sls = 0;
                    for (TraceReaderListener ls : this.listeners) {
                        ls.ss7Message(si, ni, priority, opc, dpc, sls, buf);
                    }
                }
            }

        } catch (Throwable e) {
            this.loger.error("General exception: " + e.getMessage());
            e.printStackTrace();
            throw new TraceReaderException("General exception: " + e.getMessage(), e);
        } finally {
            try {
                fis.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] hexToBytes(String hexString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        byte[] bytes = adapter.unmarshal(hexString);
        return bytes;
    }
}
