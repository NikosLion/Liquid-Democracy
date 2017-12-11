/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import static gr.csd.uoc.cs359.winter2017.lq.db.VoteDB.getAllVotes;
import gr.csd.uoc.cs359.winter2017.lq.model.Initiative;
import gr.csd.uoc.cs359.winter2017.lq.model.Vote;
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
 * @author Fouteros
 */
public class showResults extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            try {
                /* TODO output your page here. You may use following sample code. */
                List<Initiative> expiredInitiatives = InitiativeDB.getInitiativesWithStatus(2);
                response.setContentType("text/html;charset=UTF-8");
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                if (expiredInitiatives.isEmpty()) {
                    out.println("<p>" + "No results yet, initiatives might still be active." + "</p>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                } else {
                    for (int i = 0; i < expiredInitiatives.size(); i++) {
                        out.println("<h2>Title</h2>");
                        out.println("<p>" + expiredInitiatives.get(i).getTitle() + "</p>");
                        out.println("<h2>Category</h2>");
                        out.println("<p>" + expiredInitiatives.get(i).getCategory() + "</p>");
                        out.println("<h2>Description</h2>");
                        out.println("<p>" + expiredInitiatives.get(i).getDescription() + "</p>");
                        out.println("<h3>Creator</h3>");
                        out.println("<p>" + expiredInitiatives.get(i).getCreator() + "</p>");
                        out.println("<h3>Upvotes</h3>");

                        List<Vote> allVotes = getAllVotes();
                        int upvotes = 0;
                        int downvotes = 0;
                        for (int j = 0; j < allVotes.size(); j++) {
                            if (allVotes.get(j).getInitiativeID() == expiredInitiatives.get(i).getId()) {
                                if (allVotes.get(j).getVote() == 1) {
                                    upvotes++;
                                } else {
                                    downvotes++;
                                }
                            }
                        }
                        out.println("<p>" + upvotes + "</p>");
                        out.println("<h3>Downvotes</h3>");
                        out.println("<p>" + downvotes + "</p>");
                    }
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(showResults.class.getName()).log(Level.SEVERE, null, ex);
            }
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
