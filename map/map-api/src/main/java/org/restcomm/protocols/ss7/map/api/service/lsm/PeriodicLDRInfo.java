
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 *
 PeriodicLDRInfo ::= SEQUENCE { reportingAmount ReportingAmount, reportingInterval ReportingInterval, ...} --
 * reportingInterval x reportingAmount shall not exceed 8639999 (99 days, 23 hours, -- 59 minutes and 59 seconds) for
 * compatibility with OMA MLP and RLP
 *
 * ReportingAmount ::= INTEGER (1..8639999)
 *
 * ReportingInterval ::= INTEGER (1..8639999) -- ReportingInterval is in seconds
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PeriodicLDRInfo extends Serializable {

    int getReportingAmount();

    int getReportingInterval();

}
