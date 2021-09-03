
package org.restcomm.protocols.ss7.tools.traceparser;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class Ss7ParseParameters {

    private ParseDriverType fileTypeN = ParseDriverType.Pcap;
    private ParseProtocol protocol = ParseProtocol.Map;
    private String sourceFilePath = "";
    private String msgLogFilePath = "";
    private Integer applicationContextFilter = null;
    private Long dialogIdFilter = null;
    private Long dialogIdFilter2 = null;
    private boolean tcapMsgData = false;
    private boolean detailedDialog = false;
    private boolean detailedComponents = false;
    private String messageChainFilePath;
    private Integer[] opcDpcFilter;
    private SccpProtocolVersion sccpProtocolVersion = SccpProtocolVersion.ITU;

    public ParseDriverType getFileTypeN() {
        return fileTypeN;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getMsgLogFilePath() {
        return msgLogFilePath;
    }

    public Integer getApplicationContextFilter() {
        return applicationContextFilter;
    }

    public Long getDialogIdFilter() {
        return dialogIdFilter;
    }

    public Long getDialogIdFilter2() {
        return dialogIdFilter2;
    }

    public boolean getTcapMsgData() {
        return tcapMsgData;
    }

    public boolean getDetailedDialog() {
        return detailedDialog;
    }

    public boolean getDetailedComponents() {
        return detailedComponents;
    }

    public ParseProtocol getParseProtocol() {
        return protocol;
    }

    public String getMessageChainFilePath() {
        return messageChainFilePath;
    }

    public Integer[] getOpcDpcFilter() {
        return opcDpcFilter;
    }

    public SccpProtocolVersion getSccpProtocolVersion() {
        return sccpProtocolVersion;
    }

    public void setFileTypeN(ParseDriverType fileTypeN) {
        this.fileTypeN = fileTypeN;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public void setMsgLogFilePath(String msgLogFilePath) {
        this.msgLogFilePath = msgLogFilePath;
    }

    public void setApplicationContextFilter(Integer applicationContextFilter) {
        this.applicationContextFilter = applicationContextFilter;
    }

    public void setDialogIdFilter(Long dialogIdFilter) {
        this.dialogIdFilter = dialogIdFilter;
    }

    public void setDialogIdFilter2(Long dialogIdFilter2) {
        this.dialogIdFilter2 = dialogIdFilter2;
    }

    public void setTcapMsgData(boolean tcapMsgData) {
        this.tcapMsgData = tcapMsgData;
    }

    public void setDetailedDialog(boolean detailedDialog) {
        this.detailedDialog = detailedDialog;
    }

    public void setDetailedComponents(boolean detailedComponents) {
        this.detailedComponents = detailedComponents;
    }

    public void setParseProtocol(ParseProtocol protocol) {
        this.protocol = protocol;
    }

    public void setMessageChainFilePath(String dialogMessageChainFilePath) {
        this.messageChainFilePath = dialogMessageChainFilePath;
    }

    public void setOpcDpcFilter(Integer[] opcDpcFilter) {
        this.opcDpcFilter = opcDpcFilter;
    }

    public void setSccpProtocolVersion(SccpProtocolVersion sccpProtocolVersion) {
        this.sccpProtocolVersion = sccpProtocolVersion;
    }
}
