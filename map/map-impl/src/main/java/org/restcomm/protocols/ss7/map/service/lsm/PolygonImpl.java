package org.restcomm.protocols.ss7.map.service.lsm;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.service.lsm.EllipsoidPoint;
import org.restcomm.protocols.ss7.map.api.service.lsm.Polygon;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeographicalInformationImpl;

import jakarta.xml.bind.DatatypeConverter;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class PolygonImpl extends OctetStringBase implements Polygon {

    private static final String DEFAULT_VALUE = null;
    private static final String DATA = "data";

    public PolygonImpl() {
        super(19, 91, "Polygon");
    }

    public PolygonImpl(byte[] data) {
        super(19, 91, "Polygon", data);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(EllipsoidPoint[] polygon) throws MAPException {
        if (polygon == null || polygon.length < 3 || polygon.length > 15)
            throw new MAPException("Wrong number of points");

        this.data = new byte[1 + (polygon.length * 6)];

        this.data[0] = 0x50;
        this.data[0] |= (polygon.length & 0x0F);

        for (int position = 0; position < polygon.length; position++) {
            GeographicalInformationImpl.encodeLatitude(this.data, 1 + 2 * position * 3, polygon[position].getLatitude());
            GeographicalInformationImpl.encodeLongitude(this.data, 1 + 2 * position * 3 + 3, polygon[position].getLongitude());
        }
    }

    public int getNumberOfPoints() {
        int numberOfPoints = data[0] & 0x0F;
        return numberOfPoints;
    }

    @Override
    public EllipsoidPoint getEllipsoidPoint(int position) {
        EllipsoidPoint ellipsoidPoint = null;
        int numberOfPoints = data[0] & 0x0F;

        if (position < numberOfPoints) {

            double latitude = GeographicalInformationImpl.decodeLatitude(this.data, 1 + 2 * position * 3);
            double longitude = GeographicalInformationImpl.decodeLongitude(this.data, 1 + 2 * position * 3 + 3);

            ellipsoidPoint = new EllipsoidPoint(latitude, longitude);
        }

        return ellipsoidPoint;
    }

    @Override
    public String toString() {
        int numberOfPoints = getNumberOfPoints();

        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [NumberOfPoints=");
        sb.append(numberOfPoints);
        for (int position = 0; position < numberOfPoints; position++) {
            EllipsoidPoint ellipsoidPoint = getEllipsoidPoint(position);
            sb.append(String.format(", Point%d_lat=%f, ", position, ellipsoidPoint.getLatitude()));
            sb.append(String.format("Point%d_lat=%f", position, ellipsoidPoint.getLongitude()));
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<PolygonImpl> POLYGON_XML = new XMLFormat<PolygonImpl>(PolygonImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, PolygonImpl polygon) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                polygon.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(PolygonImpl polygon, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (polygon.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(polygon.data));
            }
        }
    };
}
