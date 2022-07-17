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
public class OrderDAO {

    Connection cnn; //Dung de ket noi CSDL
    Statement stm; //thuc hien cac cau lenh SQL
    ResultSet rs; //Luu tru va xu ly du lieu

    public OrderDAO() {
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

    public ArrayList<Order> orderData(User user) {
        ArrayList<Order> order = new ArrayList<Order>();
        ProductDAO p = new ProductDAO();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [Order] where email='" + user.getEmail() + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                Product phone = p.getPhone(String.valueOf(rs.getInt(2)));
                int quantity = rs.getInt(3);
                Order o = new Order(user, phone, quantity);
                order.add(o);
            }
        } catch (Exception e) {
            System.out.println("Product error: " + e.getMessage());
        }
        return order;
    }

    public long getInteger(String price) {
        long p = 0;
        String pstr = "";
        for (int i = 0; i < price.length(); i++) {
            if (price.charAt(i) != '.') {
                pstr += price.charAt(i) + "";
            }
        }
        try {
            p = Long.parseLong(pstr);
        } catch (Exception e) {
        }
        return p;

    }

    public long getTotal(ArrayList<Order> order) {
        long total = 0;
        for (Order order1 : order) {
            total += getInteger(order1.getPhone().getPrice()) * order1.getQuantity();
        }
        return total;
    }
    public String convertPrice(String price){
        String converted = "";
        for (int i = 0; i < price.length(); i++) {
            if(Character.isDigit(price.charAt(i))){
                converted+="" + price.charAt(i);
            }
        }
        return converted;
    }
    public String convertPrice(long t) {
        String price = String.valueOf(t);
        String converted = "";
        if (price.length() < 4) {
            return price;
        } else {
            int firstlength = price.length() % 3;
            if (firstlength == 0) {
                firstlength = 3;
            }
            String first = price.substring(0, firstlength);
            for (int i = price.length() - 1; i >= 3; i -= 3) {
                converted = "." + price.substring(i - 2, i + 1) + converted;
            }
            converted = first + converted;
        }
        return converted;
    }
    //1999.000
//    public static void main(String[] args) {
//        String price = "1987.999.000";
//        OrderDAO o = new OrderDAO();
//        long p = o.getInteger(price);
//        System.out.println(p);
//        System.out.println(o.convertPrice(p));
//    }

    public ArrayList<Order> wishlist(User user) {
        ArrayList<Order> wishlist = new ArrayList<Order>();
        ProductDAO p = new ProductDAO();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [Wishlist] where email='" + user.getEmail() + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                Product phone = p.getPhone(String.valueOf(rs.getInt(2)));
                int quantity = rs.getInt(3);
                Order w = new Order(user, phone, quantity);
                wishlist.add(w);
            }
        } catch (Exception e) {
            System.out.println("Product error: " + e.getMessage());
        }
        return wishlist;
    }

    public void deleteProductFromCart(User user, String id) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strDelete = "DELETE FROM [dbo].[Order]\n"
                    + "      WHERE email='" + user.getEmail() + "' and pid='" + id + "'";
            stm.execute(strDelete);
        } catch (Exception e) {
            System.out.println("Product error: " + e.getMessage());
        }
    }

    public void insertOrder(User user, String id, String quantity) {
        ProductDAO pd = new ProductDAO();
        Product porder = pd.getPhone(id);
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (checkExist(user, id)) {
                Order phone = getOrder(user, id);
                int newQuantity = phone.getQuantity() + Integer.parseInt(quantity);
                if (porder.getQuantity() >= newQuantity) {
                    String strUpdate = "UPDATE [dbo].[Order]\n"
                            + "   SET \n"
                            + "      [quantity] = '" + newQuantity + "'\n"
                            + " WHERE email='" + user.getEmail() + "' and pid='" + id + "'";
                    stm.execute(strUpdate);
                }
            } else {
                if (porder.getQuantity() >= Integer.parseInt(quantity)) {
                    String strInsert = "INSERT INTO [dbo].[Order]\n"
                            + "           ([email]\n"
                            + "           ,[pid]\n"
                            + "           ,[quantity])\n"
                            + "     VALUES\n"
                            + "           ('" + user.getEmail() + "'\n"
                            + "           ,'" + id + "'\n"
                            + "           ,'" + quantity + "')";
                    stm.execute(strInsert);
                }
            }
        } catch (Exception e) {
            System.out.println("inserOrder error: " + e.getMessage());
        }
    }

    public boolean checkExist(User user, String id) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "SELECT [email]\n"
                    + "      ,[pid]\n"
                    + "      ,[quantity]\n"
                    + "  FROM [dbo].[Order]"
                    + "WHERE email='" + user.getEmail() + "' and pid='" + id + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkExist error: " + e.getMessage());
        }
        return false;
    }

    public Order getOrder(User user, String id) {
        Order order = new Order();
        ProductDAO p = new ProductDAO();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "SELECT [email]\n"
                    + "      ,[pid]\n"
                    + "      ,[quantity]\n"
                    + "  FROM [dbo].[Order]"
                    + "WHERE email='" + user.getEmail() + "' and pid='" + id + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                Product phone = p.getPhone(id);
                order = new Order(user, phone, rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println("getOrder error: " + e.getMessage());
        }
        return order;
    }

    public void deleteProductFromWishlist(User user, String id) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strDelete = "DELETE FROM [dbo].[Wishlist]\n"
                    + "      WHERE email='" + user.getEmail() + "' and pid='" + id + "'";
            stm.execute(strDelete);
        } catch (Exception e) {
            System.out.println("Product error: " + e.getMessage());
        }
    }

    public void insertWishlist(User user, String id, String quantity) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            if (!checkExistWish(user, id)) {
                String strInsert = "INSERT INTO [dbo].[Wishlist]\n"
                        + "           ([email]\n"
                        + "           ,[pid]\n"
                        + "           ,[quantity])\n"
                        + "     VALUES\n"
                        + "           ('" + user.getEmail() + "'\n"
                        + "           ,'" + id + "'\n"
                        + "           ,'" + quantity + "')";
                stm.execute(strInsert);
            }
        } catch (Exception e) {
            System.out.println("inserOrder error: " + e.getMessage());
        }
    }

    private boolean checkExistWish(User user, String id) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "SELECT [email]\n"
                    + "      ,[pid]\n"
                    + "      ,[quantity]\n"
                    + "  FROM [dbo].[Wishlist]"
                    + "WHERE email='" + user.getEmail() + "' and pid='" + id + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkExist error: " + e.getMessage());
        }
        return false;
    }

    public void deleteAllOrder(User user) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strDelete = "DELETE FROM [dbo].[Order]\n"
                    + "      WHERE [email]='" + user.getEmail() + "'";
            stm.execute(strDelete);
        } catch (Exception e) {
            System.out.println("Product error: " + e.getMessage());
        }
    }

    public void tranferToWish(User user) {
        ArrayList<Order> cartList = orderData(user);
        for (Order cart : cartList) {
            insertWishlist(user, String.valueOf(cart.getPhone().getId()), "1");
        }
        deleteAllOrder(user);
    }

}
