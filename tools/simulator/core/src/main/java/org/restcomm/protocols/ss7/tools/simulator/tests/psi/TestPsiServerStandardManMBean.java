package org.restcomm.protocols.ss7.tools.simulator.tests.psi;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapProtocolVersion;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;
import org.restcomm.protocols.ss7.tools.simulator.tests.sms.SRIReaction;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>*
 */
public class TestPsiServerStandardManMBean extends StandardMBean {

  public TestPsiServerStandardManMBean(TestPsiServerMan impl, Class<TestPsiServerManMBean> intf) throws NotCompliantMBeanException {
    super(impl, intf);
  }

  @Override
  public MBeanInfo getMBeanInfo() {

    MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[]{

        new MBeanAttributeInfo("SRIReaction", SRIReaction.class.getName(), "SRIforSM response type", true, true, false),
        new MBeanAttributeInfo("SRIReaction_value", String.class.getName(), "SRIforSM response type", true, false, false),

        new MBeanAttributeInfo("PSIReaction", PSIReaction.class.getName(), "PSI response type", true, true, false),
        new MBeanAttributeInfo("PSIReaction_value", String.class.getName(), "PSI response type", true, false, false),

        new MBeanAttributeInfo("AddressNature", AddressNatureType.class.getName(),
            "AddressNature parameter for AddressString creating", true, true, false),
        new MBeanAttributeInfo("AddressNature_Value", String.class.getName(),
            "AddressNature parameter for AddressString creating", true, false, false),
        new MBeanAttributeInfo("NumberingPlan", NumberingPlanMapType.class.getName(),
            "NumberingPlan parameter for AddressString creating", true, true, false),
        new MBeanAttributeInfo("NumberingPlan_Value", String.class.getName(),
            "NumberingPlan parameter for AddressString creating", true, false, false),
        new MBeanAttributeInfo("ServiceCenterAddress", String.class.getName(),
            "Origination Service center address string", true, true, false),
        new MBeanAttributeInfo("MapProtocolVersion", MapProtocolVersion.class.getName(), "MAP protocol version", true,
            true, false),
        new MBeanAttributeInfo("MapProtocolVersion_Value", String.class.getName(), "MAP protocol version", true, false,
            false),
        new MBeanAttributeInfo("NetworkNodeNumberAddress", String.class.getName(),
            "NetworkNodeNumber address parameter",
            true, true, false),
        new MBeanAttributeInfo("VMSCAddress", String.class.getName(),
            "VMSC address parameter",
            true, true, false),
        new MBeanAttributeInfo("IMSI", String.class.getName(),
            "IMSI address parameter",
            true, true, false),
        new MBeanAttributeInfo("MSISDN", String.class.getName(),
            "MSISDN address parameter",
            true, true, false),
        new MBeanAttributeInfo("IMEI", String.class.getName(),
            "IMEI address parameter",
            true, true, false),
        new MBeanAttributeInfo("MCC", Integer.class.getName(),
            "MCC parameter for CellId Or SAI",
            true, true, false),
        new MBeanAttributeInfo("MNC", Integer.class.getName(),
            "MNC parameter for CellId Or SAI",
            true, true, false),
        new MBeanAttributeInfo("LAC", Integer.class.getName(),
            "LAC parameter for CellId Or SAI",
            true, true, false),
        new MBeanAttributeInfo("CellId", Integer.class.getName(),
            "CellId parameter for CellId Or SAI",
            true, true, false),
        new MBeanAttributeInfo("AgeOfLocation", Integer.class.getName(),
            "Age of Location Estimate parameter",
            true, true, false),
        new MBeanAttributeInfo("GeographicalLatitude", String.class.getName(),
            "GeographicalLatitude for response",
            true, true, false),
        new MBeanAttributeInfo("GeographicalLongitude", String.class.getName(),
            "GeographicalLatitude for response",
            true, true, false),
        new MBeanAttributeInfo("GeographicalUncertainty", String.class.getName(),
            "GeographicalLatitude for response",
            true, true, false),
        new MBeanAttributeInfo("GeographicalConfidence", String.class.getName(),
            "GeodeticLatitude for response",
            true, true, false),
        new MBeanAttributeInfo("GeodeticLatitude", String.class.getName(),
        "GeodeticLatitude parameter for response",
        true, true, false),
        new MBeanAttributeInfo("GeodeticLongitude", String.class.getName(),
            "GeodeticLatitude parameter for response",
            true, true, false),
        new MBeanAttributeInfo("GeodeticUncertainty", String.class.getName(),
            "GeodeticLatitude parameter for response",
            true, true, false),
        new MBeanAttributeInfo("GeodeticConfidence", String.class.getName(),
            "GeographicalLatitude parameter for response",
            true, true, false),
        new MBeanAttributeInfo("GeodeticConfidence", String.class.getName(),
            "GeographicalLatitude parameter for response",
            true, true, false),
        new MBeanAttributeInfo("ScreeningAndPresentationIndicators", String.class.getName(),
            "ScreeningAndPresentationIndicators parameter for response",
            true, true, false),


    };

    MBeanParameterInfo[] performSRIRequestParam = new MBeanParameterInfo[]{
        new MBeanParameterInfo("addressIMSI", String.class.getName(), "Address for IMSI")};

    MBeanParameterInfo[] signString = new MBeanParameterInfo[]{new MBeanParameterInfo("val", String.class.getName(), "Index number or value")};

    MBeanOperationInfo[] operations = new MBeanOperationInfo[]{

        new MBeanOperationInfo(
            "putAddressNature",
            "AddressNature parameter for AddressString creating: "
                + "0:unknown,1:international_number,2:national_significant_number,3:network_specific_number,4:subscriber_number,5:reserved,6:abbreviated_number,7:reserved_for_extension",
            signString, Void.TYPE.getName(), MBeanOperationInfo.ACTION),

        new MBeanOperationInfo("putNumberingPlanType", "NumberingPlan parameter for AddressString creating: "
            + "0:unknown,1:ISDN,2:spare_2,3:data,4:telex,5:spare_5,6:land_mobile,7:spare_7,8:national,9:private_plan,15:reserved", signString,
            Void.TYPE.getName(), MBeanOperationInfo.ACTION),

        new MBeanOperationInfo("performSendRoutingInfoForSMRequest", "Send Routing Information for SM request"
            + "1:ReturnSuccess, 2:SystemFailure, 3:DataMissing, 4:UnexpectedDataValue, 5:FacilityNotSupported, 6:UnknownSubscriber, 7:teleserviceNotProvisioned , 7:CallBarred , " + "8:AbsentSubscriber", performSRIRequestParam, String.class.getName(), MBeanOperationInfo.ACTION),

        new MBeanOperationInfo("performProvideSubscriberInfoRequest", "Provide Subscriber Location request"
            + "1:DataMissing, 2:UnexpectedDataValue",
            null, String.class.getName(), MBeanOperationInfo.ACTION),

    };

    return new MBeanInfo(TestPsiServerMan.class.getName(), "MapPsiServer test parameters management", attributes, null, operations, null);
  }


}
