/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.CuaHang;
import DomainModels.NhanVien;
import InterFaceRepository.IChucVuRepositories;
import InterFaceRepository.ICuaHangRepository;
import InterFaceRepository.INhanVienRepository;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienRepository implements INhanVienRepository {

    final String INSERT_SQL
            = "DECLARE @id  uniqueidentifier \n"
            + "DECLARE @idBC uniqueidentifier \n "
            + "SET @id = NEWID() \n"
            + "SET @idBC = NEWID() \n"
            + "INSERT INTO NHANVIEN VALUES(@id,?,?,?,?,?,?,?,?,?,?,?,@id,?)";
    final String UPDATE_SQL = "UPDATE NHANVIEN SET TEN = ?"
            + ", TENDEM = ? ,"
            + "HO = ? ,"
            + "GIOITINH = ?,"
            + "NGAYSINH = ?,"
            + "DIACHI = ?,"
            + "SDT = ? ,"
            + "MATKHAU = ? ,"
            + "IDCH = ?,"
            + "IDCV = ?,"
            // + "IDGUIBC= ?,"
            + "TRANGTHAI = ? WHERE MA = ?";
    final String DELETE_SQL = "DELETE NHANVIEN WHERE MA = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NHANVIEN";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NHANVIEN WHERE ID = ?";
    final String SELECT_BY_MA_SQL = "SELECT * FROM NHANVIEN WHERE MA = ?";
    private List<NhanVien> list;
    private INhanVienRepository nhanVienRepository;
    private ICuaHangRepository iCuaHangRepository;
    private IChucVuRepositories iChucVuRepositories;
    final String SELECT_MATKHAU = "SELECT MATKHAU FROM NHANVIEN WHERE MA = ?";

    public NhanVienRepository() {
        list = new ArrayList<>();

        iCuaHangRepository = new CuaHangRepository();
        iChucVuRepositories = new ChucVuRepositories();
    }

    @Override
    public List<NhanVien> getAll(int position, int pageSize) {
        list = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien findByID(String ma) {
        return getSelectSql(SELECT_BY_ID_SQL, ma).get(0);
    }

    @Override
    public NhanVien save(NhanVien nhanVien) {
        DBconnetion.ExcuteUpdate(INSERT_SQL,
                nhanVien.getMa(),
                nhanVien.getTen(),
                nhanVien.getTenDem(),
                nhanVien.getHo(),
                nhanVien.getGioiTinh(),
                nhanVien.getNgaySinh(),
                nhanVien.getDiaChi(),
                nhanVien.getSdt(),
                nhanVien.getMatKhau(),
                nhanVien.getCuaHang().getId(),
                nhanVien.getChucVu().getId(),
                nhanVien.getTrangThai()
        );
        return nhanVien;
    }

    @Override
    public NhanVien update(NhanVien nhanVien) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL,
                nhanVien.getTen(),
                nhanVien.getTenDem(),
                nhanVien.getHo(),
                nhanVien.getGioiTinh(),
                nhanVien.getNgaySinh(),
                nhanVien.getDiaChi(),
                nhanVien.getSdt(),
                nhanVien.getMatKhau(),
                nhanVien.getCuaHang().getId(),
                nhanVien.getChucVu().getId(),
                // nhanVien.getIdGuiBC(),
                nhanVien.getTrangThai(),
                nhanVien.getMa()
        );
        return nhanVien;
    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    @Override
    public int totalCount() {
        int total = 0;
        total = list.size();
        return total;
    }

    private List<NhanVien> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getString("id"),
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getString("tendem"),
                        rs.getString("ho"),
                        rs.getString("gioitinh"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getString("matkhau"),
                        rs.getString("idguibc"),
                        rs.getDate("ngaysinh"),
                        rs.getInt("trangthai"),
                        iCuaHangRepository.getCuaHangById(rs.getString("idch")),
                        iChucVuRepositories.getChucVuById(rs.getString("idcv"))
                );
                list.add(nhanVien);

            }
            return list;

        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu bảng nhân viên");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NhanVien getNhanVienByMa(String ma) {
        return getSelectSql(SELECT_BY_MA_SQL, ma).get(0);

    }

    @Override
    public String getMatKhau(String ma) {
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_MATKHAU, ma);
            while (rs.next()) {
                String matKhau = rs.getString("matkhau");
                return matKhau;
            }

        } catch (SQLException ex) {
            //  Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi Lấy mật khẩu");
            ex.printStackTrace();

        }
        return null;
    }

}
