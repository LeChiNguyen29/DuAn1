/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import DBContext.DBContext;
import Model.Internal.Model_SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class Repo_SanPham {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Repo_SanPham() {
        
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Model_SanPham> getAll(String search) {
        
        ArrayList<Model_SanPham> list = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement("SELECT id, maSanPham, tenSanPham, trangThai FROM SanPham WHERE maSanPham LIKE ? OR tenSanPham LIKE ?");
            
            ps.setString(1, "%" + search +"%");
            ps.setString(2, "%" + search +"%");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new Model_SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int them(Model_SanPham m) {
        
        try {
            ps = conn.prepareStatement("INSERT INTO SanPham (maSanPham, tenSanPham, trangThai, ngayTao) VALUES (?, ?, ?, GETDATE())");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTen());
            ps.setInt(3, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int sua(Model_SanPham m, int id) {
        
        try {
            ps = conn.prepareStatement("UPDATE SanPham SET maSanPham = ?, tenSanPham = ?, trangThai =?, ngayTao = GETDATE() WHERE id = ?");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTen());
            ps.setInt(3, m.getTrangThai());
            ps.setInt(4, id);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean checkTrung(String ma) {
        
        try {
            ps = conn.prepareStatement("SELECT * FROM SanPham WHERE maSanPham = ?");
            
            ps.setString(1, ma);
            
            rs = ps.executeQuery();
            
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Model_SanPham> modelComboBox() {
        
        ArrayList<Model_SanPham> list = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement("SELECT id, maSanPham, tenSanPham, trangThai FROM SanPham WHERE trangThai = 0");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new Model_SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
