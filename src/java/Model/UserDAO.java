/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

    public void register(User newU) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strInsert = "INSERT INTO [dbo].[User]\n"
                    + "           ([name]\n"
                    + "           ,[email]\n"
                    + "           ,[pass]\n"
                    + "           ,[address]\n"
                    + "           ,[city]\n"
                    + "           ,[country]\n"
                    + "           ,[zip]\n"
                    + "           ,[phone])\n"
                    + "     VALUES\n"
                    + "           ('" + newU.getName() + "'\n"
                    + "           ,'" + newU.getEmail() + "'\n"
                    + "           ,'" + newU.getPass() + "'\n"
                    + "           ,'" + newU.getAddress() + "'\n"
                    + "           ,'" + newU.getCity() + "'\n"
                    + "           ,'" + newU.getCountry() + "'\n"
                    + "           ,'" + newU.getZip() + "'\n"
                    + "           ,'" + newU.getPhone() + "')";
            stm.execute(strInsert);
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        System.out.println("Insert successful!");
    }

    //for admin
    public User getUser(String email) {
        User user = new User();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [User] where email='" + email + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch (Exception e) {
            System.out.println("getUser error: " + e.getMessage());
        }
        return user;
    }

    public User getUser(String email, String pass) {
        User user = new User();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [User] where email='" + email + "' and pass='" + pass + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch (Exception e) {
            System.out.println("getUser error: " + e.getMessage());
        }
        return user;
    }

    public void changePassword(String email, String newpass) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "UPDATE [dbo].[User]\n"
                    + "   SET \n"
                    + "      [pass] = '" + newpass + "'\n"
                    + " WHERE email='" + email + "'";
            stm.executeQuery(strUpdate);
        } catch (Exception e) {
            System.out.println("getUser error: " + e.getMessage());
        }
    }

    public void updateInformation(String newName, String newAddress, String newCity, String newCountry, String newZip, String newPhone, User user) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "UPDATE [dbo].[User]\n"
                    + "   SET [name] = '" + newName + "'\n"
                    + "      ,[address] = '" + newAddress + "'\n"
                    + "      ,[city] = '" + newCity + "'\n"
                    + "      ,[country] = '" + newCountry + "'\n"
                    + "      ,[zip] = '" + newZip + "'\n"
                    + "      ,[phone] = '" + newPhone + "'\n"
                    + " WHERE [email]='" + user.getEmail() + "'";
            stm.execute(strUpdate);

        } catch (Exception e) {
            System.out.println("updateInformation: " + e.getMessage());
        }
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> listUser = new ArrayList<User>();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [User]";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                listUser.add(u);
            }
        } catch (Exception e) {
            System.out.println("updateInformation: " + e.getMessage());
        }
        return listUser;
    }

    public ArrayList<NumberOfItem> getNumberOfItem() {
        ArrayList<NumberOfItem> numList = new ArrayList<NumberOfItem>();
        ArrayList<User> listUser = getAllUser();
        for (User user : listUser) {
            try {
                stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String strSelect = "SELECT COUNT(email)\n"
                        + "  FROM [Assignment].[dbo].[Wishlist]\n"
                        + "  where email='" + user.getEmail() + "'";
                rs = stm.executeQuery(strSelect);
                while (rs.next()) {
                    NumberOfItem num = new NumberOfItem(user.getEmail(), rs.getInt(1));
                    numList.add(num);
                }
            } catch (Exception e) {
                System.out.println("updateInformation: " + e.getMessage());
            }
        }
        return numList;
    }

    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        ArrayList<NumberOfItem> num = ud.getNumberOfItem();
        for (NumberOfItem numberOfItem : num) {
            System.out.println(numberOfItem.getEmail() + ", " + numberOfItem.getNumber());
        }

    }

}
