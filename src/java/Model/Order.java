/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TGDD
 */
public class Order {
    User user;
    Product phone;
    int quantity;

    public Order() {
    }
    
    
    public Order(User user, Product phone, int quantity) {
        this.user = user;
        this.phone = phone;
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getPhone() {
        return phone;
    }

    public void setPhone(Product phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
