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
import sontb.dtos.HistoryDTO;
import sontb.dtos.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "HistoryController", urlPatterns = {"/HistoryController"})
public class HistoryController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(HistoryController.class);
public static final String HISTORY_PAGE = "historyPage.jsp";
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
       
        String sql = HISTORY_PAGE;
        
        try {
            HistoryDAO dao = new HistoryDAO();
            HttpSession ss = request.getSession();
            UserDTO user = (UserDTO) ss.getAttribute("USER");
            String userID = user.getUserID();
            
            String subjectID =  request.getParameter("subjectHistorySearch");
            if(subjectID == null){
                subjectID = "";
            }
            request.setAttribute("SUBJECT_HISTORY_SEARCH", subjectID);
            
            //xu li phan trang 
            
            int numberOfPage = dao.getHistoryCount(userID, subjectID);
           request.setAttribute("NUMBER_OF_PAGE", numberOfPage);
           
           
            String activePage = request.getParameter("activePage");
            if (activePage == null) {
                //chua click
                activePage = "1";
            }
            request.setAttribute("ACTIVE_PAGE", activePage);
            
            
            //xu li du lieu ra
            ArrayList<HistoryDTO> listHistory = new ArrayList<HistoryDTO>();
            listHistory = dao.getHistory(userID, subjectID, activePage);
            request.setAttribute("LIST_HISTORY", listHistory);
            
            
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }finally{
            request.getRequestDispatcher(sql).forward(request, response);
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
