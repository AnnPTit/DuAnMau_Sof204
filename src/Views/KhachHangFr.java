/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Service.ImplService.IManagerKhachHangService;
import Service.ManagerKhachHangService;
import Utilities.XDate;
import ViewModels.QlKhachHang;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class KhachHangFr extends javax.swing.JFrame {

    /**
     * Creates new form KhachHangFr
     */
    List<QlKhachHang> list;
    DefaultTableModel model;
    DefaultComboBoxModel<String> cbxTpBoxModel;
    DefaultComboBoxModel<String> cbxQgBoxModel;
    IManagerKhachHangService iManagerKhachHangService;
    private int _currentPage;
    private int _totalPages;
    private final int _pageSize;
    private long _totalProducts;

    public KhachHangFr() {
        initComponents();
        this.setLocationRelativeTo(null);
        list = new ArrayList<>();
        model = (DefaultTableModel) tblKhachHang.getModel();
        iManagerKhachHangService = new ManagerKhachHangService();
        _currentPage = 1;
        _pageSize = 10;
        loadTable();
//        setUpCbxThanhPho();
//        setUpCbxQuocGia();

    }

    private void loadTable() {
        list = iManagerKhachHangService.getAll();
        model.setNumRows(0);
        for (QlKhachHang x : list) {
            model.addRow(new Object[]{
                x.getMa(),
                x.getHoten(),
                x.getNgaySinh(),
                x.getSdt(),
                x.getDiaChi(),
                x.getThanhPho(),
                x.getQuocGia()
            });
        }
        _totalProducts = iManagerKhachHangService.countAll();
        lbTotalKh.setText("Total: " + _totalProducts);
        _totalPages = (int) (_totalProducts / _pageSize) + 1;
        setStatePagination();
        setUpCbxQuocGia();
        setUpCbxThanhPho();
    }

    private void setUpCbxThanhPho() {
        cbxTpBoxModel = new DefaultComboBoxModel<>();
        List<String> listTp = iManagerKhachHangService.getThanhPho();
        for (int i = 0; i < listTp.size(); i++) {
            cbxTpBoxModel.addElement(listTp.get(i));
        }
        cbxThanhPho.setModel(cbxTpBoxModel);
    }

    private void setUpCbxQuocGia() {
        cbxQgBoxModel = new DefaultComboBoxModel<>();
        List<String> listQg = iManagerKhachHangService.getQuocGia();
        for (int i = 0; i < listQg.size(); i++) {
            cbxQgBoxModel.addElement(listQg.get(i));
        }
        cbxQuocGia.setModel(cbxQgBoxModel);
    }

    private void setStatePagination() {
        btnPre.setEnabled(_currentPage > 1);
        btnNext.setEnabled(_currentPage < _totalPages);
        lbnPage.setText(_currentPage + "/" + _totalPages);
    }

    private QlKhachHang validateKhachHang() {
        boolean isValid = true;
        String ma = txtMa.getText();
        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Trống", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }

        String ten = txtTen.getText();
        if (ten.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Trống", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        String tenDem = txtTenDem.getText();
        if (tenDem.trim().isEmpty()) {
            tenDem = null;
        }
        String ho = txtHo.getText();
        if (ho.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ Trống", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        String ngaySinh = txtNgaySinh.getText();
        java.util.Date date1 = XDate.toDate(ngaySinh, "dd-MM-yyyy");
        if (date1 == null) {
            return null;
        }
        java.sql.Date dateSql = new java.sql.Date(date1.getTime());
        System.out.println(dateSql);
        System.out.println(date1);

        String sdt = txtSdt.getText();
        if (sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "SĐT Trống", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        String diaChi = txtDiaChi.getText();
        if (diaChi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ Trống", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        String thanhPho = txtThanhPho.getText();
        if (thanhPho.trim().isEmpty()) {
            thanhPho = cbxThanhPho.getSelectedItem().toString();
        }
        String quocGia = txtQuoGia.getText();
        if (quocGia.trim().isEmpty()) {
            quocGia = cbxQuocGia.getSelectedItem().toString();
        }
        String matKhau = txtPass.getText();
        if (matKhau.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu Trống", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        if (isValid == true) {
            QlKhachHang khachHang = new QlKhachHang(null, ma, ten, tenDem, ho, sdt, diaChi, thanhPho, quocGia, matKhau, date1);
            return khachHang;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtTenDem = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        cbxThanhPho = new javax.swing.JComboBox<>();
        txtQuoGia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbnPage = new javax.swing.JLabel();
        cbxQuocGia = new javax.swing.JComboBox<>();
        txtThanhPho = new javax.swing.JTextField();
        lbTotalKh = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbnMatKhau = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Khách Hàng");

        jLabel2.setText("Mã");

        jLabel3.setText("Tên");

        jLabel4.setText("Tên Đệm");

        jLabel5.setText("SĐT");

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ Và Tên", "Ngày Sinh", "SĐT", "Địa Chỉ", "Thành Phố", "Quốc Gia"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jLabel6.setText("Địa chỉ");

        jLabel7.setText("Thành Phố");

        jLabel8.setText("Quốc gia");

        jLabel9.setText("Mật khẩu ");

        jLabel11.setText("Họ");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        cbxThanhPho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtQuoGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuoGiaActionPerformed(evt);
            }
        });

        jLabel10.setText("Ngày Sinh");

        btnPre.setText("<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lbnPage.setText("0/0");

        cbxQuocGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTotalKh.setText("jLabel12");

        jLabel12.setText("Quốc gia khác");

        jLabel13.setText("Thành Phố Khác");

        lbnMatKhau.setForeground(new java.awt.Color(255, 0, 0));

        jButton1.setText("Trở Về");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPre)
                            .addComponent(btnNext)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbnPage))
                            .addComponent(lbTotalKh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jButton1)
                                .addGap(309, 309, 309)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtThanhPho))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(btnThem))
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxThanhPho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 11, Short.MAX_VALUE)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnReset))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbnMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                            .addComponent(txtQuoGia, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                            .addComponent(txtPass)
                                            .addComponent(cbxQuocGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuoGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbnMatKhau))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)
                            .addComponent(btnThem)
                            .addComponent(btnReset)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnPre)
                        .addGap(13, 13, 13)
                        .addComponent(lbnPage)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext)
                        .addGap(51, 51, 51)
                        .addComponent(lbTotalKh)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        if (_currentPage > 1) {
            _currentPage--;
        }
        loadTable();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (_currentPage < _totalPages) {
            _currentPage++;
        }
        loadTable();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        QlKhachHang khachHang = validateKhachHang();
        if (khachHang == null) {
            lbnMatKhau.setText("Vui lòng nhập đúng mật khẩu để sửa");
            return;
        }
        if (list.size() != 0) {
            for (QlKhachHang x : list) {
                if (x.getMa().equalsIgnoreCase(khachHang.getMa())) {
                    JOptionPane.showMessageDialog(this, "Mã Trùng", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        if (iManagerKhachHangService.create(khachHang) != null) {
            JOptionPane.showMessageDialog(this, "Thành Công !");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại !");
        }
        loadTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        QlKhachHang khachHang = validateKhachHang();

        if (khachHang == null) {
            return;
        }
        String matKhau = iManagerKhachHangService.getMatKhau(khachHang.getMa());

        if (!matKhau.equalsIgnoreCase(khachHang.getMatKhau())) {
            // JOptionPane.showMessageDialog(this, "Sai mât khẩu ");
            lbnMatKhau.setText("Sai mật khẩu");
            return;
        }

        if (iManagerKhachHangService.update(khachHang) != null) {
            JOptionPane.showMessageDialog(this, "Thành Công !");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại !");
        }
        loadTable();

    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        int row = tblKhachHang.getSelectedRow();
        if (row != -1) {
            txtMa.setText((String) model.getValueAt(row, 0));
            String Hoten = (String) model.getValueAt(row, 1);
            String[] words = Hoten.split("\\s", 3);

            txtHo.setText(words[0]);
            txtTenDem.setText(words[1]);
            txtTen.setText(words[2]);

            txtNgaySinh.setText(XDate.toString((java.util.Date) model.getValueAt(row, 2), "dd-MM-yyyy"));
            txtSdt.setText((String) model.getValueAt(row, 3));
            txtDiaChi.setText((String) model.getValueAt(row, 4));
            cbxThanhPho.setSelectedItem(model.getValueAt(row, 5));
            cbxQuocGia.setSelectedItem(model.getValueAt(row, 6));
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void txtQuoGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuoGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuoGiaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblKhachHang.getSelectedRow();
        if (row != -1) {
            if (iManagerKhachHangService.delete((String) model.getValueAt(row, 0)) != 0) {
                JOptionPane.showMessageDialog(this, "Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Thất Bại");
            }
        }
        loadTable();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMa.setText("");
        txtDiaChi.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtTenDem.setText("");
        txtNgaySinh.setText("");
        txtPass.setText("");
        txtQuoGia.setText("");
        txtThanhPho.setText("");
        txtSdt.setText("");
        cbxQuocGia.setSelectedIndex(0);
        cbxThanhPho.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ChiTietSanPhamFr().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(KhachHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxQuocGia;
    private javax.swing.JComboBox<String> cbxThanhPho;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotalKh;
    private javax.swing.JLabel lbnMatKhau;
    private javax.swing.JLabel lbnPage;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtQuoGia;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    private javax.swing.JTextField txtThanhPho;
    // End of variables declaration//GEN-END:variables
}
