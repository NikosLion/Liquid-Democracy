/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import gr.csd.uoc.cs359.winter2017.lq.model.Initiative;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giorgosvr46
 */
public class showMyInitiatives extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("creator");//same as the username...
            List<Initiative> allInitiatives = InitiativeDB.getInitiatives(username);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>My Initiatives:</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<button id=\"createInitiative\" type=\"button\" onclick=\"createInitiativeForm()\">Create</button>\n"
                    + "    <button id=\"showOwn\" type=\"button\" onclick=\"showOwnInitiatives()\">Show Mine</button>\n"
                    + "    <button id=\"getActive\" type=\"button\" onclick=\"getActiveInitiatives()\">Active</button>\n"
                    + "    <button id=\"getExpired\" type=\"button\" onclick=\"getExpiredInitiatives()\">Expired</button>");
            for (int i = 0; i < allInitiatives.size(); i++) {
                out.println("<h2>Title</h2>");
                out.println("<p>" + allInitiatives.get(i).getTitle() + "</p>");
                out.println("<h2>Category</h2>");
                out.println("<p>" + allInitiatives.get(i).getCategory() + "</p>");
                out.println("<h2>Description</h2>");
                out.println("<p>" + allInitiatives.get(i).getDescription() + "</p>");
                out.println("<h3>Creator</h3>");
                out.println("<p>" + allInitiatives.get(i).getCreator() + "</p>");
                out.println("<div id = \"edate" + i + "\">");
                if (allInitiatives.get(i).getStatus() == 1) {//If its active vote
                    out.println("<button id='upvote' type='button' onclick='inactivateExpiredInitiatives();VoteUpdateVote(\"upvote\",\"" + allInitiatives.get(i).getTitle() + "\",\"" + request.getParameter("creator") + "\",\"" + request.getParameter("creator") + "\")'>upvote</button>");
                    out.println("<button id='downvote' type='button' onclick='inactivateExpiredInitiatives();VoteUpdateVote(\"downvote\",\"" + allInitiatives.get(i).getTitle() + "\",\"" + request.getParameter("creator") + "\",\"" + request.getParameter("creator") + "\")'>downvote</button>");
                } else if (allInitiatives.get(i).getStatus() == 0) {//If its inactive user can make it active
                    out.println("<select id='day' required>\n");
                    int counter;
                    for (counter = 1; counter <= 31; counter++) {
                        out.println("                        <option value=\"" + counter + "\">" + counter + "</option>\n");
                    }
                    out.println("                    </select><br>");
                    out.println("<select id=\"month\" required>\n"
                            + "                        <option value=\"1\" name=\"1\">January</option>\n"
                            + "                        <option value=\"2\" name=\"2\">February</option>\n"
                            + "                        <option value=\"3\" name=\"3\">March</option>\n"
                            + "                        <option value=\"4\" name=\"4\">April</option>\n"
                            + "                        <option value=\"5\" name=\"5\">May</option>\n"
                            + "                        <option value=\"6\" name=\"6\">June</option>\n"
                            + "                        <option value=\"7\" name=\"7\">July</option>\n"
                            + "                        <option value=\"8\" name=\"8\">August</option>\n"
                            + "                        <option value=\"9\" name=\"9\">September</option>\n"
                            + "                        <option value=\"10\" name=\"10\">October</option>\n"
                            + "                        <option value=\"11\" name=\"11\">November</option>\n"
                            + "                        <option value=\"12\" name=\"12\">December</option>\n"
                            + "                    </select><br>");
                    out.println("<input id='year' type=\"number\" name=\"year\" min=\"2017\" value=\"2017\" required><br>");
                    out.println("<select id=\"hour\">\n"
                            + "  <option value=\"0\">12:00 am</option>\n");
                    for (counter = 1; counter <= 11; counter++) {
                        out.println("  <option value=\"" + counter + "\">" + counter + ":00 am</option>\n");
                    }
                    for (counter = 12; counter <= 23; counter++) {
                        out.println("  <option value=\"" + counter + "\">" + counter + ":00 pm</option>\n");
                    }
                    out.println("</select><br>");
                    out.println("<select id='minute' required>\n");
                    for (counter = 0; counter <= 59; counter++) {
                        out.println("                        <option value=\"" + counter + "\">" + counter + "</option>\n");
                    }
                    out.println("                    </select><br>");
                    out.println("<select id='second' required>\n");
                    for (counter = 0; counter <= 59; counter++) {
                        out.println("                        <option value=\"" + counter + "\">" + counter + "</option>\n");
                    }
                    out.println("                    </select><br>");
                    out.println("<button id='update' type='button' onclick='getInitiativeForUpdate(\"" + allInitiatives.get(i).getTitle() + "\",\"" + allInitiatives.get(i).getCreator() + "\",\"" + allInitiatives.get(i).getDescription() + "\",\"" + allInitiatives.get(i).getCategory() + "\",\"" + allInitiatives.get(i).getId() + "\")'>Update</button>");
                    out.println("<button id='activate' type='button' onclick='activateInitiative(\"" + allInitiatives.get(i).getTitle() + "\",\"" + allInitiatives.get(i).getCreator() + "\",\"" + i + "\")'>Activate</button>");
                }
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(showMyInitiatives.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(400);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
