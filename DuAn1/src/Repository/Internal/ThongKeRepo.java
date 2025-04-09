/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Internal;

import DBContext.DBContext;
import Model.Internal.ThongKeModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author lcinu
 */
public class ThongKeRepo {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ThongKeRepo() {
        try {
            conn = DBContext.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ThongKeModel> getThongkeSP(String ten, String danhMuc, String bd, String kt) {

        ArrayList<ThongKeModel> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT 
            spct.maSanPhamChiTiet, 
            dm.tenDanhMuc + ' ' + sp.tenSanPham AS tenGiay, 
            spct.giaSanPham, 
            SUM(hdct.soLuong) AS tongSoLuong, 
            SUM(spct.giaSanPham * hdct.soLuong) AS tongTien,
            MAX(hdct.ngayTao) AS ngayTaoGanNhat,
            hd.trangThai
        FROM 
            HoaDonChiTiet hdct
        LEFT JOIN 
            HoaDon hd ON hd.id = hdct.idHoaDon
        LEFT JOIN 
            SanPhamChiTiet spct ON spct.id = hdct.idSanPhamChiTiet
        LEFT JOIN 
            SanPham sp ON sp.id = spct.idSanPham
        LEFT JOIN 
            DanhMuc dm ON dm.id = spct.idDanhMuc
        where (dm.tenDanhMuc + ' ' +  sp.tenSanPham like ? and hd.trangThai = 0)""");
        if (!danhMuc.equals("Tất Cả")) {
            sql.append(" AND dm.tenDanhMuc = N'").append(danhMuc).append("'");
        }
        if(!bd.equals("abc") || !kt.equals("abc")){
            sql.append(" AND hdct.ngayTao between '").append(bd).append("' and DATEADD(day, 1, '").append(kt).append("') ");
        }
        sql.append("""
                   GROUP BY 
                               spct.maSanPhamChiTiet, 
                               dm.tenDanhMuc + ' ' + sp.tenSanPham, 
                               spct.giaSanPham, 
                               hd.trangThai;""");
        try {

            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThongKeModel(rs.getString(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    public ArrayList<Integer> getThongkeSP(String sql) {

        ArrayList<Integer> list = new ArrayList<>();
        try {

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt(1));
                list.add(rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<ThongKeModel> getThongkeTuyChon(String bd, String kt) {

        ArrayList<ThongKeModel> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT COUNT(id) AS slhd, SUM(tongTien) AS doanhthu
        FROM HoaDon AS hd
        WHERE (trangThai = 0)""");
        if(!bd.equals("abc") || !kt.equals("abc")){
            sql.append(" AND hd.ngayTao between '").append(bd).append("' and DATEADD(day, 1, '").append(kt).append("') ");
        }
        
        try {

            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new ThongKeModel(rs.getInt(1), rs.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<String> getThongkeSQL() {

        ArrayList<String> list = new ArrayList<>();
        list.add("""
                    SELECT COUNT(id) slhd , sum(tongTien) doanhthu
                                        FROM HoaDon
                                        where cast(ngayTao as date) = cast( getdate() as date)
                                        and trangThai = 0
                """);
        list.add("""
                        SELECT COUNT(id) slhd , sum(tongTien) doanhthu
        		FROM HoaDon
        		where ngayTao between convert(date, dateadd(day, -7, GETDATE())) 
                        and getdate()
        		and trangThai = 0 """);
        list.add("""
        SELECT COUNT(id) slhd , sum(tongTien) doanhthu
                        FROM HoaDon
                        where MONTH(HoaDon.NgayTao) = Month(getdate())
                        and trangThai = 0""");

        return list;
    }

    public ArrayList<ThongKeModel> getThongkeSP(String bd, String kt) {

        ArrayList<ThongKeModel> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT 
                    spct.maSanPhamChiTiet, 
                    dm.tenDanhMuc + ' ' + sp.tenSanPham AS tenGiay, 
                    spct.giaSanPham, 
                    SUM(hdct.soLuong) AS tongSoLuong, 
                    SUM(spct.giaSanPham * hdct.soLuong) AS tongTien,
                    MAX(hdct.ngayTao) AS ngayTaoGanNhat,
                    hd.trangThai
                FROM 
                    HoaDonChiTiet hdct
                LEFT JOIN 
                    HoaDon hd ON hd.id = hdct.idHoaDon
                LEFT JOIN 
                    SanPhamChiTiet spct ON spct.id = hdct.idSanPhamChiTiet
                LEFT JOIN 
                    SanPham sp ON sp.id = spct.idSanPham
                LEFT JOIN 
                    DanhMuc dm ON dm.id = spct.idDanhMuc
        WHERE hd.trangThai = 0""");
        if(!bd.equals("abc") || !kt.equals("abc")){
            sql.append(" AND hdct.ngayTao between '").append(bd).append("' and DATEADD(day, 1, '").append(kt).append("') ");
        }
        sql.append("""
                   GROUP BY 
                               spct.maSanPhamChiTiet, 
                               dm.tenDanhMuc + ' ' + sp.tenSanPham, 
                               spct.giaSanPham, 
                               hd.trangThai;""");
        try {

            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ThongKeModel(rs.getString(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    public void inThongKeSP(String bd, String kt) {
        ArrayList<ThongKeModel> list = getThongkeSP(bd, kt);
        int tongTien = 0;
        try {
            File file = new File("E://HoaDon.xlsx");
            XSSFWorkbook workbook;
            XSSFSheet sheet;
            if (file.exists()) {

                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                fis.close();
                sheet = workbook.getSheetAt(0);
                int numberOfRows = sheet.getLastRowNum();

                // Xóa từng hàng trong sheet
                for (int i = numberOfRows; i >= 0; i--) {
                    XSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        sheet.removeRow(row);
                    }
                }
            } else {

                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Danh Sách");
            }

            XSSFRow row;
            Cell cell;

            CellStyle redTextStyle = workbook.createCellStyle();
            Font redFont = workbook.createFont();
            redFont.setColor(IndexedColors.RED.getIndex());
            redFont.setFontHeightInPoints((short) 14);
            redTextStyle.setFont(redFont);

            row = sheet.createRow(3);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Stt");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã sản phẩm chi tiết");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên giày");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Đơn giá");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Số lượng");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tổng tiền");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Ngày tạo");

            for (int i = 0; i < list.size(); i++) {

                row = sheet.createRow(4 + i);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(i);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getMaSanPham());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getTenSanPham());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getDonGia());

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getSoLuong());

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(String.valueOf(list.get(i).getTongTien()));
                tongTien += list.get(i).getTongTien();

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(list.get(i).getNgayTao());
            }

            row = sheet.createRow(4 + list.size());
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tổng: " + tongTien);
            cell.setCellStyle(redTextStyle);

            for (int i = 0; i < 7; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }

            workbook.close();

            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
