/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import DBContext.DBContext;
import Model.Internal.Model_SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lcinu
 */
public class Repo_SanPhamChiTiet {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Repo_SanPhamChiTiet() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Model_SanPhamChiTiet> getAllSanPham(String search, String danhMuc, int trangThai) {
        
        ArrayList<Model_SanPhamChiTiet> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("""
                                       SELECT 
                                       	spct.maSanPhamChiTiet, sp.tenSanPham, dm.tenDanhMuc, cl.tenChatLieu,
                                       	kc.size, ms.tenMau, nsx.tenNhaSanXuat,  
                                       	spct.anhSanPham, spct.giaSanPham, spct.soLuong, spct.moTa, spct.trangThai, spct.id 
                                       FROM SanPhamChiTiet spct
                                       LEFT JOIN SanPham sp ON spct.idSanPham = sp.id
                                       LEFT JOIN DanhMuc dm ON spct.idDanhMuc = dm.id
                                       LEFT JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                                       LEFT JOIN KichCo kc ON spct.idKichCo = kc.id
                                       LEFT JOIN MauSac ms ON spct.idMauSac = ms.id
                                       LEFT JOIN NhaSanXuat nsx ON spct.idNhaSanXuat = nsx.id
                                       WHERE (spct.maSanPhamChiTiet LIKE ? OR sp.tenSanPham LIKE ?
                                              OR dm.tenDanhMuc LIKE ? )""");
        
        if (!danhMuc.equals("Tất Cả")) {
            sql.append(" AND dm.tenDanhMuc = N'").append(danhMuc).append("'");
        }
        if (trangThai != 0) {
            sql.append(" AND spct.trangThai = ").append(trangThai - 1);
        }
//        
        try {
            
            ps = conn.prepareStatement(sql.toString());
            
            ps.setString(1, "%" + search+ "%");
            ps.setString(2, "%" + search+ "%");
            ps.setString(3, "%" + search+ "%");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new Model_SanPhamChiTiet(rs.getInt(13), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)
                                                , rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)
                                                    , rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return list;
    }
    
    public boolean checkTrung(String ma) {
        try {
            ps = conn.prepareCall("SELECT * FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?");
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
    
    public int themSanPham(Model_SanPhamChiTiet m, int idSanPham, int idLoaiSP, int idChatLieu, int idKichCo, int idMauSac, int idNSX) {
        
        try {
            ps = conn.prepareStatement("""
                                        INSERT INTO SanPhamChiTiet
                                            (maSanPhamChiTiet, idSanPham, idDanhMuc, idChatLieu,
                                                idKichCo, idMauSac, idNhaSanXuat, anhSanPham,
                                                    giaSanPham, soLuong, moTa, trangThai, ngayTao)
                                        VALUES (?, ?, ?, ?,
                                       ?, ?, ?, ?,
                                       ?, ?, ?, ?, GETDATE())""");
            
            ps.setString(1, m.getMaSanPham());
            ps.setInt(2, idSanPham);
            ps.setInt(3, idLoaiSP);
            ps.setInt(4, idChatLieu);
            ps.setInt(5, idKichCo);
            ps.setInt(6, idMauSac);
            ps.setInt(7, idNSX);
            ps.setString(8, m.getAnhSanPham());
            ps.setInt(9, m.getDonGia());
            ps.setInt(10, m.getSoLuong());
            ps.setString(11, m.getMoTa());
            ps.setInt(12, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int suaSanPham(Model_SanPhamChiTiet m, int idSpct, int idSanPham, int idLoaiSP, int idChatLieu, int idKichCo, int idMauSac, int idNSX) {
        
        try {
            ps = conn.prepareStatement("""
                                        UPDATE SanPhamChiTiet
                                            SET idSanPham = ?, idDanhMuc = ?, idChatLieu = ?, idKichCo = ?
                                                , idMauSac = ?, idNhaSanXuat = ?, anhSanPham = ?, giaSanPham = ?
                                                    , soLuong = ?, moTa = ?, trangThai = ?, ngaySua = GETDATE(), maSanPhamChiTiet = ?
                                        WHERE id = ?""");
            
            ps.setInt(1, idSanPham);
            ps.setInt(2, idLoaiSP);
            ps.setInt(3, idChatLieu);
            ps.setInt(4, idKichCo);
            ps.setInt(5, idMauSac);
            ps.setInt(6, idNSX);
            ps.setString(7, m.getAnhSanPham());
            ps.setInt(8, m.getDonGia());
            ps.setInt(9, m.getSoLuong());
            ps.setString(10, m.getMoTa());
            ps.setInt(11, m.getTrangThai());
            ps.setString(12, m.getMaSanPham());
            ps.setInt(13, idSpct);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public ArrayList<Model_SanPhamChiTiet> modelComboBox() {
        
        ArrayList<Model_SanPhamChiTiet> list = new ArrayList<>();
        
        String sql = """
                     SELECT
                     	spct.maSanPhamChiTiet, sp.tenSanPham, dm.tenDanhMuc, cl.tenChatLieu,
                     	kc.size, ms.tenMau, nsx.tenNhaSanXuat,
                     	spct.anhSanPham, spct.giaSanPham, spct.soLuong, spct.moTa, spct.trangThai, spct.id
                     FROM SanPhamChiTiet spct
                     LEFT JOIN SanPham sp ON spct.idSanPham = sp.id
                     LEFT JOIN DanhMuc dm ON spct.idDanhMuc = dm.id
                     LEFT JOIN ChatLieu cl ON spct.idChatLieu = cl.id
                     LEFT JOIN KichCo kc ON spct.idKichCo = kc.id
                     LEFT JOIN MauSac ms ON spct.idMauSac = ms.id
                     LEFT JOIN NhaSanXuat nsx ON spct.idNhaSanXuat = nsx.id""";
        
        try {
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new Model_SanPhamChiTiet(rs.getInt(13), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)
                                                , rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)
                                                    , rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return list;
    }
}