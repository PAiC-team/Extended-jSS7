package org.restcomm.protocols.ss7.m3ua.impl.router;

import javolution.util.FastList;

import org.restcomm.protocols.ss7.m3ua.impl.AsImpl;
import org.restcomm.protocols.ss7.m3ua.parameter.OPCList;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingKey;
import org.restcomm.protocols.ss7.m3ua.parameter.ServiceIndicators;

/**
 * <p>
 * All the incoming MTP3 traffic is routed as per the rules defined in M3UARouter. The routing decision is based on passed dpc,
 * opc and si from the MTP3 Signaling Information field (SIF) and Service Information Octet (SIO)
 * </p>
 * <p>
 * The {@link ServerM3UARouter} act as tree where each {@link DPCNode} act as parent node containing {@link OPCNode} as leafs.
 * Each {@link OPCNode} further contains {@link SINode} as leafs. The {@link SINode} contains the reference to corresponding
 * {@link AsImpl}.
 * </p>
 *
 * @author amit bhayani
 *
 */
public class ServerM3UARouter {

    private FastList<DPCNode> dpcList = new FastList<DPCNode>();

    public ServerM3UARouter() {
    }

    /**
     * <p>
     * Create a Tree structure adding the reference to passed As. The Tree is created starting from {@link DPCNode} as parent
     * node containing 'n' {@link OPCNode} leafs where 'n' is list of OPC passed. For each {@link OPCNode} there will be 'm'
     * {@link SINode} leafs where 'm' is number of Service Indicator passed.
     * </p>
     *
     * <p>
     * DPC is mandatory while OPC and SI list are optional. If OPC or SI is not passed the wild card '-1' leaf will be added to
     * parent node
     * </p>
     *
     * @param routingKey
     * @param asImpl
     * @throws Exception
     */
    public void addRk(RoutingKey routingKey, AsImpl asImpl) throws Exception {
        int dpc = routingKey.getDestinationPointCodes()[0].getPointCode();
        OPCList[] opcArray = routingKey.getOPCLists();

        int[] opcIntArr = null;
        if (opcArray == null) {
            opcIntArr = new int[] { -1 };
        } else {
            opcIntArr = opcArray[0].getPointCodes();
        }

        ServiceIndicators[] siArray = routingKey.getServiceIndicators();
        short[] siShortArr;
        if (siArray == null) {
            siShortArr = new short[] { -1 };
        } else {
            siShortArr = siArray[0].getIndicators();
        }

        for (FastList.Node<DPCNode> n = dpcList.head(), end = dpcList.tail(); (n = n.getNext()) != end;) {
            DPCNode dpcNode = n.getValue();
            if (dpcNode.dpc == dpc) {
                this.addSi(dpcNode, opcIntArr, siShortArr, asImpl);
                return;
            }
        }

        DPCNode dpcNode = new DPCNode(dpc);
        this.addSi(dpcNode, opcIntArr, siShortArr, asImpl);
        this.dpcList.add(dpcNode);

    }

    /**
     * <p>
     * Match the passed dpc, opc and si with Tree structure and return the As from corresponding matched {@link SINode}.
     * </p>
     *
     * <p>
     * For example if AS1 is added for Routing Key with DPC=123 only. The tree will be formed with 123 as {@link DPCNode} parent
     * node and -1 as {@link OPCNode} leaf and within {@link OPCNode} -1 as {@link SINode}
     * </p>
     *
     * @param dpc
     * @param opc
     * @param si
     * @return
     */
    public AsImpl getAs(int dpc, int opc, short si) {
        for (FastList.Node<DPCNode> n = dpcList.head(), end = dpcList.tail(); (n = n.getNext()) != end;) {
            DPCNode dpcNode = n.getValue();
            if (dpcNode.dpc == dpc) {
                return dpcNode.getAs(opc, si);
            }
        }
        return null;
    }

    private void addSi(DPCNode dpcNode, int[] opcIntArr, short[] siShortArr, AsImpl asImpl) throws Exception {
        for (int i = 0; i < opcIntArr.length; i++) {
            for (int j = 0; j < siShortArr.length; j++) {
                dpcNode.addSi(opcIntArr[i], siShortArr[j], asImpl);
            }
        }
    }
}
