package org.restcomm.protocols.ss7.m3ua.impl;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class M3UAErrorManagementStateTest {
    @Test
    public void should_return_one_if_error_is_not_configured() {
        // clear configuration
        M3UAErrorManagementState.getInstance().clearErrorRetryActions();

        int retValue = M3UAErrorManagementState.getInstance().getErrorCount(11);

        assertEquals(retValue, 1);
    }

    @Test
    public void should_return_one_if_retry_is_set_to_minus_one() {
        // setup -
        ErrorRetryActionImpl testRetryAction = new ErrorRetryActionImpl(25, "invalidRoutingContext", -1);
        // clear configuration
        M3UAErrorManagementState.getInstance().clearErrorRetryActions();
        // add test
        M3UAErrorManagementState.getInstance().addErrorAction(testRetryAction);

        int retValue = M3UAErrorManagementState.getInstance().getErrorCount(25);

        assertEquals(retValue, 1);
    }

    @Test
    public void should_return_zero_when_retry_is_configured_to_zero() {
        // setup -
        ErrorRetryActionImpl testRetryAction = new ErrorRetryActionImpl(25, "invalidRoutingContext", 0);
        // clear configuration
        M3UAErrorManagementState.getInstance().clearErrorRetryActions();
        // add test
        M3UAErrorManagementState.getInstance().addErrorAction(testRetryAction);

        int retValue = M3UAErrorManagementState.getInstance().getErrorCount(25);

        assertEquals(retValue, 0);
    }

    @Test
    public void should_return_zero_after_five_retry() {

        ErrorRetryActionImpl testRetryAction = new ErrorRetryActionImpl(25, "invalidRoutingContext", 5);
        // clear configuration
        M3UAErrorManagementState.getInstance().clearErrorRetryActions();
        // add test
        M3UAErrorManagementState.getInstance().addErrorAction(testRetryAction);

        int expectedValue = 0;
        int retValue = 99999;
        int count = 5;
        while (count-- > 0) {
            retValue = M3UAErrorManagementState.getInstance().getErrorCount(25);
        }
        assertEquals(retValue, expectedValue);
    }

    @Test
    public void should_return_4_after_completing_full_cycle_and_starting_a_new_cycle_when_retry_is_5() {

        ErrorRetryActionImpl testRetryAction = new ErrorRetryActionImpl(25, "invalidRoutingContext", 5);
        // clear configuration
        M3UAErrorManagementState.getInstance().clearErrorRetryActions();
        // add test
        M3UAErrorManagementState.getInstance().addErrorAction(testRetryAction);

        int expectedValue = 4;
        int retValue = 99999;
        int count = 11; // the 6th one will cause a restart of the association
        // this is required to ensure that if the retry is zero at least one error should happen before restart
        while (count-- > 0) {
            retValue = M3UAErrorManagementState.getInstance().getErrorCount(25);
        }
        assertEquals(retValue, expectedValue);
        count = 5; // 11 + 5 => 16
        while (count-- > 0) {
            retValue = M3UAErrorManagementState.getInstance().getErrorCount(25);
        }
        assertEquals(retValue, expectedValue); // start counting from 4, 3, 2, 1, 0 (5 count)
    }

    @Test
    public void should_return_0_when_retry_count_is_1() {
        ErrorRetryActionImpl testRetryAction = new ErrorRetryActionImpl(25, "invalidRoutingContext", 1);
        // clear configuration
        M3UAErrorManagementState.getInstance().clearErrorRetryActions();
        // add test
        M3UAErrorManagementState.getInstance().addErrorAction(testRetryAction);

        int retValue = M3UAErrorManagementState.getInstance().getErrorCount(25);
        assertEquals(retValue, 0);
    }
}
