/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Internal;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author phamq
 */
public class ChatLieuModel {

    private int id;
    private String maChatLieu;
    private Timestamp ngaySua;
    private Timestamp ngayTao;
    private String tenChatLieu;
    private int trangThai;

    public ChatLieuModel(int id, String maChatLieu, Timestamp ngaySua, Timestamp ngayTao, String tenChatLieu, int trangThai) {
        this.id = id;
        this.maChatLieu = maChatLieu;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public ChatLieuModel() {
    }

    public int getId() {
        return id;
    }

    public Timestamp getNgaySua() {
        return ngaySua;
    }

    public ChatLieuModel(String maChatLieu, Timestamp ngaySua, String tenChatLieu, int trangThai) {
        this.maChatLieu = maChatLieu;
        this.ngaySua = ngaySua;
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }

    public ChatLieuModel(String maChatLieu, Timestamp ngaySua, Timestamp ngayTao, String tenChatLieu, int trangThai) {
        this.maChatLieu = maChatLieu;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenChatLieu;
    }

    public void setNgaySua(Timestamp ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
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

  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChatLieuModel other = (ChatLieuModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maChatLieu, other.maChatLieu)) {
            return false;
        }
        if (!Objects.equals(this.tenChatLieu, other.tenChatLieu)) {
            return false;
        }
        if (!Objects.equals(this.ngaySua, other.ngaySua)) {
            return false;
        }
        return Objects.equals(this.ngayTao, other.ngayTao);
    }

}
