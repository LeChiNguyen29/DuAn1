/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Panel;

import Model.Panel.ChatLieuPanelModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class ChatLieuPanelRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ChatLieuPanelRepo() {
        try {
            conn = DBContext.DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<ChatLieuPanelModel> getAll() {
        ArrayList<ChatLieuPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maChatLieu, tenChatLieu, trangThai FROM ChatLieu");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new ChatLieuPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean checkTrung(String ma) {
        
        try {
            ps = conn.prepareStatement("SELECT * FROM ChatLieu WHERE maChatLieu = ?");
            
            ps.setString(1, ma);
            
            rs = ps.executeQuery();
            
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int them(ChatLieuPanelModel m) {
        
        try {
            ps = conn.prepareStatement("INSERT INTO ChatLieu (maChatLieu, tenChatLieu, trangThai, ngayTao) VALUES (?, ?, ?, GETDATE())");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenChatLieu());
            ps.setInt(3, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhat(ChatLieuPanelModel m) {
        
        try {
            ps = conn.prepareStatement("UPDATE ChatLieu SET maChatLieu = ?, tenChatLieu = ?, trangThai = ?, ngaySua = GETDATE() WHERE id = ?");
            
            ps.setString(1, m.getMa());
            ps.setString(2, m.getTenChatLieu());
            ps.setInt(3, m.getTrangThai());
            ps.setInt(4, m.getId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ArrayList<ChatLieuPanelModel> modelComboBox() {
        ArrayList<ChatLieuPanelModel> list = new ArrayList<>();
                
        try {
            ps = conn.prepareStatement("SELECT id, maChatLieu, tenChatLieu, trangThai FROM ChatLieu WHERE trangThai = 0");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new ChatLieuPanelModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
