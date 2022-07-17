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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author TGDD
 */
public class RegisterController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            String confirmPass = request.getParameter("confirmPass");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String zip = request.getParameter("zip");
            String phone = request.getParameter("phone");
            String note = "";

            String validateEmail = "";
            String validatePass = "";
            String validatePhone = "";
            UserDAO u = new UserDAO();
            if (request.getParameter("register") != null) {
                if (!u.checkAccount(email)) {
                    if (pass.equals(confirmPass) && pass.length() >= 6) {
                        if (phone.matches("[0]{1}[3|8|9]{1}[0-9]{8}")) {
                            User newU = new User(name, email, pass, address, city, country, zip, phone);
                            u.register(newU);
                            request.setAttribute("note", " Register Successfully!");
                            request.getRequestDispatcher("Login.jsp").forward(request, response);
                        } else {
                            validatePhone = "Phone Number must begin 09, 03 or 08 and 10 characters in length!";
                        }
                    } else if (pass.equals(confirmPass)) {
                        validatePass = "Password must be at least 6 characters long!";
                    } else {
                        validatePass = "Password was not match!";
                    }
                    request.setAttribute("email_", email);
                } else {
                    validateEmail = "Email existed!";
                }
            }
            request.setAttribute("name_", name);
            request.setAttribute("validateEmail", validateEmail);
            request.setAttribute("validatePass", validatePass);
            request.setAttribute("validatePhone", validatePhone);
            request.setAttribute("address_", address);
            request.setAttribute("city_", city);
            request.setAttribute("country_", country);
            request.setAttribute("zip_", zip);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
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
