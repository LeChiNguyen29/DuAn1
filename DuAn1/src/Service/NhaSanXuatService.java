/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Internal.NhaSanXuatModel;

import Repository.Internal.NhaSanXuatRepository;

import java.util.List;

/**
 *
 * @author phamq
 */
public class NhaSanXuatService {
    private final NhaSanXuatRepository repo= new NhaSanXuatRepository();
   
    public List<NhaSanXuatModel> getAllNhaSanXuat() {
        return repo.findAll();
    }

   
    public List<NhaSanXuatModel> getALlNSXByTrangThai(int trangThai) {
        return repo.findAllByTrangThai(trangThai);
    }

   
    public boolean insertMauSac(NhaSanXuatModel mau) {
        return repo.save(mau);
    }

   
    public boolean checkMaNSX(String ma) {
            return repo.existsByMaNhaSanXuat(ma);
        }

   
    public boolean updateNSX(int id, NhaSanXuatModel nhaSanXuatModel) {
        return repo.updateById(id, nhaSanXuatModel);
    }

   
    public NhaSanXuatModel selectById(int id) {
return repo.findById(id);
        }

   
    public boolean isMaUnique(int id, String ma) {
        return repo.isMaUnique(id, ma);
    }
    
}
