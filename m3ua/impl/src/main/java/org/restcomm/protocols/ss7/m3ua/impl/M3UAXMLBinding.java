
package org.restcomm.protocols.ss7.m3ua.impl;

import java.util.Iterator;
import java.util.Map;

import javolution.xml.XMLBinding;
import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

/**
 *
 * @author amit bhayani
 *
 */
public class M3UAXMLBinding extends XMLBinding {

    private M3UAManagementImpl m3uaManagement = null;

    public void setM3uaManagement(M3UAManagementImpl m3uaManagement) {
        this.m3uaManagement = m3uaManagement;
    }

    protected XMLFormat getFormat(Class forClass) throws XMLStreamException {
        if (RouteMap.class.equals(forClass)) {
            return ROUTEMAP;
        }
        return super.getFormat(forClass);
    }

    protected final XMLFormat<RouteMap> ROUTEMAP = new XMLFormat<RouteMap>(RouteMap.class) {

        @Override
        public void write(RouteMap obj, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {

            final Map map = (Map) obj;
            for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                RouteAsImpl asList = (RouteAsImpl) entry.getValue();

                if (asList == null) {
                    continue;
                }

                xml.add((String) entry.getKey(), "key", String.class);
                xml.add(asList, "routeAs", RouteAsImpl.class);
            }
        }

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, RouteMap obj) throws XMLStreamException {
            while (xml.hasNext()) {
                String key = xml.get("key", String.class);
                RouteAsImpl value = xml.get("routeAs", RouteAsImpl.class);
                obj.put(key, value);
            }// while
        }

    };
}
