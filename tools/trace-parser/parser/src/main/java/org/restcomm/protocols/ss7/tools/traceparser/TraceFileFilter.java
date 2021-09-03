
package org.restcomm.protocols.ss7.tools.traceparser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceFileFilter extends FileFilter {

    private String name;

    public TraceFileFilter(String name) {
        this.name = name;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;

        String s = f.getName();
        int i1 = s.lastIndexOf('.');
        if (i1 > 0) {
            String s1 = s.substring(i1 + 1);

            if (this.name.equals("Acterna")) {
                if (s1.length() == 3 && s1.startsWith("p0"))
                    return true;
            } else if (this.name.equals("Simple")) {
                if (s1.equals("msg"))
                    return true;
            } else if (this.name.equals("HexStream")) {
                if (s1.equals("txt"))
                    return true;
            } else if (this.name.equals("Pcap")) {
                if (s1.equals("pcap") || s1.equals("pcapng"))
                    return true;
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return this.name;
    }

}
