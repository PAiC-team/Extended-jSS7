
package org.restcomm.protocols.ss7.sccp.impl.router;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.restcomm.protocols.ss7.sccp.LongMessageRule;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.Mtp3Destination;
import org.restcomm.protocols.ss7.sccp.Mtp3ServiceAccessPoint;
import org.restcomm.protocols.ss7.sccp.impl.Mtp3UserPartImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class RouterStoreTest {

    @Test
    public void testVer4() throws Exception {
        String name = "RouterStoreTest";
        SccpStackImpl sccpStack = new SccpStackImpl(name, null);
        RouterImpl router = new RouterImpl(name, sccpStack);

        router.start();
        router.removeAllResources();

        Mtp3UserPartImpl mtp3UserPart11 = new Mtp3UserPartImpl(null);
        sccpStack.setMtp3UserPart(2, mtp3UserPart11);
        router.addMtp3ServiceAccessPoint(1, 2, 11, 3, 4, "44445555");
        // router.addMtp3ServiceAccessPoint(id, mtp3Id, opc, ni, networkId, localGtDigits);

        router.addMtp3Destination(1, 2, 101, 102, 0, 15, 255);
        // router.addMtp3Destination(sapId, destId, firstDpc, lastDpc, firstSls, lastSls, slsMask);

        router.addLongMessageRule(5, 201, 202, LongMessageRuleType.XUDT_ENABLED);
        // router.addLongMessageRule(id, firstSpc, lastSpc, ruleType);

        router.store();

        String fn = generatePath(name, "3");
        String content = new String(Files.readAllBytes(Paths.get(fn)));
        System.out.println(content);

        router.removeAllResources();
        Files.write(Paths.get(fn), content.getBytes());
        router.load();


        LongMessageRule lmr = router.getLongMessageRule(5);
        assertEquals(lmr.getFirstSpc(), 201);
        assertEquals(lmr.getLongMessageRuleType(), LongMessageRuleType.XUDT_ENABLED);

        Mtp3ServiceAccessPoint sap = router.getMtp3ServiceAccessPoint(1);
        assertEquals(sap.getOpc(), 11);
        Mtp3Destination dest = sap.getMtp3Destination(2);
        assertEquals(dest.getLastDpc(), 102);
    }

    @Test
    public void testVer3() throws Exception {
        String name = "RouterStoreTest";
        SccpStackImpl sccpStack = new SccpStackImpl(name, null);
        RouterImpl router = new RouterImpl(name, sccpStack);

        router.start();
        router.removeAllResources();

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
        sb.append("        <gt type=\"GT0100\" tt=\"0\" es=\"2\" np=\"1\" nai=\"4\" digits=\"000.\"/>\n");
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
        String fn3 = generatePath(name, "3");

        File f3 = new File(fn3);
        f3.delete();
        Files.write(Paths.get(fn2), content.getBytes(), StandardOpenOption.CREATE_NEW);

        router.load();

        Mtp3ServiceAccessPoint sap = router.getMtp3ServiceAccessPoint(1);
        assertEquals(sap.getOpc(), 11);
        Mtp3Destination dest = sap.getMtp3Destination(2);
        assertEquals(dest.getLastDpc(), 102);
    }

    private String generatePath(String name, String ver) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir")).append(File.separator).append(name).append("_").append("sccprouter")
                .append(ver).append(".xml");
        return sb.toString();
    }

}
