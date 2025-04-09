

package Repository.Internal;
import DBContext.DBContext;
import Model.Internal.KhachHangModel;
import Model.Internal.Model_SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class KhachHangRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private static final int PAGE_SIZE_SP = 10;
    
    public KhachHangRepo(){
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<KhachHangModel> getAllKhachHang(String keyword, int page){
        ArrayList<KhachHangModel> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement ("SELECT id, maKhachHang, hoTen, soDienThoai, ngayTao, ngaySua from KhachHang "
                    + "WHERE (maKhachHang LIKE '%" + keyword + "%' "
                            + "OR hoTen LIKE '%" + keyword + "%' "
                                    + "OR soDienThoai LIKE '%" + keyword + "%') AND id != 1"
                                        + "ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ");
            int offset = (page - 1) * PAGE_SIZE_SP;
            
            ps.setInt(1, offset);
            ps.setInt(2, PAGE_SIZE_SP);
            
            rs = ps.executeQuery();
            while (rs.next()) {   
                KhachHangModel kh = new KhachHangModel(rs.getInt(1), rs.getString(2), rs.getString(3)
                                                            , rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    
     public int themKhachHang(KhachHangModel m, String ma) {
        try {
            
            ps = conn.prepareStatement("INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai, ngayTao, trangThai) VALUES (?, ?, ?, GETDATE(), 0)");
            
            ps.setString(1, ma);
            ps.setString(2, m.getHoTen());
            ps.setString(3, m.getSoDienThoai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int capNhatKhachHang(KhachHangModel m, int idKh) {
        try {
            
            ps = conn.prepareStatement("UPDATE KhachHang SET hoTen = ?, soDienThoai = ?, ngaySua = GETDATE() WHERE id = ?");
            ps.setString(1, m.getHoTen());
            ps.setString(2, m.getSoDienThoai());
            ps.setInt(3, idKh);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    

    
    public ArrayList<Model_SanPhamChiTiet> getSpct(int idKh) {
        ArrayList<Model_SanPhamChiTiet> list = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement("""
                                       SELECT 
                                            spct.maSanPhamChiTiet, dm.tenDanhMuc + ' ' + sp.tenSanPham, SUM(hdct.soLuong)
                                            , spct.giaSanPham FROM HoaDonChiTiet hdct
                                       LEFT JOIN SanPhamChiTiet spct ON hdct.idSanPhamChiTiet = spct.id
                                       LEFT JOIN DanhMuc dm ON spct.idDanhMuc = dm.id
                                       LEFT JOIN SanPham sp ON spct.idSanPham = sp.id
                                       LEFT JOIN HoaDon hd ON hdct.idHoaDon = hd.id
                                       LEFT JOIN KhachHang kh ON hd.idKhachHang = kh.id
                                       WHERE hd.trangThai = 0 AND kh.id = ?
                                       GROUP BY spct.maSanPhamChiTiet, dm.tenDanhMuc + ' ' + sp.tenSanPham, spct.giaSanPham
                                       """);
            ps.setInt(1, idKh);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                list.add(new Model_SanPhamChiTiet(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getIdKhachHang(String keyword, int page, int index) {
        return getAllKhachHang(keyword, page).get(index).getId();
    }
     
    public int stt() {
        try {
            ps = conn.prepareStatement("SELECT * FROM KhachHang");
            
            rs = ps.executeQuery();
            
            int index = 0;
            
            while (rs.next()) {                
                index++;
            }
            return index;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public String createMaKhachHang() {
        String ma;
        
        if (stt() < 10) {
            ma = "KH00" + String.valueOf(stt());
        } else if (stt() < 100) {
            ma = "KH0" + String.valueOf(stt());
        }else {
            ma = "KH" + String.valueOf(stt());
        }
        return ma;
    }
    

    public int getTotalPages(String keyword) {
        String countQuery = "SELECT COUNT(*) FROM KhachHang WHERE (maKhachHang LIKE '%" + keyword + "%' "
                            + "OR hoTen LIKE '%" + keyword + "%' "
                                    + "OR soDienThoai LIKE '%" + keyword + "%')";
        int totalRecords = 0;
        
        try  {
            ps = conn .prepareStatement(countQuery);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (totalRecords % 10 == 1) {
            return (int) Math.ceil(totalRecords / (double) PAGE_SIZE_SP) - 1;
        }
        return (int) Math.ceil(totalRecords / (double) PAGE_SIZE_SP);
    }
}
