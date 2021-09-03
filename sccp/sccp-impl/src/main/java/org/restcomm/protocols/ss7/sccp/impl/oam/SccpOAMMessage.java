
package org.restcomm.protocols.ss7.sccp.impl.oam;

/**
 * Declares static messages used by RouterImpl
 *
 * @author amit bhayani
 *
 */
public interface SccpOAMMessage {

    String INVALID_COMMAND = "Invalid Command";

    String RULE_ALREADY_EXIST = "Rule already exist";

    String INVALID_MASK = "Invalid Mask";

    String RULE_DOESNT_EXIST = "Rule doesn't exist on stack=%s";

    String RULE_SUCCESSFULLY_ADDED = "Rule successfully added on stack=%s";

    String RULE_SUCCESSFULLY_MODIFIED = "Rule successfully modified on stack=%s";

    String RULE_SUCCESSFULLY_REMOVED = "Rule successfully removed from stack=%s";

    String ADDRESS_ALREADY_EXIST = "Address already exist";

    String ADDRESS_DOESNT_EXIST = "Address doesn't exist on stack=%s";

    String ADDRESS_SUCCESSFULLY_ADDED = "Address successfully added on stack=%s";

    String ADDRESS_SUCCESSFULLY_MODIFIED = "Address successfully modified on stack=%s";

    String ADDRESS_SUCCESSFULLY_DELETED = "Address successfully deleted from stack=%s";

    String SERVER_ERROR = "Server Error";

    String NO_PRIMARY_ADDRESS = "No primary address defined for id=%d";

    String NO_BACKUP_ADDRESS = "No backup address defined for id=%d";

    String RSPC_ALREADY_EXIST = "Remote Signaling Point Code already exists";

    String RSPC_DOESNT_EXIST = "Remote Signaling Point Code doesn't exist on stack=%s";

    String RSPC_SUCCESSFULLY_ADDED = "Remote Signaling Point Code successfully added on stack=%s";

    String RSPC_SUCCESSFULLY_MODIFIED = "Remote Signaling Point Code successfully modified on stack=%s";

    String RSPC_SUCCESSFULLY_DELETED = "Remote Signaling Point Code successfully deleted from stack=%s";

    String RSS_ALREADY_EXIST = "Remote Subsystem number already exists";

    String RSS_DOESNT_EXIST = "Remote Subsystem number doesn't exist on stack=%s";

    String RSS_SUCCESSFULLY_ADDED = "Remote Subsystem number successfully added on stack=%s";

    String RSS_SUCCESSFULLY_MODIFIED = "Remote Subsystem number successfully modified on stack=%s";

    String RSS_SUCCESSFULLY_DELETED = "Remote Subsystem number successfully deleted from stack=%s";

    String LMR_ALREADY_EXIST = "Long message rule already exists";

    String LMR_DOESNT_EXIST = "Long message rule doesn't exist on stack=%s";

    String LMR_SUCCESSFULLY_ADDED = "Long message rule successfully added on stack=%s";

    String LMR_SUCCESSFULLY_MODIFIED = "Long message rule successfully modified on stack=%s";

    String LMR_SUCCESSFULLY_DELETED = "Long message rule successfully deleted from stack=%s";

    String SAP_ALREADY_EXIST = "Service access point already exists";

    String SAP_DOESNT_EXIST = "Service access point doesn't exist on stack=%s";

    String SAP_SUCCESSFULLY_ADDED = "Service access point successfully added on stack=%s";

    String SAP_SUCCESSFULLY_MODIFIED = "Service access point successfully modified on stack=%s";

    String SAP_SUCCESSFULLY_DELETED = "Service access point successfully deleted from sack=%s";

    String MUP_DOESNT_EXIST = "Mtp3UserPart doesn't exist";

    String DEST_ALREADY_EXIST = "Destination definition already exists";

    String DEST_DOESNT_EXIST = "Destination definition doesn't exist on stack=%s";

    String DEST_SUCCESSFULLY_ADDED = "Destination definition successfully added on stack=%s";

    String DEST_SUCCESSFULLY_MODIFIED = "Destination definition successfully modified on stack=%s";

    String DEST_SUCCESSFULLY_DELETED = "Destination definition successfully deleted from stack=%s";

    String CS_ALREADY_EXIST = "Concerned SPC already exists";

    String CS_DOESNT_EXIST = "Concerned SPC doesn't exist on stack=%s";

    String CS_SUCCESSFULLY_ADDED = "Concerned SPC successfully added on stack=%s";

    String CS_SUCCESSFULLY_MODIFIED = "Concerned SPC successfully modified on stack=%s";

    String CS_SUCCESSFULLY_DELETED = "Concerned SPC successfully deleted from stack=%s";

    String PARAMETER_SUCCESSFULLY_SET = "Parameter has been successfully set on stack=%s";

    String RULETYPE_NOT_SOLI_SEC_ADD_MANDATORY = "If RuleType is not Solitary, specifying Secondary Address is mandatory";

    String SEC_MISMATCH_PATTERN = "Number of sections in mask doesn't match with number of sections in pattern GlobalTitle digits";

    String SEC_MISMATCH_PRIMADDRESS = "Number of sections in mask doesn't match with number of sections in primary address GlobalTitle digits";

    String SEC_MISMATCH_SECADDRESS = "Number of sections in mask doesn't match with number of sections in secondary address GlobalTitle digits";

    String NO_SCCP_MANAGEMENT_BEAN_FOR_NAME = "No SCCP management bean found for passed name=%s";

    String NO_SCCP_EXT_MODULE = "No SCCP external module found name=%s";
}
