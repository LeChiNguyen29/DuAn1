/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import DBContext.DBContext;
import Model.Internal.HoaDonChiTietModel;
import Model.Internal.HoaDonModel;
import Model.Internal.Model_SanPhamChiTiet;
import View.Dialog.DiaLogInput;
import View.Dialog.DiaLogThongBao;
import View.Dialog.DiaLogYesNo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lcinu
 */
public class BanHangRepo {
    
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private int tongTien = 0;
    private int soLuong = 0;
    private int count = 0;
    
    private DiaLogThongBao thongBao = new DiaLogThongBao();
    private static final int PAGE_SIZE_SPCT = 4;
    
    public BanHangRepo() {
        try {
            conn = DBContext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<HoaDonChiTietModel> getAllHoaDon(String ma) {
        ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement("""
                                       SELECT hdct.id, spct.maSanPhamChiTiet, dm.tenDanhMuc  + ' ' + sp.tenSanPham
                                       , spct.giaSanPham, hdct.soLuong, spct.giaSanPham * hdct.soLuong FROM HoaDonChiTiet hdct
                                       LEFT JOIN SanPhamChiTiet spct ON hdct.idSanPhamChiTiet = spct.id
                                       LEFT JOIN SanPham sp ON spct.idSanPham = sp.id
                                       LEFT JOIN DanhMuc dm ON spct.idDanhMuc = dm.id
                                       LEFT JOIN HoaDon hd ON hdct.idHoaDon = hd.id
                                       WHERE hd.maHoaDon = ? AND hdct.trangThai = 0""");
            
            ps.setString(1, ma);
            
            rs = ps.executeQuery();
            
            tongTien = 0;
            while (rs.next()) {
                tongTien += rs.getInt(6);
                list.add(new HoaDonChiTietModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void fillTableHoaDon(JTable table, JTable tableHoaDonCho, String ma) {
        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
        tblModel.setRowCount(0);
        
        for (HoaDonChiTietModel x : getAllHoaDon(ma)) {
            tblModel.addRow(x.toDataRow());
        }
    }
    
    public ArrayList<HoaDonModel> getAllHoaDonCho() {
        ArrayList<HoaDonModel> list = new ArrayList<>();
        
        try {
            
            ps = conn.prepareStatement("""
                                       SELECT hd.id, hd.maHoaDon, hd.loaiThanhToan, hd.tongTien, kh.hoTen, kh.soDienThoai
                                                , nv.hoTen, idVoucher, hd.ngayTao, hd.trangThai FROM HoaDon hd
                                       LEFT JOIN KhachHang kh ON hd.idKhachHang = kh.id
                                       LEFT JOIN NhanVien nv ON hd.idNhanVien = nv.id
                                       WHERE hd.trangThai = 1""");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new HoaDonModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void fillTableHoaDonCho(JTable table) {
        DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
        tblModel.setRowCount(0);
        
        for (HoaDonModel x : getAllHoaDonCho()) {
            tblModel.addRow(x.toDataRow());
        }
    }
    
    public void getAllSanPham(String search, String loai, JLabel ma, JPanel panel, JTable hoaDon,
                                    JTable hoaDonCho, JTextField tongTien, JTextField thanhTien, JTabbedPane tabbedPane
                                            , int idNhanVien, int page, JTextField tenKH) {
        JPanel newJPanels = new JPanel();
        
        count = 0;
        
        StringBuilder sql = new StringBuilder("""
                                              SELECT spct.id, spct.anhSanPham, dm.tenDanhMuc + ' ' + sp.tenSanPham
                                                            , spct.giaSanPham, spct.soLuong FROM SanPhamChiTiet spct
                                                    INNER JOIN SanPham sp ON spct.idSanPham = sp.id
                                                    INNER JOIN DanhMuc dm ON spct.idDanhMuc = dm.id
                                              WHERE spct.trangThai = 0 AND spct.soLuong > 0 AND dm.tenDanhMuc + ' ' + sp.tenSanPham LIKE ?
                                              """);
        if (!loai.equals("Tất Cả")) {
            sql.append(" AND dm.tenDanhMuc = N'").append(loai).append("'");
        }
        
        sql.append("ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        
        try {
            ps = conn.prepareStatement(sql.toString());
            
            ps.setString(1, "%" + search + "%");
            int offset = (page - 1) * PAGE_SIZE_SPCT;
            ps.setInt(2, offset);
            ps.setInt(3, PAGE_SIZE_SPCT);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                
                count++;
                JPanel newPanel = newPanel(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                newJPanels.add(newPanel);
                
                int idSpct = rs.getInt(1);
                int gia = rs.getInt(4);
                int soLuongTon = rs.getInt(5);
                
                newPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        checkHoaDonChiTiet(ma, hoaDon, hoaDonCho, tabbedPane, idSpct, gia, soLuongTon,
                                tongTien, thanhTien, search, loai, panel, idNhanVien, page, tenKH);
                    }
                });
            }
//            lblPage.setText(currentPage + "/" + getTotalPages(search, loai));
            mainPanel(newJPanels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        panel.add(newJPanels);
    }
    
    public int soLuongSp() {
        return count;
    }

    // Code hoa don 
    public int themHoaDonChiTiet(int idHoaDon, int gia, int soLuong, int idSpct) {
        
        if (check(idHoaDon, idSpct)) {
            try {
                ps = conn.prepareStatement("""
                                       INSERT INTO HoaDonChiTiet 
                                           (maHoaDonChiTiet, soLuong, tongTien, trangThai, idHoaDon, idSanPhamChiTiet, ngayTao)
                                       VALUES 
                                           ('""" + maHoaDonChiTiet() + "', ?, ?, ?, ?, ?, GETDATE())");
                ps.setInt(1, soLuong);
                ps.setInt(2, gia * soLuong);
                ps.setInt(3, 0);
                ps.setInt(4, idHoaDon);
                ps.setInt(5, idSpct);
                
                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            try {
                ps = conn.prepareStatement("UPDATE HoaDonChiTiet SET soLuong = soLuong + ? WHERE idSanPhamChiTiet = ?");
                ps.setInt(1, soLuong);
                ps.setInt(2, idSpct);
                
                return ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }
    
    public int sttHoaDonChiTiet() {
        try {
            ps = conn.prepareStatement("SELECT * FROM HoaDonChiTiet");
            
            rs = ps.executeQuery();
            
            int index = 0;
            
            while (rs.next()) {
                index++;
            }
            
            return index + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public String maHoaDonChiTiet() {
        
        String ma;
        
        if (sttHoaDonChiTiet() < 10) {
            ma = "HDCT00" + String.valueOf(sttHoaDonChiTiet());
        } else if (sttHoaDonChiTiet() < 100) {
            ma = "HDCT0" + String.valueOf(sttHoaDonChiTiet());
        } else {
            ma = "HDCT" + String.valueOf(sttHoaDonChiTiet());
        }
        return ma;
    }
    
    public String maHoaDonMoi(JTable hoaDonCho) {
        
        return getAllHoaDonCho().get(hoaDonCho.getRowCount() - 1).getMaHoaDon();
    }
    
    public int updateTrangThai(int soLuong, int idHdct) {
        try {
            ps = conn.prepareStatement("UPDATE HoaDonChiTiet SET trangThai = 1 WHERE id = ?");
            ps.setInt(1, soLuong);
            ps.setInt(2, idHdct);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public void checkHoaDonChiTiet(JLabel ma, JTable hoaDon, JTable hoaDonCho, JTabbedPane tabbedPane,
                                            int idSpct, int gia, int soLuong, JTextField tongTien, JTextField thanhTien,
                                                    String search, String loai, JPanel panel, int idNhanVien, int page, JTextField tenKH) {
        
        int index = hoaDonCho.getSelectedRow();
        
        if (ma.getText().equals("#####")) {
            
            
            DiaLogYesNo logYesNo = new DiaLogYesNo(null, "Thêm sản phẩm này vào hóa đơn mới?");
            logYesNo.setVisible(true);
            
            if (logYesNo.getResult() == JOptionPane.YES_OPTION) {
                
                themHoaDonMoi(idNhanVien);
                tenKH.setText("Khách Vãng Lai");
                thongBao.thongBao("Tạo hóa đơn thành công");
                
                fillTableHoaDonCho(hoaDonCho);
                String maHoaDon = maHoaDonMoi(hoaDonCho);
                
                tabbedPane.setTitleAt(0, maHoaDon);
                ma.setText(maHoaDon);
                index = hoaDonCho.getRowCount() - 1;
                
                themHdct(index, idSpct, maHoaDon, gia, soLuong, tongTien, thanhTien, hoaDon, hoaDonCho);
                getAllSanPham(search, loai, ma, panel, hoaDon, hoaDonCho, tongTien, thanhTien, tabbedPane, idNhanVien, page, tenKH);
                hoaDonCho.setRowSelectionInterval(index, index);
            } 
        } else {
            
            themHdct(index, idSpct, ma.getText(), gia, soLuong, tongTien, thanhTien, hoaDon, hoaDonCho);
            getAllSanPham(search, loai, ma, panel, hoaDon, hoaDonCho, tongTien, thanhTien, tabbedPane, idNhanVien, page, tenKH);
            hoaDonCho.setRowSelectionInterval(index, index);
        }
        
    }
    
    public void themHdct(int index, int idSpct, String ma, int gia, int soLuongTon,
                                                JTextField tongTien, JTextField thanhTien,
                                                                JTable hoaDon, JTable hoaDonCho) {
        
        try {
            DiaLogInput input = new DiaLogInput(null);
            input.setVisible(true);
            soLuong = input.getInputValue();
            
        } catch (NumberFormatException e) {
            soLuong = 0;
        }
        if (soLuong > 0) {
            if (soLuong <= soLuongTon) {
                
                themHoaDonChiTiet(getAllHoaDonCho().get(index).getId(), gia, soLuong, idSpct);
                updateSoLuongSpct(soLuong, idSpct);
                fillTableHoaDonCho(hoaDonCho);
                fillTableHoaDon(hoaDon, hoaDonCho, ma);
                tongTien(tongTien, thanhTien);
                hoaDonCho.setRowSelectionInterval(index, index);
                
                thongBao.thongBao("Thêm sản phẩm thành công");
            } else {
                
                thongBao.thongBao("Số lượng hàng không đủ");
            }
        } else {
            
            thongBao.thongBao("Số lượng là 1 số lớn hơn không");
        }
    }
    
    public int updateThanhToanHoaDon(String loai, long tongTien, int idHd) {
        
        try {
            ps = conn.prepareStatement("UPDATE HoaDon SET loaiThanhToan = ?, tongTien = ?, trangThai = ? WHERE id = ?");
            
            ps.setString(1, loai);
            ps.setLong(2, tongTien);
            ps.setInt(3, 0);
            ps.setInt(4, idHd);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateTrangThaiHdct(int idHoaDonChiTiet) {
        
        try {
            ps = conn.prepareStatement("UPDATE HoaDonChiTiet SET trangThai = 1 WHERE id = ?");
            
            ps.setInt(1, idHoaDonChiTiet);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getSoLuongSpct(int soLuong, int idSpct) {
        try {
            ps = conn.prepareStatement("UPDATE SanPhamChiTiet SET soLuong = soLuong + ? WHERE id = ?");
            ps.setInt(1, soLuong);
            ps.setInt(2, idSpct);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Code hoa don cho
    public int themHoaDonMoi(int idNhanVien) {
        try {
            ps = conn.prepareStatement("""
                                       INSERT INTO HoaDon
                                            (maHoaDon, loaiThanhToan, tongTien, ngayTao, idKhachHang, idNhanVien, idVoucher, trangThai)
                                       VALUES
                                            ('""" + createMaHoaDon() + "', NULL, NULL, GETDATE(), 1, ?, NULL, 1)");
            ps.setInt(1, idNhanVien);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public HoaDonModel getHoaDon(int index) {
        
        return new HoaDonModel(getAllHoaDonCho().get(index).getId(), getAllHoaDonCho().get(index).getMaHoaDon());
    }
    
    public int stt() {
        try {
            ps = conn.prepareStatement("SELECT * FROM HoaDon");
            
            rs = ps.executeQuery();
            
            int index = 0;
            
            while (rs.next()) {
                index++;
            }
            return index + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public String createMaHoaDon() {
        String ma;
        
        if (stt() < 10) {
            ma = "HD00" + String.valueOf(stt());
        } else if (stt() < 100) {
            ma = "HD0" + String.valueOf(stt());
        } else {
            ma = "HD" + String.valueOf(stt());
        }
        return ma;
    }

    // Code san pham
    public ArrayList<Model_SanPhamChiTiet> getAllSanPham() {
        ArrayList<Model_SanPhamChiTiet> list = new ArrayList<>();
        
        String sql = """
                        SELECT id, maSanPhamChiTiet, soLuong FROM SanPhamChiTiet 
                        WHERE trangThai = 0 AND soLuong >= 0
                     """;
        
        try {
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Model_SanPhamChiTiet(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Model_SanPhamChiTiet getIdMaSpct(String ma) {
        
        for (Model_SanPhamChiTiet x : getAllSanPham()) {
            
            if (x.getMaSanPham().equals(ma)) {
                
                return x;
            }
        }
        return null;
    }
    
    public int updateSoLuong(int soLuongCu, int soLuongMoi, int idSpct) {
        try {
            ps = conn.prepareStatement("UPDATE SanPhamChiTiet SET soLuong = soLuong + ? - ? WHERE id = ? ");
            
            ps.setInt(1, soLuongCu);
            ps.setInt(2, soLuongMoi);
            ps.setInt(3, idSpct);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateSoLuongSpct(int soLuong, int idSpct) {
        try {
            ps = conn.prepareStatement("UPDATE SanPhamChiTiet SET soLuong = soLuong - ? WHERE id = ?");
            ps.setInt(1, soLuong);
            ps.setInt(2, idSpct);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int updateSoLuongHdct(int soLuong, int idHdct) {
        try {
            ps = conn.prepareStatement("UPDATE HoaDonChiTiet SET soLuong = ? WHERE id = ?");
            ps.setInt(1, soLuong);
            ps.setInt(2, idHdct);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public boolean check(int idHoaDon, int idSpct) {
        try {
            ps = conn.prepareStatement("SELECT idSanPhamChiTiet FROM HoaDonChiTiet WHERE trangThai = 0 AND idHoaDon = ? AND idSanPhamChiTiet = ?");
            
            ps.setInt(1, idHoaDon);
            ps.setInt(2, idSpct);
            
            rs = ps.executeQuery();
            
            return !rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public JPanel mainPanel(JPanel mainPanel) {
        
        mainPanel.setLayout(new GridLayout(0, 4, 10, 10));
        
//        if (mainPanel.getComponentCount() % 4 == 0) {
            mainPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, 290));
//        } else {
//            mainPanel.setPreferredSize(new Dimension(mainPanel.getPreferredSize().width, 200 * (mainPanel.getComponentCount() / 4) + 225));
//        }
        return mainPanel;
    }
    
    public JPanel newPanel(String anh, String ten, int gia, int soLuong) {
        JPanel newPanel = new JPanel();
        newPanel.setBackground(new Color(0, 153, 153));
        newPanel.setPreferredSize(new Dimension(200, 250));
        newPanel.setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(new ImageIcon(anh).getImage().getScaledInstance(170, 170, Image.SCALE_DEFAULT));
        
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setVerticalAlignment(JLabel.CENTER);
        imgLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        
        JLabel textName = new JLabel(ten, SwingConstants.CENTER);
        textName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        JLabel textLabel = new JLabel(gia + " VNĐ", SwingConstants.LEFT);
        textLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 0));
        
        JLabel textSL = new JLabel("SL: " + soLuong, SwingConstants.RIGHT);
        textSL.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 20));
        
        JPanel southPanel = new JPanel(new GridLayout(1, 0));
        southPanel.setBackground(new Color(0, 153, 153));
        southPanel.add(textLabel, BorderLayout.WEST);
        southPanel.add(textSL, BorderLayout.EAST);
        
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        newPanel.add(imgLabel, BorderLayout.NORTH);
        newPanel.add(textName, BorderLayout.CENTER);
        newPanel.add(southPanel, BorderLayout.SOUTH);
        
        return newPanel;
    }
    
    public void tongTien(JTextField tongTien, JTextField thanhTien) {
        DecimalFormat format = new DecimalFormat("#,###");
        String numberFormat = format.format(this.tongTien);
        tongTien.setText(numberFormat);
        thanhTien.setText(tongTien.getText());
    }
    
    public ArrayList<String> modelComboBox() {
        ArrayList<String> model = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement("SELECT tenDanhMuc FROM DanhMuc");
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                model.add(rs.getString(1));
            }
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public int getTotalPages(String search, String loai) {
        StringBuilder countQuery = new StringBuilder("""
                                                        SELECT COUNT(*) FROM SanPhamChiTiet spct
                                                            INNER JOIN SanPham sp ON spct.idSanPham = sp.id
                                                            INNER JOIN DanhMuc dm ON spct.idDanhMuc = dm.id
                                                        WHERE (spct.trangThai = 0 AND spct.soLuong > 0) AND dm.tenDanhMuc + ' ' + sp.tenSanPham LIKE ?
                                                    """);
        if (!loai.equals("Tất Cả")) {
            countQuery.append(" AND dm.tenDanhMuc = N'").append(loai).append("'");
        } 
        
        int totalRecords = 0;
        try {
            ps = conn.prepareStatement(countQuery.toString());
            
            ps.setString(1, "%" + search + "%");

            rs = ps.executeQuery();
            
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (int) Math.ceil(totalRecords / (double) PAGE_SIZE_SPCT);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // Code khach hang 
    public int suaKhachHangHoaDon(int idHoaDon) {
        
        try {
            ps = conn.prepareStatement("UPDATE HoaDon SET idKhachHang = 1 WHERE id = ?");
            
            ps.setInt(1, idHoaDon);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Code voucher
    public int suaVoucherHoaDon(int idHoaDon) {
        
        try {
            ps = conn.prepareStatement("UPDATE HoaDon SET idVoucher = NULL WHERE id = ?");
            
            ps.setInt(1, idHoaDon);
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Code xuat hoa don
    public void xuatHoaDon(String ma, String tenKh, String sdt, String tongTien, String maVoucher, String giam, String thanhTien, boolean in) {
        String fileName = "F://DuAn1//" + ma + ".pdf";
        String fontPath = "C:/Windows/Fonts/Arial.ttf";
        // Thông tin công ty
        String companyName = "Làng Lá Stor";
        String companyAddress = "Phố Kiều Mai - Quận Bắc Từ Niêm";
        String companyPhone = "0366263926";
        String companyLogo = "D://Downloads//NetBean//Img//banner.png";

        // Thông tin khách hàng
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'tháng' MM 'năm' yyyy");
        String invoiceDate = "Ngày " + today.format(formatter);
        
        ArrayList<HoaDonChiTietModel> list = getAllHoaDon(ma);
        
        List<String[]> products = new ArrayList<>();
        
        for (HoaDonChiTietModel x : list) {
            products.add(new String[]{x.getTen(), String.valueOf(x.getSoLuong()), String.valueOf(x.getGia())});
        }
        
        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            Font fontNormal = new Font(baseFont, 12);
            Font fontBold = new Font(baseFont, 12, Font.BOLD);
            Font fontItalic = new Font(baseFont, 12, Font.ITALIC);
            Font fontLarge = new Font(baseFont, 18, Font.BOLD);
            
            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(companyLogo);
            img.scaleToFit(100, 100);
            img.setAlignment(Element.ALIGN_LEFT);
            document.add(img);
            
            Paragraph companyInfo = new Paragraph(companyName + "\n" + companyAddress + "\n" + "SĐT: " + companyPhone, fontBold);
            companyInfo.setAlignment(Element.ALIGN_RIGHT);
            document.add(companyInfo);
            
            document.add(new Paragraph("\n"));
            
            Paragraph title = new Paragraph("HÓA ĐƠN", fontLarge);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            document.add(new Paragraph("\n"));
            
            Paragraph information = new Paragraph("Thông tin khách hàng", fontBold);
            information.setAlignment(Element.ALIGN_LEFT);
            document.add(information);
            
            document.add(new Paragraph("\n"));
            
            if (!tenKh.equals("Khách Vãng Lai")) {
                Paragraph customerInfo = new Paragraph("Tên: " + tenKh + "\n" + "SĐT: " + sdt, fontNormal);
                document.add(customerInfo);
            } else {
                Paragraph customerInfo = new Paragraph(tenKh, fontNormal);
                document.add(customerInfo);
            }
            
            Paragraph date = new Paragraph(invoiceDate, fontItalic);
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);
            
            document.add(new Paragraph("\n"));
            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            
            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontBold)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", fontBold)));
            table.addCell(new PdfPCell(new Phrase("Đơn giá (VND)", fontBold)));
            
            for (String[] product : products) {
                table.addCell(new PdfPCell(new Paragraph(product[0], fontNormal)));
                table.addCell(new PdfPCell(new Paragraph(product[1], fontNormal)));
                table.addCell(new PdfPCell(new Paragraph(product[2], fontNormal)));
            }
            
            document.add(table);
            
            document.add(new Paragraph("\n"));
            
            Paragraph total = new Paragraph("Tổng số tiền: " + tongTien + " VND", fontBold);
            total.setAlignment(Element.ALIGN_LEFT);
            document.add(total);
            
            document.add(new Paragraph("\n"));
            
            if (!maVoucher.isEmpty()) {
                Paragraph voucher = new Paragraph("Mã Voucher: " + maVoucher + " \nGiảm: " + giam, fontBold);
                voucher.setAlignment(Element.ALIGN_LEFT);
                document.add(voucher);
            }
            
            document.add(new Paragraph("\n"));
            
            Paragraph ThanhTien = new Paragraph("Thành tiền: " + thanhTien + " VND", fontBold);
            ThanhTien.setAlignment(Element.ALIGN_RIGHT);
            document.add(ThanhTien);
            
            Paragraph thanksMessage = new Paragraph("\nCảm ơn quý khách đã mua hàng!", fontItalic);
            thanksMessage.setAlignment(Element.ALIGN_CENTER);
            document.add(thanksMessage);
            
            document.close();
            
            if (in) {
                File pdfFile = new File(fileName);
                if (pdfFile.exists()) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(pdfFile);
                    } else {
                        System.out.println("Desktop is not supported, cannot open the file.");
                    }
                } else {
                    System.out.println("File not found.");
                }
            }
            
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
