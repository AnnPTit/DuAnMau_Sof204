/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.GioHang;
import DomainModels.GioHangChiTiet;
import Repositories.InterFaceRepository.IGioHangRepository;
import InterFaceRepository.IKhachHangRepository;
import InterFaceRepository.INhanVienRepository;
import Utilities.DBconnetion;
import ViewModels.QlGioHang;
import ViewModels.QlHoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class GioHangRepository implements IGioHangRepository {
    
    private List<GioHang> list;
    
    public GioHangRepository() {
        list = new ArrayList<>();
        
    }
    
    @Override
    public List<QlGioHang> getAll() {
        String SELECT_GIOHANG = "SELECT [GioHang].Id\n"
                + "      ,KhachHang.Ten as 'tenKh'\n"
                + "      ,NhanVien.Ten as 'tenNV'\n"
                + "      ,[GioHang].Ma\n"
                + "      ,[NgayTao]\n"
                + "     \n"
                + "      ,[TenNguoiNhan]\n"
                + "      ,[GioHang].DiaChi\n"
                + "      ,GioHang.Sdt\n"
                + "      ,[TinhTrang]\n"
                + "  FROM [dbo].[GioHang] inner join KhachHang on KhachHang.Id = GioHang.IdKH inner join NhanVien on NhanVien.Id = GioHang.IdNV ";
        
        List<QlGioHang> listGioHangs = new ArrayList<>();
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(SELECT_GIOHANG);
            while (rs.next()) {
                QlGioHang qlGioHang = new QlGioHang();
                qlGioHang.setId(rs.getString("id"));
                qlGioHang.setTenKH(rs.getString("tenKh"));
                qlGioHang.setTenNV(rs.getString("tenNv"));
                qlGioHang.setMa(rs.getString("ma"));
                qlGioHang.setNgayTao(rs.getString("ngayTao"));
                qlGioHang.setTenNguoiNhan(rs.getString("tenNguoiNhan"));
                qlGioHang.setDiaChi(rs.getString("diaChi"));
                qlGioHang.setSdt(rs.getString("sdt"));
                qlGioHang.setTinhTrang(rs.getInt("tinhTrang"));
                listGioHangs.add(qlGioHang);
            }
            return listGioHangs;
            
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu bảng giỏ hàng");
            e.printStackTrace();
        }
        return listGioHangs;
        
    }
    
    @Override
    public GioHang createGioHang(GioHang gioHang) {
        String INSERT_GIOHANG = "	INSERT INTO [dbo].[GioHang]\n"
                + "           ([Id]\n"
                + "           ,[IdKH]\n"
                + "           ,[IdNV]\n"
                + "           ,[Ma]\n"
                + "           ,[NgayTao]\n"
                + "           ,[NgayThanhToan]\n"
                + "           ,[TenNguoiNhan]\n"
                + "           ,[DiaChi]\n"
                + "           ,[Sdt]\n"
                + "           ,[TinhTrang])\n"
                + "     VALUES\n"
                + "           (NEWID(),?,?,?,GETDATE(),null,?,?,?,0)	";
        
        try {
            DBconnetion.ExcuteUpdate(INSERT_GIOHANG,
                    gioHang.getIdKH(),
                    gioHang.getIdNV(),
                    gioHang.getMa(),
                    gioHang.getTenNguoiNhan(),
                    gioHang.getDiaChi(),
                    gioHang.getSdt()
            );
            
            return gioHang;
            
        } catch (Exception e) {
            System.out.println("Lỗi thêm giỏ hàng");
            e.printStackTrace();
        }
        return null;
        
    }
    
   
    
}
