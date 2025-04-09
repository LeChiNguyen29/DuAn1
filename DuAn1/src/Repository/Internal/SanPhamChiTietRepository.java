/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.ChatLieuModel;
import Model.Internal.DanhMucModel;
import Model.Internal.KichCoModel;
import Model.Internal.MauSacModel;
import Model.Internal.NhaSanXuatModel;
import Model.Internal.SanPhamChiTietModel;
import Model.Internal.SanPhamModel;
import DBContext.DBContext;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phamq
 */
public class SanPhamChiTietRepository {

    private static final int PAGE_SIZE = 15;
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private ChatLieuRepository chatLieuReppo = new ChatLieuRepository();
    private MauSacRepository maSacRepo = new MauSacRepository();
    private KichCoRepository kichCoRepo = new KichCoRepository();
    private NhaSanXuatRepository nsxRepo = new NhaSanXuatRepository();
    private DanhMucRepository danhMucRepo = new DanhMucRepository();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public SanPhamChiTietRepository() {
        try {
            connection = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean save(SanPhamChiTietModel sanPhamChiTietModel) {
        boolean rowInsert = false;
        try {
         

            // Prepare the SQL INSERT statement
            StringBuilder sql = new StringBuilder("INSERT INTO SanPhamChiTiet (maSanPhamChiTiet, anhSanPham, giaSanPham, moTa, soLuong, idChatLieu, idDanhMuc, idKichCo, idMauSac, idNhaSanXuat, idSanPham, trangThai, ngayTao, ngaySua)");
            sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setString(1, sanPhamChiTietModel.getMaSanPhamCt());
            preparedStatement.setString(2, sanPhamChiTietModel.getAnhSanPham());
            preparedStatement.setBigDecimal(3, sanPhamChiTietModel.getGiaSanPham());
            preparedStatement.setString(4, sanPhamChiTietModel.getMoTa());
            preparedStatement.setInt(5, sanPhamChiTietModel.getSoLuong());
            preparedStatement.setInt(6, sanPhamChiTietModel.getChatLieuModel().getId()); // idChatLieu
            preparedStatement.setInt(7, sanPhamChiTietModel.getDanhMucModel().getId()); // idDanhMuc
            preparedStatement.setInt(8, sanPhamChiTietModel.getKichCoModel().getId()); // idKichCo
            preparedStatement.setInt(9, sanPhamChiTietModel.getMauSacModel().getId()); // idMauSac
            preparedStatement.setInt(10, sanPhamChiTietModel.getNhaSanXuatModel().getId()); // idNhaSanXuat
            preparedStatement.setInt(11, sanPhamChiTietModel.getSanPhamModel().getId()); // idSanPham
            preparedStatement.setInt(12, sanPhamChiTietModel.getTrangThai()); // trangThai
            preparedStatement.setTimestamp(13, sanPhamChiTietModel.getNgayTao()); // ngayTao
            preparedStatement.setTimestamp(14, sanPhamChiTietModel.getNgaySua()); // ngaySua

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                rowInsert = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInsert;
    }

    public List<SanPhamChiTietModel> findAll() {
        List<SanPhamChiTietModel> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPhamChiTiet";
        try {
          
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String maSanPhamChiTiet = resultSet.getString("maSanPhamChiTiet");
                String anhSanChiTiet = resultSet.getString("anhSanPham");
                BigDecimal gia = resultSet.getBigDecimal("giaSanPham");
                String moTa = resultSet.getString("moTa");
                int soLuong = resultSet.getInt("soLuong");
                int trangThai = resultSet.getInt("trangThai");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int idChatLieu = resultSet.getInt("idChatLieu");
                int idDanhMuc = resultSet.getInt("idDanhMuc");
                int idKichCo = resultSet.getInt("idKichCo");
                int idMauSac = resultSet.getInt("idMauSac");
                int idNhaSanXuat = resultSet.getInt("idNhaSanXuat");
                int idSanPham = resultSet.getInt("idSanPham");
                ChatLieuModel chatLieuModel = chatLieuReppo.findById(idChatLieu);
                DanhMucModel danhMucModel = danhMucRepo.findById(idDanhMuc);
                KichCoModel kichCoModel = kichCoRepo.findById(idKichCo);
                MauSacModel mauSacModel = maSacRepo.findById(idMauSac);
                NhaSanXuatModel sanXuatModel = nsxRepo.findById(idNhaSanXuat);
                SanPhamModel sanPhamModel = sanPhamRepository.findById(idSanPham);
                SanPhamChiTietModel spct = new SanPhamChiTietModel(id, anhSanChiTiet, gia, maSanPhamChiTiet, moTa, soLuong, trangThai, chatLieuModel, danhMucModel, kichCoModel, mauSacModel, sanXuatModel, sanPhamModel, ngaySua, ngayTao);
                list.add(spct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public SanPhamChiTietModel findById(int id) {
        SanPhamChiTietModel sanPhamChiTietModel = null;
        try {
            String sql = "SELECT * FROM SanPhamChiTiet WHERE id = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int sanPhamId = resultSet.getInt("id");
                String maSanPhamChiTiet = resultSet.getString("maSanPhamChiTiet");
                String anhSanPham = resultSet.getString("anhSanPham");
                BigDecimal giaSanPham = resultSet.getBigDecimal("giaSanPham");
                String moTa = resultSet.getString("moTa");
                int soLuong = resultSet.getInt("soLuong");
                int idChatLieu = resultSet.getInt("idChatLieu");
                int idDanhMuc = resultSet.getInt("idDanhMuc");
                int idKichCo = resultSet.getInt("idKichCo");
                int idMauSac = resultSet.getInt("idMauSac");
                int idNhaSanXuat = resultSet.getInt("idNhaSanXuat");
                int idSanPham = resultSet.getInt("idSanPham");
                int trangThai = resultSet.getInt("trangThai");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");

                ChatLieuModel chatLieuModel = chatLieuReppo.findById(idChatLieu);
                DanhMucModel danhMucModel = danhMucRepo.findById(idDanhMuc);
                KichCoModel kichCoModel = kichCoRepo.findById(idKichCo);
                MauSacModel mauSacModel = maSacRepo.findById(idMauSac);
                NhaSanXuatModel sanXuatModel = nsxRepo.findById(idNhaSanXuat);
                SanPhamModel sanPhamModel = sanPhamRepository.findById(idSanPham);

                sanPhamChiTietModel = new SanPhamChiTietModel(sanPhamId, anhSanPham, giaSanPham, maSanPhamChiTiet, moTa, soLuong, trangThai, chatLieuModel, danhMucModel, kichCoModel, mauSacModel, sanXuatModel, sanPhamModel, ngaySua, ngayTao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhamChiTietModel;
    }

    public boolean existsByMaSPCT(String ma) {
        boolean check = false;
        try {
         
            String sql = "SELECT * FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateById(int id, SanPhamChiTietModel spct) {
        boolean checkUpDate = false;
        String updateSQL = "UPDATE SanPhamChiTiet SET "
                + "maSanPhamChiTiet = ?, "
                + "anhSanPham = ?, "
                + "giaSanPham = ?, "
                + "moTa = ?, "
                + "soLuong = ?, "
                + "idChatLieu = ?, "
                + "idDanhMuc = ?, "
                + "idKichCo = ?, "
                + "idMauSac = ?, "
                + "idNhaSanXuat = ?, "
                + "idSanPham = ?, "
                + "trangThai = ?, "
                + "ngayTao = ?, "
                + "ngaySua = ? "
                + "WHERE id = ?";

        try {
         
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, spct.getMaSanPhamCt());
            preparedStatement.setString(2, spct.getAnhSanPham());
            preparedStatement.setBigDecimal(3, spct.getGiaSanPham());
            preparedStatement.setString(4, spct.getMoTa());
            preparedStatement.setInt(5, spct.getSoLuong());
            preparedStatement.setInt(6, spct.getChatLieuModel().getId());
            preparedStatement.setInt(7, spct.getDanhMucModel().getId());
            preparedStatement.setInt(8, spct.getKichCoModel().getId());
            preparedStatement.setInt(9, spct.getMauSacModel().getId());
            preparedStatement.setInt(10, spct.getNhaSanXuatModel().getId());
            preparedStatement.setInt(11, spct.getSanPhamModel().getId());
            preparedStatement.setInt(12, spct.getTrangThai());
            preparedStatement.setTimestamp(13, spct.getNgayTao());
            preparedStatement.setTimestamp(14, spct.getNgaySua());
            preparedStatement.setInt(15, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                checkUpDate = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkUpDate;
    }

    public boolean isMaUnique(int id, String ma) {
        boolean rowExist = false;
        try {
            // Establish the connection
           

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM SanPhamChiTiet WHERE maSanPhamChiTiet = ? AND id <> ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            preparedStatement.setInt(2, id);

            // Execute the delete statement
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rowExist = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowExist;
    }

    public List<SanPhamChiTietModel> findByTrangThai(int trangThaiFind) {
        List<SanPhamChiTietModel> list = new ArrayList<>();
        String sql = "SELECT * FROM SanPhamChiTiet where trangThai = ?";
        try {
           
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trangThaiFind);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String maSanPhamChiTiet = resultSet.getString("maSanPhamChiTiet");
                String anhSanChiTiet = resultSet.getString("anhSanPham");
                BigDecimal gia = resultSet.getBigDecimal("giaSanPham");
                String moTa = resultSet.getString("moTa");
                int soLuong = resultSet.getInt("soLuong");
                int trangThai = resultSet.getInt("trangThai");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int idChatLieu = resultSet.getInt("idChatLieu");
                int idDanhMuc = resultSet.getInt("idDanhMuc");
                int idKichCo = resultSet.getInt("idKichCo");
                int idMauSac = resultSet.getInt("idMauSac");
                int idNhaSanXuat = resultSet.getInt("idNhaSanXuat");
                int idSanPham = resultSet.getInt("idSanPham");
                ChatLieuModel chatLieuModel = chatLieuReppo.findById(idChatLieu);
                DanhMucModel danhMucModel = danhMucRepo.findById(idDanhMuc);
                KichCoModel kichCoModel = kichCoRepo.findById(idKichCo);
                MauSacModel mauSacModel = maSacRepo.findById(idMauSac);
                NhaSanXuatModel sanXuatModel = nsxRepo.findById(idNhaSanXuat);
                SanPhamModel sanPhamModel = sanPhamRepository.findById(idSanPham);
                SanPhamChiTietModel spct = new SanPhamChiTietModel(id, anhSanChiTiet, gia, maSanPhamChiTiet, moTa, soLuong, trangThai, chatLieuModel, danhMucModel, kichCoModel, mauSacModel, sanXuatModel, sanPhamModel, ngaySua, ngayTao);
                list.add(spct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return list;

    }

    public List<SanPhamChiTietModel> search(Integer trangThai, DanhMucModel danhMuc, SanPhamModel sanPham) throws SQLException {
        List<SanPhamChiTietModel> list = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM SanPhamChiTiet WHERE 1=1");

        if (trangThai != null) {
            query.append(" AND trangThai = ?");
        }
        if (danhMuc != null) {
            query.append(" AND idDanhMuc = ?");
        }
        if (sanPham != null) {
            query.append(" AND idSanPham = ?");
        }

        try  {
            preparedStatement = connection.prepareStatement(query.toString());
            int index = 1;

            if (trangThai != null) {
                preparedStatement.setInt(index++, trangThai);
            }
            if (danhMuc != null) {
                preparedStatement.setInt(index++, danhMuc.getId());
            }
            if (sanPham != null) {
                preparedStatement.setInt(index++, sanPham.getId());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String maSanPhamChiTiet = resultSet.getString("maSanPhamChiTiet");
                    String anhSanChiTiet = resultSet.getString("anhSanPham");
                    BigDecimal gia = resultSet.getBigDecimal("giaSanPham");
                    String moTa = resultSet.getString("moTa");
                    int soLuong = resultSet.getInt("soLuong");
                    int trangThaiid = resultSet.getInt("trangThai");
                    Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                    Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                    int idChatLieu = resultSet.getInt("idChatLieu");
                    int idDanhMuc = resultSet.getInt("idDanhMuc");
                    int idKichCo = resultSet.getInt("idKichCo");
                    int idMauSac = resultSet.getInt("idMauSac");
                    int idNhaSanXuat = resultSet.getInt("idNhaSanXuat");
                    int idSanPham = resultSet.getInt("idSanPham");

                    ChatLieuModel chatLieuModel = chatLieuReppo.findById(idChatLieu);
                    DanhMucModel danhMucModel = danhMucRepo.findById(idDanhMuc);
                    KichCoModel kichCoModel = kichCoRepo.findById(idKichCo);
                    MauSacModel mauSacModel = maSacRepo.findById(idMauSac);
                    NhaSanXuatModel sanXuatModel = nsxRepo.findById(idNhaSanXuat);
                    SanPhamModel sanPhamModel = sanPhamRepository.findById(idSanPham);
                    SanPhamChiTietModel spct = new SanPhamChiTietModel(id, anhSanChiTiet, gia, maSanPhamChiTiet, moTa, soLuong, trangThaiid, chatLieuModel, danhMucModel, kichCoModel, mauSacModel, sanXuatModel, sanPhamModel, ngaySua, ngayTao);
                    list.add(spct);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPhamChiTietModel> searchNew(String tenSanPham, String tenDanhMuc, Integer trangThai) throws SQLException {
        List<SanPhamChiTietModel> list = new ArrayList<>();

        // Câu truy vấn cơ bản với JOIN để lấy thông tin từ các bảng liên quan
        StringBuilder query = new StringBuilder("""
            SELECT spct.*, sp.tenSanPham, dm.tenDanhMuc 
            FROM SanPhamChiTiet spct 
            JOIN SanPham sp ON spct.idSanPham = sp.id 
            JOIN DanhMuc dm ON spct.idDanhMuc = dm.id 
            WHERE 1=1
        """);

        // Thêm điều kiện vào câu truy vấn nếu các tham số không phải là null
        if (tenSanPham != null && !tenSanPham.isEmpty()) {
            query.append(" AND sp.tenSanPham LIKE ?");
        }
        if (tenDanhMuc != null && !tenDanhMuc.isEmpty()) {
            query.append(" AND dm.tenDanhMuc LIKE ?");
        }
        if (trangThai != null) {
            query.append(" AND spct.trangThai = ?");
        }

        try  {
            preparedStatement=connection.prepareStatement(query.toString());
            int index = 1;

            // Gán giá trị cho các tham số
            if (tenSanPham != null && !tenSanPham.isEmpty()) {
                preparedStatement.setString(index++, "%" + tenSanPham + "%");
            }
            if (tenDanhMuc != null && !tenDanhMuc.isEmpty()) {
                preparedStatement.setString(index++, "%" + tenDanhMuc + "%");
            }
            if (trangThai != null) {
                preparedStatement.setInt(index++, trangThai);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Lấy thông tin từ ResultSet
                    int id = resultSet.getInt("id");
                    String maSanPhamChiTiet = resultSet.getString("maSanPhamChiTiet");
                    String anhSanPham = resultSet.getString("anhSanPham");
                    BigDecimal giaSanPham = resultSet.getBigDecimal("giaSanPham");
                    String moTa = resultSet.getString("moTa");
                    int soLuong = resultSet.getInt("soLuong");
                    int trangThaiId = resultSet.getInt("trangThai");
                    Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                    Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                    int idChatLieu = resultSet.getInt("idChatLieu");
                    int idDanhMuc = resultSet.getInt("idDanhMuc");
                    int idKichCo = resultSet.getInt("idKichCo");
                    int idMauSac = resultSet.getInt("idMauSac");
                    int idNhaSanXuat = resultSet.getInt("idNhaSanXuat");
                    int idSanPham = resultSet.getInt("idSanPham");

                    // Tạo các model từ các repository tương ứng
                    ChatLieuModel chatLieuModel = chatLieuReppo.findById(idChatLieu);
                    DanhMucModel danhMucModel = danhMucRepo.findById(idDanhMuc);
                    KichCoModel kichCoModel = kichCoRepo.findById(idKichCo);
                    MauSacModel mauSacModel = maSacRepo.findById(idMauSac);
                    NhaSanXuatModel nhaSanXuatModel = nsxRepo.findById(idNhaSanXuat);
                    SanPhamModel sanPhamModel = sanPhamRepository.findById(idSanPham);

                    // Tạo đối tượng SanPhamChiTietModel và thêm vào danh sách kết quả
                    SanPhamChiTietModel spct = new SanPhamChiTietModel(id, anhSanPham, giaSanPham, maSanPhamChiTiet, moTa, soLuong, trangThaiId, chatLieuModel, danhMucModel, kichCoModel, mauSacModel, nhaSanXuatModel, sanPhamModel, ngaySua, ngayTao);
                    list.add(spct);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<SanPhamChiTietModel> getSanPhamByPage(int page) {
        List<SanPhamChiTietModel> sanPhamList = new ArrayList<>();
        String query = "SELECT * FROM SanPhamChiTiet ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try  {
            preparedStatement = connection.prepareStatement(query);
            int offset = (page - 1) * PAGE_SIZE;
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, PAGE_SIZE);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maSanPhamChiTiet = resultSet.getString("maSanPhamChiTiet");
                String anhSanPham = resultSet.getString("anhSanPham");
                BigDecimal giaSanPham = resultSet.getBigDecimal("giaSanPham");
                String moTa = resultSet.getString("moTa");
                int soLuong = resultSet.getInt("soLuong");
                int trangThaiId = resultSet.getInt("trangThai");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int idChatLieu = resultSet.getInt("idChatLieu");
                int idDanhMuc = resultSet.getInt("idDanhMuc");
                int idKichCo = resultSet.getInt("idKichCo");
                int idMauSac = resultSet.getInt("idMauSac");
                int idNhaSanXuat = resultSet.getInt("idNhaSanXuat");
                int idSanPham = resultSet.getInt("idSanPham");

                // Tạo các model từ các repository tương ứng
                ChatLieuModel chatLieuModel = chatLieuReppo.findById(idChatLieu);
                DanhMucModel danhMucModel = danhMucRepo.findById(idDanhMuc);
                KichCoModel kichCoModel = kichCoRepo.findById(idKichCo);
                MauSacModel mauSacModel = maSacRepo.findById(idMauSac);
                NhaSanXuatModel nhaSanXuatModel = nsxRepo.findById(idNhaSanXuat);
                SanPhamModel sanPhamModel = sanPhamRepository.findById(idSanPham);

                // Tạo đối tượng SanPhamChiTietModel và thêm vào danh sách kết quả
                SanPhamChiTietModel spct = new SanPhamChiTietModel(id, anhSanPham, giaSanPham, maSanPhamChiTiet, moTa, soLuong, trangThaiId, chatLieuModel, danhMucModel, kichCoModel, mauSacModel, nhaSanXuatModel, sanPhamModel, ngaySua, ngayTao);
                sanPhamList.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }

    public int getTotalPages() {
        String countQuery = "SELECT COUNT(*) FROM SanPhamChiTiet";
        int totalRecords = 0;

        try  {
           preparedStatement = connection .prepareStatement(countQuery);
           resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRecords = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) Math.ceil(totalRecords / (double) PAGE_SIZE);
    }

}
