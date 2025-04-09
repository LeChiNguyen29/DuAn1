/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Internal.ChatLieuModel;


import Repository.Internal.ChatLieuRepository;



import java.util.List;

/**
 *
 * @author phamq
 */
public class ChatLieuService {
    private ChatLieuRepository repo = new ChatLieuRepository();
    
   
    public List<ChatLieuModel> getAllChatLieu() {
        return repo.findAll();
    }

   
    public boolean insertChatLieu(ChatLieuModel chatLieuModel) {
        return repo.save(chatLieuModel);
    }

   
    public boolean checkMaChatLieu(String ma) {
        return  repo.existsByMaChatLieu(ma);
    }

   
    public boolean updateChatLieu(int id, ChatLieuModel chatLieu) {
        return repo.updateById(id, chatLieu);
    }

   
    public List<ChatLieuModel> getAllChatLieuByTrangThai(int trangThai) {
        return repo.findAllByTrangThai(trangThai);
    }

   
    public boolean isMaChatLieuUnique(int id, String ma) {
        return repo.isMaChatLieuUnique(id, ma);
    }

 
    
}
