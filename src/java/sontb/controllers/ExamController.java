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
import sontb.dtos.QuestionDTO;
import sontb.dtos.SubjectDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ExamController", urlPatterns = {"/ExamController"})
public class ExamController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ExamController.class);
//    public static final String ERROR = "invalid.jsp";
    public static final String EXAM_PAGE = "examPage.jsp";
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
       
        String url = EXAM_PAGE;
        
        try {
            HttpSession ss = request.getSession();
            QuestionDAO dao = new QuestionDAO();
            //Lấy ra đối tượng subject đã chọn thi
            String subjectID = request.getParameter("subjectDoQuiz");
            ArrayList<SubjectDTO> listSubject = new ArrayList<>();
            listSubject =  (ArrayList<SubjectDTO>) ss.getAttribute("LIST_SUBJECT");
            SubjectDTO subject = null;   
            for (SubjectDTO sub: listSubject) {
                if(sub.getSubjectID().equals(subjectID)){
                    subject = sub;
                }
            }
            if(subject!=null){
                //Để render ra giao diện
                ss.setAttribute("SUBJECT_EXAM", subject);
            }
            
            ArrayList<QuestionDTO> listQuestion = new ArrayList<QuestionDTO>();
            listQuestion = dao.getQuestionExam(subject);
            int numberOfQuesiton = subject.getNumberOfQuestions();
            
            ///Xử lí nếu không đủ câu hỏi
           
            
            if(listQuestion!=null){
                ss.setAttribute("LIST_QUESTION",listQuestion);
            }
            
             if(listQuestion.size() < numberOfQuesiton){
                url="MessPage.jsp";
            }
            
            
            
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
