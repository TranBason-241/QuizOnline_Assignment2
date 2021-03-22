/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sontb.daos.QuestionDAO;
import sontb.dtos.AnswerDTO;
import sontb.dtos.SubjectDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateController.class);

    public static final String ERROR = "invalid.jsp";
    public static final String ADMIN_CONTROLLER = "AdminController";

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

        String url = ERROR;
        try {
            HttpSession ss = request.getSession();
            QuestionDAO dao = new QuestionDAO();
            //xu li update TRUE/FALSE answer truoc
            String questionID = request.getParameter("questionIDUpdate");  //questionID chinh la name cua parameter chua questionID
            String answerID = request.getParameter(questionID);
            ArrayList<AnswerDTO> listAnswer = new ArrayList<>();
            listAnswer = dao.getListAnswer(questionID);
            for (AnswerDTO answerDTO : listAnswer) {

                if (answerDTO.isIsCorrect() == true) {
                    dao.updateAnswerIsCorrect(answerDTO.getAnswerID(), 0);
                }
            }
            dao.updateAnswerIsCorrect(answerID, 1);
            //End xu li answer TRUE/FALSE

            //XU LI IPDATE ANSWER CONTENT
            for (AnswerDTO answerDTO : listAnswer) {
                  String content = request.getParameter(answerDTO.getAnswerID());
                  dao.updateAnswerContent(answerDTO.getAnswerID(), content);
            }

            //Xu li question 
            String questionContent = request.getParameter("txtQuestionContent");
            String status = request.getParameter("txtStatus");
            String subjectName = request.getParameter("txtSubject"); //Phai chuyen sang id
            String subjectID = "";
            //Tu subjectName chuyen sang subjectID
            ArrayList<SubjectDTO> listQuestion = new ArrayList<>();
            listQuestion = (ArrayList<SubjectDTO>) ss.getAttribute("LIST_SUBJECT"); //Listsubject da lay len o ben adminController
            for (SubjectDTO q : listQuestion) {
                if(q.getSubjectID().equals(subjectName)){
                    subjectID = q.getSubjectID();
                }
            }
            dao.updateQuestion(questionID, subjectID, status, questionContent);
            // END xu li question
            
      
            url = ADMIN_CONTROLLER;

        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
