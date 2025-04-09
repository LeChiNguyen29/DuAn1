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
public class NhaSanXuatModel {
     private int id;
    private String maNSX;
    private Timestamp ngaySua;
    private Timestamp ngayTao;
    private String tenNSX;
    private int trangThai;

    public NhaSanXuatModel() {
    }

    public NhaSanXuatModel(String maNSX, Timestamp ngaySua, String tenNSX, int trangThai) {
        this.maNSX = maNSX;
        this.ngaySua = ngaySua;
        this.tenNSX = tenNSX;
        this.trangThai = trangThai;
    }

    public NhaSanXuatModel(String maNSX, Timestamp ngaySua, Timestamp ngayTao, String tenNSX, int trangThai) {
        this.maNSX = maNSX;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenNSX = tenNSX;
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
return  tenNSX;
    }

    public NhaSanXuatModel(int id, String maNSX, Timestamp ngaySua, Timestamp ngayTao, String tenNSX, int trangThai) {
        this.id = id;
        this.maNSX = maNSX;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenNSX = tenNSX;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

   

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
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

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
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
        final NhaSanXuatModel other = (NhaSanXuatModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maNSX, other.maNSX)) {
            return false;
        }
        if (!Objects.equals(this.tenNSX, other.tenNSX)) {
            return false;
        }
        if (!Objects.equals(this.ngaySua, other.ngaySua)) {
            return false;
        }
        return Objects.equals(this.ngayTao, other.ngayTao);
    }
    
}
