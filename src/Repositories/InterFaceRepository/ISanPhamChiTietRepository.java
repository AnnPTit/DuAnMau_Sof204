/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.InterFaceRepository;

import InterFaceRepository.*;
import Repositories.*;
import DomainModels.SanPhamChiTiet;
import ViewModels.QlSanPhamChiTiet;
import ViewModels.SPCT;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ISanPhamChiTietRepository {

    List<QlSanPhamChiTiet> getListSanPham();

    List<SPCT> getListSPCT();

    int createSPCT(SanPhamChiTiet spct);

    List<QlSanPhamChiTiet> findSpct(String ten);

    int updateSPCT(SanPhamChiTiet spct);

    int deleteSPCT(String idSp);

}
