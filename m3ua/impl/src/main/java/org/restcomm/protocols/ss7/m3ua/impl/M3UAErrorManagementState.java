package org.restcomm.protocols.ss7.m3ua.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.ErrorRetryAction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author <a href="jarmex@gmail.com"> James Amo </a>
 */
public class M3UAErrorManagementState {

    private static final Logger logger = Logger.getLogger(M3UAErrorManagementState.class);
    private static M3UAErrorManagementState sInstance;
    // errorCode, action
    private final Map<Integer, ErrorRetryAction> eMap = new ConcurrentHashMap<>();
    // errorCode, count
    private final Map<Integer, Integer> errorCount = new ConcurrentHashMap<>();
    private String pathName;

    private M3UAErrorManagementState() {
        //
    }

    public void addErrorAction(ErrorRetryActionImpl errorAction) {
        this.eMap.put(errorAction.getErrorCode(), errorAction);
        // add to the error counter
        if (errorAction.getRetryCount() >= 0) {
            this.errorCount.put(errorAction.getErrorCode(), errorAction.getRetryCount());
        }
        this.saveConfiguration();
    }

    public void clearErrorRetryActions() {
        this.eMap.clear();
        this.errorCount.clear();
    }

    public void removeErrorAction(ErrorRetryAction errorAction) {
        this.eMap.remove(errorAction.getErrorCode());
        this.errorCount.remove(errorAction.getErrorCode());
        this.saveConfiguration();
    }

    public List<ErrorRetryAction> getErrorRetry()  {
       return new ArrayList<>(this.eMap.values());
    }

    public static M3UAErrorManagementState getInstance() {
        if (sInstance == null) {
            sInstance = new M3UAErrorManagementState();
        }
        return sInstance;
    }

    private void saveConfiguration() {
        try {
            // OutputStream newFile = new FileOutputStream(this.pathName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            // root element
            Element errorManagement = document.createElement("errorManagement");
            document.appendChild(errorManagement);
            for (ErrorRetryAction eAction: getErrorRetry()){
                Element errorDoc = document.createElement("error");
                errorDoc.setAttribute("name", eAction.getName());
                errorDoc.setAttribute("code", String.valueOf(eAction.getErrorCode()));
                errorDoc.setAttribute("retry", String.valueOf(eAction.getRetryCount()));
                errorManagement.appendChild(errorDoc);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(this.pathName));
            transformer.transform(source, result);
        } catch (Exception e) {
            logger.error("Error writing file", e);
        }
    }

    public void loadConfig(String configPath) {
        // load configuration here
        try {
            // 1. get the file name for the configuration and load
            File configFile = new File(configPath);
            // get the directory
            String directoryName = Paths.get(configFile.getCanonicalPath()).getParent().toString();
            // new path
            Path errorConfigFile = Paths.get(directoryName, "M3ua_ManagementMessageHandler.xml");
            this.pathName = errorConfigFile.toString();
            // check if the file exist then read it else return
            if (Files.exists(errorConfigFile)) {
                // read the file
                readErrorConfigFile(errorConfigFile.toFile());
            } else {
                saveConfiguration();
            }
        } catch (Exception e) {
            logger.error("Error loading configuration for error management. Error: ", e);
        }
    }

    private void readErrorConfigFile(File filename) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(filename);
            doc.getDocumentElement().normalize();
            if (!doc.getDocumentElement().getTagName().equalsIgnoreCase("errorManagement")) {
                return;
            }
            NodeList getErrorConfigList = doc.getElementsByTagName("error");
            for (int i = 0; i < getErrorConfigList.getLength(); i++) {
                Node node = getErrorConfigList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    readXmlTag(node);
                }
            }
        } catch (Exception e) {
            logger.error("Error reading configuration file. Error: ", e);
        }
    }

    private void readXmlTag(Node node) {
        try {
            Element eElement = (Element) node;
            int errorCode = Integer.parseInt(eElement.getAttribute("code"));
            String name = eElement.getAttribute("name");
            int retryCount = Integer.parseInt(eElement.getAttribute("retry"));
            ErrorRetryActionImpl eAction = new ErrorRetryActionImpl(errorCode, name, retryCount);
            this.eMap.put(errorCode, eAction);
        } catch (Exception e) {
            // no action
        }
    }


    public int getErrorCount(int errorCode) {
        Integer currentValue = this.errorCount.get(errorCode);
        if (currentValue == null || currentValue < 1) {
            ErrorRetryAction curAction = this.eMap.get(errorCode);
            if (curAction == null || curAction.getRetryCount() < 0) {
                return 1;
            }
            if (curAction.getRetryCount() == 0){
                return 0;
            }
            currentValue = curAction.getRetryCount();
        }
        currentValue -= 1;
        this.errorCount.put(errorCode, currentValue);
        return currentValue;
    }
}
