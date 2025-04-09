/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Main;


import View.Internal.BanHangInternal;
import View.Internal.HoaDonInternal;
import View.Internal.HomeInternal;
import View.Internal.KhachHangInternal;
import View.Internal.NhanVienInternal;
import View.Internal.SanPhamInternal;
import View.Internal.ThongKeInternal;
import View.Internal.VoucherInternal;
import javax.swing.JDesktopPane;

/**
 *
 * @author lcinu
 */
public class Repo_Admin {
    
    private int idNhanVien;
    private HomeInternal home = new HomeInternal();
    private BanHangInternal bh = new BanHangInternal(0);
    private HoaDonInternal hd = new HoaDonInternal();
    private SanPhamInternal sp = new SanPhamInternal();
    private VoucherInternal voucher = new VoucherInternal();
    private ThongKeInternal tk = new ThongKeInternal();
    private KhachHangInternal kh = new KhachHangInternal();
    private NhanVienInternal nv = new NhanVienInternal();
    
    public void home(JDesktopPane desktopPane) {
        updaetDesktopPanel(desktopPane);
        desktopPane.add(home);
        home.setVisible(true);
    }
    
    public void banHang(JDesktopPane desktopPane, int idNhanVien) {
        this.idNhanVien = idNhanVien;
        bh = new BanHangInternal(idNhanVien);
        updaetDesktopPanel(desktopPane);
        desktopPane.add(bh);
        bh.setVisible(true);
        bh.loadForm();
    }
    
    public void hoaDon(JDesktopPane desktopPane) {
        updaetDesktopPanel(desktopPane);
        desktopPane.add(hd);
        hd.setVisible(true);
        hd.loadForm();
//        hd.selectedArrFirst();
    }
    
    public void sanPham(JDesktopPane desktopPane) {
        updaetDesktopPanel(desktopPane);
        desktopPane.add(sp);
        sp.setVisible(true);
        sp.setTab();
        sp.loadForm();
    }
    
    public void voucher(JDesktopPane desktopPane) {
        updaetDesktopPanel(desktopPane);
        desktopPane.add(voucher);
        voucher.setVisible(true);
        voucher.loadForm();
    }
    
    public void thongKe(JDesktopPane desktopPane) {
        updaetDesktopPanel(desktopPane);
        desktopPane.add(tk);
        tk.setVisible(true);
        tk.loadForm();
    }
    
    public void khachHang(JDesktopPane desktopPane) {
        updaetDesktopPanel(desktopPane);
        desktopPane.add(kh);
        kh.setVisible(true);
        kh.loadForm();
    }
    
    public void nhanVien(JDesktopPane desktopPane) {
        updaetDesktopPanel(desktopPane);
        desktopPane.add(nv);
        nv.setVisible(true);
        nv.loadForm();
    }
    
    public void updaetDesktopPanel(JDesktopPane desktopPane) {
        desktopPane.removeAll();
        desktopPane.revalidate();
        desktopPane.repaint();
    }
    
    public void setForcus() {
        bh.setMouse();
        hd.setMouse();
        sp.setMouse();
        voucher.setMouse();
    }
}
