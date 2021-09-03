
package org.restcomm.protocols.ss7.sccp.impl;

import javolution.text.CharArray;
import javolution.xml.XMLFormat;
import javolution.xml.XMLSerializable;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.sccp.RemoteSubSystem;

/**
 *
 * @author amit bhayani
 *
 */
public class RemoteSubSystemImpl implements XMLSerializable, RemoteSubSystem {

    private static final String REMOTE_SPC = "remoteSpc";
    private static final String REMOTE_SSN = "remoteSsn";
    private static final String REMOTE_SSN_FLAG = "remoteSsnFlag";
    private static final String MARK_PROHIBITED_WHEN_SPC_RESUMING = "markProhibitedWhenSpcResuming";

    private int remoteSpc;
    private int remoteSsn;
    private int remoteSsnFlag;
    private boolean markProhibitedWhenSpcResuming;

    private boolean remoteSsnProhibited;

    public RemoteSubSystemImpl() {

    }

    public RemoteSubSystemImpl(int remoteSpc, int remoteSsn, int remoteSsnFlag, boolean markProhibitedWhenSpcResuming) {
        this.remoteSpc = remoteSpc;
        this.remoteSsn = remoteSsn;
        this.remoteSsnFlag = remoteSsnFlag;
        this.markProhibitedWhenSpcResuming = markProhibitedWhenSpcResuming;
    }

    public boolean isRemoteSsnProhibited() {
        return this.remoteSsnProhibited;
    }

    public void setRemoteSsnProhibited(boolean remoteSsnProhibited) {
        this.remoteSsnProhibited = remoteSsnProhibited;
    }

    public int getRemoteSpc() {
        return this.remoteSpc;
    }

    /**
     * @param remoteSpc the remoteSpc to set
     */
    protected void setRemoteSpc(int remoteSpc) {
        this.remoteSpc = remoteSpc;
    }

    /**
     * @param remoteSsn the remoteSsn to set
     */
    protected void setRemoteSsn(int remoteSsn) {
        this.remoteSsn = remoteSsn;
    }

    /**
     * @param remoteSsnFlag the remoteSsnFlag to set
     */
    protected void setRemoteSsnFlag(int remoteSsnFlag) {
        this.remoteSsnFlag = remoteSsnFlag;
    }

    /**
     * @param markProhibitedWhenSpcResuming the markProhibitedWhenSpcResuming to set
     */
    protected void setMarkProhibitedWhenSpcResuming(boolean markProhibitedWhenSpcResuming) {
        this.markProhibitedWhenSpcResuming = markProhibitedWhenSpcResuming;
    }

    public int getRemoteSsn() {
        return this.remoteSsn;
    }

    public int getRemoteSsnFlag() {
        return this.remoteSsnFlag;
    }

    public boolean getMarkProhibitedWhenSpcResuming() {
        return markProhibitedWhenSpcResuming;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("rsp=").append(this.remoteSpc).append(" rss=").append(this.remoteSsn).append(" rss-flag=")
                .append(this.remoteSsnFlag).append(" rss-prohibited=").append(this.remoteSsnProhibited);
        if (this.markProhibitedWhenSpcResuming)
            sb.append(" markProhibitedWhenSpcResuming");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + remoteSpc;
        result = prime * result + remoteSsn;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RemoteSubSystemImpl other = (RemoteSubSystemImpl) obj;
        if (remoteSpc != other.remoteSpc)
            return false;
        if (remoteSsn != other.remoteSsn)
            return false;
        return true;
    }

    protected static final XMLFormat<RemoteSubSystemImpl> XML = new XMLFormat<RemoteSubSystemImpl>(RemoteSubSystemImpl.class) {

        public void write(RemoteSubSystemImpl ai, OutputElement xml) throws XMLStreamException {
            xml.setAttribute(REMOTE_SPC, ai.remoteSpc);
            xml.setAttribute(REMOTE_SSN, ai.remoteSsn);
            xml.setAttribute(REMOTE_SSN_FLAG, ai.remoteSsnFlag);
            xml.setAttribute(MARK_PROHIBITED_WHEN_SPC_RESUMING, ai.markProhibitedWhenSpcResuming);

        }

        public void read(InputElement xml, RemoteSubSystemImpl ai) throws XMLStreamException {
            ai.remoteSpc = xml.getAttribute(REMOTE_SPC).toInt();
            ai.remoteSsn = xml.getAttribute(REMOTE_SSN).toInt();
            ai.remoteSsnFlag = xml.getAttribute(REMOTE_SSN_FLAG).toInt();
            CharArray charArray = xml.getAttribute(MARK_PROHIBITED_WHEN_SPC_RESUMING);
            if (charArray == null) {
                ai.markProhibitedWhenSpcResuming = false;
            } else {
                ai.markProhibitedWhenSpcResuming = charArray.toBoolean();
            }
        }
    };
}
