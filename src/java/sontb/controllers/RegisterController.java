/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sontb.daos.UsersDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

    public static final String REGIS = "register.jsp";

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

        String url = REGIS;
        try {
            UsersDAO dao = new UsersDAO();

            String name = request.getParameter("txtName");
            String email = request.getParameter("txtEmail");
            String pass1 = request.getParameter("txtPassword");
            String pass2 = request.getParameter("txtPassword2");

            request.setAttribute("NAME_REGISTER", name);
            request.setAttribute("NAME_EMAIL", email);

            if (name != null) {  // Check null la lần đầu từ trang login qua chưa làm gì, vì nếu k qua
                //COntroller thì vi phạm mvc2
                if (!pass1.equals(pass2)) {
                    request.setAttribute("MESSAGE_PASS", "Password's not same");
                } else {
                    //Check id có chưa
                    if (dao.checkUser(email) == 1) {  //Co roi
                        request.setAttribute("MESSAGE_EMAIL", "Email already exists");
                    } else {
                        String passwordEn = sontb.utils.GFG.toHexString(sontb.utils.GFG.getSHA(pass1));
                        int rs = dao.createuser(email, passwordEn, name);
                        if (rs != -1) {
                            request.setAttribute("MESSAGE_SUCCESS", "Create successfully");
                            //thành công thì xóa đi chứ k nó lại liện
                            request.setAttribute("NAME_REGISTER", null);
                            request.setAttribute("NAME_EMAIL", null);
                        } else {
                            request.setAttribute("MESSAGE_SUCCESS", "failed");
                        }
                    }
                }
            } else {
                url = REGIS;
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
