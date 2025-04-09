/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Internal;

/**
 *
 * @author lcinu
 */
public class Model_KhuyenMai {
    
    private String maKhuyenMai;
    private String tenKhuyenMai;
    private String loaiKhuyenmai;
    private int giaTri;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String ngayTao;
    private String ngaySua;
    private int trangThai;

    public Model_KhuyenMai() {
    }

    public Model_KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String loaiKhuyenmai, int giaTri, int trangThai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenmai = loaiKhuyenmai;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
    }

    public Model_KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String loaiKhuyenmai, int giaTri, String ngayBatDau, String ngayKetThuc, int trangThai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenmai = loaiKhuyenmai;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public Model_KhuyenMai(String maKhuyenMai, String tenKhuyenMai, String loaiKhuyenmai, int giaTri, String ngayBatDau, String ngayKetThuc, String ngayTao, String ngaySua, int trangThai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.loaiKhuyenmai = loaiKhuyenmai;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getLoaiKhuyenmai() {
        return loaiKhuyenmai;
    }

    public void setLoaiKhuyenmai(String loaiKhuyenmai) {
        this.loaiKhuyenmai = loaiKhuyenmai;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Object[] toDataRow() {
        return new Object[] {getMaKhuyenMai(), getTenKhuyenMai(), getLoaiKhuyenmai(), getGiaTri(), getTrangThai() == 0 ? "Đang Áp Dụng":"Ngừng Áp Dụng"};
    }

    public Object[] to() {
        return new Object[] {getMaKhuyenMai(), getTenKhuyenMai(), getLoaiKhuyenmai(), getGiaTri(), getNgayBatDau(), getNgayKetThuc(), getNgaySua(), getNgayTao(), getTrangThai()};
    }
    
}
