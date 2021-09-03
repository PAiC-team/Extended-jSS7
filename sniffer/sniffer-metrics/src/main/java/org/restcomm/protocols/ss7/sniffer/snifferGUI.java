package org.restcomm.protocols.ss7.sniffer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class snifferGUI
 */
@WebServlet("/monitor")
public class snifferGUI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public snifferGUI() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String document = getLogFile();
        final File file = new File(document);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        if (!file.exists()) {

            out.println("<html><body><h1>File" + file.getPath() + " not found</h1></body></html>");

        } else if (!file.canRead() || file.isDirectory()) {

            out.println("<html><body><h1>Access denied</h1></body></html>");

        } else {
            //Read Complete file
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line, data = "";
            while ((line = br.readLine()) != null) {
                data = data + line + "\n";
            }
            br.close();
            fr.close();
            out.print(data);
        }
        //response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    private String getLogFile() {
        String base = System.getProperty("jboss.server.log.dir");
        String name = "monitor.log";
        String logFile = base + "/" + name;
        return logFile;
    }

}

