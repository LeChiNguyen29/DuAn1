/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Panel;

import Model.Panel.DanhMucPanelModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class DanhMucPanelRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public DanhMucPanelRepo() {
        try {
            conn = DBContext.DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<DanhMucPanelModel> getAll() {
        ArrayList<DanhMucPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maDanhMuc, tenDanhMuc, trangThai FROM DanhMuc");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new DanhMucPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean checkTrung(String ma) {
        
        try {
            ps = conn.prepareStatement("SELECT * FROM DanhMuc WHERE maDanhMuc = ?");
            
            ps.setString(1, ma);
            
            rs = ps.executeQuery();
            
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int them(DanhMucPanelModel m) {
        
        try {
            ps = conn.prepareStatement("INSERT INTO DanhMuc (maDanhMuc, tenDanhMuc, trangThai, ngayTao) VALUES (?, ?, ?, GETDATE())");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenDanhMuc());
            ps.setInt(3, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhat(DanhMucPanelModel m) {
        
        try {
            ps = conn.prepareStatement("UPDATE DanhMuc SET maDanhMuc = ?, tenDanhMuc = ?, trangThai = ?, ngaySua = GETDATE() WHERE id = ?");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenDanhMuc());
            ps.setInt(3, m.getTrangThai());
            ps.setInt(4, m.getId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ArrayList<DanhMucPanelModel> modelComboBox() {
        ArrayList<DanhMucPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maDanhMuc, tenDanhMuc, trangThai FROM DanhMuc WHERE trangThai = 0");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new DanhMucPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
