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
import org.apache.log4j.Logger;
import sontb.daos.QuestionDAO;
import sontb.dtos.AnswerDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateController.class);
//public static final String ERROR = "invalid.jsp";

    public static final String CREATE_PAGE = "createPage.jsp";

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

        String url = CREATE_PAGE;
        try {
            String questionContent = request.getParameter("questionContentCREATE");
            String subject = request.getParameter("txtSubjectCREATE");

            String answer1 = request.getParameter("txtAnswer1CREATE");
            String answer2 = request.getParameter("txtAnswer2CREATE");
            String answer3 = request.getParameter("txtAnswer3CREATE");
            String answer4 = request.getParameter("txtAnswer4CREATE");
            String NameOfAnswerIsCorrect = request.getParameter("txtAnswerCorrectCREATE");
            //bỏ answer vào list để kiểm tra cái nào đúng cho nhanh
            //từ đó tạo 1 AnswerDTO rồi lưu vào ArrayListAnswer để lưu database

            if (questionContent != null || subject != null) {

                //AnswerDTO rồi lưu vào ArrayListAnswer để lưu database
                ArrayList<AnswerDTO> listAnswer = new ArrayList<>();

                if (NameOfAnswerIsCorrect.equals("txtAnswer1CREATE")) {
                    AnswerDTO answer = new AnswerDTO(null, null, answer1, true, null);
                    listAnswer.add(answer);
                } else {
                    AnswerDTO answer = new AnswerDTO(null, null, answer1, false, null);
                    listAnswer.add(answer);
                }

                if (NameOfAnswerIsCorrect.equals("txtAnswer2CREATE")) {
                    AnswerDTO answer = new AnswerDTO(null, null, answer2, true, null);
                    listAnswer.add(answer);
                } else {
                    AnswerDTO answer = new AnswerDTO(null, null, answer2, false, null);
                    listAnswer.add(answer);
                }

                if (NameOfAnswerIsCorrect.equals("txtAnswer3CREATE")) {
                    AnswerDTO answer = new AnswerDTO(null, null, answer3, true, null);
                    listAnswer.add(answer);
                } else {
                    AnswerDTO answer = new AnswerDTO(null, null, answer3, false, null);
                    listAnswer.add(answer);
                }

                if (NameOfAnswerIsCorrect.equals("txtAnswer4CREATE")) {
                    AnswerDTO answer = new AnswerDTO(null, null, answer4, true, null);
                    listAnswer.add(answer);
                } else {
                    AnswerDTO answer = new AnswerDTO(null, null, answer4, false, null);
                    listAnswer.add(answer);
                }

                QuestionDAO dao = new QuestionDAO();
                int rs = dao.createQuestion(questionContent, subject, listAnswer);
                if (rs != -1) {
                    request.setAttribute("MESSAGE_CREATE", "Create successful");
                }

            }

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
