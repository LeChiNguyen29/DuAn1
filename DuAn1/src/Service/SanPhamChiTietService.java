/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Internal.DanhMucModel;
import Model.Internal.SanPhamChiTietModel;
import Model.Internal.SanPhamModel;

import Repository.Internal.SanPhamChiTietRepository;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author phamq
 */
public class SanPhamChiTietService {

    private SanPhamChiTietRepository rp = new SanPhamChiTietRepository();

    public List<SanPhamChiTietModel> getAllSanPhamChiTiet() {
        return rp.findAll();
    }

    public SanPhamChiTietModel getById(int id) {
        return rp.findById(id);
    }

    public boolean insert(SanPhamChiTietModel chiTietModel) {
        return rp.save(chiTietModel);
    }

    public boolean checkMaSPCT(String ma) {
        return rp.existsByMaSPCT(ma);
    }

    public boolean updateSPCT(int id, SanPhamChiTietModel sanPhamChiTietModel) {
        return rp.updateById(id, sanPhamChiTietModel);
    }

    public boolean isMaMauUnique(int id, String ma) {
        return rp.isMaUnique(id, ma);
    }

    public List<SanPhamChiTietModel> getAllByTrangThai(int trangThai) {
        return rp.findByTrangThai(trangThai);
    }

    public List<SanPhamChiTietModel> search(Integer trangThai, DanhMucModel danhMucModel, SanPhamModel sp) throws SQLException {
        return rp.search(trangThai, danhMucModel, sp);
    }

    public List<SanPhamChiTietModel> searchNew(String tenSanPham, String tenDanhMuc, Integer trangThai) throws SQLException {
        return rp.searchNew(tenSanPham, tenDanhMuc, trangThai);
    }

    public List<SanPhamChiTietModel> getSanPhamByPage(int page)  {
        return rp.getSanPhamByPage(page);
    }

    public int getTotalPages() {
        return rp.getTotalPages();
    }
}
