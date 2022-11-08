/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Repositories.InterFaceRepository.IHoaDonReppository;
import Utilities.DBconnetion;
import ViewModels.QlHoaDon;
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
public class HoaDonReposirory implements IHoaDonReppository {

    private final String SELECT_HOADON = "select hoadon.Ma,NgayTao, NhanVien.Ten,HoaDon.TinhTrang,HoaDon.Id from hoaDon \n"
            + "            inner join NHANVIEN on NHANVIEN.Id = HoaDon.IdNV order by hoadon.ma desc";

    @Override
    public List<QlHoaDon> getListHoaDon() {
        List<QlHoaDon> list = new ArrayList<>();
        try {

            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_HOADON);
            while (rs.next()) {
                QlHoaDon hoaDon = new QlHoaDon();
                hoaDon.setMaHD(rs.getString(1));
                hoaDon.setNgayTao(rs.getDate(2));
                hoaDon.setTenNv(rs.getString(3));
                hoaDon.setTinhTrang(rs.getInt(4));
                hoaDon.setId(rs.getString(5));
                list.add(hoaDon);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu bảng hóa đơn");
            e.printStackTrace();
        }
        return list;
    }

//    public static void main(String[] args) {
//        for (QlHoaDon arg : new HoaDonReposirory().getListHoaDon()) {
//            System.err.println(arg.toString());
//        }
//    }
    @Override
    public HoaDon createHoaDon(HoaDon hoaDon) {
        String INSERT_HOADON = "INSERT INTO [dbo].[HoaDon]\n"
                + "           ([Id]\n"
                + "           ,[IdKH]\n"
                + "           ,[IdNV]\n"
                + "           ,[Ma]\n"
                + "           ,[NgayTao]\n"
                + "           ,[NgayThanhToan]\n"
                + "           ,[NgayShip]\n"
                + "           ,[NgayNhan]\n"
                + "           ,[TinhTrang]\n"
                + "           ,[TenNguoiNhan]\n"
                + "           ,[DiaChi]\n"
                + "           ,[Sdt])\n"
                + "     VALUES\n"
                + "        (NEWID(),?,?,?,GETDATE(),null,?,?,?,?,?,?)\n";

        try {
            DBconnetion.ExcuteUpdate(INSERT_HOADON,
                    hoaDon.getKhachHang(),
                    hoaDon.getNhanVien(),
                    hoaDon.getMa(),
                    hoaDon.getNgayShip(),
                    hoaDon.getNgayNhan(),
                    0,
                    hoaDon.getTenNguoiNhan(),
                    hoaDon.getDiaChi(),
                    hoaDon.getSdt()
            );

            return hoaDon;
        } catch (Exception e) {
            System.out.println("Lỗi thêm bảng hóa đơn");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HoaDon getHoaDonByMa(String ma) {
        String SELECT_Hoadon_Ma = "select id,IdKH,IdNV,NgayShip,NgayNhan,TenNguoiNhan,DiaChi,Sdt from HoaDon where Ma =?";
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_Hoadon_Ma, ma);
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getString("id"));
                hoaDon.setKhachHang(rs.getString("idKh"));
                hoaDon.setNhanVien(rs.getString("idNV"));
                hoaDon.setNgayShip(rs.getDate("NgayShip"));
                hoaDon.setNgayNhan(rs.getDate("ngayNhan"));
                hoaDon.setTenNguoiNhan(rs.getString("TenNguoiNhan"));
                hoaDon.setDiaChi(rs.getString("DiaChi"));
                hoaDon.setSdt(rs.getString("sdt"));

                return hoaDon;
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy hóa đơn theo mã");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HoaDonChiTiet createHDCT(HoaDonChiTiet hdct) {
        String INSERT_HDCT = "INSERT INTO [dbo].[HoaDonChiTiet]\n"
                + "           ([IdHoaDon]\n"
                + "           ,[IdChiTietSP]\n"
                + "           ,[SoLuong]\n"
                + "           ,[DonGia])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            DBconnetion.ExcuteUpdate(INSERT_HDCT,
                    hdct.getIdHoaDon(),
                    hdct.getIdCTSP(),
                    hdct.getSoLuong(),
                    hdct.getDonGia()
            );
            return hdct;
        } catch (Exception e) {
            System.out.println("Lỗi thêm hóa đơn chi tiết");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateHoaDon(String hoaDon) {

        String UPDATE_HD = "UPDATE [dbo].[HoaDon]\n"
                + "   SET TinhTrang = 2 , NgayThanhToan =GETDATE() where Ma = ?\n";
        int kq = 0;
        try {
            kq = DBconnetion.ExcuteUpdate(UPDATE_HD,
                    // hoaDon.getTinhTrang(),
                    hoaDon
            );
            return kq;
        } catch (Exception e) {
            System.out.println("Lỗi sửa hóa đơn");
            e.printStackTrace();
        }
        return kq;

    }

    @Override
    public List<QlHoaDon> getHoaDonByTinhTrang(int tinhTrang) {
        String SELECT_TINHTRANGHD = "select hoadon.Ma,NgayTao, NhanVien.Ten,HoaDon.TinhTrang,Hoadon.id from hoaDon \n"
                + "            inner join NHANVIEN on NHANVIEN.Id = HoaDon.IdNV where TinhTrang = ?";
        List<QlHoaDon> list1 = new ArrayList<>();
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_TINHTRANGHD, tinhTrang);
            while (rs.next()) {
                QlHoaDon hoaDon = new QlHoaDon();
                hoaDon.setMaHD(rs.getString(1));
                hoaDon.setNgayTao(rs.getDate(2));
                hoaDon.setTenNv(rs.getString(3));
                hoaDon.setTinhTrang(rs.getInt(4));
                hoaDon.setId(rs.getString(5));
                list1.add(hoaDon);
            }
            return list1;

        } catch (Exception e) {
            System.out.println("Lỗi trạng thái");
            e.printStackTrace();
        }
        return list1;
    }

    @Override
    public List<ViewModels.HoaDonChiTiet> getListHdct(String maHD) {
        String SELECT_HDCT = "select SanPham.Ma,HoaDonChiTiet.SoLuong,DonGia from HoaDonChiTiet \n"
                + "join HoaDon on HoaDon.Id = HoaDonChiTiet.IdHoaDon \n"
                + "join ChiTietSP on ChiTietSP.Id = HoaDonChiTiet.IdChiTietSP \n"
                + "join SanPham on SanPham.Id = ChiTietSP.IdSP where HoaDon .Ma =?";
        List<ViewModels.HoaDonChiTiet> listHdChiTiets = new ArrayList<>();
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_HDCT, maHD);
            while (rs.next()) {
                ViewModels.HoaDonChiTiet hdct = new ViewModels.HoaDonChiTiet();
                hdct.setMaSP(rs.getString("ma"));
                hdct.setSoLuong(rs.getInt("soLuong"));
                hdct.setDonGia(rs.getFloat("donGia"));
                listHdChiTiets.add(hdct);

            }
            return listHdChiTiets;
        } catch (Exception e) {
            System.out.println("Lỗi lấy list hóa đơn chi tiết");
            e.printStackTrace();
        }
        return listHdChiTiets;
    }

    @Override
    public int deleteHoaDon(String id) {
        String DELETE_HD = " delete hoadon where Id = ? ";
        int kq = 0;
        try {
            kq = DBconnetion.ExcuteUpdate(DELETE_HD, id);
            return kq;
        } catch (Exception e) {
            System.out.println("lỗi xóa hóa đơn chi tiết ");
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public int updateHoaDon2(HoaDon hoaDon) {
        String UPDATE_HOADON = " UPDATE [dbo].[HoaDon]\n"
                + "   SET \n"
                + "      [IdKH] = ?\n"
                + "      ,[IdNV] = ?\n"
                + "      ,[TenNguoiNhan] = ?\n"
                + "      ,[DiaChi] = ?\n"
                + "      ,[Sdt] = ?\n"
                + " WHERE Ma =?";
        int kq = 0;
        try {
            kq = DBconnetion.ExcuteUpdate(UPDATE_HOADON, hoaDon.getKhachHang(),
                    hoaDon.getNhanVien(),
                    hoaDon.getTenNguoiNhan(),
                    hoaDon.getDiaChi(),
                    hoaDon.getSdt(),
                    hoaDon.getMa()
            );
            return kq;
        } catch (Exception e) {
            System.out.println("lỗi cập nhật hóa đơn");
            e.printStackTrace();
        }
        return 0;
    }

}
