/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author TGDD
 */
public class UserDAO {

    Connection cnn; //Dung de ket noi CSDL
    Statement stm; //thuc hien cac cau lenh SQL
    ResultSet rs; //Luu tru va xu ly du lieu

    public UserDAO() {
        connectDB();
    }

    private void connectDB() {
        try {
            cnn = (new DBContext()).getConnection();
            System.out.println("Connect successfully");
        } catch (Exception e) {
            System.out.println("Connect error: " + e.getMessage());
        }
    }

    public boolean checkLogin(String email, String password) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [User] where email = '" + email + "' and pass = '" + password + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return false;
    }

    public boolean checkAccount(String email) { //for register
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [User] where email = '" + email + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkAccount error: " + e.getMessage());
        }
        return false;
    }

    public void updatePass(String email, String newPass) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "Update [User] set pass = '" + newPass + "' where account = '" + email + "'";
            stm.execute(strUpdate);

        } catch (Exception e) {
            System.out.println("checkDOBByAccount: " + e.getMessage());
        }
    }

    public void register(User newU) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strInsert = "insert into [User] values('" + newU.getName() + "', '" + newU.getEmail() + "', '" + newU.getPass() + "');";
            stm.execute(strInsert);
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        System.out.println("Insert successful!");
    }

    public User getUser(String email, String pass) {
        User user = new User();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [User] where email='" + email + "' and pass='" + pass + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("getUser error: " + e.getMessage());
        }

        return user;
    }
}
