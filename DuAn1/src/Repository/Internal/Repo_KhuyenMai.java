/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.Model_KhuyenMai;
import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class Repo_KhuyenMai {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Repo_KhuyenMai() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Model_KhuyenMai> getAllKhuyenmai() {
        
        ArrayList<Model_KhuyenMai> list = new ArrayList<>();
        
        try {
            
            ps = conn.prepareStatement("SELECT maKhuyenMai, tenKhuyenMai, loaiKhuyenMai, giaTri, ngayBatDau, ngayKetThuc, trangThai FROM KhuyenMai");
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new Model_KhuyenMai(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return list;
    }
    
    public int themKhuyenMai(Model_KhuyenMai m) {
        
        try {
            ps = conn.prepareStatement("INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, loaiKhuyenMai, giaTri, ngayBatDau, ngayKetThuc, trangThai, ngayTao) VALUES (?, ?, ?, ?, ?, ?, ?" + ", GETDATE())");
            
            ps.setString(1, m.getMaKhuyenMai());
            ps.setString(2, m.getTenKhuyenMai());
            ps.setString(3, m.getLoaiKhuyenmai());
            ps.setInt(4, m.getGiaTri());
            ps.setString(5, m.getNgayBatDau());
            ps.setString(6, m.getNgayKetThuc());
            ps.setInt(7, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int suaKhuyenMai(Model_KhuyenMai m) {
        
        try {
            ps = conn.prepareStatement("UPDATE KhuyenMai SET tenKhuyenMai = ?, loaiKhuyenMai = ?, giaTri = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = ?, ngaySua = GETDATE() WHERE maKhuyenMai = ?");
            
            ps.setString(1, m.getTenKhuyenMai());
            ps.setString(2, m.getLoaiKhuyenmai());
            ps.setInt(3, m.getGiaTri());
            ps.setString(4, m.getNgayBatDau());
            ps.setString(5, m.getNgayKetThuc());
            ps.setInt(6, m.getTrangThai());
            ps.setString(7, m.getMaKhuyenMai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ArrayList<Model_KhuyenMai> searchKhuyenmai(String searchLoai, int searchTrangThai, String searchName) {
        
        ArrayList<Model_KhuyenMai> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT maKhuyenMai, tenKhuyenMai, loaiKhuyenMai, giaTri, ngayBatDau, ngayKetThuc, trangThai FROM KhuyenMai WHERE tenKhuyenmai LIKE '%" + searchName + "%'");
        
        if (!searchLoai.equals("Tất Cả")) {
            sql.append(" AND loaiKhuyenMai = N'").append(searchLoai).append("'");
        }
        
        if (searchTrangThai != 0) {
            sql.append(" AND trangThai = ").append(searchTrangThai - 1);
        }
        
        try {
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new Model_KhuyenMai(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return list;
    }
}
