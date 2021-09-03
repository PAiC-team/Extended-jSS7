
package org.restcomm.protocols.ss7.sccpext.impl.router;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.sccp.LoadSharingAlgorithm;
import org.restcomm.protocols.ss7.sccp.Mtp3Destination;
import org.restcomm.protocols.ss7.sccp.Mtp3ServiceAccessPoint;
import org.restcomm.protocols.ss7.sccp.OriginationType;
import org.restcomm.protocols.ss7.sccp.Rule;
import org.restcomm.protocols.ss7.sccp.RuleType;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.sccp.impl.router.RouterImpl;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccpext.impl.router.RouterExtImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class RouterStoreExtTest {

    ParameterFactory factory = new ParameterFactoryImpl();

    @Test
    public void testVer4() throws Exception {
        String name = "RouterStoreExtTest";
        SccpStackImpl sccpStack = new SccpStackImpl(name, null);
        RouterImpl router = new RouterImpl(name, sccpStack);
        RouterExtImpl routerExt = new RouterExtImpl(name, sccpStack, router);

        routerExt.start();
        routerExt.removeAllResources();

        SccpAddress primaryAddress1 = factory.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                factory.createGlobalTitle("123456789", 1), 123, 0);
        routerExt.addRoutingAddress(1, primaryAddress1);

        SccpAddress pattern = factory.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE,
                factory.createGlobalTitle("*", 1), 123, 0);
        routerExt.addRule(11, RuleType.SOLITARY, LoadSharingAlgorithm.Bit0, OriginationType.LOCAL, pattern, "K", 1, -1, null,
                3, null);
        // routerExt.addRule(id, ruleType, algo, originationType, pattern, mask, pAddressId, sAddressId,
        // newCallingPartyAddressAddressId, networkId, patternCallingAddress);

        routerExt.store();

        String fn = generatePath(name, "3_ext");
        String content = new String(Files.readAllBytes(Paths.get(fn)));
        System.out.println(content);

        routerExt.removeAllResources();
        Files.write(Paths.get(fn), content.getBytes());
        routerExt.load();


        SccpAddress addr = routerExt.getRoutingAddress(1);
        assertEquals(addr.getGlobalTitle().getDigits(), "123456789");

        Rule rule = routerExt.getRule(11);
        assertEquals(rule.getPrimaryAddressId(), 1);
        assertEquals(rule.getMask(), "K");
    }

    @Test
    public void testVer3() throws Exception {
        String name = "RouterStoreExtTest";
        SccpStackImpl sccpStack = new SccpStackImpl(name, null);
        RouterImpl router = new RouterImpl(name, sccpStack);
        RouterExtImpl routerExt = new RouterExtImpl(name, sccpStack, router);

        router.start();
        router.removeAllResources();

        routerExt.start();
        routerExt.removeAllResources();

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append("<rule>\n");
        sb.append("  <id value=\"3\"/>\n");
        sb.append("  <value ruleType=\"Solitary\" loadSharingAlgo=\"Undefined\" originatingType=\"LocalOriginated\" mask=\"K\" paddress=\"1\" saddress=\"-1\" networkId=\"11\">\n");
        sb.append("        <patternSccpAddress pc=\"0\" ssn=\"8\">\n");
        sb.append("            <ai value=\"82\"/>\n");
        sb.append("            <gt type=\"GT0100\" tt=\"0\" es=\"2\" np=\"1\" nai=\"4\" digits=\"888888\"/>\n");
        sb.append("        </patternSccpAddress>\n");
        sb.append("    </value>\n");
        sb.append("    <id value=\"1\"/>\n");
        sb.append("</rule>\n");
        sb.append("<routingAddress>\n");
        sb.append("    <id value=\"1\"/>\n");
        sb.append("    <sccpAddress pc=\"1\" ssn=\"8\">\n");
        sb.append("        <ai value=\"83\"/>\n");
        sb.append("        <gt type=\"GT0100\" tt=\"0\" es=\"2\" np=\"1\" nai=\"4\" digits=\"000\"/>\n");
        sb.append("    </sccpAddress>\n");
        sb.append("</routingAddress>\n");
        sb.append("<longMessageRule/>\n");
        sb.append("<sap>\n");
        sb.append("    <id value=\"1\"/>\n");
        sb.append("    <value mtp3Id=\"1\" opc=\"11\" ni=\"2\" networkId=\"11\">\n");
        sb.append("        <mtp3DestinationMap>\n");
        sb.append("            <id value=\"2\"/>\n");
        sb.append("            <value firstDpc=\"1\" lastDpc=\"102\" firstSls=\"0\" lastSls=\"255\" slsMask=\"255\"/>\n");
        sb.append("        </mtp3DestinationMap>\n");
        sb.append("    </value>\n");
        sb.append("</sap>;\n");
        String content = sb.toString();

        String fn2 = generatePath(name, "2");
        String fn3 = generatePath(name, "3_ext");
        String fn4 = generatePath(name, "3");

        File f3 = new File(fn3);
        f3.delete();
        File f4 = new File(fn4);
        f4.delete();
        Files.write(Paths.get(fn2), content.getBytes(), StandardOpenOption.CREATE);

        // loading of old format, we need to copy a file firstly
        RouterExtImpl.makeOldConfigCopy(routerExt.getPersistDir(), routerExt.getName());
        router.load();
        routerExt.load();

        Mtp3ServiceAccessPoint sap = router.getMtp3ServiceAccessPoint(1);
        assertEquals(sap.getOpc(), 11);
        Mtp3Destination dest = sap.getMtp3Destination(2);
        assertEquals(dest.getLastDpc(), 102);

        SccpAddress addr = routerExt.getRoutingAddress(1);
        assertEquals(addr.getGlobalTitle().getDigits(), "000");

        Rule rule = routerExt.getRule(3);
        assertEquals(rule.getPrimaryAddressId(), 1);
        assertEquals(rule.getPattern().getSubsystemNumber(), 8);
        assertEquals(rule.getMask(), "K");
    }

    private String generatePath(String name, String ver) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir")).append(File.separator).append(name).append("_").append("sccprouter")
                .append(ver).append(".xml");
        return sb.toString();
    }

}
