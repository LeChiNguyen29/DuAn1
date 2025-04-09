/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Internal.DanhMucModel;

import Repository.Internal.DanhMucRepository;

import java.util.List;

/**
 *
 * @author phamq
 */
public class DanhMucService {
    private DanhMucRepository repo = new DanhMucRepository();
   
    public List<DanhMucModel> getAllDanhMuc() {
        return repo.findAll();
    }

   
    public boolean insertDanhMuc(DanhMucModel danhMucModel) {
        return repo.save(danhMucModel);
    }

   
    public boolean checkMaDanhMuc(String ma) {
      return  repo.existsByMaDanhMuc(ma);
    }

   
    public boolean updateDanhMuc(int id, DanhMucModel danhMucModel) {
        return repo.updateById(id, danhMucModel);
    }

   
    public List<DanhMucModel> getAllDanhMucByTrangThai(int trangThai) {
        return repo.findAllByTrangThai(trangThai);
    }

   
    public boolean isMaUnique(int id, String ma) {
        return repo.isMaUnique(id, ma);
    }
    
}
