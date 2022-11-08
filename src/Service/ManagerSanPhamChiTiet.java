/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.SanPham;
import DomainModels.SanPhamChiTiet;
import Service.ImplService.IManagerSPchiTietService;
import Repositories.InterFaceRepository.ISanPhamChiTietRepository;
import Repositories.SanPhamChiTietRepository;
import ViewModels.QLSanPham;
import ViewModels.QlSanPhamChiTiet;
import ViewModels.SPCT;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerSanPhamChiTiet implements IManagerSPchiTietService {

    private final ISanPhamChiTietRepository iSanPhamChiTietRepository;
    List<QlSanPhamChiTiet> list;

    public ManagerSanPhamChiTiet() {
        this.iSanPhamChiTietRepository = new SanPhamChiTietRepository();
        list = new ArrayList<>();
    }

    @Override
    public List<QlSanPhamChiTiet> getListSpChiTiet() {
        return iSanPhamChiTietRepository.getListSanPham();
    }

    @Override
    public List<SPCT> getListSpCT() {
        return iSanPhamChiTietRepository.getListSPCT();
    }

    @Override
    public int createSPCT(SanPhamChiTiet spct) {
        return iSanPhamChiTietRepository.createSPCT(spct);
    }

    @Override
    public List<QlSanPhamChiTiet> findSpct(String ten) {
        return iSanPhamChiTietRepository.findSpct(ten);
    }

    @Override
    public int updateSPCT(SanPhamChiTiet spct) {
        return iSanPhamChiTietRepository.updateSPCT(spct);
    }

    @Override
    public int deleteSPCT(String idSP) {
        return iSanPhamChiTietRepository.deleteSPCT(idSP);
    }

}
