/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import Model.Internal.ChatLieuModel;

import DBContext.DBContext;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author phamq
 */
public class ChatLieuRepository {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ChatLieuRepository() {
        try {
            connection = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean save(ChatLieuModel chatLieuModel) {

        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL INSERT statement
            String sql = "INSERT INTO ChatLieu (maChatLieu, tenChatLieu, ngayTao, ngaySua, trangThai) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Set the values for the prepared statement
            preparedStatement.setString(1, chatLieuModel.getMaChatLieu());
            preparedStatement.setNString(2, chatLieuModel.getTenChatLieu());
            preparedStatement.setTimestamp(3, chatLieuModel.getNgayTao());
            preparedStatement.setTimestamp(4, chatLieuModel.getNgaySua());
            preparedStatement.setInt(5, chatLieuModel.getTrangThai());

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<ChatLieuModel> findAll() {
        List<ChatLieuModel> chatLieus = new ArrayList<>();
        String sql = "Select * from ChatLieu";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maChatLieu = resultSet.getString("maChatLieu");
                String tenChatLieu = resultSet.getNString("tenChatLieu");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                ChatLieuModel chatLieuModel = new ChatLieuModel(id, maChatLieu, ngaySua, ngayTao, tenChatLieu, trangThai);
                chatLieus.add(chatLieuModel);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatLieus;
    }

    public ChatLieuModel findById(int id) {
        ChatLieuModel chatLieuModel = null;
        String sql = "Select * from ChatLieu where id =?";
        try {
            connection = DBContext.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idFind = resultSet.getInt("id");
                String maChatLieu = resultSet.getString("maChatLieu");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                String tenChatLieu = resultSet.getString("tenChatLieu");
                int trangThai = resultSet.getInt("trangThai");
                chatLieuModel = new ChatLieuModel(idFind, maChatLieu, ngaySua, ngayTao, tenChatLieu, trangThai);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return chatLieuModel;
    }

    public boolean deleteById(int id) {

        boolean rowDeleted = false;

        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "DELETE FROM ChatLieu WHERE id = ?";
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

    public boolean updateById(int id, ChatLieuModel chatLieuModel) {
        boolean checkUpdate = false;

        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "UPDATE ChatLieu SET maChatLieu = ?, tenChatLieu = ?, ngaySua = ?, trangThai = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, chatLieuModel.getMaChatLieu());
            preparedStatement.setNString(2, chatLieuModel.getTenChatLieu());
            preparedStatement.setTimestamp(3, chatLieuModel.getNgaySua());
            preparedStatement.setInt(4, chatLieuModel.getTrangThai());
            preparedStatement.setInt(5, id);

            // Execute the delete statement
            int rowsAffected = preparedStatement.executeUpdate();
            checkUpdate = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
//       
        }
        return checkUpdate;
    }

    public boolean existsByMaChatLieu(String maChatLieu) {
        boolean exists = false;

        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL SELECT statement
            String sql = "SELECT 1 FROM ChatLieu WHERE maChatLieu = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maChatLieu);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if any record is returned
            exists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
//        
        }

        return exists;

    }

    public List<ChatLieuModel> findAllByTrangThai(int trangThaiFind) {
        List<ChatLieuModel> chatLieus = new ArrayList<>();
        String sql = "Select * from ChatLieu where trangThai = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, trangThaiFind);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String maChatLieu = resultSet.getString("maChatLieu");
                String tenChatLieu = resultSet.getNString("tenChatLieu");
                Timestamp ngayTao = resultSet.getTimestamp("ngayTao");
                Timestamp ngaySua = resultSet.getTimestamp("ngaySua");
                int trangThai = resultSet.getInt("trangThai");
                ChatLieuModel chatLieuModel = new ChatLieuModel(id, maChatLieu, ngaySua, ngayTao, tenChatLieu, trangThai);
                chatLieus.add(chatLieuModel);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatLieus;
    }

    public boolean isMaChatLieuUnique(int id, String ma) {
        boolean rowExist = false;
        try {
            // Establish the connection
            connection = DBContext.getConnection();

            // Prepare the SQL DELETE statement
            String sql = "SELECT COUNT(*) FROM ChatLieu WHERE maChatLieu = ? AND id <> ?";
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
