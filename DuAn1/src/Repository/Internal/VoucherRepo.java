

package Repository.Internal;
import Model.Internal.HoaDonModel;
import Model.Internal.VoucherModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class VoucherRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
     
    public VoucherRepo(){
         try {
             conn = DBContext.DBContext.getConnection();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

    public ArrayList<VoucherModel> getAllVoucher(String keyword, String loaiVoucher, int trangThai) {
        ArrayList<VoucherModel> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder(
                "SELECT id, maVoucher, tenVoucher, loaiVoucher, ngayBatDau, ngayKetThuc, giaTri, ngayTao, ngaySua, trangThai "
                        + "FROM Voucher WHERE (maVoucher LIKE '%" + keyword + "%' OR tenVoucher LIKE '%" + keyword + "%')");
        
        if (!loaiVoucher.equals("Tất Cả")) {
            sql.append(" AND loaiVoucher = N'").append(loaiVoucher).append("'");
        }
        
        if (trangThai != 0) {
            sql.append(" AND trangThai = ").append(trangThai - 1);
        }
        
        try {
            
            ps = conn.prepareStatement(sql.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new VoucherModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                            , rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10)));
            }
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int themVoucher(VoucherModel m) {
        try {
            
            ps = conn.prepareStatement("""
                                       INSERT INTO Voucher (maVoucher, tenVoucher, loaiVoucher, ngayBatDau, ngayKetThuc, giaTri, ngayTao, ngaySua, trangThai)
                                       VALUES (?, ?, ?, ?, ?, ?, GETDATE(), GETDATE(), ?)""");
            
            ps.setString(1, m.getMaVoucher());
            ps.setString(2, m.getTenVoucher());
            ps.setString(3, m.getLoaiVoucher());
            ps.setString(4, m.getNgayBatDau());
            ps.setString(5, m.getNgayKetThuc());
            ps.setDouble(6, m.getGiaTri());
            ps.setInt(7, m.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhatVoucher(VoucherModel m, int id) {
        try {
            
            ps = conn.prepareStatement("""
                                       UPDATE Voucher SET maVoucher = ?, tenVoucher = ?, loaiVoucher = ?, ngayBatDau = ?, ngayKetThuc = ?, giaTri = ?, ngaySua = GETDATE(), trangThai = ?
                                                WHERE id = ?""");
            
            ps.setString(1, m.getMaVoucher());
            ps.setString(2, m.getTenVoucher());
            ps.setString(3, m.getLoaiVoucher());
            ps.setString(4, m.getNgayBatDau());
            ps.setString(5, m.getNgayKetThuc());
            ps.setDouble(6, m.getGiaTri());
            ps.setInt(7, m.getTrangThai());
            
            ps.setInt(8, id);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public VoucherModel getIdVoucher(int id) {
        VoucherModel model = new VoucherModel();
        
        try {
            
            ps = conn.prepareStatement("""
                                       SELECT id, maVoucher, tenVoucher, loaiVoucher, ngayBatDau, ngayKetThuc, giaTri, ngayTao, ngaySua, trangThai "
                                                               + "FROM Voucher WHERE id = ?""");
            
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while (rs.next()) { 
                
                model = new VoucherModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
                        , rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10));
            }
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<HoaDonModel> getAllHoaDon(int idVoucher) {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement("""
                                       SELECT 
                                       	hd.id, hd.maHoaDon, nv.hoTen, kh.hoTen
                                       	, hd.loaiThanhToan, hd.tongTien, hd.ngayTao 
                                       FROM HoaDon hd
                                       LEFT JOIN NhanVien nv ON hd.idNhanVien = nv.id
                                       LEFT JOIN KhachHang kh ON hd.idKhachHang = kh.id
                                       WHERE hd.trangThai = 0 AND hd.idVoucher = ?
                                       """);
            ps.setInt(1, idVoucher);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new HoaDonModel(rs.getInt(1), rs.getString(2), rs.getString(3)
                            , rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int idVoucher(String keyword, String loaiVoucher, int trangThai, int index) {
        return getAllVoucher(keyword, loaiVoucher, trangThai).get(index).getId();
    }
    
    public boolean checkTrung (int idVoucher) {
        try {
            ps = conn.prepareStatement("SELECT * FROM Voucher WHERE id = ?");
            
            ps.setInt(1, idVoucher);
            
            rs = ps.executeQuery();
            
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
