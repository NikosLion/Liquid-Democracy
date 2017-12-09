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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giorgosvr46
 */
public class activateInitiative extends HttpServlet {

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
        boolean initiativeExists = false;
        String title = request.getParameter("title");
        String creator = request.getParameter("creator");
        PrintWriter out = response.getWriter();
        int i = 0;
        try {//We want the creator too to be sure that he activates the correct initiative and not another ones with the same title
            if (request.getParameter("creator") != null && !request.getParameter("creator").equals("") && request.getParameter("title") != null && !request.getParameter("title").equals("")) {
                List<Initiative> myInitiatives = InitiativeDB.getInitiatives(creator);
                for (i = 0; i < myInitiatives.size(); i++) {
                    if (myInitiatives.get(i).getTitle().equals(title)) {
                        initiativeExists = true;
                        break;
                    }
                }
                if (initiativeExists == false) {
                    response.setStatus(400);
                    out.print("Wrong initiative title.");
                } else {//We make the initiative active
                    Date expdate = setExpirationDate(request);
                    if (expdate.before(new Date())) {//If the expiration date is older than the current one
                        response.setStatus(400);
                        out.print(expdate);
                        out.print("Wrong date.");
                    } else {
                        for (int j = 0; j < myInitiatives.size(); j++) {
                            if (myInitiatives.get(j).getId() != -1) {
                                System.out.println("j=" + j);
                                if (myInitiatives.get(j).getTitle().equals(title)) {
                                    int initiativeId = myInitiatives.get(j).getId();
                                    Initiative tmpinitiative = InitiativeDB.getInitiative(initiativeId);
                                    tmpinitiative.setStatus(1);
                                    System.out.println("expiration date = " + expdate);
                                    tmpinitiative.setExpires(expdate);
                                    InitiativeDB.updateInitiative(tmpinitiative);
                                    System.out.println(InitiativeDB.getInitiative(initiativeId));
                                    RequestDispatcher rd = request.getRequestDispatcher("getActiveInitiatives");//after the initiative is activated we call the getActiveInitiatives servlet
                                    rd.forward(request, response);
                                }
                            }
                        }
                    }

                }
            } else {
                response.setStatus(400);
                out.print("Wrong parameters:");
                if (request.getParameter("title") == null || request.getParameter("title").equals("")) {
                    out.print("title");
                }
                if (request.getParameter("creator") == null || request.getParameter("creator").equals("")) {
                    out.print("creator");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(createInitiative.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(500);
        }
    }

    private Date setExpirationDate(HttpServletRequest request) {
        System.out.println("year=" + request.getParameter("year") + " month=" + request.getParameter("month") + "day=" + request.getParameter("day") +"hour=" + request.getParameter("hour") + "minute=" + request.getParameter("minute") +"second=" + request.getParameter("second"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(request.getParameter("year")));
        cal.set(Calendar.MONTH, Integer.parseInt(request.getParameter("month")));
        cal.set(Calendar.DAY_OF_WEEK, Integer.parseInt(request.getParameter("day")));//EDW DN EIMAI SIGOUROS
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(request.getParameter("hour")));//POTE THELEI NA KANEI EXPIRE(TI MERA WRA KLP)
        cal.set(Calendar.MINUTE, Integer.parseInt(request.getParameter("minute")));//OXI SE POSES MERES WRES KLP
        cal.set(Calendar.SECOND, Integer.parseInt(request.getParameter("second")));
        return cal.getTime();
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
