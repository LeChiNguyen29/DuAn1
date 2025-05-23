/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import Model.Internal.MauSacModel;

import Service.MauSacService;
import View.Dialog.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lcinu
 */
public class MauSacPanel extends javax.swing.JPanel {

    private final MauSacService mauSacService = new MauSacService();
    private DiaLogThongBao thongBao = new DiaLogThongBao();

    /**
     * Creates new form MauSacPanel
     */
    public MauSacPanel() {
        initComponents();

        this.fillTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rdoHD = new javax.swing.JRadioButton();
        rdoDHD = new javax.swing.JRadioButton();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        lblErrMa = new javax.swing.JLabel();
        lblErrTen = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bảng Màu Sắc");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên màu:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Trạng Thái:");

        rdoHD.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoHD);
        rdoHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoHD.setSelected(true);
        rdoHD.setText("Đang bán");

        rdoDHD.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoDHD);
        rdoDHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoDHD.setText("Dừng bán");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKeyReleased(evt);
            }
        });

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKeyReleased(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 153, 153));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 153, 153));
        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(0, 153, 153));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        tblMauSac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Tên Màu", "Trạng Thái", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMauSac.setRowHeight(25);
        tblMauSac.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMauSac);
        if (tblMauSac.getColumnModel().getColumnCount() > 0) {
            tblMauSac.getColumnModel().getColumn(3).setMinWidth(0);
            tblMauSac.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblMauSac.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        lblErrMa.setForeground(new java.awt.Color(255, 0, 0));

        lblErrTen.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnLamMoi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoHD)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoDHD))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTen))
                                    .addComponent(lblErrMa, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblErrTen, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(30, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblErrMa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addComponent(lblErrTen, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdoHD)
                    .addComponent(rdoDHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnLamMoi)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        MauSacModel mauSacModel = this.getForm();
        if (mauSacModel == null) {
            return;
        } else {
            if (mauSacService.checkMaMau(mauSacModel.getMaMauSac())) {
                this.lblErrMa.setText("Mã chất liệu " + mauSacModel.getMaMauSac() + " đã tồn tại");
                return;
            } else {
                if (mauSacService.insertMauSac(mauSacModel)) {
                    this.fillTable();

                    this.clearForm();
                    thongBao.thongBao("Thêm dữ liệu thành công!");

                } else {
                    thongBao.thongBao("Thêm dữ liệu thất bại!");
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = this.tblMauSac.getSelectedRow();
        if (row < 0) {
            thongBao.thongBao("Chọn dữ liệu để sửa!");
        } else {
            int id = Integer.parseInt(String.valueOf(this.tblMauSac.getValueAt(row, 3)));
            MauSacModel mauSacModel = this.getForm();
            if (mauSacModel == null) {
                return;
            } else {

                if (mauSacService.isMaMauUnique(id, mauSacModel.getMaMauSac())) {
                    this.lblErrMa.setText("Mã đã tồn tại");
                    return;
                } else {

                    DiaLogYesNo logYesNo = new DiaLogYesNo(null, "Bạn có muốn cập nhật dữ liệu không?");
                    logYesNo.setVisible(true);

                    if (logYesNo.getResult() == JOptionPane.YES_OPTION) {
                        if (mauSacService.updateMauSac(id, mauSacModel)) {
                            thongBao.thongBao("Cập nhật dữ liệu thành công!");
                            fillTable();
                            clearForm();
                        }else{
                            thongBao.thongBao("Cập nhật dữ liệu thất bại!");
                        }
                    }
                }

            }

        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        int index = this.tblMauSac.getSelectedRow();
        if (index < 0) {
            return;
        } else {
            txtMa.setText(this.tblMauSac.getValueAt(index, 0).toString());
            txtTen.setText(this.tblMauSac.getValueAt(index, 1).toString());
            if (this.tblMauSac.getValueAt(index, 2).toString().equals("Đang bán")) {
                rdoHD.setSelected(true);
            } else {
                rdoDHD.setSelected(true);
            }
        }
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void txtMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKeyReleased
        lblErrMa.setText(null);
    }//GEN-LAST:event_txtMaKeyReleased

    private void txtTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKeyReleased
        lblErrTen.setText(null);
    }//GEN-LAST:event_txtTenKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrMa;
    private javax.swing.JLabel lblErrTen;
    private javax.swing.JRadioButton rdoDHD;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) this.tblMauSac.getModel();
        defaultTableModel.setRowCount(0);
        mauSacService.getAllMauSac().forEach(item -> defaultTableModel.addRow(new Object[]{
            item.getMaMauSac(),
            item.getTenMauSac(),
            fillTrangThai(item.getTrangThai()),
            item.getId()
        }));

    }

    private String fillTrangThai(int trangThai) {
        if (trangThai == 0) {
            return "Đang bán";
        } else {
            return "Dừng bán";
        }
    }

    public void clearForm() {
        txtMa.setText(null);
        txtTen.setText(null);
        rdoHD.setSelected(true);
        lblErrMa.setText(null);
        lblErrTen.setText(null);
    }

    private MauSacModel getForm() {
        String ma = this.txtMa.getText().trim();
        String ten = this.txtTen.getText().trim();

        boolean isValid = true;

        if (ma.isEmpty()) {
            this.lblErrMa.setText("Không để trống");
            isValid = false;
        } else {
            this.lblErrMa.setText(null);
        }

        if (ten.isEmpty()) {
            this.lblErrTen.setText("Không để trống");
            isValid = false;
        } else {
            this.lblErrTen.setText(null);
        }

        int trangThai = this.rdoHD.isSelected() ? 0 : 1;

        if (isValid) {
            return new MauSacModel(ma, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), ten, trangThai);
        }

        return null;
    }

}
