/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Internal.SanPhamChiTietModel;
import Model.Internal.SanPhamModel;

import Repository.Internal.SanPhamRepository;

import java.util.List;

/**
 *
 * @author phamq
 */
public class SanPhamService {
       private SanPhamRepository repo = new SanPhamRepository();
   
    public boolean createSanPham(SanPhamModel sanPhamModel) {
       
      return  repo.save(sanPhamModel);
    }

   
    public List<SanPhamModel> getAllSanPham() {
           return repo.findAll();
    }

   
    public SanPhamModel getSanPhamById(int id) {
        return  repo.findById(id);
    }

   
    public String deleteById(int id) {
        String mess=null;
        SanPhamModel sp = repo.findById(id);
        if(sp==null){
            mess = "Sản phẩm không tồn tại";
        }else{
            mess ="Xóa thành công";
        }
        return  mess;
    }

   
    public List<SanPhamModel> getAllSanPhamByTrangThai(int trangThai) {
        return repo.findAllByTrangThai(trangThai);
    }

   
    public boolean isMaUnique(int id, String ma) {
        return repo.isMaSanPhamUnique(id, ma);
    }

   
    public boolean checkMaSP(String ma) {
        return repo.existsByMaSanPham(ma);
    }

   
    public boolean updateById(int id, SanPhamModel sp) {
        return repo.updateById(id, sp);
    }

   
    public List<SanPhamModel> searchSanPham(String ma, String ten) {
        return repo.searchSanPham(ma, ten);
    }
     public List<SanPhamModel> searchTenSanPham( String ten) {
        return repo.searchTenSanPham(ten);
    }
     
   public List<SanPhamModel> getSanPhamByPage(int page)  {
        return repo.getSanPhamByPage(page);
    }

    public int getTotalPages() {
        return repo.getTotalPages();
    }
}
