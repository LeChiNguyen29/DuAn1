/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Panel;

/**
 *
 * @author lcinu
 */
public class VoucherPanelModel {
    private int id;
    private String ma;
    private String ten;
    private String loai;
    private int giam;

    public VoucherPanelModel() {
    }

    public VoucherPanelModel(int id, String ma, String ten, String loai, int giam) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.giam = giam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getGiam() {
        return giam;
    }

    public void setGiam(int giam) {
        this.giam = giam;
    }
    
    
    public Object[] toDataRow() {
        return new Object[] {getId(), getMa(), getTen(), getLoai(), getGiam()};
    }
}
