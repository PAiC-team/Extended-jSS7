package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:14:23:10 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface UserToUserIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x2A;

    // FIXME: Add C defs

    /**
     * Service 1,2,3 request : no info
     */
    int _REQ_Sx_NO_INFO = 0;
    /**
     * Service 1,2,3 request : not essential
     */
    int _REQ_Sx_RNE = 2;
    /**
     * Service 1,2,3 request : essential
     */
    int _REQ_Sx_RE = 3;

    /**
     * Service 1,2,3 request : no info
     */
    int _RESP_Sx_NO_INFO = 0;
    /**
     * Service 1,2,3 request : not provided
     */
    int _RESP_Sx_NOT_PROVIDED = 1;

    /**
     * Service 1,2,3 request : provided
     */
    int _RESP_Sx_PROVIDED = 2;

    /**
     * See Q.763 3.60 Network discard indicator : no information
     */
    boolean _NDI_NO_INFO = false;

    /**
     * See Q.763 3.60 Network discard indicator : user-to-user information discarded by the network
     */
    boolean _NDI_UTUIDBTN = true;

    boolean isResponse();

    void setResponse(boolean response);

    int getServiceOne();

    void setServiceOne(int serviceOne);

    int getServiceTwo();

    void setServiceTwo(int serviceTwo);

    int getServiceThree();

    void setServiceThree(int serviceThree);

    boolean isNetworkDiscardIndicator();

    void setNetworkDiscardIndicator(boolean networkDiscardIndicator);

}
