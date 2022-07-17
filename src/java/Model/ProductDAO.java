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
public class ProductDAO {

    Connection cnn; //Dung de ket noi CSDL
    Statement stm; //thuc hien cac cau lenh SQL
    ResultSet rs; //Luu tru va xu ly du lieu

    public ProductDAO() {
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

    public ArrayList<Product> downloadData() {
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [Phone] ";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("downloadData error: " + e.getMessage());
        }
        return list;
    }

    public Product getPhone(String id) {
        Product phone = new Product();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [Phone] where id='" + id + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                phone = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
            }
        } catch (Exception e) {
            System.out.println("getPhone error: " + e.getMessage());
        }
        return phone;
    }

    public ArrayList<Product> search(String partOfName) {
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from [Phone] where name like '%" + partOfName + "%'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                if (rs.getBoolean(9)) {
                    Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
                    list.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("search error: " + e.getMessage());
        }
        return list;
    }

    public void updateQuantityAndSold(int id, int quantity) {
        Product phone = getPhone(String.valueOf(id));
        int newQuan = phone.getQuantity() - quantity;
        int newSold = phone.getSold() + quantity;
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "UPDATE [dbo].[Phone]\n"
                    + "   SET [sold] = '" + newSold + "'\n"
                    + "      ,[quantity] = '" + newQuan + "'\n"
                    + " WHERE id='" + id + "'";
            stm.execute(strUpdate);
        } catch (Exception e) {
            System.out.println("updateQuantityAndSold error: " + e.getMessage());
        }
    }

    public void setShow(String id, String isShow) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "UPDATE [dbo].[Phone]\n"
                    + "   SET \n"
                    + "      [show] = '" + isShow + "'"
                    + " WHERE id='" + id + "'";
            stm.execute(strUpdate);
        } catch (Exception e) {
            System.out.println("setShow error: " + e.getMessage());
        }
    }

    public void updateProduct(Product newP) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strUpdate = "UPDATE [dbo].[Phone]\n"
                    + "   SET [creator] = '" + newP.getCreator() + "'\n"
                    + "      ,[name] = '" + newP.getName() + "'\n"
                    + "      ,[price] = '" + newP.getPrice() + "'\n"
                    + "      ,[information] = '" + newP.getInformation() + "'\n"
                    + "      ,[img] = '" + newP.getImg() + "'\n"
                    + "      ,[sold] = '" + newP.getSold() + "'\n"
                    + "      ,[quantity] = '" + newP.getQuantity() + "'\n"
                    + " WHERE id='" + newP.getId() + "'";
            stm.execute(strUpdate);
        } catch (Exception e) {
            System.out.println("updateProduct error: " + e.getMessage());
        }
    }
}
