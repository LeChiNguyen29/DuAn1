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
public class DanhMucModel {
    private int id;
    private String maDanhMuc;
    private Timestamp ngayTao;
    private Timestamp ngaySua;
    private String tenDanhMuc;
    private int trangThai;
    public DanhMucModel() {
    }

    public DanhMucModel(String maDanhMuc, Timestamp ngaySua, String tenDanhMuc, int trangThai) {
        this.maDanhMuc = maDanhMuc;
        this.ngaySua = ngaySua;
        this.tenDanhMuc = tenDanhMuc;
        this.trangThai = trangThai;
    }

    public DanhMucModel(String maDanhMuc, Timestamp ngayTao, Timestamp ngaySua, String tenDanhMuc, int trangThai) {
        this.maDanhMuc = maDanhMuc;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tenDanhMuc = tenDanhMuc;
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenDanhMuc;
    }

    public DanhMucModel(int id, String maDanhMuc, Timestamp ngayTao, Timestamp ngaySua, String tenDanhMuc, int trangThai) {
        this.id = id;
        this.maDanhMuc = maDanhMuc;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.tenDanhMuc = tenDanhMuc;
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }


    public int getId() {
        return id;
    }


    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Timestamp getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Timestamp ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final DanhMucModel other = (DanhMucModel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.trangThai != other.trangThai) {
            return false;
        }
        if (!Objects.equals(this.maDanhMuc, other.maDanhMuc)) {
            return false;
        }
        if (!Objects.equals(this.tenDanhMuc, other.tenDanhMuc)) {
            return false;
        }
        if (!Objects.equals(this.ngayTao, other.ngayTao)) {
            return false;
        }
        return Objects.equals(this.ngaySua, other.ngaySua);
    }
    
}
