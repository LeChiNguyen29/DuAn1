/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Main;

import DBContext.DBContext;
import Model.Main.Model_Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lcinu
 */
public class Repo_View {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Repo_View() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean login(String sdt, String matKhau) {
        try {
            ps = conn.prepareStatement("SELECT maNhanVien, hoTen, vaiTro FROM NhanVien WHERE soDienThoai = ? AND matKhau = ?");
            
            ps.setString(1, sdt);
            ps.setString(2, matKhau);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    } 
    
    public Model_Login tkNhanVien(String sdt, String matKhau) {
        try {
            ps = conn.prepareStatement("SELECT id, hoTen, soDienThoai, matKhau FROM NhanVien WHERE soDienThoai = ? AND matKhau = ?");
            
            ps.setString(1, sdt);
            ps.setString(2, matKhau);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Model_Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    } 
}
