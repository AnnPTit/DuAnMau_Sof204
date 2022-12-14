/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModels.GioHang;
import Service.ImplService.IManagerGioHangService;
import Service.ImplService.IManagerKhachHangService;
import Service.ImplService.IManagerNhanVienService;
import Service.ManagerGioHangService;
import Service.ManagerKhachHangService;
import Service.ManagerNhanVienService;
import ViewModels.QlGioHang;
import ViewModels.QlKhachHang;
import ViewModels.QlNhanVien;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class GioHangFr extends javax.swing.JFrame {

    /**
     * Creates new form GioHangFr
     */
    List<QlGioHang> list = new ArrayList<>();
    List<QlKhachHang> listKh = new ArrayList<>();
    List<QlNhanVien> listNv = new ArrayList<>();
    IManagerGioHangService iManagerGioHangService = new ManagerGioHangService();
    IManagerKhachHangService iManagerKhachHangService = new ManagerKhachHangService();
    IManagerNhanVienService iManagerNhanVienService = new ManagerNhanVienService();
    DefaultTableModel model;

    public GioHangFr() {
        initComponents();
        model = (DefaultTableModel) tblGioHang.getModel();
        list = iManagerGioHangService.getListGioHang();
        loadTable(list);
        loadCbxKhachHang();
        loadCbxNhanVien();
    }

    private void loadTable(List<QlGioHang> list) {

        model.setNumRows(0);
        for (QlGioHang qlGioHang : list) {
            model.addRow(new Object[]{
                qlGioHang.getTenKH(), qlGioHang.getTenNV(), qlGioHang.getMa(), qlGioHang.getTenNguoiNhan(), qlGioHang.getTinhTrang()
            });
        }

    }

    private void loadCbxKhachHang() {
        listKh = iManagerKhachHangService.getAll();
        DefaultComboBoxModel khBoxModel = new DefaultComboBoxModel();
        for (QlKhachHang qlKhachHang : listKh) {
            khBoxModel.addElement(qlKhachHang);
        }
        cbxKh.setModel(khBoxModel);
    }

    private void loadCbxNhanVien() {
        listNv = iManagerNhanVienService.getNhanVien(0, 1000);
        DefaultComboBoxModel nvBoxModel = new DefaultComboBoxModel();
        for (QlNhanVien qlNhanVien : listNv) {
            nvBoxModel.addElement(qlNhanVien);
        }
        cbxNv.setModel(nvBoxModel);
    }

    private GioHang validateGH() {
        boolean isValid = true;
        list = iManagerGioHangService.getListGioHang();
        String ma = txtMa.getText();
        if (ma.trim().isEmpty()) {

            ma = "GH" + list.size();
        }
        for (QlGioHang qlGioHang : list) {
            if (ma.equalsIgnoreCase(qlGioHang.getMa())) {
                lbnMa.setText("M??   " + ma + "   ???? t???n t???i");
                lbnMa.setForeground(Color.red);
                isValid = false;
            }
        }
        QlKhachHang kh = (QlKhachHang) cbxKh.getSelectedItem();
        String idKh = kh.getId();
        QlNhanVien nv = (QlNhanVien) cbxNv.getSelectedItem();
        String idNv = nv.getId();

        String tenNguoiNhan = txtTenNguoiNhan.getText();
        if (tenNguoiNhan.trim().isEmpty()) {
            tenNguoiNhan = kh.getTen();
        }
        String diaChi = kh.getDiaChi();
        String sdt = kh.getSdt();
        if (isValid == true) {
            GioHang gioHang = new GioHang(null, idKh, idNv, ma, null, null, tenNguoiNhan, diaChi, sdt, 0);
            return gioHang;
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenNguoiNhan = new javax.swing.JTextField();
        cbxKh = new javax.swing.JComboBox<>();
        cbxNv = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lbnMa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Gi??? H??ng");

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "T??n Kh??ch H??ng", "T??n Nh??n Vi??n", "Ma", "T??n Ng?????i Nh???n", "T??nh Tr???ng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        jLabel2.setText("M??");

        jLabel3.setText("T??n Ng?????i Nh???n ");

        cbxKh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxNv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Kh??ch H??ng");

        jLabel5.setText("Nh??n Vi??n");

        btnThem.setText("Th??m ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jButton2.setText("S???a");

        jButton3.setText("X??a");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(242, 242, 242)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbnMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))))))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxKh, 0, 139, Short.MAX_VALUE)
                            .addComponent(cbxNv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnThem)
                        .addGap(31, 31, 31)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbxKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbnMa))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        GioHang gioHang = validateGH();
        if (gioHang == null) {
            return;
        }
        if (iManagerGioHangService.createGioHang(gioHang) != null) {
            JOptionPane.showMessageDialog(this, "Th??nh c??ng");
            loadTable(iManagerGioHangService.getListGioHang());
        } else {
            JOptionPane.showMessageDialog(this, "Th???t b???i");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
        int row = tblGioHang.getSelectedRow();
        txtMa.setText((String) model.getValueAt(row, 2));
        txtTenNguoiNhan.setText((String) model.getValueAt(row, 3));
        lbnMa.setText("");
    }//GEN-LAST:event_tblGioHangMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GioHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GioHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GioHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GioHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GioHangFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbxKh;
    private javax.swing.JComboBox<String> cbxNv;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbnMa;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTenNguoiNhan;
    // End of variables declaration//GEN-END:variables
}
