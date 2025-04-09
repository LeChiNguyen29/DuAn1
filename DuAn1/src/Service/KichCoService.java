/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;


import Model.Internal.KichCoModel;



import Repository.Internal.KichCoRepository;


import java.util.List;

/**
 *
 * @author phamq
 */
public class KichCoService {
    private KichCoRepository repo = new KichCoRepository();
    
   
    public List<KichCoModel> getAllKichCo() {
        return repo.findAll();
    }

   
    public List<KichCoModel> getALlKichCoByTrangThai(int trangThai) {
        return repo.findAllByTrangThai(trangThai);
    }

   
    public boolean insertKichCo(KichCoModel kichCo) {
        return repo.save(kichCo);
    }

   
    public boolean checkMaKichCo(String ma) {
        return repo.existsByMaKichCo(ma);
    }

   public boolean checkSizeKichCo(int size) {
        return repo.existsBySizeKichCo(size);
    }

    public boolean updateKichCo(int id, KichCoModel kichCo) {
        return repo.updateById(id, kichCo);
    }

   
    public boolean isSizeUnique(int id, int ma) {
        return repo.isSizeUnique(id, ma);
    }

    public boolean isMaUnique(int id, String ma) {
        return repo.isMaUnique(id, ma);
    }
    public KichCoModel getById(int id) {
        return repo.findById(id);
    }

 
    
}
