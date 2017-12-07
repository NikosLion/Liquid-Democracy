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
            String username = request.getParameter("creator");//same as the username... maybe can get it from session if we want to
            List<Initiative> allInitiatives = InitiativeDB.getInitiatives(username);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Active Initiatives:</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Users</h2>");
            for (int i = 0; i < allInitiatives.size(); i++) {
                out.println("<h2>Title</h2>");
                out.println("<h3>" + allInitiatives.get(i).getTitle() + "</h3>");
                out.println("<h2>Category</h2>");
                out.println("<h3>" + allInitiatives.get(i).getCategory() + "</h3>");
                out.println("<h2>Description</h2>");
                out.println("<h3>" + allInitiatives.get(i).getDescription() + "</h3>");
                out.println("<h3>Creator</h3>");
                out.println("<h4>" + allInitiatives.get(i).getCreator() + "</h4>");
            }
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
