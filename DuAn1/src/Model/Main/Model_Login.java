/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Main;

/**
 *
 * @author lcinu
 */
public class Model_Login {
    private int id;
    private String hoTen;
    private String sdt;
    private String pass;

    public Model_Login() {
    }

    public Model_Login(int id, String hoTen, String sdt, String pass) {
        this.id = id;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
