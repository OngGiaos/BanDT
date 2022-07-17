/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author TGDD
 */
public class HistoryDAO {

    Connection cnn; //Dung de ket noi CSDL
    Statement stm; //thuc hien cac cau lenh SQL
    ResultSet rs; //Luu tru va xu ly du lieu

    public HistoryDAO() {
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

    public void toSold(Order order, LocalDate date, String account) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (!checkSold(order.getPhone().getId(), date, account)) {
                String strInsertOrder = "INSERT INTO [dbo].[Sold]\n"
                        + "           ([account]\n"
                        + "           ,[date]\n"
                        + "           ,[pid]\n"
                        + "           ,[pname]\n"
                        + "           ,[pprice]\n"
                        + "           ,[quantity])\n"
                        + "     VALUES\n"
                        + "           ('" + account + "'\n"
                        + "           ,'" + date + "'\n"
                        + "           ,'" + order.getPhone().getId() + "'\n"
                        + "           ,'" + order.getPhone().getName() + "'\n"
                        + "           ,'" + order.getPhone().getPrice() + "'\n"
                        + "           ,'" + order.getQuantity() + "')";
                stm.execute(strInsertOrder);
            } else {
                Sold sold = getSold(order.getPhone().getId(), date, account);
                int newQuantity = sold.getQuantity() + order.getQuantity();
                String strUpdate = "UPDATE [dbo].[Sold]\n"
                        + "   SET [quantity] = '" + newQuantity + "'\n"
                        + " WHERE [pid]='" + order.getPhone().getId() + "' and [date]='" + date + "' and [account]='" + account + "'";
                stm.execute(strUpdate);
            }
        } catch (Exception e) {
            System.out.println("toSold error: " + e.getMessage());
        }
    }

    public void toHistory(User user, History his) {
        ArrayList<Order> order = his.getOrder();
        for (Order order1 : order) {
            toSold(order1, his.getDate(), user.getEmail());
        }
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (checkExistHistory(user, his)) {
            } else {
                String strInsertHistory = "INSERT INTO [dbo].[History]\n"
                        + "           ([name]\n"
                        + "           ,[email]\n"
                        + "           ,[address]\n"
                        + "           ,[city]\n"
                        + "           ,[country]\n"
                        + "           ,[zip]\n"
                        + "           ,[tel]\n"
                        + "           ,[note]\n"
                        + "           ,[date]\n"
                        + "           ,[account])\n"
                        + "     VALUES\n"
                        + "           ('" + his.getName() + "'\n"
                        + "           ,'" + his.getEmail() + "'\n"
                        + "           ,'" + his.getAddress() + "'\n"
                        + "           ,'" + his.getCity() + "'\n"
                        + "           ,'" + his.getCountry() + "'\n"
                        + "           ,'" + his.getZip() + "'\n"
                        + "           ,'" + his.getTel() + "'\n"
                        + "           ,'" + his.getNote() + "'\n"
                        + "           ,'" + his.getDate() + "'\n"
                        + "           ,'" + user.getEmail() + "')";
                stm.execute(strInsertHistory);
            }
        } catch (Exception e) {
            System.out.println("toHistory error: " + e.getMessage());
        }
    }

    public Sold getSold(int pid, LocalDate date, String account) {
        Sold sold = new Sold();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select * from Sold\n"
                    + "where [date]='" + date + "' and [account]='" + account + "' and [pid]='" + pid + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                sold = new Sold(account, date, pid, rs.getString(4), rs.getString(5), rs.getInt(6));
            }
        } catch (Exception e) {
            System.out.println("getSold error: " + e.getMessage());
        }
        return sold;
    }

    private boolean checkSold(int pid, LocalDate date, String account) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select * from Sold\n"
                    + "where [date]='" + date + "' and [account]='" + account + "' and [pid]='" + pid + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkSold error: " + e.getMessage());
        }
        return false;
    }

    public boolean checkExistHistory(User user, History his) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "SELECT *\n"
                    + "  FROM [dbo].[History]\n"
                    + "  WHERE [name] = '" + his.getName() + "'\n"
                    + "      and [email] = '" + his.getEmail() + "'\n"
                    + "      and [address] = '" + his.getAddress() + "'\n"
                    + "      and [city] = '" + his.getCity() + "'\n"
                    + "      and [country] = '" + his.getCountry() + "'\n"
                    + "      and [zip] = '" + his.getZip() + "'\n"
                    + "      and [tel] = '" + his.getTel() + "'\n"
                    + "      and [note] = '" + his.getNote() + "'\n"
                    + "      and [date] = '" + his.getDate() + "'\n"
                    + "      and [account] = '" + user.getEmail() + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkExistHistory error: " + e.getMessage());
        }
        return false;
    }
//    public static void main(String[] args) {
//        HistoryDAO hd = new HistoryDAO();
//        LocalDate date = LocalDate.parse("2022-07-15");
//        Sold sold = hd.getSold(date, "truong9988hiro@gmail.com");
//        System.out.println(sold.getQuantity());
//    }

    public ArrayList<Sold> getAllSold(User user) {
        ArrayList<Sold> allSold = new ArrayList<Sold>();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select * from Sold\n"
                    + "Where [account]='" + user.getEmail() + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                LocalDate date = LocalDate.parse(String.valueOf(rs.getDate(2)));
                Sold sold = new Sold(user.getEmail(), date, rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                allSold.add(sold);
            }
        } catch (Exception e) {
            System.out.println("getAllSold error: " + e.getMessage());
        }
        return allSold;
    }

    public ArrayList<Sold> getAllSold() {
        ArrayList<Sold> allSold = new ArrayList<Sold>();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select * from Sold Order by [date]";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                LocalDate date = LocalDate.parse(String.valueOf(rs.getDate(2)));
                Sold sold = new Sold(rs.getString(1), date, rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                allSold.add(sold);
            }
        } catch (Exception e) {
            System.out.println("getAllSold error: " + e.getMessage());
        }
        return allSold;
    }
}
