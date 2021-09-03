package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TypeOfShapeEnumerated extends EnumeratedBase {

  private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
  private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

  static {
    intMap.put(TypeOfShape.EllipsoidPoint.getCode(), TypeOfShape.EllipsoidPoint.toString());
    intMap.put(TypeOfShape.EllipsoidPointWithUncertaintyCircle.getCode(), TypeOfShape.EllipsoidPointWithUncertaintyCircle.toString());
    intMap.put(TypeOfShape.EllipsoidPointWithUncertaintyEllipse.getCode(), TypeOfShape.EllipsoidPointWithUncertaintyEllipse.toString());
    intMap.put(TypeOfShape.Polygon.getCode(), TypeOfShape.Polygon.toString());
    intMap.put(TypeOfShape.EllipsoidPointWithAltitude.getCode(), TypeOfShape.EllipsoidPointWithAltitude.toString());
    intMap.put(TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid.getCode(), TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid.toString());
    intMap.put(TypeOfShape.EllipsoidArc.getCode(), TypeOfShape.EllipsoidArc.toString());

    stringMap.put(TypeOfShape.EllipsoidPoint.toString(), TypeOfShape.EllipsoidPoint.getCode());
    stringMap.put(TypeOfShape.EllipsoidPointWithUncertaintyCircle.toString(), TypeOfShape.EllipsoidPointWithUncertaintyCircle.getCode());
    stringMap.put(TypeOfShape.EllipsoidPointWithUncertaintyEllipse.toString(), TypeOfShape.EllipsoidPointWithUncertaintyEllipse.getCode());
    stringMap.put(TypeOfShape.Polygon.toString(), TypeOfShape.Polygon.getCode());
    stringMap.put(TypeOfShape.EllipsoidPointWithAltitude.toString(), TypeOfShape.EllipsoidPointWithAltitude.getCode());
    stringMap.put(TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid.toString(), TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid.getCode());
    stringMap.put(TypeOfShape.EllipsoidArc.toString(), TypeOfShape.EllipsoidArc.getCode());
  }

  public TypeOfShapeEnumerated() {
  }

  public TypeOfShapeEnumerated(int val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public TypeOfShapeEnumerated(Integer val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public TypeOfShapeEnumerated(String val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public static org.restcomm.protocols.ss7.tools.simulator.tests.psi.TypeOfShapeEnumerated createInstance(String s) {
    Integer instance = doCreateInstance(s, stringMap, intMap);
    if (instance == null)
      return new org.restcomm.protocols.ss7.tools.simulator.tests.psi.TypeOfShapeEnumerated(TypeOfShape.EllipsoidPoint.getCode());
    else
      return new org.restcomm.protocols.ss7.tools.simulator.tests.psi.TypeOfShapeEnumerated(instance);
  }

  @Override
  protected Hashtable<Integer, String> getIntTable() {
    return intMap;
  }

  @Override
  protected Hashtable<String, Integer> getStringTable() {
    return stringMap;
  }


}
