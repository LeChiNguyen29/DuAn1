/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Internal;

/**
 *
 * @author ADMIN
 */
public class VoucherModel {
    private int id;
    private String maVoucher;
    private String tenVoucher;
    private String loaiVoucher;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int giaTri;
    private String ngayTao;
    private String ngaySua;

    
    public VoucherModel() {
    }

    public VoucherModel(int id, String maVoucher, String tenVoucher, String loaiVoucher, String ngayBatDau, String ngayKetThuc, int giaTri, String ngayTao, String ngaySua, int trangThai) {
        this.id = id;
        this.maVoucher = maVoucher;
        this.tenVoucher = tenVoucher;
        this.loaiVoucher = loaiVoucher;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaTri = giaTri;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    private int trangThai;

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public String getLoaiVoucher() {
        return loaiVoucher;
    }

    public void setLoaiVoucher(String loaiVoucher) {
        this.loaiVoucher = loaiVoucher;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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
    
    
    public Object[] toDataRow(){
        return new Object[] {
            this.getMaVoucher(), this.getTenVoucher(), this.getLoaiVoucher(), this.getGiaTri(), this.getTrangThai() == 0 ? "Đang Áp Dụng":"Ngừng Áp Dụng"
        };
    }
    
}