/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.SanPham;
import DomainModels.SanPhamChiTiet;
import InterFaceRepository.IDongSpRepository;
import InterFaceRepository.IMauRepository;
import InterFaceRepository.INSXRepository;
import Repositories.InterFaceRepository.ISanPhamChiTietRepository;
import InterFaceRepository.ISanPhamReporitory;
import Utilities.DBconnetion;
import ViewModels.QlSanPhamChiTiet;
import ViewModels.SPCT;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SanPhamChiTietRepository implements ISanPhamChiTietRepository {

    private final String SELECT_SANPHAMCHITIET = " select distinct SanPham.Ma,Ten,ChiTietSP.NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan,ChiTietSP.Id from ChiTietSP \n"
            + "            inner join SanPham on SanPham.Id = ChiTietSP.IdSP";
    private final String UPDATE_SANPHAMCHITIET = "update ChiTietSP set IdNsx = ?, IdMauSac =?,IdDongSP =?,NamBH = ? , MoTa =?,SoLuongTon = ? , GiaNhap = ?, GiaBan = ? where IdSP =?";

    private final String DELETE_SANPHAMCHITIET = "delete ChiTietSP where Id = ?";
    private final String SELECT_IN_HDCT = "select IdChiTietSP from HoaDonChiTiet where IdChiTietSP = ?";

    @Override

    public List<QlSanPhamChiTiet> getListSanPham() {
        List<QlSanPhamChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_SANPHAMCHITIET);
            while (rs.next()) {
                QlSanPhamChiTiet x = new QlSanPhamChiTiet();
                x.setMaSP(rs.getString("ma"));
                x.setTenSP(rs.getString("ten"));
                x.setNamBH(rs.getInt("NamBH"));
                x.setMoTa(rs.getString("Mota"));
                x.setSoLuong(rs.getInt("SoLuongTon"));
                x.setGiaNhap(rs.getFloat("GiaNhap"));
                x.setGiaBan(rs.getFloat("GiaBan"));
                x.setId(rs.getString("id"));
                list.add(x);

            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu bảng sản phẩm chi tiết");
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        for (QlSanPhamChiTiet arg : new SanPhamChiTietRepository().getListSanPham()) {
            System.out.println(arg.toString());
        }
    }

    @Override
    public List<SPCT> getListSPCT() {
        String SELECT_SPCT = "select ChiTietSP.Id,\n"
                + "SanPham.Ten as 'TenSp',\n"
                + "NSX.Ten as 'TenNSX',\n"
                + "MauSac.Ten as 'Mau',\n"
                + "DongSP.Ten as 'Dong',\n"
                + "ChiTietSP.NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan \n"
                + "from ChiTietSP \n"
                + "inner join SanPham on SanPham.Id = ChiTietSP.IdSP\n"
                + "inner join DongSP on DongSP.Id = ChiTietSP.IdDongSP\n"
                + "inner join MauSac on MauSac.Id = ChiTietSP.IdMauSac\n"
                + "inner join NSX on NSX.Id = ChiTietSP.IdNsx";
        List<ViewModels.SPCT> list = new ArrayList<>();
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_SPCT);
            while (rs.next()) {
                SPCT spct = new SPCT();
                spct.setId(rs.getString("id"));
                spct.setTenSP(rs.getString("tenSp"));
                spct.setTenNSX(rs.getString("tenNSX"));
                spct.setMau(rs.getString("mau"));
                spct.setDong(rs.getString("dong"));
                spct.setNamBh(rs.getInt("namBh"));
                spct.setMoTa(rs.getString("moTa"));
                spct.setSoLuongTon(rs.getInt("soLuongTon"));
                spct.setGiaNhap(rs.getFloat("GiaNhap"));
                spct.setGiaBan(rs.getFloat("giaBan"));

                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi lấy ds spct");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int createSPCT(SanPhamChiTiet spct) {
        String INSERT_SQL = "INSERT INTO [dbo].[ChiTietSP]\n"
                + "           ([Id]\n"
                + "           ,[IdSP]\n"
                + "           ,[IdNsx]\n"
                + "           ,[IdMauSac]\n"
                + "           ,[IdDongSP]\n"
                + "           ,[NamBH]\n"
                + "           ,[MoTa]\n"
                + "           ,[SoLuongTon]\n"
                + "           ,[GiaNhap]\n"
                + "           ,[GiaBan])\n"
                + "     VALUES\n"
                + "           (NEWID(),?,?,?,?,?,?,?,?,?)";
        try {
            return DBconnetion.ExcuteUpdate(INSERT_SQL,
                    spct.getIdSP(),
                    spct.getIdnsx(),
                    spct.getIdMau(),
                    spct.getDongSp(),
                    spct.getNamBH(),
                    spct.getMoTa(),
                    spct.getSoLuong(),
                    spct.getGiaNhap(),
                    spct.getGiaBan()
            );

        } catch (Exception e) {
            System.out.println("Lỗi thêm bảng chi tiết sản phẩm ");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<QlSanPhamChiTiet> findSpct(String ten) {
        String FIND_SQL = "select SanPham.Ma,Ten,ChiTietSP.NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan,ChiTietSP.Id from ChiTietSP \n"
                + "       inner join SanPham on SanPham.Id = ChiTietSP.IdSP where sanpham.ma = ? or sanpham.ten like ? ";
//        String FIND_SQL_TEN = "select SanPham.Ma,Ten,ChiTietSP.NamBH,MoTa,SoLuongTon,GiaNhap,GiaBan,ChiTietSP.Id from ChiTietSP \n"
//                + "      inner join SanPham on SanPham.Id = ChiTietSP.IdSP where  SanPham.Ten like ?";
        List<QlSanPhamChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            rs = DBconnetion.getDataFromQuery(FIND_SQL, ten, "%" + ten + "%");
//            if (DBconnetion.getDataFromQuery(FIND_SQL_TEN, ten) == null) {
//               // rs = DBconnetion.getDataFromQuery(FIND_SQL_TEN, ten);
//                System.out.println("ok");
//            }

            while (rs.next()) {
                QlSanPhamChiTiet x = new QlSanPhamChiTiet();
                x.setMaSP(rs.getString("ma"));
                x.setTenSP(rs.getString("ten"));
                x.setNamBH(rs.getInt("NamBH"));
                x.setMoTa(rs.getString("Mota"));
                x.setSoLuong(rs.getInt("SoLuongTon"));
                x.setGiaNhap(rs.getFloat("GiaNhap"));
                x.setGiaBan(rs.getFloat("GiaBan"));
                x.setId(rs.getString("id"));
                list.add(x);

            }
            return list;

        } catch (Exception e) {
            System.out.println("Lỗi tìm sản phẩm chi tiết");
            e.printStackTrace();
        }
        return list;
    }

    public int updateSPCT(SanPhamChiTiet spct) {
        // update ChiTietSP set IdNsx =  ?, IdMauSac =  ?, IdDongSP =  ?, NamBH = 10, MoTa =  ?, SoLuongTon =  ?, GiaNhap =  ?, GiaBan =  ? where  IdSP =  ?
        int kq = 0;
        try {
            kq = DBconnetion.ExcuteUpdate(UPDATE_SANPHAMCHITIET,
                    spct.getIdnsx(),
                    spct.getIdMau(),
                    spct.getDongSp(),
                    spct.getNamBH(),
                    spct.getMoTa(),
                    spct.getSoLuong(),
                    spct.getGiaNhap(),
                    spct.getGiaBan(),
                    spct.getIdSP());
            return kq;
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật giỏ hàng chi tiết");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteSPCT(String idSp) {
        String id = null;
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_IN_HDCT, idSp);
            while (rs.next()) {
                id = rs.getString("IdChiTietSP");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int kq = 0;
        try {
            if (id != null) {
                return 0;
            }
            kq = DBconnetion.ExcuteUpdate(DELETE_SANPHAMCHITIET, idSp);
            return kq;
        } catch (Exception e) {
            System.out.println("Lỗi xóa chi tiết sản phẩm");
            e.printStackTrace();
        }
        return 0;
    }

}
