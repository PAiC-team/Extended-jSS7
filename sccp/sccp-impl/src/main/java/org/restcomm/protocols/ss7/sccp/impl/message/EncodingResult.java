package org.restcomm.protocols.ss7.sccp.impl.message;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum EncodingResult {

    // Success encoding, data available for transfer
    Success,
    // Sccp error, returnCause should be returned or message discarded
    ReturnFailure,
    // Exception: data==null or has zero length
    DataMissed,
    // Exception: data length exceeds maximum possible safe length (2560) or corresponding max data size for used message type
    DataMaxLengthExceeded,
    // Exception: CalledPartyAddress is missed
    CalledPartyAddressMissing,
    // Exception: CallingPartyAddress is missed
    CallingPartyAddressMissing,
    // Exception: ProtocolClass is missed
    ProtocolClassMissing,
    // Exception: SourceLocalReferenceNumber is missing
    SourceLocalReferenceNumberMissing,
    // Exception: MessageTypeMissing
    MessageTypeMissing,
    // Exception: DestinationLocalReferenceNumber is missing
    DestinationLocalReferenceNumberMissing,
    // Exception: ReleaseCause is missing
    ReleaseCauseMissing,
    // Exception: RefusalCause is missing
    RefusalCauseMissing,
    // Exception: SegmentingReassembling is missing
    SegmentingReassemblingMissing,
    // Exception: ReceiveSequenceNumber is missing
    ReceiveSequenceNumberMissing,
    // Exception: Credit is missing
    CreditMissing,
    // Exception: ResetCause is missing
    ResetCauseMissing,
    // Exception: ErrorCause is missing
    ErrorCauseMissing,
    // Exception: SequencingSegmenting is missing
    SequencingSegmentingMissing,
    SegmentationNotSupported
}
