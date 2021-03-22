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
import sontb.daos.QuestionDAO;
import sontb.daos.SubjectDAO;
import sontb.dtos.QuestionDTO;
import sontb.dtos.SubjectDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AdminController.class);
//    public static final String ERROR = "invalid.jsp";
    public static final String ADMIN_PAGE = "adminPage.jsp";

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

        String url = ADMIN_PAGE;
        try {

            HttpSession ss = request.getSession();
            QuestionDAO questionDao = new QuestionDAO();

            //Lấy Subject lên bỏ dô mảng để render cbx ở giao diện
            ArrayList<SubjectDTO> listSubject = new ArrayList<>();
            SubjectDAO subjectDAO = new SubjectDAO();
            listSubject = subjectDAO.getSubject();
            if (listSubject != null) {
                ss.setAttribute("LIST_SUBJECT", listSubject);
            }
            ArrayList<String> listStatus = new ArrayList<>();
            // gán cứng status để đưa ra giao diện
            listStatus.add("active");
            listStatus.add("deActive");
            ss.setAttribute("LIST_STATUS", listStatus);

            //--------------XỬ LÍ SEARCH---------------------
            String txtSubject = request.getParameter("txtSubjectSEARCH");
            String txtStatus = request.getParameter("txtStatusSEARCH");
            String txtName = request.getParameter("txtNameSEARCH");
            String optionSearch = request.getParameter("optionSEARCH"); //Để so sánh tìm ra option search đc chọn

            String searchValueToDataBase = "";
            String optionSearchToDataBase = "";

            //Lần đầu load 3 ba bằng null thì load hết
            if (optionSearch == null && txtSubject == null && txtName == null && txtStatus == null) {
//                ss.setAttribute("SUBJECT_SEARCH", "");
//                ss.setAttribute("STATUS_SEARCH", "");
//                ss.setAttribute("NAME_SEARCH", "");
//                ss.setAttribute("OPTION_SEARCH", "");

                searchValueToDataBase = "";
                optionSearchToDataBase = "";

            } else {

                request.setAttribute("subjectSEARCHED", txtSubject);
                request.setAttribute("statusSEARCHED", txtStatus);
                request.setAttribute("nameSEARCHED", txtName);
                request.setAttribute("optionSEARCHED", optionSearch);

                //Xử lí option search by Subject
                if (optionSearch.equals("txtSubjectSEARCH")) {
                    if (txtSubject.equals("Subject") || txtSubject.equals("")) {
                        // Có chọn kiểu search nhưng k chọn giá trị
                        // Search all
                        searchValueToDataBase = "";
                        optionSearchToDataBase = "";
                    } else {
                        searchValueToDataBase = txtSubject;
                        optionSearchToDataBase = optionSearch;
                    }
                }
                //Xử lí option search by Status
                if (optionSearch.equals("txtStatusSEARCH")) {
                    if (txtStatus.equals("Status")  || txtStatus.equals("")) {
                        // Có chọn kiểu search nhưng k chọn giá trị
                        // Search all
                        searchValueToDataBase = "";
                        optionSearchToDataBase = "";
                    } else {
                        searchValueToDataBase = txtStatus;
                        optionSearchToDataBase = optionSearch;
                    }
                }

                //Xử lí option search by name
                if (optionSearch.equals("txtNameSEARCH")) {
                    if (txtName.trim().equals("") || txtName == null) {
                        // Có chọn kiểu search nhưng k chọn giá trị
                        // Search all
                        searchValueToDataBase = "";
                        optionSearchToDataBase = "";
                    } else {
                        searchValueToDataBase = txtName;
                        optionSearchToDataBase = optionSearch;
                    }
                }
            }

            //Xử lí phân trang 
            int count = questionDao.getCount(optionSearchToDataBase, searchValueToDataBase);
            request.setAttribute("NUMBER_OF_PAGE", count);

            String activePage = request.getParameter("activePage");
            if (activePage == null) {
                //chua click
                activePage = "1";
            }
            request.setAttribute("ACTIVE_PAGE", activePage);

            //Xử lí list question ra giao diện
            ArrayList<QuestionDTO> listQuestion = new ArrayList<>();
            listQuestion = questionDao.getQuestions(activePage,optionSearchToDataBase,searchValueToDataBase);
            if (listQuestion != null) {
                ss.setAttribute("LIST_QUESTION", listQuestion);
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
//            request.getRequestDispatcher(url).forward(request, response);
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
