
package org.restcomm.protocols.ss7.tools.simulator.level1;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.api.IpChannelType;
import org.restcomm.protocols.ss7.m3ua.ExchangeType;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.IPSPType;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class M3uaConfigurationData_OldFormat extends M3uaConfigurationData {

    protected static final XMLFormat<M3uaConfigurationData_OldFormat> XML_OLD = new XMLFormat<M3uaConfigurationData_OldFormat>(
            M3uaConfigurationData_OldFormat.class) {

        public void write(M3uaConfigurationData_OldFormat m3ua, OutputElement xml) throws XMLStreamException {
        }

        public void read(InputElement xml, M3uaConfigurationData_OldFormat m3ua) throws XMLStreamException {
            m3ua.setIsSctpServer(xml.getAttribute(IS_SCTP_SERVER).toBoolean());
            m3ua.setLocalPort(xml.getAttribute(LOCAL_PORT).toInt());
            m3ua.setRemotePort(xml.getAttribute(REMOTE_PORT).toInt());
            String str = xml.getAttribute(IP_CHANNEL_TYPE).toString();
            m3ua.setIpChannelType(IpChannelType.valueOf(str));
            m3ua.setDpc(xml.getAttribute(DPC).toInt());
            m3ua.setOpc(xml.getAttribute(OPC).toInt());
            m3ua.setSi(xml.getAttribute(SI).toInt());
            m3ua.setRoutingContext(xml.getAttribute(ROUTING_CONTEXT).toInt());
            m3ua.setNetworkAppearance(xml.getAttribute(NETWORK_APPEARANCE).toInt());
            str = xml.getAttribute(M3UA_FUNCTIONALITY).toString();
            m3ua.setM3uaFunctionality(Functionality.valueOf(str));
            str = xml.getAttribute(M3UA_EXCHANGE_TYPE).toString();
            m3ua.setM3uaExchangeType(ExchangeType.valueOf(str));
            str = xml.getAttribute(M3UA_IPSPType).toString();
            m3ua.setM3uaIPSPType(IPSPType.valueOf(str));

            m3ua.setLocalHost((String) xml.get(LOCAL_HOST, String.class));
            m3ua.setRemoteHost((String) xml.get(REMOTE_HOST, String.class));
            m3ua.setSctpExtraHostAddresses((String) xml.get(EXTRA_HOST_ADDRESSES, String.class));
        }
    };

}
