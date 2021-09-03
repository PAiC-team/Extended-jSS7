
package org.restcomm.ss7;

/**
 *
 * @author kulikov
 */
public interface SS7ServiceMBean {

    String ONAME = "org.restcomm.ss7:service=SS7Service";

    void start() throws Exception;

    void stop();

    /**
     * Returns SCCP Provider jndi name.
     */
    String getJndiName();

    /**
     * Returns SS7 Name for this release
     *
     * @return
     */
    String getSS7Name();

    /**
     * Get Vendor supporting this SS7
     *
     * @return
     */
    String getSS7Vendor();

    /**
     * Return SS7 version of this release
     *
     * @return
     */
    String getSS7Version();

    String getSS7ServiceName();

    boolean isStarted();

    Object getStack();

    void setStack(Object stack);

}
