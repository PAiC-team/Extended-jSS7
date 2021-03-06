
package org.restcomm.protocols.ss7.tools.traceparser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceParserUtil {

    public static void parceLegacyMtp3(byte[] data, ArrayList<TraceReaderListener> listeners) throws TraceReaderException {
        try {
            ByteArrayInputStream in0 = new ByteArrayInputStream(data);
            DataInputStream in = new DataInputStream(in0);

            int bt = in.read();
            int bsn = bt & 0x7F;
            int bib = (bt & 0x80) >>> 7;
            bt = in.read();
            int fsn = bt & 0x7F;
            int fib = (bt & 0x80) >>> 7;
            int li = in.read() & 0x3F;
            if (li < 2) {
                // not MSU - LSSU or FISU - skip
                return;
            }

            int sio = in.read();
            int si = sio & 0x0F;
            int ni = sio >>> 6;
            int priority = (sio & 0x30) >>> 4;
            if (si == 3) {
                // sccp message
                byte b1 = in.readByte();
                byte b2 = in.readByte();
                byte b3 = in.readByte();
                byte b4 = in.readByte();

                int dpc = ((b2 & 0x3f) << 8) | (b1 & 0xff);
                int opc = ((b4 & 0x0f) << 10) | ((b3 & 0xff) << 2) | ((b2 & 0xc0) >> 6);
                int sls = ((b4 & 0xf0) >> 4);

                byte[] mesData = new byte[data.length - 8];
                System.arraycopy(data, 8, mesData, 0, mesData.length);

                for (TraceReaderListener ls : listeners) {
                    ls.ss7Message(si, ni, priority, opc, dpc, sls, mesData);
                }
            } else {
                // other Service Indicator
                return;
            }
        } catch (IOException e) {
            throw new TraceReaderException("IOException: " + e.getMessage(), e);
        }
    }
}
