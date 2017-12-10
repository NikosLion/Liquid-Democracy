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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fouteros
 */
public class updateInitiative extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String initiativeTitle = request.getParameter("title");
            String initiativeCreator = request.getParameter("creator");
            int initiativeID = Integer.parseInt(request.getParameter("id"));
            List<Initiative> allInitiatives = InitiativeDB.getAllInitiatives();
            int countCreator = 0;
            int countOthers = 0;
            for (int i = 0; i < allInitiatives.size(); i++) {
                //check initiatives with same title
                if (allInitiatives.get(i).getTitle().equals(initiativeTitle)) {
                    //check initiatives with same title and same creator
                    if (allInitiatives.get(i).getCreator().equals(initiativeCreator)) {
                        countCreator++;
                    } else {
                        countOthers++;
                    }
                }
            }
            //ok mexri edw
            System.out.println("countCreator: " + countCreator + " countOthers: " + countOthers);
            if (countCreator > 1 || countOthers > 0) {
                response.setStatus(400);
                out.print("Initiative with this title already exists.");
            } else if (countCreator == 1) {
                if (initiativeTitle.equals(InitiativeDB.getInitiative(initiativeID).getTitle())) {
                    if (request.getParameter("creator") != null && !request.getParameter("creator").equals("") && request.getParameter("description") != null && !request.getParameter("description").equals("") && request.getParameter("category") != null && !request.getParameter("category").equals("") && request.getParameter("title") != null && !request.getParameter("title").equals("")) {
                        Initiative updatedInitiative = new Initiative();
                        updatedInitiative.setCreator(initiativeCreator);
                        updatedInitiative.setTitle(initiativeTitle);
                        updatedInitiative.setDescription(request.getParameter("description"));
                        updatedInitiative.setCategory(request.getParameter("category"));
                        //na dw an prepei na tou kanw set k to id to idio me to palio id
                        updatedInitiative.setId(InitiativeDB.getInitiative(initiativeID).getId());
                        updatedInitiative.setStatus(0);//Default status to inactive
                        //updatedInitiative.setCreated(new Date());
                        InitiativeDB.updateInitiative(updatedInitiative);
                    } else {
                        response.setStatus(400);
                        out.print("Wrong parameters");
                        return;
                    }
                }
            } else {
                if (request.getParameter("creator") != null && !request.getParameter("creator").equals("") && request.getParameter("description") != null && !request.getParameter("description").equals("") && request.getParameter("category") != null && !request.getParameter("category").equals("") && request.getParameter("title") != null && !request.getParameter("title").equals("")) {
                    Initiative updatedInitiative = new Initiative();
                    updatedInitiative.setCreator(initiativeCreator);
                    updatedInitiative.setTitle(initiativeTitle);
                    updatedInitiative.setDescription(request.getParameter("description"));
                    updatedInitiative.setCategory(request.getParameter("category"));
                    //na dw an prepei na tou kanw set k to id to idio me to palio id
                    updatedInitiative.setId(InitiativeDB.getInitiative(initiativeID).getId());
                    updatedInitiative.setStatus(0);//Default status to inactive
                    //updatedInitiative.setCreated(new Date());
                    InitiativeDB.updateInitiative(updatedInitiative);
                } else {
                    response.setStatus(400);
                    out.print("Wrong parameters");
                    return;
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("showMyInitiatives");//AFTER THE INITIATIVE IS UPDATED WE CALL THE showMyInitiatives SERVLET
            rd.forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updateInitiative.class.getName()).log(Level.SEVERE, null, ex);
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
