/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Repositories.InterFaceRepository.IHoaDonReppository;
import Repositories.HoaDonReposirory;
import ViewModels.QlHoaDon;
import java.util.ArrayList;
import java.util.List;
import Service.ImplService.IManagerHoaDonService;

/**
 *
 * @author ADMIN
 */
public class ManagerHoaDonService implements IManagerHoaDonService {

    private final IHoaDonReppository iHoaDonReppository;
    List<QlHoaDon> list;

    public ManagerHoaDonService() {
        this.iHoaDonReppository = new HoaDonReposirory();
        list = new ArrayList<>();
    }

    @Override
    public List<QlHoaDon> getListHoaDon() {
        return iHoaDonReppository.getListHoaDon();
    }

    @Override
    public HoaDon createHoaDon(HoaDon hoaDon) {
        return iHoaDonReppository.createHoaDon(hoaDon);
    }

    @Override
    public HoaDon getHoaDonByMa(String ma) {
        return iHoaDonReppository.getHoaDonByMa(ma);
    }

    @Override
    public int updateHD(String ma) {
        return iHoaDonReppository.updateHoaDon(ma);
    }

    @Override
    public HoaDonChiTiet createHDCT(HoaDonChiTiet hdct) {
        return iHoaDonReppository.createHDCT(hdct);
    }

    @Override
    public List<QlHoaDon> getHoaDonByTinhTrang(int tinhTrang) {
        return iHoaDonReppository.getHoaDonByTinhTrang(tinhTrang);
    }

    @Override
    public List<ViewModels.HoaDonChiTiet> getListHdct(String maHD) {
        return iHoaDonReppository.getListHdct(maHD);
    }

    @Override
    public int deleteHD(String id) {
        return iHoaDonReppository.deleteHoaDon(id);
    }

    @Override
    public int updateHoaDon2(HoaDon hoaDon) {
        return iHoaDonReppository.updateHoaDon2(hoaDon);
    }

}
