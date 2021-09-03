package org.restcomm.protocols.ss7.tools.simulator.tests.psi;

import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>*
 */
public class PSIReaction extends EnumeratedBase {

  private static final long serialVersionUID = 70L;

  public static final int VAL_RETURN_SUCCESS = 1;
  public static final int VAL_UNEXPECTED_DATA_VALUE = 2;
  public static final int VAL_ERROR_SYSTEM_FAILURE = 3;
  public static final int VAL_DATA_MISSING = 4;
  public static final int VAL_ERROR_UNKNOWN_SUBSCRIBER = 5;


  private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
  private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

  static {
    intMap.put(VAL_RETURN_SUCCESS, "Return success");
    intMap.put(VAL_UNEXPECTED_DATA_VALUE, "Return error unexpected data value");
    intMap.put(VAL_ERROR_SYSTEM_FAILURE, "Return error system failure");
    intMap.put(VAL_DATA_MISSING, "Return error data missing");
    intMap.put(VAL_ERROR_UNKNOWN_SUBSCRIBER, "Return error unknown subscriber");

    stringMap.put("Return success", VAL_RETURN_SUCCESS);
    stringMap.put("Return error unexpected data value", VAL_UNEXPECTED_DATA_VALUE);
    stringMap.put("Return error system failure", VAL_ERROR_SYSTEM_FAILURE);
    stringMap.put("Return error data missing", VAL_DATA_MISSING);
    stringMap.put("Return error unknown subscriber", VAL_ERROR_UNKNOWN_SUBSCRIBER);
  }

  public PSIReaction() {
  }

  public PSIReaction(int val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public PSIReaction(Integer val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public PSIReaction(String val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public static PSIReaction createInstance(String s) {
    Integer instance = doCreateInstance(s, stringMap, intMap);
    if (instance == null)
      return new PSIReaction(VAL_RETURN_SUCCESS);
    else
      return new PSIReaction(instance);
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
