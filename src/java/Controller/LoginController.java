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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author TGDD
 */
public class LoginController extends HttpServlet {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        UserDAO u = new UserDAO();
        String note = "";
        if (!u.checkAccount(email)) {
            request.setAttribute("note", "Email not exist!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else if (pass.length() < 6) {
            note = "Password must be at least 6 characters long!";
            request.setAttribute("email", email);
            request.setAttribute("note", note);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            if (u.checkLogin(email, pass)) {
                User user = u.getUser(email, pass);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("Home");
            } else {
                note = "Password incorrect!";
                request.setAttribute("email", email);
                request.setAttribute("note", note);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
//        if (u.checkAccount(email)) {
//
//        } else {
//            request.setAttribute("note", "Email not exist!");
//            request.getRequestDispatcher("Login.jsp").forward(request, response);
//        }
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
