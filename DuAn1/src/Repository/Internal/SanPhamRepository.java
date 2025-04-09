/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.SanPhamModel;

import DBContext.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phamq
 */
public class SanPhamRepository {

    private static final int PAGE_SIZE_SP = 10;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public SanPhamRepository() {
        try {
            connection = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean save(SanPhamModel sanPham) {
        String sql = "INSERT INTO SanPham ( maSanPham, ngaySua, ngayTao, tenSanPham, trangThai) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sanPham.getMaSanPham());
            preparedStatement.setTimestamp(2, sanPham.getNgaySua());
            preparedStatement.setTimestamp(3, sanPham.getNgayTao());
            preparedStatement.setString(4, sanPham.getTenSanPham());
            preparedStatement.setInt(5, sanPham.getTrangThai());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<SanPhamModel> findAll() {
        List<SanPhamModel> sanPhams = new ArrayList<>();
        String sql = "Select * from SanPham";
        try  {
            String query = "SELECT id, maSanPham, ngaySua, ngayTao, tenSanPham, trangThai FROM SanPham";
            preparedStatement=  connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String maSanPham = resultSet.getString("maSanPham");
                    Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                    Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                    String tenSanPham = resultSet.getString("tenSanPham");
                    int trangThai = resultSet.getInt("trangThai");
                    SanPhamModel sanPham = new SanPhamModel(id, maSanPham, ngaySua, ngayTao, tenSanPham, trangThai);
                    sanPhams.add(sanPham);

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }

    public SanPhamModel findById(int id) {
        SanPhamModel sanPhamModel = null;
        String sql = "SELECT * FROM SanPham where id=?";
        try {
            
          preparedStatement   = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int idFind = rs.getInt("id");
                String maSanPham = rs.getString("maSanPham");
                Timestamp ngaySua = rs.getTimestamp("ngaySua");
                Timestamp ngayTao = rs.getTimestamp("ngayTao");
                String tenSanPham = rs.getString("tenSanPham");
                int trangThai = rs.getInt("trangThai");
                sanPhamModel = new SanPhamModel(idFind, maSanPham, ngaySua, ngayTao, tenSanPham, trangThai);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhamModel;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM SanPham WHERE id = ?";
        try {
           
          preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByMaSanPham(String maSanPham) {
        String sql = "select * FROM SanPham WHERE maSanPham = ?";
        try {
           
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maSanPham);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<SanPhamModel> findAllByTrangThai(int trangThaiFind) {
        List<SanPhamModel> sanPhams = new ArrayList<>();
        String sql = "Select * from SanPham where trangThai = ?";
      
        try {
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trangThaiFind);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maSanPham = resultSet.getString("maSanPham");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                String tenSanPham = resultSet.getString("tenSanPham");
                int trangThai = resultSet.getInt("trangThai");
                SanPhamModel sanPham = new SanPhamModel(id, maSanPham, ngaySua, ngayTao, tenSanPham, trangThai);
                sanPhams.add(sanPham);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }

    public boolean isMaSanPhamUnique(int id, String ma) {
        
        boolean rowExist = false;
        try {
           

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM SanPham WHERE maSanPham = ? AND id <> ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            preparedStatement.setInt(2, id);

            // Execute the delete statement
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                rowExist = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowExist;
    }

    public boolean updateById(int id, SanPhamModel sp) {
        boolean checkUpdate = false;
       
        try {
         

            // Prepare the SQL DELETE statement
            String sql = "UPDATE SanPham SET maSanPham = ?, tenSanPham = ?, ngaySua = ?, trangThai = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sp.getMaSanPham());
            preparedStatement.setNString(2, sp.getTenSanPham());

            preparedStatement.setTimestamp(3, sp.getNgaySua());
            preparedStatement.setInt(4, sp.getTrangThai());
            preparedStatement.setInt(5, id);
            // Execute the delete statement
            int rowsAffected = preparedStatement.executeUpdate();
            checkUpdate = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
//            try {
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return checkUpdate;
    }

    public List<SanPhamModel> searchSanPham(String ma, String ten) {

        List<SanPhamModel> sanPhams = new ArrayList<>();
        String query = "SELECT * FROM SanPham where maSanPham  like ? or tenSanPham like ?";
        
        
        try {
           
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, "%" + ma + "%");
            preparedStatement.setString(2, "%" + ten + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maSanPham = resultSet.getString("maSanPham");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                String tenSanPham = resultSet.getString("tenSanPham");
                int trangThai = resultSet.getInt("trangThai");
                SanPhamModel sanPham = new SanPhamModel(id, maSanPham, ngaySua, ngayTao, tenSanPham, trangThai);
                sanPhams.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }

    public List<SanPhamModel> searchTenSanPham(String ten) {

        List<SanPhamModel> sanPhams = new ArrayList<>();
        String query = "SELECT * FROM SanPham where tenSanPham like ?";
       
        try {
           
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, "%" + ten + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maSanPham = resultSet.getString("maSanPham");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                String tenSanPham = resultSet.getString("tenSanPham");
                int trangThai = resultSet.getInt("trangThai");
                SanPhamModel sanPham = new SanPhamModel(id, maSanPham, ngaySua, ngayTao, tenSanPham, trangThai);
                sanPhams.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }

    public List<SanPhamModel> getSanPhamByPage(int page) {
        List<SanPhamModel> sanPhams = new ArrayList<>();
        String query = "SELECT * FROM SanPham ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try  {
            preparedStatement = connection.prepareStatement(query);
            int offset = (page - 1) * PAGE_SIZE_SP;
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, PAGE_SIZE_SP);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maSanPham = resultSet.getString("maSanPham");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                String tenSanPham = resultSet.getString("tenSanPham");
                int trangThai = resultSet.getInt("trangThai");
                SanPhamModel sanPham = new SanPhamModel(id, maSanPham, ngaySua, ngayTao, tenSanPham, trangThai);
                sanPhams.add(sanPham);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sanPhams;
    }

    public int getTotalPages() {
        String countQuery = "SELECT COUNT(*) FROM SanPham";
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

        return (int) Math.ceil(totalRecords / (double) PAGE_SIZE_SP);
    }
 
}
