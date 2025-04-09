/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Panel;

/**
 *
 * @author lcinu
 */
public class ChatLieuPanelModel {
    private int id;
    private String ma;
    private String tenChatLieu;
    private int trangThai;

    public ChatLieuPanelModel() {
    }

    public ChatLieuPanelModel(String ma, String tenChatLieu, int trangThai) {
        this.ma = ma;
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }

    public ChatLieuPanelModel(int id, String ma, String tenChatLieu, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.tenChatLieu = tenChatLieu;
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

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Object[] toDataRow() {
        return new Object[] {getMa(), getTenChatLieu(), getTrangThai() == 0 ? "Còn Hoạt Động":"Dừng Hoạt Động"};
    }
}
