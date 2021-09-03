package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface Polygon extends Serializable {

    byte[] getData();

    int getNumberOfPoints();

    EllipsoidPoint getEllipsoidPoint(int position);

}
