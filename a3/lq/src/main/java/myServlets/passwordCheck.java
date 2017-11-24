/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fouteros
 */
public class passwordCheck extends HttpServlet {

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
            out.println("<title>Servlet passwordCheck</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet passwordCheck at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        String value1 = new String();
        String value2 = new String();
        String ourPattern = "^(?=.*?[a-z,A-Z])(?=.*?[0-9])(?=.*?[~!@#$%^&*-]).{8,10}$";

        if (request.getParameter("pass1") != null && request.getParameter("pass2") != null) {
            value1 = request.getParameter("pass1");
            value2 = request.getParameter("pass2");
            if (value1.equals(value2)) {
                response.addHeader("value1", value1);
                response.addHeader("value2", value2);
            } else {
                response.addHeader("value1", "");
                response.setStatus(400);
            }
        }
        //MAKE PATTERN OPWS STO EMAIL
        //Pattern pattern = Pattern.compile(ourPattern);
        //Matcher matcher = pattern.matcher(request.getParameter("pass1"));
        //boolean result = matcher.matches();
        //if (!result) {
        //out.print("match");
        //} else {
        //out.print("no match");
        //}
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
