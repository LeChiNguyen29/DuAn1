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
public class SanPhamModel {
     private int id;
    private String maSanPham;
    private Timestamp ngaySua;
    private Timestamp ngayTao;
    private String tenSanPham;
    private int trangThai;

    public SanPhamModel() {
    }

    public SanPhamModel(int id, String teString) {
        this.id = id;
        this.tenSanPham = teString;
    }

    public SanPhamModel(String maSanPham, Timestamp ngaySua, Timestamp ngayTao, String tenSanPham, int trangThai) {
        this.maSanPham = maSanPham;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
    }

    public SanPhamModel(String maSanPham, Timestamp ngaySua, String tenSanPham, int trangThai) {
        this.maSanPham = maSanPham;
        this.ngaySua = ngaySua;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenSanPham;
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
        final SanPhamModel other = (SanPhamModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maSanPham, other.maSanPham)) {
            return false;
        }
        if (!Objects.equals(this.tenSanPham, other.tenSanPham)) {
            return false;
        }
        if (!Objects.equals(this.ngaySua, other.ngaySua)) {
            return false;
        }
        return Objects.equals(this.ngayTao, other.ngayTao);
    }

    public SanPhamModel(int id, String maSanPham, Timestamp ngaySua, Timestamp ngayTao, String tenSanPham, int trangThai) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

   

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
