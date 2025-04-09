/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Panel;

/**
 *
 * @author lcinu
 */
public class MauSacPanelModel {
    private int id;
    private String ma;
    private String tenMau;
    private int trangThai;

    public MauSacPanelModel() {
    }

    public MauSacPanelModel(String ma, String tenMau, int trangThai) {
        this.ma = ma;
        this.tenMau = tenMau;
        this.trangThai = trangThai;
    }

    public MauSacPanelModel(int id, String ma, String tenMau, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.tenMau = tenMau;
        this.trangThai = trangThai;
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

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Object[] toDataRow() {
        return new Object[] {getMa(), getTenMau(), getTrangThai() == 0 ? "Còn Hoạt Động":"Dừng Hoạt Động"};
    }
}
