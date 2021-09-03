package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPPrivateExtension;

/**
 *
 SLR-ArgExtensionContainer ::= SEQUENCE { privateExtensionList [0]PrivateExtensionList OPTIONAL, slr-Arg-PCS-Extensions
 * [1]SLR-Arg-PCS-Extensions OPTIONAL, ...}
 *
 * PrivateExtensionList ::= SEQUENCE SIZE (1..10) OF PrivateExtension
 *
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface SLRArgExtensionContainer extends Serializable {

    ArrayList<MAPPrivateExtension> getPrivateExtensionList();

    SLRArgPCSExtensions getSlrArgPcsExtensions();

}
