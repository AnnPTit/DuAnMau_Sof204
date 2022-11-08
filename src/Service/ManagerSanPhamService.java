/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.SanPham;
import Service.ImplService.IManagerSanPhamService;
import InterFaceRepository.ISanPhamReporitory;
import Repositories.SanPhamRepository;
import Utilities.DBconnetion;
import ViewModels.QLSanPham;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerSanPhamService implements IManagerSanPhamService {

    private final ISanPhamReporitory iSanPhamReporitory;
    private List<QLSanPham> list;

    public ManagerSanPhamService() {
        this.iSanPhamReporitory = new SanPhamRepository();
        list = new ArrayList<>();
    }

    @Override
    public List<QLSanPham> getAll() {
        list = new ArrayList<>();
        List<SanPham> listM = iSanPhamReporitory.getAll();
        for (SanPham x : listM) {
            list.add(new QLSanPham(x.getId(),x.getMa(), x.getTen()));
        }
        return list;
    }

    @Override
    public QLSanPham getByID(String id) {
        SanPham x = iSanPhamReporitory.findById(id);
        return new QLSanPham(x.getMa(), x.getTen());
    }

    @Override
    public QLSanPham getByMa(String ma) {
        SanPham x = iSanPhamReporitory.findByMa(ma);
        return new QLSanPham(x.getMa(), x.getTen());
    }

    @Override
    public QLSanPham create(QLSanPham sanPham) {
        iSanPhamReporitory.save(new SanPham(null, sanPham.getMa(), sanPham.getTen()));
        return sanPham;
    }

    @Override
    public QLSanPham update(QLSanPham sanPham) {
        iSanPhamReporitory.update(new SanPham(null, sanPham.getMa(), sanPham.getTen()));
        return sanPham;
    }

    @Override
    public int delete(String ma) {
        return iSanPhamReporitory.delete(ma);
    }

    @Override
    public QLSanPham getByName(String name) {
        SanPham x = iSanPhamReporitory.findByName(name);
        return new QLSanPham(x.getMa(), x.getTen());
    }

}
