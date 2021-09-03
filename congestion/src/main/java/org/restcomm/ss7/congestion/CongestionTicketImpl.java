package org.restcomm.ss7.congestion;

import java.util.Map.Entry;

import javolution.util.FastMap;

/**
 * The Congestion Ticket interface contains info for the source of congestion and the congestion level
 *
 * @author sergey vetyutnev
 *
 */
public class CongestionTicketImpl implements CongestionTicket {

    private String source;
    private int level;
    private FastMap<String, Object> attributeList;

    public CongestionTicketImpl(String source, int level) {
        this.source = source;
        this.level = level;
    }

    public void setAttribute(String key, Object value) {
        if (attributeList == null)
            attributeList = new FastMap<String, Object>();
        attributeList.put(key, value);
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public Object getAttribute(String key) {
        if (attributeList != null)
            return attributeList.get(key);
        else
            return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CongestionTicket=[source=");
        sb.append(source);
        sb.append(", level=");
        sb.append(level);

        if (attributeList != null) {
            sb.append(", attributes=[");
            int i1 = 0;
            for (Entry<String, Object> el : attributeList.entrySet()) {
                if (i1 == 0)
                    i1 = 1;
                else
                    sb.append(", ");

                sb.append(el.getKey());
                sb.append("=");
                sb.append(el.getValue());
            }
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

}
