/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModels.DongSp;
import DomainModels.Mau;
import DomainModels.NSX;
import DomainModels.SanPham;
import DomainModels.SanPhamChiTiet;
import Service.ImplService.IManagerDongSpService;
import Service.ImplService.IManagerMauService;
import Service.ImplService.IManagerNSXService;
import Service.ImplService.IManagerSPchiTietService;
import Service.ImplService.IManagerSanPhamService;
import Service.ManagerDongSpService;
import Service.ManagerMauService;
import Service.ManagerNSXservice;
import Service.ManagerSanPhamChiTiet;
import Service.ManagerSanPhamService;
import Utilities.User;
import ViewModels.QLSanPham;
import ViewModels.QlDongSp;
import ViewModels.QlMau;
import ViewModels.QlNSX;
import ViewModels.QlSanPhamChiTiet;
import ViewModels.SPCT;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ChiTietSanPhamFr extends javax.swing.JFrame {

    /**
     * Creates new form ChiTietSanPhamFr
     */
    List<SPCT> list;
    DefaultTableModel model;
    DefaultTableModel modelGioHang;
    IManagerSPchiTietService iManagerSPchiTietService;
    IManagerSanPhamService iManagerSanPhamService;
    IManagerDongSpService iManagerDongSpService;
    IManagerMauService iManagerMauService;
    IManagerNSXService iManagerNSXService;
    List<QLSanPham> listSp = new ArrayList<>();
    List<QlDongSp> listDong = new ArrayList<>();
    List<QlMau> listMau = new ArrayList<>();
    List<QlNSX> listNsx = new ArrayList<>();

    public ChiTietSanPhamFr() {
        initComponents();
        this.setLocationRelativeTo(null);
        model = (DefaultTableModel) tblSpct.getModel();
        iManagerSPchiTietService = new ManagerSanPhamChiTiet();
        iManagerSanPhamService = new ManagerSanPhamService();
        iManagerDongSpService = new ManagerDongSpService();
        iManagerMauService = new ManagerMauService();
        iManagerNSXService = new ManagerNSXservice();
        list = iManagerSPchiTietService.getListSpCT();
        loadTable(list);
        loadCBXSanpham();
        loadCBXDong();
        loadCBXmau();
        loadCBXNsx();
    }

    private void loadTable(List<SPCT> list) {

        model.setNumRows(0);
        for (SPCT x : list) {
            model.addRow(new Object[]{
                x.getTenSP(), x.getTenNSX(), x.getMau(), x.getDong(), x.getNamBh(), x.getMoTa(), x.getSoLuongTon(), x.getGiaNhap(), x.getGiaBan()
            });
        }
    }

    private void loadCBXSanpham() {
        listSp = iManagerSanPhamService.getAll();
        DefaultComboBoxModel spBoxModel = new DefaultComboBoxModel();
        for (QLSanPham x : listSp) {
            spBoxModel.addElement(x);
        }
        cbxSanPham.setModel(spBoxModel);

    }

    private void loadCBXDong() {
        listDong = iManagerDongSpService.getAll();
        DefaultComboBoxModel dongBoxModel = new DefaultComboBoxModel();
        for (QlDongSp x : listDong) {
            dongBoxModel.addElement(x);
        }
        cbxDongSp.setModel(dongBoxModel);

    }

    private void loadCBXmau() {
        listMau = iManagerMauService.getAll();
        DefaultComboBoxModel mauBoxModel = new DefaultComboBoxModel();
        for (QlMau x : listMau) {
            mauBoxModel.addElement(x);
        }
        cbxMauSac.setModel(mauBoxModel);
    }

    private void loadCBXNsx() {
        listNsx = iManagerNSXService.getAll();
        DefaultComboBoxModel nsxBoxModel = new DefaultComboBoxModel();
        for (QlNSX x : listNsx) {
            nsxBoxModel.addElement(x);
        }
        cbxNSX.setModel(nsxBoxModel);
    }

    private SanPhamChiTiet validateSPCT() {
        QLSanPham sp = (QLSanPham) cbxSanPham.getSelectedItem();
        String idSp = sp.getId();
        QlNSX nsx = (QlNSX) cbxNSX.getSelectedItem();
        String idNsx = nsx.getId();
        QlMau mau = (QlMau) cbxMauSac.getSelectedItem();
        String idMau = mau.getId();
        QlDongSp dong = (QlDongSp) cbxDongSp.getSelectedItem();
        String idDong = dong.getId();
        int namBH = 0;

        try {
            namBH = Integer.valueOf(txtNamBh.getText());
            if (txtNamBh.getText().trim().isEmpty()) {
                namBH = 0;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số");
            return null;
        }
        String moTa = taMoTa.getText();
        if (moTa.trim().isEmpty()) {
            moTa = null;
        }
        int soLuong = 0;
        if (txtSLTon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
            return null;
        }
        try {
            soLuong = Integer.valueOf(txtSLTon.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số");
            return null;
        }
        float giaNhap, giaBan = 0;
        if (txtGiaNhap.getText().trim().isEmpty() || txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán và giá nhập không được để trống");
            return null;
        }
        try {
            giaNhap = Float.valueOf(txtGiaNhap.getText());
            giaBan = Float.valueOf(txtGiaBan.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số");
            e.printStackTrace();
            return null;
        }
        SanPhamChiTiet spct = new SanPhamChiTiet(null, idSp, idNsx, idMau, idDong, namBH, moTa, soLuong, giaNhap, giaBan);
        return spct;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSpct = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxSanPham = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxDongSp = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbxNSX = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbxMauSac = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNamBh = new javax.swing.JTextField();
        txtSLTon = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMoTa = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Chi Tiết Sản Phẩm");

        tblSpct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên SP", "NSX", "Màu Sắc", "Dòng SP", "Năm BH", "Mô Tả ", "Số Lượng Tồn", "Giá Nhập", "Giá Bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSpct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSpctMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSpct);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Sản phẩm");

        cbxSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Dòng SP");

        cbxDongSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("NSX");

        cbxNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Màu Sắc");

        cbxMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Năm BH");

        jLabel7.setText("Mô Tả");

        jLabel8.setText("Số lượng tồn");

        jLabel9.setText("Giá Nhập");

        jLabel10.setText("Giá Bán");

        taMoTa.setColumns(20);
        taMoTa.setRows(5);
        jScrollPane1.setViewportView(taMoTa);

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSLTon))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel5))
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxDongSp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxNSX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbxMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtNamBh)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btnSua)
                        .addGap(41, 41, 41)
                        .addComponent(btnXoa)
                        .addGap(47, 47, 47))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbxSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel6))
                    .addComponent(txtNamBh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel7)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSLTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jMenu1.setText("Hệ Thống");

        jMenu3.setText("Đăng Nhập");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenu3);

        jMenu4.setText("Đăng Xuất");
        jMenu1.add(jMenu4);

        jMenu5.setText("Đổi mật khẩu");
        jMenu1.add(jMenu5);

        jMenu6.setText("Exit");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lí nhân viên");

        jMenu7.setText("Quản lí Nhân Viên");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenu7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu7);

        jMenu8.setText("Quản lí cửa hàng");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu8);

        jMenu9.setText("Quản lí chức vụ");
        jMenu9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu9MouseClicked(evt);
            }
        });
        jMenu9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu9);

        jMenuBar1.add(jMenu2);

        jMenu10.setText("Quản lí khách hàng");
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu10);

        jMenu11.setText("Quản lí sản phẩm");

        jMenu12.setText("Quản lí sản phẩm");
        jMenu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu12MouseClicked(evt);
            }
        });
        jMenu11.add(jMenu12);

        jMenu13.setText("Quản lí màu sắc");
        jMenu13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu13MouseClicked(evt);
            }
        });
        jMenu11.add(jMenu13);

        jMenu14.setText("Quản lí dòng sản phẩm");
        jMenu14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu14MouseClicked(evt);
            }
        });
        jMenu11.add(jMenu14);

        jMenu15.setText("Quản lí NSX");
        jMenu15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu15MouseClicked(evt);
            }
        });
        jMenu11.add(jMenu15);

        jMenuBar1.add(jMenu11);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(464, 464, 464)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
        new LoginFr().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        SanPhamChiTiet spct = validateSPCT();
        if (spct == null) {
            return;
        }
        if (iManagerSPchiTietService.createSPCT(spct) != 0) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
        for (SPCT spct1 : list) {
            if (spct1.getId().equalsIgnoreCase(spct.getIdSP())) {
                spct1.setSoLuongTon(spct.getSoLuong() + spct1.getSoLuongTon());
            }
            loadTable(iManagerSPchiTietService.getListSpCT());
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void jMenu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu7ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu7ActionPerformed

    private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu8ActionPerformed

    private void jMenu9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu9ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu9ActionPerformed

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new NhanVienFr().setVisible(true);
    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new CuaHangFr().setVisible(true);
    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenu9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu9MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ChucVuFr().setVisible(true);
    }//GEN-LAST:event_jMenu9MouseClicked

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new KhachHangFr().setVisible(true);
    }//GEN-LAST:event_jMenu10MouseClicked

    private void jMenu12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu12MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new SanPhamFr().setVisible(true);
    }//GEN-LAST:event_jMenu12MouseClicked

    private void jMenu13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu13MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new MauSacFr().setVisible(true);
    }//GEN-LAST:event_jMenu13MouseClicked

    private void jMenu14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu14MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new DonSpFr().setVisible(true);
    }//GEN-LAST:event_jMenu14MouseClicked

    private void jMenu15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu15MouseClicked
        // TODO add your handling code here:  
        this.dispose();
        new NSXFr().setVisible(true);
    }//GEN-LAST:event_jMenu15MouseClicked

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        SanPhamChiTiet sanPhamChiTiet = validateSPCT();
        if (sanPhamChiTiet == null) {
            return;
        }
        if (iManagerSPchiTietService.updateSPCT(sanPhamChiTiet) != 0) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
        
        loadTable(iManagerSPchiTietService.getListSpCT());


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
//        QLSanPham qlsp = (QLSanPham) cbxSanPham.getSelectedItem();
//        String idSp = qlsp.getId();
//        if(iManagerSPchiTietService.deleteSPCT(idSp)!= 0){
//            JOptionPane.showMessageDialog(this, "Thành công");
//        }else{
//            JOptionPane.showMessageDialog(this, "Thất bại");
//        }
//        loadTable(iManagerSPchiTietService.getListSpCT());
        int row = tblSpct.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần xóa");
            return;
        }
        String id = list.get(row).getId();
        if(iManagerSPchiTietService.deleteSPCT(id)!=0){
            JOptionPane.showMessageDialog(this, "Thành công");
        }else{
            JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong hóa đơn chi tiết không thể xóa");
        }
        System.out.println(list.get(row));
        loadTable(iManagerSPchiTietService.getListSpCT());

    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblSpctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSpctMouseClicked
        // TODO add your handling code here:
        int row = tblSpct.getSelectedRow();
        for (QLSanPham sp : listSp) {
            if (sp.getTen().equalsIgnoreCase((String) model.getValueAt(row, 0))) {
                cbxSanPham.setSelectedItem(sp);
            }
        }
        for (QlDongSp dsp : listDong) {
            if (dsp.getTen().equalsIgnoreCase((String) model.getValueAt(row, 3))) {
                cbxDongSp.setSelectedItem(dsp);
            }
        }
        for (QlNSX nSX : listNsx) {
            if (nSX.getTen().equalsIgnoreCase((String) model.getValueAt(row, 1))) {
                cbxNSX.setSelectedItem(nSX);
            }
        }
        for (QlMau mau : listMau) {
            if (mau.getTen().equalsIgnoreCase((String) model.getValueAt(row, 2))) {
                cbxMauSac.setSelectedItem(mau);
            }
        }
        txtNamBh.setText(model.getValueAt(row, 4).toString());
        taMoTa.setText(model.getValueAt(row, 5).toString());
        txtSLTon.setText(model.getValueAt(row, 6).toString());
        txtGiaNhap.setText(model.getValueAt(row, 7).toString());
        txtGiaBan.setText(model.getValueAt(row, 8).toString());

    }//GEN-LAST:event_tblSpctMouseClicked

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
            java.util.logging.Logger.getLogger(ChiTietSanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPhamFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietSanPhamFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxDongSp;
    private javax.swing.JComboBox<String> cbxMauSac;
    private javax.swing.JComboBox<String> cbxNSX;
    private javax.swing.JComboBox<String> cbxSanPham;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea taMoTa;
    private javax.swing.JTable tblSpct;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtNamBh;
    private javax.swing.JTextField txtSLTon;
    // End of variables declaration//GEN-END:variables
}
