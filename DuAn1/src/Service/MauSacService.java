/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Internal.MauSacModel;

import Repository.Internal.MauSacRepository;

import java.util.List;

/**
 *
 * @author phamq
 */
public class MauSacService {
    
    private final MauSacRepository repo = new MauSacRepository();
   
    public List<MauSacModel> getAllMauSac() {
        return repo.findAll();
    }

   
    public List<MauSacModel> getALlMauSacByTrangThai(int trangThai) {
        return repo.findAllByTrangThai(trangThai);
    }

   
    public boolean insertMauSac(MauSacModel mau) {
        return repo.save(mau);
    }

   
    public boolean checkMaMau(String ma) {
return repo.existsByMaSanPham(ma);
    }

   
    public boolean updateMauSac(int id, MauSacModel kichCo) {
        return repo.updateById(id, kichCo);
        }

   
    public MauSacModel selectById(int id) {
        return repo.findById(id);
    }

   
    public boolean isMaMauUnique(int id, String ma) {
return  repo.isMaMauUnique(id, ma);
    }
    
}
