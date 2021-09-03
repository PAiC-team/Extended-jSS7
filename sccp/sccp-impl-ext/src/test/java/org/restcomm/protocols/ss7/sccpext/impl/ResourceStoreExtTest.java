
package org.restcomm.protocols.ss7.sccpext.impl;

import static org.testng.Assert.assertNotNull;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.restcomm.protocols.ss7.sccp.impl.RemoteSignalingPointCodeImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpResourceImpl;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccpext.impl.SccpExtModuleImpl;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ResourceStoreExtTest {

    @Test
    public void testStoreLoad() throws Exception {

        String name = "ResourceStoreExtTest";
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceImpl();
        SccpExtModuleImpl sccpExtModule1 = new SccpExtModuleImpl();
        ss7ExtInterface.setSs7ExtSccpInterface(sccpExtModule1);
        SccpStackImpl sccpStack = new SccpStackImpl(name, ss7ExtInterface);
        sccpExtModule1.init(sccpStack);

        SccpResourceImpl resource = new SccpResourceImpl(sccpStack.getName(), sccpExtModule1);
        resource.start();
        resource.removeAllResources();

        resource.addRemoteSpc(81, 101, 0, 0);

        RemoteSignalingPointCodeImpl spc = (RemoteSignalingPointCodeImpl) resource.getRemoteSpc(81);
        assertNotNull(spc.getRemoteSignalingPointCodeExt());

        resource.store();

        String fn = generatePath(name, "2");
        String content = new String(Files.readAllBytes(Paths.get(fn)));
        System.out.println(content);

        resource.removeAllResources();
        Files.write(Paths.get(fn), content.getBytes());
        resource.load();

        spc = (RemoteSignalingPointCodeImpl) resource.getRemoteSpc(81);
        assertNotNull(spc.getRemoteSignalingPointCodeExt());
    }

    private String generatePath(String name, String ver) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir")).append(File.separator).append(name).append("_").append("sccpresource")
                .append(ver).append(".xml");
        return sb.toString();
    }

}
