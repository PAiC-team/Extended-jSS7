
package org.restcomm.protocols.ss7.sccpext.impl.router;

import javolution.util.FastMap;
import javolution.xml.XMLBinding;
import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.sccp.impl.parameter.SccpAddressImpl;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 * @author amit bhayani
 *
 */
public class SccpRouterXMLBindingExt extends XMLBinding {

    /**
     *
     */
    public SccpRouterXMLBindingExt() {
        // TODO Auto-generated constructor stub
    }

    protected XMLFormat getFormat(Class forClass) throws XMLStreamException {
        if (SccpAddressMap.class.equals(forClass)) {
            return SCCPADDRESSMAP;
        }

        if (RuleMap.class.equals(forClass)) {
            return RULEMAP;
        }

        return super.getFormat(forClass);
    }

    protected final XMLFormat<RuleMap> RULEMAP = new XMLFormat<RuleMap>(RuleMap.class) {

        @Override
        public void write(RuleMap obj, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            for (FastMap.Entry<Integer, RuleImpl> e = obj.head(), end = obj.tail(); (e = e.getNext()) != end;) {
                Integer id = e.getKey();
                RuleImpl rule = e.getValue();

                xml.add(id, "id", Integer.class);
                xml.add(rule, "value", RuleImpl.class);
            }
        }

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, RuleMap obj) throws XMLStreamException {
            while (xml.hasNext()) {
                Integer id = xml.get("id", Integer.class);
                RuleImpl rule = xml.get("value", RuleImpl.class);
                obj.put(id, rule);
            }
        }

    };

    protected final XMLFormat<SccpAddressMap> SCCPADDRESSMAP = new XMLFormat<SccpAddressMap>(SccpAddressMap.class) {

        @Override
        public void write(SccpAddressMap obj, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            for (FastMap.Entry<Integer, SccpAddress> e = obj.head(), end = obj.tail(); (e = e.getNext()) != end;) {
                Integer id = e.getKey();
                SccpAddress sccpAddress = e.getValue();

                xml.add(id, "id", Integer.class);
                xml.add((SccpAddressImpl)sccpAddress, "sccpAddress", SccpAddressImpl.class);
            }
        }

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, SccpAddressMap obj) throws XMLStreamException {
            while (xml.hasNext()) {
                Integer id = xml.get("id", Integer.class);
                SccpAddress sccpAddress = xml.get("sccpAddress", SccpAddressImpl.class);

                obj.put(id, sccpAddress);
            }
        }

    };

}
