/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import gr.csd.uoc.cs359.winter2017.lq.db.InitiativeDB;
import gr.csd.uoc.cs359.winter2017.lq.db.VoteDB;
import gr.csd.uoc.cs359.winter2017.lq.model.Initiative;
import gr.csd.uoc.cs359.winter2017.lq.model.Vote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
            String isdelegator = request.getParameter("isdelegator");
            List<Initiative> activeInitiatives = InitiativeDB.getInitiativesWithStatus(1);
            if (action.equals("vote")) {
                for (int i = 0; i < activeInitiatives.size(); i++) {
                    if (activeInitiatives.get(i).getTitle().equals(title) && activeInitiatives.get(i).getCreator().equals(creator)) {
                        System.out.println("vrhke to initiative");
                        int id = activeInitiatives.get(i).getId();//ara exoume to id tou initiative pou theloume na pame kai na kanoume vote
                        Vote tmpVote = new Vote();
                        tmpVote.setId(VoteDB.getAllVotes().size() + 1);
                        tmpVote.setUser(username);
                        List<Vote> userVotes = VoteDB.getVotes(username);
                        boolean hasvoted = false;
                        for (int k = 0; k < userVotes.size(); k++) {
                            if (tmpVote.getId() == userVotes.get(k).getId()) {//If Delegator has already voted
                                hasvoted = true;
                            }
                        }
                        if (upvotedownvote.equals("upvote")) {//Setting vote
                            if (isdelegator != null && isdelegator.equals("true")) {
                                if (hasvoted == true) {
                                    if (tmpVote.getVotedBy() == 0) {//ama exei psifisei hdh kapoios delegator
                                        //GIATI O DELEGATOR DEN THA PREPEI NA MPOREI NA ALLAKSEI THN PSIFO ENOS KANONIKOU XRHSTH
                                        tmpVote.setVote(true, false);
                                    } else {
                                        //EDW ALERT OTI DN MPOREI ENAS DELEGATOR NA ALAKSEI THN PSIFO ENOS XRHSTH
                                    }
                                } else {
                                    tmpVote.setVote(true, false);//delegator upvote
                                }
                            } else {
                                tmpVote.setVote(true, true);//user upvote
                            }
                        } else if (upvotedownvote.equals("downvote")) {
                            if (isdelegator != null && isdelegator.equals("true")) {
                                if (hasvoted == true) {
                                    if (tmpVote.getVotedBy() == 0) {//ama exei psifisei hdh kapoios delegator
                                        //GIATI O DELEGATOR DEN THA PREPEI NA MPOREI NA ALLAKSEI THN PSIFO ENOS KANONIKOU XRHSTH
                                        tmpVote.setVote(false, false);
                                    } else {
                                        //EDW ALERT OTI DN MPOREI ENAS DELEGATOR NA ALAKSEI THN PSIFO ENOS XRHSTH
                                    }
                                } else {
                                    tmpVote.setVote(false, false);//delegator downvote
                                }
                            } else {
                                tmpVote.setVote(false, true);//user downvote
                            }
                        }
                        tmpVote.setInitiativeID(id);
                        tmpVote.setCreated(new Date());
                        if (hasvoted == true) {
                            VoteDB.updateVote(tmpVote);
                        } else {
                            VoteDB.addVote(tmpVote);
                        }
                        System.out.println("tmpVote is : " + tmpVote);
                        System.out.println("in DB there is: " + VoteDB.getVote(tmpVote.getId()));
                        break;
                    }
                }
            } else if (action.equals("updatevote")) {
                for (int i = 0; i < activeInitiatives.size(); i++) {
                    if (activeInitiatives.get(i).getTitle().equals(title) && activeInitiatives.get(i).getCreator().equals(creator)) {
                        int initiativeid = activeInitiatives.get(i).getId();//ara exoume to id tou initiative pou theloume na pame kai na kanoume vote
                        List<Vote> allVotes = VoteDB.getAllVotes();
                        for (int j = 0; j < allVotes.size(); j++) {
                            if (allVotes.get(j).getInitiativeID() == initiativeid && allVotes.get(j).getUser().equals(username)) {//We found the vote we 
                                int voteid = allVotes.get(j).getId();
                                Vote voteupdate = VoteDB.getVote(voteid);
                                if (upvotedownvote.equals("upvote")) {
                                    if (isdelegator.equals(true)) {//delegator upvote
                                        if (voteupdate.isVotedBy() == false) {//If user has voted and delegator tries to vote
                                            out.println("Delegator tried to update vote for user");
                                            response.setStatus(400);
                                        } else {
                                            voteupdate.setVote(true, false);
                                        }
                                    } else {//user upvote
                                        voteupdate.setVote(true, true);

                                    }
                                } else {//downvote
                                    if (isdelegator.equals(true)) {//delegator downvote
                                        if (voteupdate.isVotedBy() == false) {//If user has voted and delegator tries to vote
                                            out.println("Delegator tried to update vote for user");
                                            response.setStatus(400);
                                        } else {
                                            voteupdate.setVote(false, false);
                                        }
                                    } else {//user downvote
                                        voteupdate.setVote(false, true);
                                    }
                                }
                                voteupdate.setModified(new Date());
                                VoteDB.updateVote(voteupdate);//EDW KANW UPDATE STO VOTE MESA STO DB
                                break;
                            }
                        }
                    }
                }
            } else {
                out.println("Wrong parameter: action");
                response.setStatus(400);
            }
            RequestDispatcher redirect = request.getRequestDispatcher("showResults");
            redirect.forward(request, response);
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
