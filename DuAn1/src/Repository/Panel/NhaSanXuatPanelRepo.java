/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Panel;

import Model.Panel.NhaSanXuatPanelModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class NhaSanXuatPanelRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public NhaSanXuatPanelRepo() {
        try {
            conn = DBContext.DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<NhaSanXuatPanelModel> getAll() {
        ArrayList<NhaSanXuatPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maNhaSanXuat, tenNhaSanXuat, trangThai FROM NhaSanXuat");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new NhaSanXuatPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean checkTrung(String ma) {
        
        try {
            ps = conn.prepareStatement("SELECT maNhaSanXuat, tenNhaSanXuat, trangThai FROM NhaSanXuat WHERE maNhaSanXuat = ?");
            
            ps.setString(1, ma);
            
            rs = ps.executeQuery();
            
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int them(NhaSanXuatPanelModel m) {
        
        try {
            ps = conn.prepareStatement("INSERT INTO NhaSanXuat (maNhaSanXuat, tenNhaSanXuat, trangThai, ngayTao) VALUES (?, ?, ?, GETDATE())");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenNhaSanXuat());
            ps.setInt(3, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhat(NhaSanXuatPanelModel m) {
        
        try {
            ps = conn.prepareStatement("UPDATE NhaSanXuat SET maNhaSanXuat = ?, tenNhaSanXuat = ?, trangThai = ?, ngaySua = GETDATE() WHERE id = ?");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenNhaSanXuat());
            ps.setInt(3, m.getTrangThai());
            ps.setInt(4, m.getId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ArrayList<NhaSanXuatPanelModel> modelComboBox() {
        ArrayList<NhaSanXuatPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maNhaSanXuat, tenNhaSanXuat, trangThai FROM NhaSanXuat WHERE trangThai = 0");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new NhaSanXuatPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
