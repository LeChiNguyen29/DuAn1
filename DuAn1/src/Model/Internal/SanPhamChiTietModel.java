/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Internal;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author phamq
 */
public class SanPhamChiTietModel {

    private int id;
    private String anhSanPham;
    private BigDecimal giaSanPham;
    private String maSanPhamCt;
    private String moTa;
    private int soLuong;
    private int trangThai;
    private ChatLieuModel chatLieuModel;
    private DanhMucModel danhMucModel;
    private KichCoModel kichCoModel;
    private MauSacModel mauSacModel;
    private NhaSanXuatModel nhaSanXuatModel;
    private SanPhamModel sanPhamModel;
    private Timestamp ngaySua;
    private Timestamp ngayTao;

    public SanPhamChiTietModel() {
    }

    public SanPhamChiTietModel(String anhSanPham, BigDecimal giaSanPham, String maSanPhamCt, String moTa, int soLuong, int trangThai, ChatLieuModel chatLieuModel, DanhMucModel danhMucModel, KichCoModel kichCoModel, MauSacModel mauSacModel, NhaSanXuatModel nhaSanXuatModel, SanPhamModel sanPhamModel, Timestamp ngaySua, Timestamp ngayTao) {
        this.anhSanPham = anhSanPham;
        this.giaSanPham = giaSanPham;
        this.maSanPhamCt = maSanPhamCt;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.chatLieuModel = chatLieuModel;
        this.danhMucModel = danhMucModel;
        this.kichCoModel = kichCoModel;
        this.mauSacModel = mauSacModel;
        this.nhaSanXuatModel = nhaSanXuatModel;
        this.sanPhamModel = sanPhamModel;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
    }

    public SanPhamChiTietModel(int id, String anhSanPham, BigDecimal giaSanPham, String maSanPhamCt, String moTa, int soLuong, int trangThai, ChatLieuModel chatLieuModel, DanhMucModel danhMucModel, KichCoModel kichCoModel, MauSacModel mauSacModel, NhaSanXuatModel nhaSanXuatModel, SanPhamModel sanPhamModel, Timestamp ngaySua, Timestamp ngayTao) {
        this.id = id;
        this.anhSanPham = anhSanPham;
        this.giaSanPham = giaSanPham;
        this.maSanPhamCt = maSanPhamCt;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.chatLieuModel = chatLieuModel;
        this.danhMucModel = danhMucModel;
        this.kichCoModel = kichCoModel;
        this.mauSacModel = mauSacModel;
        this.nhaSanXuatModel = nhaSanXuatModel;
        this.sanPhamModel = sanPhamModel;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public BigDecimal getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(BigDecimal giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getMaSanPhamCt() {
        return maSanPhamCt;
    }

    public void setMaSanPhamCt(String maSanPhamCt) {
        this.maSanPhamCt = maSanPhamCt;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public ChatLieuModel getChatLieuModel() {
        return chatLieuModel;
    }

    public void setChatLieuModel(ChatLieuModel chatLieuModel) {
        this.chatLieuModel = chatLieuModel;
    }

    public DanhMucModel getDanhMucModel() {
        return danhMucModel;
    }

    public void setDanhMucModel(DanhMucModel danhMucModel) {
        this.danhMucModel = danhMucModel;
    }

    public KichCoModel getKichCoModel() {
        return kichCoModel;
    }

    public void setKichCoModel(KichCoModel kichCoModel) {
        this.kichCoModel = kichCoModel;
    }

    public MauSacModel getMauSacModel() {
        return mauSacModel;
    }

    public void setMauSacModel(MauSacModel mauSacModel) {
        this.mauSacModel = mauSacModel;
    }

    public NhaSanXuatModel getNhaSanXuatModel() {
        return nhaSanXuatModel;
    }

    public void setNhaSanXuatModel(NhaSanXuatModel nhaSanXuatModel) {
        this.nhaSanXuatModel = nhaSanXuatModel;
    }

    public SanPhamModel getSanPhamModel() {
        return sanPhamModel;
    }

    public void setSanPhamModel(SanPhamModel sanPhamModel) {
        this.sanPhamModel = sanPhamModel;
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

    @Override
    public String toString() {
        return "SanPhamChiTietModel{" + "id=" + id + ", anhSanPham=" + anhSanPham + ", giaSanPham=" + giaSanPham + ", maSanPhamCt=" + maSanPhamCt + ", moTa=" + moTa + ", soLuong=" + soLuong + ", trangThai=" + trangThai + ", chatLieuModel=" + chatLieuModel.getTenChatLieu() + ", danhMucModel=" + danhMucModel.getTenDanhMuc() + ", kichCoModel=" + kichCoModel.getSize() + ", mauSacModel=" + mauSacModel.getTenMauSac() + ", nhaSanXuatModel=" + nhaSanXuatModel.getTenNSX() + ", sanPhamModel=" + sanPhamModel.getTenSanPham() + ", ngaySua=" + ngaySua + ", ngayTao=" + ngayTao + '}';
    }

}
