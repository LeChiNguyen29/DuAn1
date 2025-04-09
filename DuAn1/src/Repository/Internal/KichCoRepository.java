/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.KichCoModel;

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
public class KichCoRepository {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public KichCoRepository() {
        try {
            connection = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean save(KichCoModel kichCoModel) {
        boolean rowInsert = false;
        try {
            String sql = "INSERT INTO KichCo (maKichCo, size, ngayTao, ngaySua, trangThai) VALUES (?, ?, ?, ?, ?)";
            connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            // Execute the statement

            preparedStatement.setString(1, kichCoModel.getMaKichCo());
            preparedStatement.setInt(2, kichCoModel.getSize());
            preparedStatement.setTimestamp(3, kichCoModel.getNgayTao());
            preparedStatement.setTimestamp(4, kichCoModel.getNgaySua());
            preparedStatement.setInt(5, kichCoModel.getTrangThai());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInsert;
    }

    public List<KichCoModel> findAll() {
        List<KichCoModel> kichCos = new ArrayList<>();
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM KichCo";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maKichCo = resultSet.getString("maKichCo");
                int size = resultSet.getInt("size");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                KichCoModel kichCo = new KichCoModel(id, maKichCo, ngayTao, ngaySua, size, trangThai);
                kichCos.add(kichCo);
                // Printing the result
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kichCos;
    }

    public KichCoModel findById(int id) {
        KichCoModel kichCoModel = null;

        try {

            connection = DBContext.getConnection();
            String sql = "SELECT * FROM KichCo WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idFind = resultSet.getInt("id");
                String maKichCo = resultSet.getString("maKichCo");
                int size = resultSet.getInt("size");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                kichCoModel = new KichCoModel(idFind, maKichCo, ngayTao, ngaySua, size, trangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return kichCoModel;
    }

    public boolean deleteById(int id) {
        boolean rowDeleted = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "DELETE FROM KichCo WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // Execute the delete statement
            int rowsAffected = preparedStatement.executeUpdate();
            rowDeleted = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean existsByMaKichCo(String maKichCo) {
        boolean check = false;
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM KichCo WHERE maKichCo = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maKichCo);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean existsBySizeKichCo(int size) {
        boolean check = false;
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM KichCo WHERE size = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateById(int id, KichCoModel kichCoModel) {
        boolean checkUpdate = false;
        try {
            connection = DBContext.getConnection();
            String sql = "UPDATE KichCo SET maKichCo = ?, size = ?, ngaySua = ?, trangThai = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, kichCoModel.getMaKichCo());
            preparedStatement.setInt(2, kichCoModel.getSize());
            preparedStatement.setTimestamp(3, kichCoModel.getNgaySua());
            preparedStatement.setInt(4, kichCoModel.getTrangThai());
            preparedStatement.setInt(5, id);
            int rowsAffected = preparedStatement.executeUpdate();
            checkUpdate = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkUpdate;
    }

    public List<KichCoModel> findAllByTrangThai(int trangThaiFind) {
        List<KichCoModel> kichCos = new ArrayList<>();
        try {
            connection = DBContext.getConnection();
            String sql = "SELECT * FROM KichCo where trangThai = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, trangThaiFind);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maKichCo = resultSet.getString("maKichCo");
                int size = resultSet.getInt("size");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                KichCoModel kichCo = new KichCoModel(id, maKichCo, ngayTao, ngaySua, size, trangThai);
                kichCos.add(kichCo);
                // Printing the result
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kichCos;
    }

    public boolean isSizeUnique(int id, int size) {
        boolean rowExist = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM KichCo WHERE size = ? AND id <> ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
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

    public boolean isMaUnique(int id, String ma) {
        boolean rowExist = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM KichCo WHERE maKichCo = ? AND id <> ?";
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
}
