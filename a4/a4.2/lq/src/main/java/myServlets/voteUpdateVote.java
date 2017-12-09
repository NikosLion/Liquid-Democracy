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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giorgosvr46
 */
public class voteUpdateVote extends HttpServlet {

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
            PrintWriter out = response.getWriter();
            String action = request.getParameter("action");
            String upvotedownvote = request.getParameter("upvotedownvote");
            String title = request.getParameter("title");
            String creator = request.getParameter("creator");
            String username = request.getParameter("username");//Its the username of the user voting. Not necessarily the creator but could also be him voting his own initiative.
            if (action.equals("vote")) {
                List<Initiative> activeInitiatives = InitiativeDB.getInitiativesWithStatus(1);
                for (int i = 0; i < activeInitiatives.size(); i++) {
                    if (activeInitiatives.get(i).getTitle().equals(title) && activeInitiatives.get(i).getCreator().equals(creator)) {
                        int id = activeInitiatives.get(i).getId();//ara exoume to id tou initiative pou theloume na pame kai na kanoume vote

                    }
                }
            } else if (action.equals("updatevote")) {

            } else {
                out.println("Wrong parameter: action");
                response.setStatus(400);
            }
            response.setContentType("text/html;charset=UTF-8");

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet voteUpdateVote</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet voteUpdateVote at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
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
