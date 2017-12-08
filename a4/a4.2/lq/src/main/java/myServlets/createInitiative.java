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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giorgosvr46
 */
public class createInitiative extends HttpServlet {

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
        boolean initiativeExists = false;
        PrintWriter out = response.getWriter();
        try {
            String initiativeTitle = request.getParameter("title");
            List<Initiative> allInitiatives = InitiativeDB.getAllInitiatives();
            int id = allInitiatives.size();
            for (int i = 0; i < allInitiatives.size(); i++) {
                if (allInitiatives.get(i).getTitle().equals(initiativeTitle)) {
                    initiativeExists = true;
                }
            }
            if (initiativeExists == false) {//If initiative does not already exist
                if (request.getParameter("creator") != null && !request.getParameter("creator").equals("") && request.getParameter("description") != null && !request.getParameter("description").equals("") && request.getParameter("category") != null && !request.getParameter("category").equals("") && request.getParameter("title") != null && !request.getParameter("title").equals("")) {
                    Initiative newInitiative = new Initiative();
                    newInitiative.setCreator(request.getParameter("creator"));//Creator is the same thing as username
                    newInitiative.setDescription(request.getParameter("description"));
                    newInitiative.setCategory(request.getParameter("category"));
                    newInitiative.setTitle(request.getParameter("title"));
                    newInitiative.setStatus(0);//Default status to inactive
                    //Date expdate = setExpirationDate(request);
                    //newInitiative.setExpires(expdate);
                    newInitiative.setId(id);
                    newInitiative.setCreated(new Date());//nmz pws etsi vazei to current date and time
                    InitiativeDB.addInitiative(newInitiative);
                    RequestDispatcher rd = request.getRequestDispatcher("showMyInitiatives");//AFTER THE INITIATIVE IS CREATED WE CALL THE showMyInitiatives SERVLET
                    rd.forward(request, response);
                } else {
                    response.setStatus(400);
                    out.print("Wrong parameters:");
                    if (request.getParameter("creator") == null || request.getParameter("creator").equals("")) {
                        out.println("creator");
                    }
                    if (request.getParameter("description") == null || request.getParameter("description").equals("")) {
                        out.print("description");
                    }
                    if (request.getParameter("category") == null || request.getParameter("category").equals("")) {
                        out.print("category");
                    }
                    if (request.getParameter("title") == null || request.getParameter("title").equals("")) {
                        out.print("title");
                    }
                }

            } else {
                response.setStatus(400);
                out.print("Initiative Already exists");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(createInitiative.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(500);
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
