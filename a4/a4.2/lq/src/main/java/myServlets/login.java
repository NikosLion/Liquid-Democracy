/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import static gr.csd.uoc.cs359.winter2017.lq.db.UserDB.getUser;
import gr.csd.uoc.cs359.winter2017.lq.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fouteros
 */
public class login extends HttpServlet {

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
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
        User araujo = new User();
        User araujo2 = new User();
        PrintWriter out = response.getWriter();

        try {
            //this user's attributes will come from the login form
            araujo = getUser((String) request.getParameter("username"));
            //this user's attributes will exist if our session is active
            araujo2 = getUser((String) request.getAttribute("username"));
            if (araujo.getUserName() != null && !(araujo.getUserName().equals(""))) {
                if (araujo.getPassword().equals(request.getParameter("password"))) {
                    //there is no active session if we are here so make a new one
                    HttpSession session = request.getSession(true);
                    synchronized (session) {
                        session.setAttribute("username", araujo.getUserName());
                        session.setAttribute("password", araujo.getPassword());
                        session.setMaxInactiveInterval(-1);
                    }
                    response = setupRespHeaders(response, araujo);
                    response.setHeader("result", araujo.getUserName() + " is now online");
                    response.setHeader("activeUser", araujo.getUserName());
                    response.setHeader("activeEmail", araujo.getEmail());
                    out.print("Welcome " + araujo.getUserName());
                } else {
                    response.setStatus(400);
                    out.print("Wrong Password");
                }
            } else if (araujo2.getUserName() != null && !(araujo2.getUserName().equals(""))) {
                if (araujo2.getPassword().equals(request.getAttribute("password"))) {
                    //session is active here so we don't create a new one
                    response = setupRespHeaders(response, araujo2);
                    out.print("Welcome back " + araujo2.getUserName());
                } else {
                    response.setStatus(400);
                    out.print("Failed to login");
                }
            } else {
                response.setStatus(400);
                out.print(request.getAttribute("username"));
                response.setHeader("password", request.getAttribute("password").toString());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HttpServletResponse setupRespHeaders(HttpServletResponse response, User user) {
        response.setHeader("username", user.getUserName());
        response.setHeader("email", user.getEmail());
        response.setHeader("password", user.getPassword());
        response.setHeader("name", user.getFirstName());
        response.setHeader("lastname", user.getLastName());
        response.setHeader("birthdate", user.getBirthDate());
        response.setHeader("country", user.getCountry());
        response.setHeader("city", user.getTown());
        response.setHeader("address", user.getAddress());
        response.setHeader("profession", user.getOccupation());
        response.setHeader("gender", user.getGender().toString());
        response.setHeader("interests", user.getInterests());
        response.setHeader("genInfo", user.getInfo());

        return response;
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
