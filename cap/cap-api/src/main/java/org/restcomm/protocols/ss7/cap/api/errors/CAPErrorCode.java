
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 * CAP Error codes Carried by ReturnError primitive
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPErrorCode {

    int minimalCodeValue = 0;
    int maximumCodeValue = 51;

    // -- The operation has been canceled.
    int canceled = 0;
    // -- The operation failed to be canceled.
    int cancelFailed = 1;
    // -- The establish temporary connection failed.
    int eTCFailed = 3;
    // -- The caller response was not as expected.
    int improperCallerResponse = 4;
    // -- The Service Logic Program could not be found in the gsmSCF.
    int missingCustomerRecord = 6;
    // -- An expected optional parameter was not received.
    int missingParameter = 7;
    // -- The parameter was not as expected (e.g. missing or out of range).
    int parameterOutOfRange = 8;
    // -- The requested information cannot be found.
    int requestedInfoError = 10;
    // -- The operation could not be completed due to a system failure at the serving physical entity.
    int systemFailure = 11;
    // -- An entity normally capable of the task requested cannot or chooses not to perform the task at
    // -- this time. This includes error situations like congestion and unobtainable address as used in
    // -- e.g. the connect operation.)
    int taskRefused = 12;
    // -- A requested resource is not available at the serving entity.
    int unavailableResource = 13;
    // -- An incorrect sequence of Components was received (e.g. 'DisconnectForwardConnection'
    // -- followed by 'PlayAnnouncement').
    int unexpectedComponentSequence = 14;
    // -- The data value was not as expected (e.g. route number expected but billing number received)
    int unexpectedDataValue = 15;
    // -- A parameter received was not expected.
    int unexpectedParameter = 16;
    // -- Leg not known to the gsmSSF.
    int unknownLegID = 17;
    // -- PDPID not known by the receiving entity.
    int unknownPDPID = 50;
    // -- Call Segment not known to the gsmSSF.
    int unknownCSID = 51;
}
