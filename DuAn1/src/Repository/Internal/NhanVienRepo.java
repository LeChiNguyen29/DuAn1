/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import DBContext.DBContext;
import Model.Internal.NhanVienModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class NhanVienRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public NhanVienRepo() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<NhanVienModel> getAllNhanVien(String search, String vaiTro, int trangThai) {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder(
                "SELECT id, maNhanVien, hoTen, gioiTinh, ngaySinh, diaChi, soCCCD, soDienThoai, matKhau, vaiTro, ghiChu, trangThai "
                        + "FROM NhanVien WHERE (hoTen LIKE '%" + search + "%' OR soDienThoai LIKE '%" + search + "%')");
        
        if (!vaiTro.equals("Tất Cả")) {
            sql.append(" AND vaiTro = N'").append(vaiTro).append("'");
        }
        
        if (trangThai != 0) {
            sql.append(" AND trangThai = ").append(trangThai - 1);
        }
        
        try {
            
            ps = conn.prepareStatement(sql.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new NhanVienModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                        , rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themNhanVien(NhanVienModel m) {
        try {
            
            ps = conn.prepareStatement("""
                                       INSERT INTO NhanVien (maNhanVien, hoTen, gioiTinh, ngaySinh, diaChi, soCCCD
                                                                , soDienThoai, matKhau, vaiTro, ghiChu, trangThai, ngayTao)
                                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE() )""");
            
            ps.setString(1, m.getMaNhanVien());
            ps.setString(2, m.getHoTen());
            ps.setString(3, m.getGioiTinh());
            ps.setString(4, m.getNgaySinh());
            ps.setString(5, m.getDiaChi());
            ps.setString(6, m.getSoCCCD());
            ps.setString(7, m.getSoDienThoai());
            ps.setString(8, m.getMatKhau());
            ps.setString(9, m.getVaiTro());
            ps.setString(10, m.getGhiChu());
            ps.setInt(11, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhatNhanVien(NhanVienModel m, int idNhanVien) {
        try {
            
            ps = conn.prepareStatement("""
                                       UPDATE NhanVien SET maNhanVien = ?, hoTen = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, soCCCD = ?
                                            , soDienThoai = ?, matKhau = ?, vaiTro = ?, ghiChu = ?, trangThai = ?, ngaySua = GETDATE()
                                                WHERE id = ?""");
            
            ps.setString(1, m.getMaNhanVien());
            ps.setString(2, m.getHoTen());
            ps.setString(3, m.getGioiTinh());
            ps.setString(4, m.getNgaySinh());
            ps.setString(5, m.getDiaChi());
            ps.setString(6, m.getSoCCCD());
            ps.setString(7, m.getSoDienThoai());
            ps.setString(8, m.getMatKhau());
            ps.setString(9, m.getVaiTro());
            ps.setString(10, m.getGhiChu());
            ps.setInt(11, m.getTrangThai());
            ps.setInt(12, idNhanVien);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int idNhanVien(String search, String vaiTro, int trangThai, int index) {
        return getAllNhanVien(search, vaiTro, trangThai).get(index).getId();
    }
    
    public boolean checkTrungMa(String search) {
        try {
            ps = conn.prepareStatement("SELECT * FROM NhanVien WHERE maNhanVien = ?");
            
            ps.setString(1, search);
            
            rs= ps.executeQuery();
            
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkTrungSoCCCD(String search) {
        try {
            ps = conn.prepareStatement("SELECT * FROM NhanVien WHERE soCCCD = ?");
            
            ps.setString(1, search);
            
            rs= ps.executeQuery();
            
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkTrungSdt(String search) {
        try {
            ps = conn.prepareStatement("SELECT * FROM NhanVien WHERE soDienThoai = ?");
            
            ps.setString(1, search);
            
            rs= ps.executeQuery();
            
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public NhanVienModel getModel(int idNhanVien) {
        String sql = """
                        SELECT id, maNhanVien, hoTen, gioiTinh, ngaySinh, diaChi, soCCCD
                                , soDienThoai, matKhau, vaiTro, ghiChu, trangThai
                        FROM NhanVien WHERE id = ?
                    """;
        
        try {
            
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idNhanVien);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {                
                NhanVienModel model = new NhanVienModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                        , rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)
                                , rs.getString(11), rs.getInt(12));
                
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
