/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import DBContext.DBContext;
import Model.Internal.HoaDonChiTietModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.util.Date;

/**
 *
 * @author LENHATLINH
 */
public class HoaDonChiTietRepo {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public HoaDonChiTietRepo() {
        try {
            
            this.conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<HoaDonChiTietModel> getAllHDCT(String ma) {
        
        ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        
        String sql = ("""
        select hdct.id, spct.maSanPhamChiTiet,kh.hoTen,kh.soDienThoai,hdct.ngayTao, hd.loaiThanhToan,hdct.trangThai,hd.tongTien,
        dm.tenDanhMuc + ' ' + sp.tenSanPham tenGiay, spct.giaSanPham, hdct.soLuong, spct.giaSanPham * hdct.soLuong thanhTien from HoaDonChiTiet hdct
        LEFT JOIN SanPhamChiTiet spct ON hdct.idSanPhamChiTiet = spct.id
        LEFT JOIN SanPham sp ON spct.idSanPham = sp.id
        LEFT JOIN DanhMuc dm ON spct.idDanhMuc = dm.id
        LEFT JOIN HoaDon hd ON hdct.idHoaDon = hd.id
        LEFT JOIN KhachHang kh ON hd.idKhachHang = kh.id
        where hd.maHoaDon = ? and hdct.trangThai = 0""");
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ma); // Sử dụng biến kiểu int trong truy vấn

            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new HoaDonChiTietModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
                        , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12)));     
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return list;
    }
     public ArrayList<HoaDonChiTietModel> getIdHDCT() {
        
        ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        
        String sql = ("""
        select hdct.id, spct.maSanPhamChiTiet,kh.hoTen,kh.soDienThoai,hd.ngayTao, hd.loaiThanhToan,spct.trangThai,hd.tongTien from HoaDonChiTiet hdct
                LEFT JOIN HoaDon hd ON hdct.idHoaDon = hd.id
                LEFT JOIN KhachHang kh ON hd.idKhachHang = kh.id
                where spct.trangThai = 0""");
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new HoaDonChiTietModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
                        , rs.getString(6), rs.getInt(7), rs.getInt(8)));     
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return list;
    }
}