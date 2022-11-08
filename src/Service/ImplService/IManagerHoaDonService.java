/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import ViewModels.QlHoaDon;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerHoaDonService {

    List<QlHoaDon> getListHoaDon();

    HoaDon createHoaDon(HoaDon hoaDon);

    HoaDon getHoaDonByMa(String ma);

    int updateHD(String ma);

    HoaDonChiTiet createHDCT(HoaDonChiTiet hdct);

    List<QlHoaDon> getHoaDonByTinhTrang(int tinhTrang);

    List<ViewModels.HoaDonChiTiet> getListHdct(String maHD);

    int deleteHD(String id);

    int updateHoaDon2(HoaDon hoaDon);
}
