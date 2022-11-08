/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModels.ChucVu;
import DomainModels.CuaHang;
import Service.ImplService.IManagerChucVuService;
import Service.ImplService.IManagerCuaHangService;
import Service.ImplService.IManagerNhanVienService;
import Service.ManagerChucvuService;
import Service.ManagerCuaHangService;
import Service.ManagerNhanVienService;
import Utilities.XDate;
import ViewModels.QLChucVu;
import ViewModels.QlCuaHang;
import ViewModels.QlNhanVien;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class NhanVienFr extends javax.swing.JFrame {

    /**
     * Creates new form NhanVienFr
     */
    private final IManagerNhanVienService iManagerNhanVienService;
    private final IManagerCuaHangService iManagerCuaHangService;
    private final IManagerChucVuService iManagerChucVuService;
    List<QlNhanVien> list;
    DefaultTableModel model;
    DefaultComboBoxModel<String> chucVuBoxModel;
    DefaultComboBoxModel<String> cuaHangBoxModel;

    private int _currentPage;
    private int _totalPages;
    private final int _pageSize;
    private long _totalNhanVien;
    private String dateFormat;

    public NhanVienFr() {
        initComponents();
        this.setLocationRelativeTo(null);
        iManagerChucVuService = new ManagerChucvuService();
        iManagerCuaHangService = new ManagerCuaHangService();
        iManagerNhanVienService = new ManagerNhanVienService();

        list = new ArrayList<>();

        model = (DefaultTableModel) tblNhanVien.getModel();
        chucVuBoxModel = new DefaultComboBoxModel<>();
        cuaHangBoxModel = new DefaultComboBoxModel<>();

        _currentPage = 1;
        _pageSize = 10;
        loadTable();
        setUpCbxChucVu();
        setUpCbxCuaHang();
        setUpTrangThai();
//        List<CuaHang> cuaHangs = iManagerCuaHangService.getAllCuaHang();
//        cbxCuaHang.setModel(new DefaultComboBoxModel(cuaHangs.toArray()));

    }

    private void setUpCbxChucVu() {
        List<QLChucVu> chucVus = iManagerChucVuService.getChucVu();

        for (int i = 0; i < chucVus.size(); i++) {
            chucVuBoxModel.addElement(chucVus.get(i).getTen());
        }
        cbxChucVu.setModel(chucVuBoxModel);
    }

    private void setUpCbxCuaHang() {
        List<QlCuaHang> cuaHangs = iManagerCuaHangService.getAllCuaHang();
        //   cbxCuaHang.setModel(new DefaultComboBoxModel((cuaHangs.toArray())));
        for (int i = 0; i < cuaHangs.size(); i++) {
            cuaHangBoxModel.addElement(cuaHangs.get(i).getTen());
        }
        cbxCuaHang.setModel(cuaHangBoxModel);
    }

    private void setUpTrangThai() {
        DefaultComboBoxModel<String> trangThaiModel = new DefaultComboBoxModel<>();
        trangThaiModel.addElement("Tốt");
        trangThaiModel.addElement("Bình Thường");
        trangThaiModel.addElement("Xấu");

        cbxTrangThai.setModel(trangThaiModel);
    }

    private void loadTable() {
        model.setNumRows(0);
        list = iManagerNhanVienService.getNhanVien(_currentPage - 1, _pageSize);

        for (QlNhanVien x : list) {
            //System.out.println(x.getMa());
            model.addRow(new Object[]{
                x.getMa(),
                x.getHoten(),
                x.getGioiTinh(),
                x.getNgaySinh(),
                x.getDiaChi(),
                x.getSdt(),
                x.getCuaHang().getTen(),
                x.getChucVu().getTen(),
                x.getIdBaoCao(),
                x.getTrangThai()

            });

        }
        _totalNhanVien = iManagerNhanVienService.countAllNhanVien();
        lbnTotal.setText("Total: " + _totalNhanVien);
        _totalPages = (int) (_totalNhanVien / _pageSize) + 1;
        setStatePagination();

    }

    private void setStatePagination() {
        btnPre.setEnabled(_currentPage > 1);
        btnNext.setEnabled(_currentPage < _totalPages);
        lbnPage.setText(_currentPage + "/" + _totalPages);
    }

    private QlNhanVien validateNhanVien() {

        boolean isValid = true;
        String ma = txtMa.getText();
        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
//        for (QlNhanVien qlNhanVien : list) {
//            if (qlNhanVien.getMa().equalsIgnoreCase(ma)) {
//                JOptionPane.showMessageDialog(this, "Mã trùng");
//                isValid = false;
//                return null;
//            }
//        }
        String ten = txtTen.getText();
        if (ten.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        String tenDem = txtTenDem.getText();
        if (tenDem.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đệm trống", "ERROR", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;

        }
        String ho = txtHo.getText();
        if (ho.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        String gioiTinh = null;
        if (rbtnMale.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        String sdt = txtSdt.getText();
        if (sdt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "SĐT trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }

        String date = txtNgaysinh.getText();
        Date date1 = XDate.toDate(date, "dd-MM-yyyy");
        if (date1 == null) {
            return null;
        }
        java.sql.Date dateSql = new java.sql.Date(date1.getTime());
        System.out.println(dateSql);
        System.out.println(date1);

        String diaChi = txtDiachi.getText();
        if (diaChi.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        String matKhau = txtPass.getText();
        if (matKhau.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            isValid = false;
            return null;
        }
        int trangThai = 0;
        if (cbxTrangThai.getSelectedIndex() == 0) {
            trangThai = 0;
        } else if (cbxTrangThai.getSelectedIndex() == 1) {
            trangThai = 1;
        } else {
            trangThai = 2;
        }
        String TenCH = cbxCuaHang.getSelectedItem().toString();
        // System.out.println(maCH);
        QlCuaHang cuaHang = iManagerCuaHangService.getCuaHangByTen(TenCH);
        //CuaHang cuaHang = (CuaHang) this.cbxCuaHang.getSelectedItem();
        String tenCV = cbxChucVu.getSelectedItem().toString();
        QLChucVu chucVu = iManagerChucVuService.getChucVuByName(tenCV);

        ChucVu chucVu1 = new ChucVu(chucVu.getId(), chucVu.getMa(), chucVu.getTen());

        CuaHang cuaHang1 = new CuaHang(cuaHang.getId(), cuaHang.getMa(), cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia());

        if (isValid == true) {
            QlNhanVien nhanVien = new QlNhanVien(null,ma, ten, tenDem, ho, diaChi, sdt, matKhau, cuaHang1, chucVu1, gioiTinh, dateSql, trangThai);
            return nhanVien;
        } else {
            return null;
        }

    }

    private boolean isEmpty(String string) {
        boolean isValid = true;

        if (string.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống", "ERORR", JOptionPane.ERROR_MESSAGE);
            isValid = false;

        }
        return isValid;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNgaysinh = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        txtTenDem = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        rbtnMale = new javax.swing.JRadioButton();
        rbtnFemale = new javax.swing.JRadioButton();
        cbxChucVu = new javax.swing.JComboBox<>();
        txtIdBaocao = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        BtnReset = new javax.swing.JButton();
        cbxTrangThai = new javax.swing.JComboBox<>();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbnPage = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbxCuaHang = new javax.swing.JComboBox<>();
        lbnTotal = new javax.swing.JLabel();
        lbnMatKhau = new javax.swing.JLabel();
        bntTroLai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nhân Viên");

        jLabel2.setText("Mã");

        jLabel3.setText("Tên");

        jLabel4.setText("Tên đệm");

        jLabel5.setText("Họ");

        jLabel6.setText("Giới tính");

        jLabel7.setText("Ngày sinh");

        jLabel8.setText("SĐT ");

        jLabel9.setText("Địa chỉ");

        jLabel10.setText("Mật khẩu ");

        jLabel12.setText("Chức vụ");

        jLabel13.setText("ID Báo Cáo");

        jLabel14.setText("Trạng thái");

        buttonGroup1.add(rbtnMale);
        rbtnMale.setText("Nam");

        buttonGroup1.add(rbtnFemale);
        rbtnFemale.setText("Nữ");

        cbxChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtIdBaocao.setEditable(false);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Họ và tên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Cửa hàng", "Chức vụ", "ID báo cáo", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        btnThem.setText("Thêm");
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

        BtnReset.setText("Làm mới");

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jLabel15.setText("Cửa Hàng");

        lbnTotal.setText("jLabel11");

        lbnMatKhau.setForeground(new java.awt.Color(255, 0, 0));

        bntTroLai.setText("Trở Lại");
        bntTroLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntTroLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 388, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addComponent(txtTenDem)
                                .addComponent(txtHo, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addComponent(txtMa))
                            .addComponent(txtNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua)
                                .addGap(26, 26, 26)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(BtnReset))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbtnMale)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtnFemale)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiachi)
                                    .addComponent(txtPass)
                                    .addComponent(txtIdBaocao)
                                    .addComponent(lbnMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(64, 64, 64)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxCuaHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPre)
                    .addComponent(btnNext)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lbnPage))
                    .addComponent(lbnTotal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(bntTroLai)
                .addGap(206, 206, 206)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bntTroLai)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbnMatKhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13)
                            .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdBaocao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cbxCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNgaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rbtnMale)
                            .addComponent(rbtnFemale))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)
                            .addComponent(BtnReset))
                        .addGap(11, 11, 11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnPre)
                        .addGap(18, 18, 18)
                        .addComponent(lbnPage)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(lbnTotal)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        QlNhanVien nhanVien = validateNhanVien();
        if (nhanVien == null) {
            return;
        }
        for (QlNhanVien qlNhanVien : list) {
            if (qlNhanVien.getMa().equalsIgnoreCase(nhanVien.getMa())) {
                JOptionPane.showMessageDialog(this, "Mã trùng");
                return;
            }
        }

        if (iManagerNhanVienService.createNewNhanVien(nhanVien) != null) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTable();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int index = tblNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa");
            return;
        } else {
            String ma = (String) model.getValueAt(index, 0);
            if (iManagerNhanVienService.deleteNhanVien(ma) != 0) {
                JOptionPane.showMessageDialog(this, "Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
            loadTable();
        }


    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int index = tblNhanVien.getSelectedRow();
        String ma = (String) model.getValueAt(index, 0);
        txtMa.setText(ma);
        String Hoten = (String) model.getValueAt(index, 1);
        String[] words = Hoten.split("\\s", 3);

        txtHo.setText(words[0]);
        txtTenDem.setText(words[1]);
        txtTen.setText(words[2]);
        txtSdt.setText((String) model.getValueAt(index, 5));
        txtDiachi.setText((String) model.getValueAt(index, 4));
        txtNgaysinh.setText(XDate.toString((Date) model.getValueAt(index, 3), "dd-MM-yyyy"));
        int trangThai = (int) model.getValueAt(index, 9);
        cbxTrangThai.setSelectedIndex(trangThai);
        String gioiTinh = (String) model.getValueAt(index, 2);
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rbtnMale.setSelected(true);
        } else {
            rbtnFemale.setSelected(true);
        }
//        String cuaHang = (String) model.getValueAt(index, 6);
//        cbxCuaHang.setSelectedItem(cuaHang);
//        String chucVu = (String) model.getValueAt(index, 7);
//        cbxCuaHang.setSelectedItem(chucVu);


    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        QlNhanVien nhanVien = validateNhanVien();
        if (nhanVien == null) {
            return;
        }
        String matKhau = iManagerNhanVienService.getMatKhau(nhanVien.getMa());

        if (!matKhau.equalsIgnoreCase(nhanVien.getMatKhau())) {
            // JOptionPane.showMessageDialog(this, "Sai mât khẩu ");
            lbnMatKhau.setText("Sai mật khẩu");
            return;
        }

        if (iManagerNhanVienService.updateNhanVien(nhanVien) != null) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        loadTable();
    }//GEN-LAST:event_btnSuaActionPerformed

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

    private void bntTroLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntTroLaiActionPerformed
        // TODO add your handling code here:
        new ChiTietSanPhamFr().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntTroLaiActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienFr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVienFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnReset;
    private javax.swing.JButton bntTroLai;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxChucVu;
    private javax.swing.JComboBox<String> cbxCuaHang;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbnMatKhau;
    private javax.swing.JLabel lbnPage;
    private javax.swing.JLabel lbnTotal;
    private javax.swing.JRadioButton rbtnFemale;
    private javax.swing.JRadioButton rbtnMale;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtIdBaocao;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNgaysinh;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    // End of variables declaration//GEN-END:variables
}
