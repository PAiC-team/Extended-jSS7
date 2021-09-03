
package org.restcomm.protocols.ss7.tools.traceparser;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TraceReaderDriverSimpleSeq extends TraceReaderDriverBase implements TraceReaderDriver {

    public TraceReaderDriverSimpleSeq(ProcessControl processControl, String fileName) {
        super(processControl, fileName);
    }

    @Override
    public void startTraceFile() throws TraceReaderException {

        if (this.listeners.size() == 0)
            throw new TraceReaderException("TraceReaderListener list is empty");

        this.isStarted = true;

        FileInputStream fis = null;

        try {
            if (this.processControl.checkNeedInterrupt())
                return;

            fis = new FileInputStream(fileName);

            while (fis.available() > 0) {
                if (this.processControl.checkNeedInterrupt())
                    return;

                int b1 = fis.read();
                int b2 = fis.read();
                int length = b1 + (b2 << 8);

                byte[] bb = new byte[length];
                int rb = fis.read(bb);
                if (rb < length)
                    throw new TraceReaderException("Not enouph data in the file for reading a message");

                byte[] bufMsg = new byte[length + 3];
                System.arraycopy(bb, 0, bufMsg, 3, length);
                bufMsg[0] = 0;
                bufMsg[1] = 0;
                bufMsg[2] = 63;

                for (TraceReaderListener ls : this.listeners) {
                    TraceParserUtil.parceLegacyMtp3(bufMsg, this.listeners);
                }
            }

        } catch (Throwable e) {
            this.loger.error("General exception: " + e.getMessage());
            e.printStackTrace();
            throw new TraceReaderException("General exception: " + e.getMessage(), e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
