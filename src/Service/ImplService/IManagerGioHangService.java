/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import DomainModels.GioHang;
import DomainModels.GioHangChiTiet;
import ViewModels.QlGioHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerGioHangService {

    List<QlGioHang> getListGioHang();

    GioHang createGioHang(GioHang gioHang);

}
