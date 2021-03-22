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
import sontb.daos.HistoryDAO;
import sontb.dtos.AnswerDTO;
import sontb.dtos.HistoryDTO;
import sontb.dtos.QuestionDTO;
import sontb.dtos.SubjectDTO;
import sontb.dtos.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckExamController", urlPatterns = {"/CheckExamController"})
public class CheckExamController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(CheckExamController.class);
    public static final String RESULT_PAGE = "resultPage.jsp";

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

        String url = RESULT_PAGE;
        try {
            HttpSession ss = request.getSession();
            ArrayList<QuestionDTO> listQuestion = new ArrayList<>();
            listQuestion = (ArrayList<QuestionDTO>) ss.getAttribute("LIST_QUESTION");
            SubjectDTO subject = (SubjectDTO) ss.getAttribute("SUBJECT_EXAM");
            float numOfQuestion = subject.getNumberOfQuestions();
            String subjectID = subject.getSubjectID();
            float numCorrect = 0;

            for (QuestionDTO question : listQuestion) {
                String answerSelectedID = request.getParameter(question.getQuestionID());
                for (AnswerDTO answerDTO : question.getAnswer()) {
                    if (answerDTO.getAnswerID().equals(answerSelectedID)) {
                        answerDTO.setIsSelected("selected");
                        if (answerDTO.isIsCorrect() == true) {
                            numCorrect++;
                        }
                    }else {
                        answerDTO.setIsSelected("NOTselected");
                    }
                }
            }
            String mess = "";
            String messCorrect = "Correct answer:  " + numCorrect+"/"+numOfQuestion;
            float result = (10/numOfQuestion)*numCorrect;
            
            request.setAttribute("RESULT", result);
            request.setAttribute("NUMBER_CORRECT", messCorrect);
            if(result > 8){
                 mess= "Very Good";
            }else if (result>6){
                mess = "Good";
            }else if(result>4){
                mess = "Not bad";
            }else {
                mess = "So bad!!!";
            }
            request.setAttribute("MESS", mess);
            
            //Luu lai history detail
            
                //Tạo 1 historyDTO
                UserDTO user = (UserDTO) ss.getAttribute("USER");
                String userID = user.getUserID();
                int numberOfcorrect = (int)numCorrect;
                HistoryDTO history = new HistoryDTO(0, userID, subjectID, null, numberOfcorrect, result);
                
            HistoryDAO historyDao = new HistoryDAO();
             historyDao.saveHistory(history, listQuestion);
            
            
            
            
            
            
            
            
            

        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }finally{
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
