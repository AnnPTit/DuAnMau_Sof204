/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.GioHang;
import DomainModels.GioHangChiTiet;
import Service.ImplService.IManagerGioHangService;
import Repositories.InterFaceRepository.IGioHangRepository;
import Repositories.GioHangRepository;
import ViewModels.QlGioHang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerGioHangService implements IManagerGioHangService {

    private final IGioHangRepository iGioHangRepository;
    List<QlGioHang> listGioHangs;

    public ManagerGioHangService() {
        this.iGioHangRepository = new GioHangRepository();
        listGioHangs = new ArrayList<>();
    }

    @Override
    public List<QlGioHang> getListGioHang() {
        return iGioHangRepository.getAll();
    }

    @Override
    public GioHang createGioHang(GioHang gioHang) {
        return iGioHangRepository.createGioHang(gioHang);
    }



}
