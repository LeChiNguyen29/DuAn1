/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Panel;

import Model.Panel.KichCoPanelModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class KichCoPanelRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public KichCoPanelRepo() {
        try {
            conn = DBContext.DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<KichCoPanelModel> getAll() {
        ArrayList<KichCoPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maKichCo, size, trangThai FROM KichCo");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new KichCoPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean checkTrung(String ma) {
        
        try {
            ps = conn.prepareStatement("SELECT * FROM KichCo WHERE maKichCo = ?");
            
            ps.setString(1, ma);
            
            rs = ps.executeQuery();
            
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int them(KichCoPanelModel m) {
        
        try {
            ps = conn.prepareStatement("INSERT INTO KichCo (maKichCo, size, trangThai, ngayTao) VALUES (?, ?, ?, GETDATE())");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getSize());
            ps.setInt(3, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhat(KichCoPanelModel m) {
        
        try {
            ps = conn.prepareStatement("UPDATE KichCo SET maKichCo = ?, size = ?, trangThai = ?, ngaySua = GETDATE() WHERE id = ?");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getSize());
            ps.setInt(3, m.getTrangThai());
            ps.setInt(4, m.getId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ArrayList<KichCoPanelModel> modelComboBox() {
        ArrayList<KichCoPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maKichCo, size, trangThai FROM KichCo WHERE trangThai = 0");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new KichCoPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
