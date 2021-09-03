package org.restcomm.protocols.ss7.tools.simulator.tests.psi;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.DomainType;
import org.restcomm.protocols.ss7.tools.simulator.common.EnumeratedBase;

import java.util.Hashtable;


public class PsiDomainType extends EnumeratedBase {

  private static final long serialVersionUID = -3235851008369990995L;

  public static final int NO_VALUE = -1;

  private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
  private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

  static {
    intMap.put(NO_VALUE, "No value");
    intMap.put(DomainType.csDomain.getType(), "csDomain");
    intMap.put(DomainType.psDomain.getType(), "psDomain");

    stringMap.put("No value", NO_VALUE);
    stringMap.put("csDomain", DomainType.csDomain.getType());
    stringMap.put("psDomain", DomainType.psDomain.getType());
  }

  public PsiDomainType() {
  }

  public PsiDomainType(int val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public PsiDomainType(Integer val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public PsiDomainType(String val) throws java.lang.IllegalArgumentException {
    super(val);
  }

  public static PsiDomainType createInstance(String s) {
    Integer i1 = doCreateInstance(s, stringMap, intMap);
    if (i1 == null)
      return new PsiDomainType(DomainType.csDomain.getType());
    else
      return new PsiDomainType(i1);
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
