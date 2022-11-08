/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModels.GioHang;
import DomainModels.GioHangChiTiet;
import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Service.ImplService.IManagerGioHangService;
import ViewModels.QlHoaDon;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Service.ImplService.IManagerHoaDonService;
import Service.ImplService.IManagerKhachHangService;
import Service.ImplService.IManagerNhanVienService;
import Service.ImplService.IManagerSPchiTietService;
import Service.ImplService.IManagerSanPhamService;
import Service.ManagerGioHangService;
import Service.ManagerHoaDonService;
import Service.ManagerKhachHangService;
import Service.ManagerNhanVienService;
import Service.ManagerSanPhamChiTiet;
import Utilities.XDate;
import ViewModels.QlGioHang;
import ViewModels.QlKhachHang;
import ViewModels.QlNhanVien;
import ViewModels.QlSanPhamChiTiet;
import ViewModels.SPCT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ChucNangBanHangFr extends javax.swing.JFrame implements KeyListener {

    /**
     * Creates new form ChucNangBanHangFr
     */
    IManagerHoaDonService iManagerHoaDonService;
    IManagerSPchiTietService iManagerSPchiTietService;
    IManagerKhachHangService iManagerKhachHangService;
    IManagerNhanVienService iManagerNhanVienService;
    IManagerGioHangService iManagerGioHangService;
    List<QlHoaDon> list = new ArrayList<>();
    List<ViewModels.HoaDonChiTiet> listHdct = new ArrayList<>();
    List<QlSanPhamChiTiet> listSp = new ArrayList<>();
    List<QlKhachHang> listKh = new ArrayList<>();
    List<QlNhanVien> listNV = new ArrayList<>();

    public ChucNangBanHangFr() {
        initComponents();
        this.setLocationRelativeTo(null);
        iManagerHoaDonService = new ManagerHoaDonService();
        iManagerSPchiTietService = new ManagerSanPhamChiTiet();
        iManagerKhachHangService = new ManagerKhachHangService();
        iManagerNhanVienService = new ManagerNhanVienService();
        iManagerGioHangService = new ManagerGioHangService();
        list = iManagerHoaDonService.getListHoaDon();
        listSp = iManagerSPchiTietService.getListSpChiTiet();
        loadTableHoaDon(list);
        loadTableSanPham(listSp);
        loadCBXKhachHang();
        loadBCXNhanVien();
        btnThanhToan.setEnabled(false);

        rbtnAll.setSelected(true);
    }

    private void loadTableHoaDon(List<QlHoaDon> list) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();

        model.setNumRows(0);
        for (int i = 0; i < list.size(); i++) {
            // System.out.println(list.get(i).getId());
            String tinhTrang = "";
            if (list.get(i).getTinhTrang() == 0) {
                tinhTrang = "Chờ Thanh Toán";
            } else if (list.get(i).getTinhTrang() == 1) {
                tinhTrang = "Hủy";
            } else {
                tinhTrang = "Đã Thanh Toán";
            }
            model.addRow(new Object[]{
                i + 1, list.get(i).getMaHD(), list.get(i).getNgayTao(), list.get(i).getTenNv(), tinhTrang});
        }
    }

    private void loadTableSanPham(List<QlSanPhamChiTiet> listSp) {
        DefaultTableModel sanPhamModel = (DefaultTableModel) tblSanPham.getModel();
        sanPhamModel.setNumRows(0);
        if (listSp.size() == 0) {
            return;
        }
        for (int i = 0; i < listSp.size(); i++) {
            sanPhamModel.addRow(new Object[]{
                i + 1, listSp.get(i).getMaSP(), listSp.get(i).getTenSP(), listSp.get(i).getNamBH(),
                listSp.get(i).getMoTa(), listSp.get(i).getSoLuong(), listSp.get(i).getGiaBan()
            });
        }
    }

    private void loadCBXKhachHang() {
         listKh = iManagerKhachHangService.getAll();
        DefaultComboBoxModel khBoxModel = new DefaultComboBoxModel();
        for (QlKhachHang x : listKh) {
            khBoxModel.addElement(x);
        }
        cbxKhachHang.setModel(khBoxModel);
    }

    private void loadBCXNhanVien() {
        listNV = iManagerNhanVienService.getNhanVien(0, 100);
        DefaultComboBoxModel NvBoxModel = new DefaultComboBoxModel();
        for (QlNhanVien x : listNV) {
            NvBoxModel.addElement(x);
        }
        cbxNhanVien.setModel(NvBoxModel);
    }

    private void loadTableGioHang(List<ViewModels.HoaDonChiTiet> list) {
        DefaultTableModel gioHangModel = (DefaultTableModel) tblGioHang.getModel();
        gioHangModel.setNumRows(0);
        int i = 1;
        for (ViewModels.HoaDonChiTiet x : list) {
            gioHangModel.addRow(new Object[]{
                i++, x.getMaSP(), x.getSoLuong(), x.getDonGia(), x.getDonGia() * x.getSoLuong()
            });
        }
    }

    private HoaDon validateHoaDon() {
        boolean isValid = true;
        String maHD = txtMaHD.getText();
        if (maHD.trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mã trống", "ERROR", JOptionPane.ERROR_MESSAGE);
//            isValid = false;
//            return null;
            list = iManagerHoaDonService.getListHoaDon();
            int so = list.size() + 1;
            maHD = "HD" + so;

        }
        QlKhachHang qlKhachHang = (QlKhachHang) cbxKhachHang.getSelectedItem();
        String idKH = qlKhachHang.getId();
        QlNhanVien qlNhanVien = (QlNhanVien) cbxNhanVien.getSelectedItem();
        String idNV = qlNhanVien.getId();
        String tenNguoiNhan = txtTenNguoiNhan.getText();
        if (tenNguoiNhan.trim().isEmpty()) {
            tenNguoiNhan = qlKhachHang.getHoten();
        }
        String diaChi = txtDiaChi.getText();
        if (diaChi.trim().isEmpty()) {
            diaChi = qlKhachHang.getDiaChi();
        }
        String sdt = txtSDT.getText();
        if (sdt.trim().isEmpty()) {
            sdt = qlKhachHang.getSdt();
        }
//        String dateShip = txtNgaySHIP.getText();
//        if (dateShip.trim().isEmpty()) {
//            dateShip = null;
////            JOptionPane.showMessageDialog(this, "Ngày ship trống", "ERROR", JOptionPane.ERROR_MESSAGE);
////            return null;
//        }

//        Date date1 = XDate.toDate(dateShip, "dd-MM-yyyy");
//        if (date1 == null) {
//            return null;
//        }
//        java.sql.Date dateSqlShip = new java.sql.Date(date1.getTime());
//
//        String dateNhan = txtNgayNhan.getText();
//        if (dateNhan.trim().isEmpty()) {
//
//        }
//        Date dateNhanJv = XDate.toDate(dateNhan, "dd-MM-yyyy");
//        if (dateNhanJv == null) {
//            return null;
//        }
//        java.sql.Date dateNhanSql = new java.sql.Date(dateNhanJv.getTime());
        java.sql.Date ngayTao = null;
        java.sql.Date ngayThanhToan = null;
        int tinhTrang = 0;
        if (isValid == true) {
            HoaDon hoaDon = new HoaDon("", idKH, idNV, maHD, ngayTao, ngayThanhToan, null, null, tinhTrang, tenNguoiNhan, diaChi, sdt);
            return hoaDon;
        }
        return null;

    }

    private ViewModels.HoaDonChiTiet validateGioHang() {

        ViewModels.HoaDonChiTiet hoaDonChiTiet = new ViewModels.HoaDonChiTiet();
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm  ");
            return null;
        }
        hoaDonChiTiet.setMaSP((String) tblSanPham.getValueAt(row, 1));
        int soLuong = 0;
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng trống");
            return null;
        }
        try {
            soLuong = Integer.valueOf(txtSoLuong.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số");
            return null;
        }
        hoaDonChiTiet.setSoLuong(soLuong);
        float donGia = 0;
        donGia = (float) tblSanPham.getValueAt(row, 6);
        hoaDonChiTiet.setDonGia(donGia);
        List<SPCT> newlList = iManagerSPchiTietService.getListSpCT();
        hoaDonChiTiet.setIdSPCT(newlList.get(row).getId());
        hoaDonChiTiet.setThanhTien(donGia * soLuong);
        hoaDonChiTiet.setIdHD(null);
        return hoaDonChiTiet;

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
        rbtnChuaTT = new javax.swing.JRadioButton();
        rbtnAll = new javax.swing.JRadioButton();
        rbtnHuy = new javax.swing.JRadioButton();
        rbtnDaTT = new javax.swing.JRadioButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienThua = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbxKhachHang = new javax.swing.JComboBox<>();
        cbxNhanVien = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTenNguoiNhan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnHuy = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        bntThemVaoGioHang = new javax.swing.JButton();
        txtSoLuong = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(rbtnChuaTT);
        rbtnChuaTT.setText("Chờ Thanh Toán");
        rbtnChuaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnChuaTTActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnAll);
        rbtnAll.setText("Tất Cả");
        rbtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnAllActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnHuy);
        rbtnHuy.setText("Hủy");
        rbtnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnHuyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtnDaTT);
        rbtnDaTT.setText("Đã Thanh Toán");
        rbtnDaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnDaTTActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MaHD", "Ngày Tạo", "Tên NV", "Tình Trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Mã HD");

        jLabel3.setText("Ngày Tạo");

        jLabel4.setText("Tên NV");

        jLabel5.setText("Tổng Tiền ");

        jLabel6.setText("Tiền Khách Đưa");

        jLabel7.setText("Tiền Thừa");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        txtTienThua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTienThuaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTienThuaMousePressed(evt);
            }
        });

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel8.setText("Tên Khách Hàng");

        cbxKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Tên Người Nhận");

        jLabel12.setText("Địa chỉ");

        jLabel13.setText("SĐT");

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(btnThanhToan)
                        .addGap(26, 26, 26)
                        .addComponent(btnHuy))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                            .addComponent(cbxKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenNguoiNhan)
                            .addComponent(txtDiaChi)
                            .addComponent(txtSDT))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(cbxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTenNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(130, 130, 130)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnHuy))
                .addGap(64, 64, 64))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MaSP", "Số Lượng", "Đơn Giá", "Thành Tiền"
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
        jScrollPane2.setViewportView(tblGioHang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel3KeyTyped(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Năm BH", "Mô Tả", "SL SP", "Giá Bán"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        bntThemVaoGioHang.setText("Thêm Vào Giỏ Hàng");
        bntThemVaoGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntThemVaoGioHangActionPerformed(evt);
            }
        });

        jLabel14.setText("Số lượng");

        jLabel15.setText("Tìm Kiếm : ");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(bntThemVaoGioHang)
                .addGap(55, 55, 55))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntThemVaoGioHang)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setText("Hóa Đơn");

        jButton1.setText("<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa Hóa Đơn");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(rbtnChuaTT)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(rbtnAll)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(rbtnHuy)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(rbtnDaTT)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtnChuaTT)
                            .addComponent(rbtnAll)
                            .addComponent(rbtnHuy)
                            .addComponent(rbtnDaTT))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTaoHoaDon)
                                .addGap(27, 27, 27)
                                .addComponent(btnXoa)
                                .addGap(29, 29, 29)
                                .addComponent(btnSua))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntThemVaoGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntThemVaoGioHangActionPerformed

        ViewModels.HoaDonChiTiet hdct = validateGioHang();
        if (hdct == null) {
            return;
        }
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm");
            return;
        }
        int soLuong = (int) tblSanPham.getValueAt(row, 5);
        int soLuong2 = hdct.getSoLuong();
        if (soLuong2 > soLuong || soLuong2 < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng > 0 && < số lượng tồn");
            return;
        }

        float tong = 0;
        float tong2 = 0;
        for (ViewModels.HoaDonChiTiet x : listHdct) {

            if (x.getMaSP().equalsIgnoreCase(hdct.getMaSP())) {
                if (x.getSoLuong() >= (int) tblSanPham.getValueAt(row, 5)) {
                    x.setSoLuong((int) tblSanPham.getValueAt(row, 5));
                    JOptionPane.showMessageDialog(this, "Vượt quá số lượng sản phẩm");
                    return;
                }
                x.setSoLuong(x.getSoLuong() + hdct.getSoLuong());
                x.setThanhTien((float) (x.getSoLuong() * x.getDonGia()));
                loadTableGioHang(listHdct);

                for (ViewModels.HoaDonChiTiet hd : listHdct) {
                    tong = (float) (tong + (hd.getDonGia() * hd.getSoLuong()));
                }
                txtTongTien.setText(String.valueOf(tong + tong2));
                loadTableGioHang(listHdct);
                return;
            }
        }
        listHdct.add(hdct);
        loadTableGioHang(listHdct);

        for (ViewModels.HoaDonChiTiet hd : listHdct) {
            tong2 = (float) (tong2 + (hd.getDonGia() * hd.getSoLuong()));
        }
        txtTongTien.setText(String.valueOf(tong + tong2));


    }//GEN-LAST:event_bntThemVaoGioHangActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        HoaDon hoaDon = validateHoaDon();
        if (hoaDon == null) {
            return;
        }
        for (QlHoaDon x : list) {
            if (x.getMaHD().equalsIgnoreCase(hoaDon.getMa())) {
                JOptionPane.showMessageDialog(this, "Mã hóa đơn đã tồn tại");
                return;
            }
        }
        if (iManagerHoaDonService.createHoaDon(hoaDon) != null) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
        list = iManagerHoaDonService.getListHoaDon();
        loadTableHoaDon(list);


    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        System.out.println(list.get(row).getId());
        txtMaHD.setText((String) tblHoaDon.getValueAt(row, 1));
        
        for (QlNhanVien qlNhanVien : listNV) {
            if(qlNhanVien.getTen().equalsIgnoreCase((String) tblHoaDon.getValueAt(row, 3))){
              cbxNhanVien.setSelectedItem(qlNhanVien);
            }
        }

        txtNgayTao.setText(XDate.toString((Date) tblHoaDon.getValueAt(row, 2), "dd-MM-yyyy"));
        HoaDon hoaDon = iManagerHoaDonService.getHoaDonByMa((String) tblHoaDon.getValueAt(row, 1));
        String idKH = hoaDon.getKhachHang();

        txtTenNguoiNhan.setText(hoaDon.getTenNguoiNhan());
        txtDiaChi.setText(hoaDon.getDiaChi());
        txtSDT.setText(hoaDon.getSdt());
        List<ViewModels.HoaDonChiTiet> listhChiTiets = iManagerHoaDonService.getListHdct((String) tblHoaDon.getValueAt(row, 1));
        double tongTien = 0;
        for (ViewModels.HoaDonChiTiet x : listhChiTiets) {
            tongTien = tongTien + (x.getDonGia() * x.getSoLuong());
        }
        txtTongTien.setText(String.valueOf(tongTien));
        loadTableGioHang(listhChiTiets);
        if (list.get(row).getTinhTrang() == 0) {
            btnXoa.setEnabled(true);
        }
        if (list.get(row).getTinhTrang() == 2) {
            btnXoa.setEnabled(false);
        }
        if (tblHoaDon.getValueAt(row, 4).equals("Đã Thanh Toán")) {
            btnThanhToan.setEnabled(false);
            return;
        } else {
            btnThanhToan.setEnabled(true);
        }

        if (txtTienKhachDua.getText().trim().isEmpty()) {
            btnThanhToan.setEnabled(false);
        }


    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        String maHd = txtMaHD.getText();
        HoaDon hd = iManagerHoaDonService.getHoaDonByMa(maHd);
        String idHD = hd.getId();
        List<HoaDonChiTiet> lsthdct = new ArrayList<>();
        for (ViewModels.HoaDonChiTiet hdct : listHdct) {
            HoaDonChiTiet DoMain = new HoaDonChiTiet();
            DoMain.setIdHoaDon(idHD);
            DoMain.setIdCTSP(hdct.getIdSPCT());
            DoMain.setSoLuong(hdct.getSoLuong());
            DoMain.setDonGia((float) hdct.getDonGia());
            lsthdct.add(DoMain);

        }
        for (HoaDonChiTiet hoaDonChiTiet : lsthdct) {
            iManagerHoaDonService.createHDCT(hoaDonChiTiet); // thêm hóa đơn vào hóa đơn chi tiết 
        }
        iManagerHoaDonService.updateHD(maHd); //  sửa tình trạng -> đã thanh toán

        loadTableHoaDon(iManagerHoaDonService.getListHoaDon());
        loadTableSanPham(iManagerSPchiTietService.getListSpChiTiet());
        JOptionPane.showMessageDialog(this, "Thanh toán thành công hóa đơn mã : " + maHd);


    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked

        int row = tblGioHang.getSelectedRow();
        txtTongTien.setText(String.valueOf(tblGioHang.getValueAt(row, 4)));
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtTienThuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienThuaMouseClicked


    }//GEN-LAST:event_txtTienThuaMouseClicked

    private void txtTienThuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienThuaMousePressed

    }//GEN-LAST:event_txtTienThuaMousePressed

    private void rbtnChuaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnChuaTTActionPerformed
        // TODO add your handling code here:
        list.removeAll(list);
        list = iManagerHoaDonService.getHoaDonByTinhTrang(0);
        loadTableHoaDon(list);
        btnXoa.setEnabled(true);

    }//GEN-LAST:event_rbtnChuaTTActionPerformed

    private void rbtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnAllActionPerformed
        // TODO add your handling code here:
        btnXoa.setEnabled(true);
        int row = tblHoaDon.getSelectedRow();

        list = iManagerHoaDonService.getListHoaDon();
        loadTableHoaDon(list);
    }//GEN-LAST:event_rbtnAllActionPerformed

    private void rbtnDaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnDaTTActionPerformed
        // TODO add your handling code here:
        list.removeAll(list);
        list = iManagerHoaDonService.getHoaDonByTinhTrang(2);
        loadTableHoaDon(list);
        btnXoa.setEnabled(false);

    }//GEN-LAST:event_rbtnDaTTActionPerformed

    private void rbtnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnHuyActionPerformed
        // TODO add your handling code here:
        list.removeAll(list);
        list = iManagerHoaDonService.getHoaDonByTinhTrang(1);
        loadTableHoaDon(list);
    }//GEN-LAST:event_rbtnHuyActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        listHdct.clear();
        loadTableGioHang(listHdct);
        txtTongTien.setText("");
        txtTienThua.setText("");
        txtTienKhachDua.setText("");
        txtDiaChi.setText("");
        txtTenNguoiNhan.setText("");
        txtSDT.setText("");
        txtNgayTao.setText("");
        txtTongTien.setText("");
        txtTienKhachDua.setText("");
        txtTienThua.setText("");
        txtSoLuong.setText("");
    }//GEN-LAST:event_btnHuyActionPerformed

    private void jPanel3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel3KeyTyped

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        String tienKhachDua = txtTienKhachDua.getText();
        if (tienKhachDua.trim().isEmpty()) {
            return;
        }
        try {
            double tienKH = Double.valueOf(tienKhachDua);
            double tongTien = Double.valueOf(txtTongTien.getText());
            double tienThua = tienKH - tongTien;
            txtTienThua.setText(String.valueOf(tienThua));
            if (txtTienThua.getText().trim().isEmpty()) {
                btnThanhToan.setEnabled(false);
            } else {
                btnThanhToan.setEnabled(true);
            }
            if (tienThua < 0) {
                btnThanhToan.setEnabled(false);
            } else {
                btnThanhToan.setEnabled(true);
            }
        } catch (Exception e) {
            System.out.println("Lỗi");
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String key = txtTimKiem.getText();
        if (key.trim().isEmpty()) {
            loadTableSanPham(listSp);
            return;
        }
        try {
            List<QlSanPhamChiTiet> list = new ArrayList<>();
            list = iManagerSPchiTietService.findSpct(key);
            if (list == null) {
                return;
            }
            loadTableSanPham(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new LoginFr().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần xóa");
            return;
        }
        System.out.println(list.get(row).getId());
        if (iManagerHoaDonService.deleteHD(list.get(row).getId()) != 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công: " + list.get(row).getMaHD());
            loadTableHoaDon(iManagerHoaDonService.getListHoaDon());
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        HoaDon hoaDon = validateHoaDon();
        if(hoaDon == null){
            return;
        }
        if(iManagerHoaDonService.updateHoaDon2(hoaDon)!=0){
            JOptionPane.showMessageDialog(this, "Thành công : "+ hoaDon.getMa());
            loadTableHoaDon(iManagerHoaDonService.getListHoaDon());
        }else{
            JOptionPane.showMessageDialog(this, "Thất bại");
        }

    }//GEN-LAST:event_btnSuaActionPerformed

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
            java.util.logging.Logger.getLogger(ChucNangBanHangFr.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChucNangBanHangFr.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChucNangBanHangFr.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChucNangBanHangFr.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChucNangBanHangFr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntThemVaoGioHang;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxKhachHang;
    private javax.swing.JComboBox<String> cbxNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rbtnAll;
    private javax.swing.JRadioButton rbtnChuaTT;
    private javax.swing.JRadioButton rbtnDaTT;
    private javax.swing.JRadioButton rbtnHuy;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenNguoiNhan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
