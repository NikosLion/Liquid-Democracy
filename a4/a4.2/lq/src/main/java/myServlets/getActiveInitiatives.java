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
public class getActiveInitiatives extends HttpServlet {

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
            List<Initiative> activeInitiatives = InitiativeDB.getInitiativesWithStatus(1);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<head>");
            out.println("<title>Active Initiatives:</title>");
            out.println("</head>");
            out.println("<body>");
            for (int i = 0; i < activeInitiatives.size(); i++) {
                out.println("<h2>Title</h2>");
                out.println("<p>" + activeInitiatives.get(i).getTitle() + "</p>");
                out.println("<h2>Category</h2>");
                out.println("<p>" + activeInitiatives.get(i).getCategory() + "</p>");
                out.println("<h2>Description</h2>");
                out.println("<p>" + activeInitiatives.get(i).getDescription() + "</p>");
                out.println("<h3>Creator</h3>");
                out.println("<p>" + activeInitiatives.get(i).getCreator() + "</p>");
                //EDW THELW TWRA 2 BUTTONS GIA TO VOTE 
                //EDW TO ATTRIBUTE GIA NA PAIRNOUME TO NAME AFTOU POU PSIFIZEI
                out.println("<button id='upvote' type='button' onclick='VoteInitiative('upvote'," + activeInitiatives.get(i).getTitle() + "," + request.getAttribute("creator") + ")'>upvote</button>");//isws na to kanoume apo js ta buttons gia to vote
                out.println("<button id='downvote' type='button' onclick='VoteInitiative('downvote'," + activeInitiatives.get(i).getTitle() + "," + request.getAttribute("creator") + ")'>downvote</button>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getActiveInitiatives.class.getName()).log(Level.SEVERE, null, ex);
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
