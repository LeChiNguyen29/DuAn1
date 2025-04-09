/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Internal;

/**
 *
 * @author lcinu
 */
public class ThongKeModel {
    private int id;
    private String maSanPham;
    private String tenSanPham;
    private String ngayTao;
    private int donGia;
    private int soLuong;
    private int soHoaDon;
    private int tongTien;
    private String moTa;
    private int trangThai;

    public ThongKeModel() {
    }
    
    public ThongKeModel(int id, String maSanPham, String tenSanPham,int donGia, int soLuong,String ngayTao, int trangThai) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public ThongKeModel(String maSanPham, String tenSanPham, int donGia, int soLuong, int tongTien, String ngayTao, int trangThai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.ngayTao = ngayTao;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }
    

    public ThongKeModel(int soHoaDon, int tongTien) {
        this.soHoaDon = soHoaDon;
        this.tongTien = tongTien;
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    
    public Object[] toDataRowThongKe() {
        return new Object[] {getMaSanPham(),getTenSanPham(), getDonGia(), getSoLuong()};
    }
}
