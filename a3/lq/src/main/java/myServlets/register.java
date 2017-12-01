/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import gr.csd.uoc.cs359.winter2017.lq.db.UserDB;
import static gr.csd.uoc.cs359.winter2017.lq.db.UserDB.checkValidEmail;
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
public class register extends HttpServlet {

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
            out.println("<title>Servlet register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet register at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();

        try {
            if (UserDB.checkValidUserName(request.getParameter("username"))) {
                if (checkValidEmail(request.getParameter("email"))) {
                    // if request.getParameter("update") != null
                    // && request.getParameter("update").equals("update")
                    // do update and return
                    /*if (isUpdate != null && isUpdate.equals("update")) {
                        araujo = initUser(araujo, request);
                        updateUser(araujo);
                        if (UserDB.checkValidUserName(araujo.getUserName())) {
                            response.setStatus(400);
                            out.print("Error updating user data.");
                        } else {
                            //get active session
                            HttpSession session = request.getSession(false);
                            if (session != null) {
                                //update session info with current values
                                session.setAttribute("username", araujo.getUserName());
                                session.setAttribute("password", araujo.getPassword());
                                //set up headers according to the changes that the user made
                                response = setupRespHeaders(response, araujo);
                                response.setHeader("result", araujo.getUserName() + " has updated his info");
                                response.setHeader("activeUser", araujo.getUserName());
                                response.setHeader("activeEmail", araujo.getEmail());
                            }
                        }
                        return;
                    }*/
                    // Add araujo to database
                    araujo = initUser(araujo, request);
                    UserDB.addUser(araujo);
                    //check that user was succesfully added to DB
                    if (UserDB.checkValidUserName(araujo.getUserName())) {
                        response.setStatus(400);
                        out.print("Error in registration.Try again");
                    } else {
                        HttpSession session = request.getSession(true);
                        synchronized (session) {
                            session.setAttribute("username", araujo.getUserName());
                            session.setAttribute("password", araujo.getPassword());
                            //session.setAttribute("", out);
                            session.setMaxInactiveInterval(-1);
                        }
                        response = setupRespHeaders(response, araujo);
                        response.setHeader("result", araujo.getUserName() + " is now on Liquid");
                        response.setHeader("activeUser", araujo.getUserName());
                        response.setHeader("activeEmail", araujo.getEmail());
                    }
                }
            } else {
                System.out.println("User already exists.... Go get Messi!");
                out.print("Invalid Username");
                response.setStatus(400);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private User initUser(User araujo, HttpServletRequest request) {
        araujo.setUserName(request.getParameter("username"));
        araujo.setEmail(request.getParameter("email"));
        araujo.setPassword(request.getParameter("password"));
        araujo.setFirstName(request.getParameter("name"));
        araujo.setLastName(request.getParameter("lastName"));

        String birthdate = new String();
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        birthdate = year + "/" + month + "/" + day;

        araujo.setBirthDate(birthdate);
        araujo.setCountry(request.getParameter("country"));
        araujo.setTown(request.getParameter("city"));
        araujo.setAddress(request.getParameter("address"));
        araujo.setOccupation(request.getParameter("profession"));
        araujo.setGender(request.getParameter("gender"));
        araujo.setInterests(request.getParameter("interests"));
        araujo.setInfo(request.getParameter("genInfo"));

        return araujo;
    }

    private HttpServletResponse setupRespHeaders(HttpServletResponse response, User user) {
        try {
            PrintWriter out = response.getWriter();
            out.print("Your registration was completed succesfully");
        } catch (IOException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }

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
