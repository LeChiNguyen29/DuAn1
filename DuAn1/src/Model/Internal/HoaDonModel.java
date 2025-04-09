/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Internal;

/**
 *
 * @author lcinu
 */
public class HoaDonModel {
    private int id;
    private String maHoaDon;
    private String loaiThanhToan;
    private int tongTien;
    private String tenKhachHang;
    private String soDienThoai;
    private String tenNhanVien;
    private int idVoucher;
    private int giaTri;
    private String thoiGianTao;
    private int trangThai;

    public HoaDonModel() {
    }

    public HoaDonModel(int id, String maHoaDon) {
        this.id = id;
        this.maHoaDon = maHoaDon;
    }

    public HoaDonModel(int id, String maHoaDon, int tongTien, String tenNhanVien, String tenKhachHang, String thoiGianTao, int trangThai, String loaiThanhToan, String soDienThoai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.tenKhachHang = tenKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.thoiGianTao = thoiGianTao;
        this.trangThai = trangThai;
        this.loaiThanhToan = loaiThanhToan;
        this.soDienThoai = soDienThoai;
    }

    public HoaDonModel(int id, String maHoaDon, String tenNhanVien, String tenKhachHang, String soDienThoai, String thoiGianTao, String loaiThanhToan, int tongTien, int giaTri, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.thoiGianTao = thoiGianTao;
        this.loaiThanhToan = loaiThanhToan;
        this.tongTien = tongTien;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
    }
    
    public HoaDonModel(int id, String maHoaDon, String loaiThanhToan, int tongTien, String tenKhachHang, String soDienThoai, String tenNhanVien, int idVoucher, String thoiGianTao, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.loaiThanhToan = loaiThanhToan;
        this.tongTien = tongTien;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.tenNhanVien = tenNhanVien;
        this.idVoucher = idVoucher;
        this.thoiGianTao = thoiGianTao;
        this.trangThai = trangThai;
    }

    public HoaDonModel(int id, String maHoaDon, String tenKhachHang, String tenNhanVien, String loaiThanhToan, int tongTien, String thoiGianTao) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.loaiThanhToan = loaiThanhToan;
        this.tongTien = tongTien;
        this.tenKhachHang = tenKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.thoiGianTao = thoiGianTao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
    
    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }
    
    public Object[] toDataRow() {
        return new Object[] {getMaHoaDon(), getTenNhanVien(), getTenKhachHang(), getThoiGianTao(), getTrangThai() == 1 ? "Chưa Thanh Toán":"Đã Thanh Toán"};
    }
    
    public Object[] toDataRowHoaDon() {
        return new Object[] {getMaHoaDon(), getTenNhanVien(), getTenKhachHang(), getThoiGianTao(), getTongTien(), getTrangThai() == 1 ? "Chưa Thanh Toán":"Đã Thanh Toán"};
    }
    
    public Object[] toDataRowHoaDonVoucher() {
        return new Object[] {getMaHoaDon(), getTenNhanVien(), getTenKhachHang(), getLoaiThanhToan(), getTongTien(), getThoiGianTao()};
    }
}
