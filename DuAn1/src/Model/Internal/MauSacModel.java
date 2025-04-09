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
public class MauSacModel {
     private int id;
    private String maMauSac;
    private Timestamp ngaySua;

   
    private Timestamp ngayTao;
    private String tenMauSac;
    private int trangThai;
 @Override
    public String toString() {
        return tenMauSac;
    }

    public MauSacModel(String maMauSac, Timestamp ngaySua, Timestamp ngayTao, String tenMauSac, int trangThai) {
        this.maMauSac = maMauSac;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenMauSac = tenMauSac;
        this.trangThai = trangThai;
    }
    public MauSacModel(int id, String maMauSac, Timestamp ngaySua, Timestamp ngayTao, String tenMauSac, int trangThai) {
        this.id = id;
        this.maMauSac = maMauSac;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenMauSac = tenMauSac;
        this.trangThai = trangThai;
    }

    public MauSacModel() {
    }

    public int getId() {
        return id;
    }


    public String getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public Timestamp getNgaySua() {
        return ngaySua;
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

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenKichCo) {
        this.tenMauSac = tenKichCo;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final MauSacModel other = (MauSacModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maMauSac, other.maMauSac)) {
            return false;
        }
        if (!Objects.equals(this.tenMauSac, other.tenMauSac)) {
            return false;
        }
        if (!Objects.equals(this.ngaySua, other.ngaySua)) {
            return false;
        }
        return Objects.equals(this.ngayTao, other.ngayTao);
    }

}
