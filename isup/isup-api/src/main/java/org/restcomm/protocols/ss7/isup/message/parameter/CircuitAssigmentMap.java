package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:17:32 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CircuitAssigmentMap extends ISUPParameter {
    int _PARAMETER_CODE = 0x25;

    /**
     * See Q.763 3.69 Map type : 1544 kbit/s digital path map format (64 kbit/s base rate)
     */
    int _MAP_TYPE_1544 = 1;

    /**
     * See Q.763 3.69 Map type : 2048 kbit/s digital path map format (64 kbit/s base rate)
     */
    int _MAP_TYPE_2048 = 2;

    int getMapType();

    void setMapType(int mapType);

    int getMapFormat();

    void setMapFormat(int mapFormat);

    /**
     * Enables circuit
     *
     * @param circuitNumber - index of circuit - must be number <1,31>
     * @throws IllegalArgumentException - when number is not in range
     */
    void enableCircuit(int circuitNumber) throws IllegalArgumentException;

    /**
     * Disables circuit
     *
     * @param circuitNumber - index of circuit - must be number <1,31>
     * @throws IllegalArgumentException - when number is not in range
     */
    void disableCircuit(int circuitNumber) throws IllegalArgumentException;

    boolean isCircuitEnabled(int circuitNumber) throws IllegalArgumentException;

}
