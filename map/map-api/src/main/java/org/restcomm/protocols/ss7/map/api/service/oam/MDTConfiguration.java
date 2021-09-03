
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
MDT-Configuration ::= SEQUENCE {
  jobType              JobType,
  areaScope            AreaScope OPTIONAL,
  listOfMeasurements   ListOfMeasurements OPTIONAL,
  reportingTrigger     [0] ReportingTrigger OPTIONAL,
  reportInterval       ReportInterval OPTIONAL,
  reportAmount         [1] ReportAmount OPTIONAL,
  eventThresholdRSRP   EventThresholdRSRP OPTIONAL,
  eventThresholdRSRQ   [2] EventThresholdRSRQ OPTIONAL,
  loggingInterval      [3] LoggingInterval OPTIONAL,
  loggingDuration      [4] LoggingDuration OPTIONAL,
  extensionContainer   [5] ExtensionContainer OPTIONAL,
  ...
}

EventThresholdRSRP ::= INTEGER (0..97)
EventThresholdRSRQ ::= INTEGER (0..34)
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface MDTConfiguration extends Serializable {

    JobType getJobType();

    AreaScope getAreaScope();

    ListOfMeasurements getListOfMeasurements();

    ReportingTrigger getReportingTrigger();

    ReportInterval getReportInterval();

    ReportAmount getReportAmount();

    Integer getEventThresholdRSRP();

    Integer getEventThresholdRSRQ();

    LoggingInterval getLoggingInterval();

    LoggingDuration getLoggingDuration();

    MAPExtensionContainer getExtensionContainer();

}
