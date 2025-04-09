/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.NhaSanXuatModel;

import DBContext.DBContext;
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
public class NhaSanXuatRepository {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public NhaSanXuatRepository() {
        try {
            connection = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean save(NhaSanXuatModel nhaSanXuatModel) {
        boolean rowInsert = false;
        try {
            String sql = "INSERT INTO NhaSanXuat (maNhaSanXuat, tenNhaSanXuat, ngayTao, ngaySua, trangThai) VALUES (?, ?, ?, ?, ?)";
            connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            // Execute the statement

            preparedStatement.setString(1, nhaSanXuatModel.getMaNSX());
            preparedStatement.setString(2, nhaSanXuatModel.getTenNSX());
            preparedStatement.setTimestamp(3, nhaSanXuatModel.getNgayTao());
            preparedStatement.setTimestamp(4, nhaSanXuatModel.getNgaySua());
            preparedStatement.setInt(5, nhaSanXuatModel.getTrangThai());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInsert;
    }

    public List<NhaSanXuatModel> findAll() {
        List<NhaSanXuatModel> list = new ArrayList<>();

        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM NhaSanXuat";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maDanhMuc = resultSet.getString("maNhaSanXuat");
                String tenDanhMuc = resultSet.getString("tenNhaSanXuat");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                NhaSanXuatModel nhaSanXuatModel = new NhaSanXuatModel(id, maDanhMuc, ngayTao, ngaySua, tenDanhMuc, trangThai);
                list.add(nhaSanXuatModel);
                // Printing the result
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public NhaSanXuatModel findById(int id) {
        NhaSanXuatModel nhaSanXuatModel = null;

        try {

            connection = DBContext.getConnection();
            String sql = "SELECT * FROM NhaSanXuat WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idFind = resultSet.getInt("id");
                String maDanhMuc = resultSet.getString("maNhaSanXuat");
                String tenDanhMuc = resultSet.getString("tenNhaSanXuat");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                nhaSanXuatModel = new NhaSanXuatModel(idFind, maDanhMuc, ngayTao, ngaySua, tenDanhMuc, trangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaSanXuatModel;
    }

    public boolean deleteById(int id) {
        boolean rowDeleted = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "DELETE FROM NhaSanXuat WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // Execute the delete statement
            int rowsAffected = preparedStatement.executeUpdate();
            rowDeleted = rowsAffected > 0;
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
        return rowDeleted;
    }

    public boolean existsByMaNhaSanXuat(String maNSX) {
        boolean check = false;
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM NhaSanXuat WHERE maNhaSanXuat = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maNSX);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
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
        return check;
    }

    public boolean updateById(int id, NhaSanXuatModel nhaSanXuatModel) {
        boolean checkUpdate = false;
        try {
            connection = DBContext.getConnection();
            String sql = "UPDATE NhaSanXuat SET maNhaSanXuat = ?, tenNhaSanXuat = ?, ngaySua = ?, trangThai = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nhaSanXuatModel.getMaNSX());
            preparedStatement.setString(2, nhaSanXuatModel.getTenNSX());
            preparedStatement.setTimestamp(3, nhaSanXuatModel.getNgaySua());
            preparedStatement.setInt(4, nhaSanXuatModel.getTrangThai());
            preparedStatement.setInt(5, id);
            int rowsAffected = preparedStatement.executeUpdate();
            checkUpdate = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkUpdate;
    }

    public boolean isMaUnique(int id, String ma) {
        boolean rowExist = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM NhaSanXuat WHERE maNhaSanXuat = ? AND id <> ?";
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

    public List<NhaSanXuatModel> findAllByTrangThai(int trangThaiFind) {
        List<NhaSanXuatModel> list = new ArrayList<>();

        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM NhaSanXuat where trangThai = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trangThaiFind);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maDanhMuc = resultSet.getString("maNhaSanXuat");
                String tenDanhMuc = resultSet.getString("tenNhaSanXuat");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                NhaSanXuatModel nhaSanXuatModel = new NhaSanXuatModel(id, maDanhMuc, ngayTao, ngaySua, tenDanhMuc, trangThai);
                list.add(nhaSanXuatModel);
                // Printing the result
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
