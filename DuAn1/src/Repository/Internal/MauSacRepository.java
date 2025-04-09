/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.MauSacModel;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author phamq
 */
public class MauSacRepository  {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public MauSacRepository() {
        try {
            connection = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean save(MauSacModel mauSacModel) {
        boolean rowInsert = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL INSERT statement
            String sql = "INSERT INTO MauSac (maMauSac, tenMau, ngayTao, ngaySua, trangThai) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set the values for the prepared statement
            preparedStatement.setString(1, mauSacModel.getMaMauSac());
            preparedStatement.setNString(2, mauSacModel.getTenMauSac());
            preparedStatement.setTimestamp(3, mauSacModel.getNgayTao());
            preparedStatement.setTimestamp(4, mauSacModel.getNgaySua());
            preparedStatement.setInt(5, mauSacModel.getTrangThai());

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInsert;
    }

   
    public List<MauSacModel> findAll() {
        List<MauSacModel> mauSacs = new ArrayList<>();
        String sql = "Select * from MauSac";
        try {
            Connection connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maMauSac = resultSet.getString("maMauSac");
                String tenMau = resultSet.getNString("tenMau");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                MauSacModel mauSacModel = new MauSacModel(id, maMauSac, ngaySua, ngayTao, tenMau, trangThai);
                mauSacs.add(mauSacModel);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSacs;
    }

   
    public MauSacModel findById(int id) {
        MauSacModel mauSacModel = null;
        String sql = "SELECT * FROM MauSac WHERE id = ?";
        try {
            connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idFind = resultSet.getInt("id");
                String maMau = resultSet.getString("maMauSac");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                String tenMau = resultSet.getString("tenMau");
                int trangThai = resultSet.getInt("trangThai");
                mauSacModel = new MauSacModel(idFind, maMau, ngaySua, ngayTao, tenMau, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return mauSacModel;
    }

   
    public boolean deleteById(int id) {
        boolean rowDeleted = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "DELETE FROM MauSac WHERE id = ?";
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

   
    public boolean existsByMaSanPham(String maMauSac) {
        boolean rowExist = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "DELETE FROM MauSac WHERE maMauSac = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maMauSac);

            // Execute the delete statement
            int rowsAffected = preparedStatement.executeUpdate();
            rowExist = rowsAffected > 0;
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
        return rowExist;
    }


   
    public boolean updateById(int id, MauSacModel mauSacModel) {
             boolean checkUpdate = false;
          try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "UPDATE MauSac SET maMauSac = ?, tenMau = ?, ngaySua = ?, trangThai = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mauSacModel.getMaMauSac());
            preparedStatement.setNString(2, mauSacModel.getTenMauSac());
        
            preparedStatement.setTimestamp(3, mauSacModel.getNgaySua());
            preparedStatement.setInt(4, mauSacModel.getTrangThai());
                preparedStatement.setInt(5, id);
            // Execute the delete statement
            int rowsAffected = preparedStatement.executeUpdate();
            checkUpdate = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
//            try {
//                if (preparedStatement != null) preparedStatement.close();
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
        return  checkUpdate;
    }

   
    public List<MauSacModel> findAllByTrangThai(int trangThaiFind) {
 List<MauSacModel> mauSacs = new ArrayList<>();
        String sql = "Select * from MauSac where trangThai = ?";
        try {
            connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trangThaiFind);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maMauSac = resultSet.getString("maMauSac");
                String tenMau = resultSet.getNString("tenMau");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                MauSacModel mauSacModel = new MauSacModel(id, maMauSac, ngaySua, ngayTao, tenMau, trangThai);
                mauSacs.add(mauSacModel);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSacs;    }

   
    public boolean isMaMauUnique(int id, String ma) {
        boolean rowExist = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM MauSac WHERE maMauSac = ? AND id <> ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ma);
            preparedStatement.setInt(2, id);

            // Execute the delete statement
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                    rowExist= resultSet.getInt(1) > 0;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return rowExist;
    }

}
