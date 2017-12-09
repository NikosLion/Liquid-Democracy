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
            String username = request.getParameter("creator");//same as the username...
            List<Initiative> allInitiatives = InitiativeDB.getInitiatives(username);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>My Initiatives:</title>");
            out.println("</head>");
            out.println("<body>");
            for (int i = 0; i < allInitiatives.size(); i++) {
                out.println("<h2>Title</h2>");
                out.println("<p>" + allInitiatives.get(i).getTitle() + "</p>");
                out.println("<h2>Category</h2>");
                out.println("<p>" + allInitiatives.get(i).getCategory() + "</p>");
                out.println("<h2>Description</h2>");
                out.println("<p>" + allInitiatives.get(i).getDescription() + "</p>");
                out.println("<h3>Creator</h3>");
                out.println("<p>" + allInitiatives.get(i).getCreator() + "</p>");
                out.println("<div id = \"edate" + i + "\">");
                out.println("<select id='day' required>\n"
                        + "                        <option value=\"1\">1</option>\n"
                        + "                        <option value=\"2\">2</option>\n"
                        + "                        <option value=\"3\">3</option>\n"
                        + "                        <option value=\"4\">4</option>\n"
                        + "                        <option value=\"5\">5</option>\n"
                        + "                        <option value=\"6\">6</option>\n"
                        + "                        <option value=\"7\">7</option>\n"
                        + "                        <option value=\"8\">8</option>\n"
                        + "                        <option value=\"9\">9</option>\n"
                        + "                        <option value=\"10\">10</option>\n"
                        + "                        <option value=\"11\">11</option>\n"
                        + "                        <option value=\"12\">12</option>\n"
                        + "                        <option value=\"13\">13</option>\n"
                        + "                        <option value=\"14\">14</option>\n"
                        + "                        <option value=\"15\">15</option>\n"
                        + "                        <option value=\"16\">16</option>\n"
                        + "                        <option value=\"17\">17</option>\n"
                        + "                        <option value=\"18\">18</option>\n"
                        + "                        <option value=\"19\">19</option>\n"
                        + "                        <option value=\"20\">20</option>\n"
                        + "                        <option value=\"21\">21</option>\n"
                        + "                        <option value=\"22\">22</option>\n"
                        + "                        <option value=\"23\">23</option>\n"
                        + "                        <option value=\"24\">24</option>\n"
                        + "                        <option value=\"25\">25</option>\n"
                        + "                        <option value=\"26\">26</option>\n"
                        + "                        <option value=\"27\">27</option>\n"
                        + "                        <option value=\"28\">28</option>\n"
                        + "                        <option value=\"29\">29</option>\n"
                        + "                        <option value=\"30\">30</option>\n"
                        + "                        <option value=\"31\">31</option>\n"
                        + "                    </select><br>");
                out.println("<select id=\"month\" required>\n"
                        + "                        <option value=\"1\" name=\"1\">January</option>\n"
                        + "                        <option value=\"2\" name=\"2\">February</option>\n"
                        + "                        <option value=\"3\" name=\"3\">March</option>\n"
                        + "                        <option value=\"4\" name=\"4\">April</option>\n"
                        + "                        <option value=\"5\" name=\"5\">May</option>\n"
                        + "                        <option value=\"6\" name=\"6\">June</option>\n"
                        + "                        <option value=\"7\" name=\"7\">July</option>\n"
                        + "                        <option value=\"8\" name=\"8\">August</option>\n"
                        + "                        <option value=\"9\" name=\"9\">September</option>\n"
                        + "                        <option value=\"10\" name=\"10\">October</option>\n"
                        + "                        <option value=\"11\" name=\"11\">November</option>\n"
                        + "                        <option value=\"12\" name=\"12\">December</option>\n"
                        + "                    </select><br>");
                out.println("<input id='year' type=\"number\" name=\"year\" min=\"2017\" value=\"2017\" required><br>");
                out.println("<select id=\"hour\">\n"
                        + "  <option value=\"0\">12:00 am</option>\n"
                        + "  <option value=\"1\">1:00 am</option>\n"
                        + "  <option value=\"2\">2:00 am</option>\n"
                        + "  <option value=\"3\">3:00 am</option>\n"
                        + "  <option value=\"4\">4:00 am</option>\n"
                        + "  <option value=\"5\">5:00 am</option>\n"
                        + "  <option value=\"6\">6:00 am</option>\n"
                        + "  <option value=\"7\">7:00 am</option>\n"
                        + "  <option value=\"8\">8:00 am</option>\n"
                        + "  <option value=\"9\">9:00 am</option>\n"
                        + "  <option value=\"10\">10:00 am</option>\n"
                        + "  <option value=\"11\">11:00 am</option>\n"
                        + "  <option value=\"12\">12:00 pm</option>\n"
                        + "  <option value=\"13\">13:00 pm</option>\n"
                        + "  <option value=\"14\">14:00 pm</option>\n"
                        + "  <option value=\"15\">15:00 pm</option>\n"
                        + "  <option value=\"16\">16:00 pm</option>\n"
                        + "  <option value=\"17\">17:00 pm</option>\n"
                        + "  <option value=\"18\">18:00 pm</option>\n"
                        + "  <option value=\"19\">19:00 pm</option>\n"
                        + "  <option value=\"20\">20:00 pm</option>\n"
                        + "  <option value=\"21\">21:00 pm</option>\n"
                        + "  <option value=\"22\">22:00 pm</option>\n"
                        + "  <option value=\"23\">23:00 pm</option>\n"
                        + "</select><br>");
                out.println("<select id='minute' required>\n"
                        + "                        <option value=\"0\">0</option>\n"
                        + "                        <option value=\"1\">1</option>\n"
                        + "                        <option value=\"2\">2</option>\n"
                        + "                        <option value=\"3\">3</option>\n"
                        + "                        <option value=\"4\">4</option>\n"
                        + "                        <option value=\"5\">5</option>\n"
                        + "                        <option value=\"6\">6</option>\n"
                        + "                        <option value=\"7\">7</option>\n"
                        + "                        <option value=\"8\">8</option>\n"
                        + "                        <option value=\"9\">9</option>\n"
                        + "                        <option value=\"10\">10</option>\n"
                        + "                        <option value=\"11\">11</option>\n"
                        + "                        <option value=\"12\">12</option>\n"
                        + "                        <option value=\"13\">13</option>\n"
                        + "                        <option value=\"14\">14</option>\n"
                        + "                        <option value=\"15\">15</option>\n"
                        + "                        <option value=\"16\">16</option>\n"
                        + "                        <option value=\"17\">17</option>\n"
                        + "                        <option value=\"18\">18</option>\n"
                        + "                        <option value=\"19\">19</option>\n"
                        + "                        <option value=\"20\">20</option>\n"
                        + "                        <option value=\"21\">21</option>\n"
                        + "                        <option value=\"22\">22</option>\n"
                        + "                        <option value=\"23\">23</option>\n"
                        + "                        <option value=\"24\">24</option>\n"
                        + "                        <option value=\"25\">25</option>\n"
                        + "                        <option value=\"26\">26</option>\n"
                        + "                        <option value=\"27\">27</option>\n"
                        + "                        <option value=\"28\">28</option>\n"
                        + "                        <option value=\"29\">29</option>\n"
                        + "                        <option value=\"30\">30</option>\n"
                        + "                        <option value=\"31\">31</option>\n"
                        + "                        <option value=\"32\">32</option>\n"
                        + "                        <option value=\"33\">33</option>\n"
                        + "                        <option value=\"34\">34</option>\n"
                        + "                        <option value=\"35\">35</option>\n"
                        + "                        <option value=\"36\">36</option>\n"
                        + "                        <option value=\"37\">37</option>\n"
                        + "                        <option value=\"38\">38</option>\n"
                        + "                        <option value=\"39\">39</option>\n"
                        + "                        <option value=\"40\">40</option>\n"
                        + "                        <option value=\"41\">41</option>\n"
                        + "                        <option value=\"42\">42</option>\n"
                        + "                        <option value=\"43\">43</option>\n"
                        + "                        <option value=\"44\">44</option>\n"
                        + "                        <option value=\"45\">45</option>\n"
                        + "                        <option value=\"46\">46</option>\n"
                        + "                        <option value=\"47\">47</option>\n"
                        + "                        <option value=\"48\">48</option>\n"
                        + "                        <option value=\"49\">49</option>\n"
                        + "                        <option value=\"50\">50</option>\n"
                        + "                        <option value=\"51\">51</option>\n"
                        + "                        <option value=\"52\">52</option>\n"
                        + "                        <option value=\"53\">53</option>\n"
                        + "                        <option value=\"54\">54</option>\n"
                        + "                        <option value=\"55\">55</option>\n"
                        + "                        <option value=\"56\">56</option>\n"
                        + "                        <option value=\"57\">57</option>\n"
                        + "                        <option value=\"58\">58</option>\n"
                        + "                        <option value=\"59\">59</option>\n"
                        + "                    </select><br>");
                out.println("<select id='second' required>\n"
                        + "                        <option value=\"0\">0</option>\n"
                        + "                        <option value=\"1\">1</option>\n"
                        + "                        <option value=\"2\">2</option>\n"
                        + "                        <option value=\"3\">3</option>\n"
                        + "                        <option value=\"4\">4</option>\n"
                        + "                        <option value=\"5\">5</option>\n"
                        + "                        <option value=\"6\">6</option>\n"
                        + "                        <option value=\"7\">7</option>\n"
                        + "                        <option value=\"8\">8</option>\n"
                        + "                        <option value=\"9\">9</option>\n"
                        + "                        <option value=\"10\">10</option>\n"
                        + "                        <option value=\"11\">11</option>\n"
                        + "                        <option value=\"12\">12</option>\n"
                        + "                        <option value=\"13\">13</option>\n"
                        + "                        <option value=\"14\">14</option>\n"
                        + "                        <option value=\"15\">15</option>\n"
                        + "                        <option value=\"16\">16</option>\n"
                        + "                        <option value=\"17\">17</option>\n"
                        + "                        <option value=\"18\">18</option>\n"
                        + "                        <option value=\"19\">19</option>\n"
                        + "                        <option value=\"20\">20</option>\n"
                        + "                        <option value=\"21\">21</option>\n"
                        + "                        <option value=\"22\">22</option>\n"
                        + "                        <option value=\"23\">23</option>\n"
                        + "                        <option value=\"24\">24</option>\n"
                        + "                        <option value=\"25\">25</option>\n"
                        + "                        <option value=\"26\">26</option>\n"
                        + "                        <option value=\"27\">27</option>\n"
                        + "                        <option value=\"28\">28</option>\n"
                        + "                        <option value=\"29\">29</option>\n"
                        + "                        <option value=\"30\">30</option>\n"
                        + "                        <option value=\"31\">31</option>\n"
                        + "                        <option value=\"32\">32</option>\n"
                        + "                        <option value=\"33\">33</option>\n"
                        + "                        <option value=\"34\">34</option>\n"
                        + "                        <option value=\"35\">35</option>\n"
                        + "                        <option value=\"36\">36</option>\n"
                        + "                        <option value=\"37\">37</option>\n"
                        + "                        <option value=\"38\">38</option>\n"
                        + "                        <option value=\"39\">39</option>\n"
                        + "                        <option value=\"40\">40</option>\n"
                        + "                        <option value=\"41\">41</option>\n"
                        + "                        <option value=\"42\">42</option>\n"
                        + "                        <option value=\"43\">43</option>\n"
                        + "                        <option value=\"44\">44</option>\n"
                        + "                        <option value=\"45\">45</option>\n"
                        + "                        <option value=\"46\">46</option>\n"
                        + "                        <option value=\"47\">47</option>\n"
                        + "                        <option value=\"48\">48</option>\n"
                        + "                        <option value=\"49\">49</option>\n"
                        + "                        <option value=\"50\">50</option>\n"
                        + "                        <option value=\"51\">51</option>\n"
                        + "                        <option value=\"52\">52</option>\n"
                        + "                        <option value=\"53\">53</option>\n"
                        + "                        <option value=\"54\">54</option>\n"
                        + "                        <option value=\"55\">55</option>\n"
                        + "                        <option value=\"56\">56</option>\n"
                        + "                        <option value=\"57\">57</option>\n"
                        + "                        <option value=\"58\">58</option>\n"
                        + "                        <option value=\"59\">59</option>\n"
                        + "                    </select><br>");
                if (allInitiatives.get(i).getStatus() == 1) {//If its active vote
                    out.println("<button id='upvote' type='button' onclick='VoteInitiative('upvote'," + allInitiatives.get(i).getTitle() + "," + request.getParameter("creator") + ")'>upvote</button>");//isws na to kanoume apo js ta buttons gia to vote
                    out.println("<button id='downvote' type='button' onclick='VoteInitiative('downvote'," + allInitiatives.get(i).getTitle() + "," + request.getParameter("creator") + ")'>downvote</button>");
                } else if (allInitiatives.get(i).getStatus() == 0) {//If its inactive user can make it active
                    out.println("<button id='activate' type='button' onclick='activateInitiative(\"" + allInitiatives.get(i).getTitle() + "\",\"" + allInitiatives.get(i).getCreator() + "\",\"" + i + "\")'>Activate</button>");
                }
            }
            out.println("</div>");
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
