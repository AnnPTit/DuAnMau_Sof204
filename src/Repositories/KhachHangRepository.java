/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.KhachHang;
import InterFaceRepository.IKhachHangRepository;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class KhachHangRepository implements IKhachHangRepository {

    private List<KhachHang> list;
    final String INSERT_SQL = "DECLARE @id  uniqueidentifier \n"
            + "SET @id = NEWID() \n"
            + "INSERT INTO KhachHang VALUES(@id,?,?,?,?,?,?,?,?,?,?)";

    final String UPDATE_SQL = "UPDATE KhachHang "
            + "SET TEN = ? , tendem = ? , ho = ?"
            + " , ngaySinh = ? , sdt = ? ,diachi = ? "
            + ", thanhPho = ? , quocGia = ? "
            + " WHERE MA = ?";

    final String DELETE_SQL = " DELETE KhachHang WHERE MA = ?";
    final String SELECT_ALL_SQL = "SELECT id,ma,ten,tendem,ho,ngaysinh,sdt,diachi, thanhpho,quocgia,matkhau FROM KhachHang ";
    final String SELECT_BY_ID = "SELECT id,ma,ten,tendem,ho,ngaysinh,sdt,diachi, thanhpho,quocgia,matkhau FROM KhachHang WHERE ID = ?";
    final String SELECT_BY_MA = "SELECT id,ma,ten,tendem,ho,ngaysinh,sdt,diachi, thanhpho,quocgia,matkhau FROM KhachHang WHERE MA = ?";
    final String SELECT_BY_TEN = "SELECT id,ma,ten,tendem,ho,ngaysinh,sdt,diachi, thanhpho,quocgia,matkhau FROM KhachHang WHERE ten = ?";
    final String SELECT_THANHPHO = "SELECT distinct THANHPHO FROM KHACHHANG";
    final String SELECT_QUOCGIA = "SELECT distinct QUOCGIA FROM KHACHHANG";
    final String SELECT_MATKHAU = "SELECT MATKHAU FROM KHACHHANG WHERE MA = ?";

    public KhachHangRepository() {
        list = new ArrayList<>();
    }

    @Override
    public List<KhachHang> getAll() {
        list = new ArrayList<>();
        return getSelectSql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang findById(String Id) {
        return getSelectSql(SELECT_BY_ID, Id).get(0);
    }

    @Override
    public KhachHang save(KhachHang kh) {
        DBconnetion.ExcuteUpdate(INSERT_SQL,
                kh.getMa(),
                kh.getTen(),
                kh.getTenDem(),
                kh.getHo(),
                kh.getNgaySinh(),
                kh.getSdt(),
                kh.getDiaChi(),
                kh.getThanhPho(),
                kh.getQuocGia(),
                kh.getMatKhau());
        return kh;
    }

    @Override
    public KhachHang update(KhachHang kh) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL,
                kh.getTen(),
                kh.getTenDem(),
                kh.getHo(),
                kh.getNgaySinh(),
                kh.getSdt(),
                kh.getDiaChi(),
                kh.getThanhPho(),
                kh.getQuocGia(),
                kh.getMa()
        );
        return kh;

    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    @Override
    public KhachHang findByMa(String ma) {
        return getSelectSql(SELECT_BY_MA, ma).get(0);
    }

    @Override
    public KhachHang findByName(String name) {
        return getSelectSql(SELECT_BY_TEN, name).get(0);
    }

    List<KhachHang> getSelectSql(String sql, Object... args) {
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getString("id"),
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getString("tendem"),
                        rs.getString("ho"),
                        rs.getString("sdt"),
                        rs.getString("diachi"),
                        rs.getString("thanhpho"),
                        rs.getString("quocgia"),
                        rs.getString("matKhau"),
                        rs.getDate("ngaySinh"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu bảng khách hàng");
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public int totalCount() {
        int total = 0;
        total = list.size();
        return total;
    }

    @Override
    public List<String> getThanhPho() {
        List<String> listTp = new ArrayList<>();
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_THANHPHO);
            while (rs.next()) {
                listTp.add(rs.getString("ThanhPho"));
            }
            return listTp;
        } catch (SQLException ex) {
            //  Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi Lấy Thành Phố");
            ex.printStackTrace();

        }
        return null;
    }

    @Override
    public List<String> getQuocGia() {
        List<String> listQg = new ArrayList<>();
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_QUOCGIA);
            while (rs.next()) {
                listQg.add(rs.getString("QUOCGIA"));
            }
            return listQg;
        } catch (SQLException ex) {
            //  Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi Lấy Quốc  Gia");
            ex.printStackTrace();

        }
        return null;
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
