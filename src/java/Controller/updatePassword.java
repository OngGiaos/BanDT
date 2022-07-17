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
public class updatePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirm = request.getParameter("confirm");
        User user = (User) request.getSession().getAttribute("user");
        if (request.getParameter("changePass") != null) {
            if (newPassword.length() < 6 || oldPassword.length() < 6 || confirm.length() < 6) {
                request.setAttribute("note", "Password must be at least 6 characters long!");
            } else if (user.getPass().equals(oldPassword) && newPassword.equals(confirm)) {
                UserDAO ud = new UserDAO();
                ud.changePassword(user.getEmail(), newPassword);
                User userUpdated = ud.getUser(user.getEmail(), newPassword);
                request.getSession().setAttribute("user", userUpdated);
                request.setAttribute("note", "Change Password Success!");
                request.getSession().setAttribute("change", false);
            } else if (user.getPass().equals(oldPassword)) {
                request.setAttribute("note", "New Password does not match!");
            } else {
                request.setAttribute("note", "Old Password incorrect!");
            }
            request.getRequestDispatcher("UserInformation").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
