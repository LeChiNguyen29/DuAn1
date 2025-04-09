/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Panel;

/**
 *
 * @author lcinu
 */
public class DanhMucPanelModel {
    private int id;
    private String ma;
    private String tenDanhMuc;
    private int trangThai;

    public DanhMucPanelModel() {
    }
    
    public DanhMucPanelModel(String ma, String tenDanhMuc, int trangThai) {
        this.ma = ma;
        this.tenDanhMuc = tenDanhMuc;
        this.trangThai = trangThai;
    }

    public DanhMucPanelModel(int id, String ma, String tenDanhMuc, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.tenDanhMuc = tenDanhMuc;
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

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Object[] toDataRow() {
        return new Object[] {getMa(), getTenDanhMuc(), getTrangThai() == 0 ? "Còn Hoạt Động":"Dừng Hoạt Động"};
    }
}
