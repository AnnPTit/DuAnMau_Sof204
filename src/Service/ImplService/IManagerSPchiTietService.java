/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import DomainModels.SanPhamChiTiet;
import ViewModels.QlSanPhamChiTiet;
import ViewModels.SPCT;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerSPchiTietService {

    List<QlSanPhamChiTiet> getListSpChiTiet();

    List<SPCT> getListSpCT();

    int createSPCT(SanPhamChiTiet spct);

    public List<QlSanPhamChiTiet> findSpct(String tenOrMa);

    int updateSPCT(SanPhamChiTiet spct);

    int deleteSPCT(String idSP);

}
