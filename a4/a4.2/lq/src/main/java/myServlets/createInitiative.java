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
import java.util.Calendar;
import java.util.Date;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet createInitiative</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createInitiative at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        boolean initiativeExists = false;
        PrintWriter out = response.getWriter();
        try {
            String initiativeTitle = request.getParameter("title");
            List<Initiative> allInitiatives = InitiativeDB.getAllInitiatives();
            for (int i = 0; i < allInitiatives.size(); i++) {
                if (allInitiatives.get(i).getTitle().equals(initiativeTitle)) {
                    initiativeExists = true;
                }
            }
            if (initiativeExists == false) {//If initiative does not already exist
                Initiative newInitiative = new Initiative();
                newInitiative.setCreator(request.getParameter("creator"));
                newInitiative.setDescription(request.getParameter("description"));
                newInitiative.setCategory(request.getParameter("category"));
                newInitiative.setTitle(request.getParameter("title"));
                newInitiative.setStatus(0);//Default status to inactive
                //SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date expdate = setExpirationDate(request);
                newInitiative.setExpires(expdate);
                InitiativeDB.addInitiative(newInitiative);
            } else {
                response.setStatus(400);
                out.print("Initiative Already exists");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(createInitiative.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(400);
        }
    }

    private Date setExpirationDate(HttpServletRequest request) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(request.getParameter("year")));
        cal.set(Calendar.MONTH, Integer.parseInt(request.getParameter("month")));
        cal.set(Calendar.DAY_OF_WEEK, Integer.parseInt(request.getParameter("day")));//EDW DN EIMAI SIGOUROS
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(request.getParameter("hour")));//POTE THELEI NA KANEI EXPIRE(TI MERA WRA KLP)
        cal.set(Calendar.MINUTE, Integer.parseInt(request.getParameter("minute")));//OXI SE POSES MERES WRES KLP
        cal.set(Calendar.SECOND, Integer.parseInt(request.getParameter("second")));
        return cal.getTime();
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
