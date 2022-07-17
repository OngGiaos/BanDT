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
public class updateProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().removeAttribute("change");
        String save = request.getParameter("save");
        User user = (User) request.getSession().getAttribute("user");
        if (save != null) {
            String newName = request.getParameter("newName");
            String newAddress = request.getParameter("newAddress");
            String newCity = request.getParameter("newCity");
            String newCountry = request.getParameter("newCountry");
            String newZip = request.getParameter("newZip");
            String newPhone = request.getParameter("newPhone");

            //updateUser Here
            if (newPhone.matches("[0]{1}[3|8|9]{1}[0-9]{8}")) {
                UserDAO ud = new UserDAO();
                ud.updateInformation(newName, newAddress, newCity, newCountry, newZip, newPhone, user);
                User newU = ud.getUser(user.getEmail(), user.getPass());

                request.getSession().setAttribute("user", newU);
                request.getSession().setAttribute("update", false);
            }
        } else {
            request.getSession().setAttribute("update", true);
        }
        request.getRequestDispatcher("User.jsp").forward(request, response);
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
