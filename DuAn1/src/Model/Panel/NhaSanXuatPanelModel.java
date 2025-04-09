/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Panel;

/**
 *
 * @author lcinu
 */
public class NhaSanXuatPanelModel {
    private int id;
    private String ma;
    private String tenNhaSanXuat;
    private int trangThai;

    public NhaSanXuatPanelModel() {
    }

    public NhaSanXuatPanelModel(String ma, String tenNhaSanXuat, int trangThai) {
        this.ma = ma;
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.trangThai = trangThai;
    }

    public NhaSanXuatPanelModel(int id, String ma, String tenNhaSanXuat, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.tenNhaSanXuat = tenNhaSanXuat;
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

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[] {getMa(), getTenNhaSanXuat(), getTrangThai() == 0 ? "Còn Hoạt Động":"Dừng Hoạt Động"};
    }
}
