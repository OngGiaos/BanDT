/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TGDD
 */
public class User {

    private String name, email, pass, address, city, country, zip, phone;

    public User() {
    }

    public User(String name, String email, String pass, String address, String city, String country, String zip, String phone) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
    }

   

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + ", pass=" + pass + ", address=" + address + ", city=" + city + ", country=" + country + ", zip=" + zip + ", phone=" + phone + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
