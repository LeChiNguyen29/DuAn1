/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Panel;

import Model.Panel.MauSacPanelModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class MauSacPanelRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public MauSacPanelRepo() {
        try {
            conn = DBContext.DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<MauSacPanelModel> getAll() {
        ArrayList<MauSacPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maMauSac, tenMau, trangThai FROM MauSac");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new MauSacPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean checkTrung(String ma) {
        
        try {
            ps = conn.prepareStatement("SELECT maMauSac, tenMau, trangThai FROM MauSac WHERE maMauSac = ?");
            
            ps.setString(1, ma);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {                
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return true;
    }
    
    public int them(MauSacPanelModel m) {
        
        try {
            ps = conn.prepareStatement("INSERT INTO MauSac (maMauSac, tenMau, trangThai, ngayTao) VALUES (?, ?, ?, GETDATE())");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenMau());
            ps.setInt(3, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhat(MauSacPanelModel m) {
        
        try {
            ps = conn.prepareStatement("UPDATE MauSac SET maMauSac = ?, tenMau = ?, trangThai = ?, ngaySua = GETDATE() WHERE id = ?");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenMau());
            ps.setInt(3, m.getTrangThai());
            ps.setInt(4, m.getId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ArrayList<MauSacPanelModel> modelComboBox() {
        ArrayList<MauSacPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maMauSac, tenMau, trangThai FROM MauSac WHERE trangThai = 0");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new MauSacPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
