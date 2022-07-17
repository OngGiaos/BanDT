/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TGDD
 */
public class ForgotController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String createNewPass = request.getParameter("createNewPass");
        UserDAO ud = new UserDAO();
        if (createNewPass != null) {
            String note = "";
            String notePhone = "";
            if (ud.checkAccount(email)) {
                User user = ud.getUser(email);
                if (phone.equals(user.getPhone())) {
                    int max = 999999;
                    int min = 100000;
                    int range = max - min + 1;
                    String newpass = String.valueOf((int) (Math.random() * range) + min);
                    ud.changePassword(email, newpass);
                    request.setAttribute("email", email);
                    request.setAttribute("newpass", newpass);
                    request.setAttribute("phoneNumber", phone);
                    note = "Successful!";
                } else{
                    notePhone = "Phone number incorrect!";
                }
            } else {
                note = "Email not exist!";
            }
            request.setAttribute("note", note);
            request.setAttribute("notePhone", notePhone);
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
