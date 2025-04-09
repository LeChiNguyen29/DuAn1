/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.DanhMucModel;

import DBContext.DBContext;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author phamq
 */
public class DanhMucRepository {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public DanhMucRepository() {
        try {
            connection = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean save(DanhMucModel danhMucModel) {
        boolean rowInsert = false;
        try {
            String sql = "INSERT INTO DanhMuc (maDanhMuc, tenDanhMuc, ngayTao, ngaySua, trangThai) VALUES (?, ?, ?, ?, ?)";
            connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            // Execute the statement

            preparedStatement.setString(1, danhMucModel.getMaDanhMuc());
            preparedStatement.setString(2, danhMucModel.getTenDanhMuc());
            preparedStatement.setTimestamp(3, danhMucModel.getNgayTao());
            preparedStatement.setTimestamp(4, danhMucModel.getNgaySua());
            preparedStatement.setInt(5, danhMucModel.getTrangThai());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInsert;
    }

    public List<DanhMucModel> findAll() {
        List<DanhMucModel> danhMucs = new ArrayList<>();
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM DanhMuc";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maDanhMuc = resultSet.getString("maDanhMuc");
                String tenDanhMuc = resultSet.getString("tenDanhMuc");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                DanhMucModel danhMucModel = new DanhMucModel(id, maDanhMuc, ngayTao, ngaySua, tenDanhMuc, trangThai);
                danhMucs.add(danhMucModel);
                // Printing the result
            }
        } catch (SQLException e) {
            e.printStackTrace();
//       
        }
        return danhMucs;

    }

    public DanhMucModel findById(int id) {
        DanhMucModel danhMucModel = null;

        try {

            connection = DBContext.getConnection();
            String sql = "SELECT * FROM DanhMuc WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idFind = resultSet.getInt("id");
                String maDanhMuc = resultSet.getString("maDanhMuc");
                String tenDanhMuc = resultSet.getString("tenDanhMuc");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                danhMucModel = new DanhMucModel(idFind, maDanhMuc, ngayTao, ngaySua, tenDanhMuc, trangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhMucModel;
    }

    public boolean deleteById(int id) {
        boolean rowDeleted = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "DELETE FROM DanhMuc WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // Execute the delete statement
            int rowsAffected = preparedStatement.executeUpdate();
            rowDeleted = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
//        
        }
        return rowDeleted;
    }

    public boolean existsByMaDanhMuc(String maDanhMuc) {
        boolean check = false;
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM DanhMuc WHERE maDanhMuc = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maDanhMuc);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
//        
        }
        return check;
    }

    public boolean updateById(int id, DanhMucModel danhMucModel) {
        boolean checkUpdate = false;
        try {
            connection = DBContext.getConnection();
            String sql = "UPDATE DanhMuc SET maDanhMuc = ?, tenDanhMuc = ?, ngaySua = ?, trangThai = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, danhMucModel.getMaDanhMuc());
            preparedStatement.setNString(2, danhMucModel.getTenDanhMuc());
            preparedStatement.setTimestamp(3, danhMucModel.getNgaySua());
            preparedStatement.setInt(4, danhMucModel.getTrangThai());
            preparedStatement.setInt(5, id);
            int rowsAffected = preparedStatement.executeUpdate();
            checkUpdate = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkUpdate;

    }

//    public static void main(String[] args) {
//        DanhMucRepository danhMucRepository = new DanhMucRepository();
//        
//    }
    public List<DanhMucModel> findAllByTrangThai(int trangThaiFind) {
        List<DanhMucModel> danhMucs = new ArrayList<>();
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM DanhMuc where trangThai = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trangThaiFind);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maDanhMuc = resultSet.getString("maDanhMuc");
                String tenDanhMuc = resultSet.getString("tenDanhMuc");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                DanhMucModel danhMucModel = new DanhMucModel(id, maDanhMuc, ngayTao, ngaySua, tenDanhMuc, trangThai);
                danhMucs.add(danhMucModel);
                // Printing the result
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhMucs;
    }

    public boolean isMaUnique(int id, String ma) {
        boolean rowExist = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM DanhMuc WHERE maDanhMuc = ? AND id <> ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            preparedStatement.setInt(2, id);

            // Execute the delete statement
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rowExist = resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowExist;
    }
}
