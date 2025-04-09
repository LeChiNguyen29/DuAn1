/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Panel;

/**
 *
 * @author lcinu
 */
public class KichCoPanelModel {
    private int id;
    private String ma;
    private String size;
    private int trangThai;

    public KichCoPanelModel() {
    }

    public KichCoPanelModel(String ma, String size, int trangThai) {
        this.ma = ma;
        this.size = size;
        this.trangThai = trangThai;
    }

    public KichCoPanelModel(int id, String ma, String size, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.size = size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Object[] toDataRow() {
        return new Object[] {getMa(), getSize(), getTrangThai() == 0 ? "Còn Hoạt Động":"Dừng Hoạt Động"};
    }
}
