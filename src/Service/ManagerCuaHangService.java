/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.ChucVu;
import DomainModels.CuaHang;
import Service.ImplService.IManagerCuaHangService;
import Repositories.CuaHangRepository;
import InterFaceRepository.ICuaHangRepository;
import ViewModels.QlCuaHang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerCuaHangService implements IManagerCuaHangService {

    private final ICuaHangRepository repository;
    private List<QlCuaHang> list;

    public ManagerCuaHangService() {
        this.repository = new CuaHangRepository();
        list = new ArrayList<>();
    }

    @Override
    public List<QlCuaHang> getAllCuaHang() {
        List<CuaHang> cuaHangs = new ArrayList<>();
        list = new ArrayList<>();
        cuaHangs = repository.getAll();
        for (CuaHang x : cuaHangs) {
            list.add(new QlCuaHang(x.getMa(), x.getTen(), x.getDiaChi(), x.getThanhPho(), x.getQuocGia()));

        }
        return list;
    }

    @Override
    public QlCuaHang getCuaHangById(String id) {
        CuaHang x = repository.getCuaHangById(id);
        return new QlCuaHang(x.getMa(), x.getTen(), x.getDiaChi(), x.getThanhPho(), x.getQuocGia());
    }

    @Override
    public QlCuaHang getCuaHangByTen(String ten) {
        CuaHang x = repository.getCuaHangByName(ten);
        return new QlCuaHang(x.getId(), x.getMa(), x.getTen(), x.getDiaChi(), x.getThanhPho(), x.getQuocGia());
    }

    @Override
    public QlCuaHang createNewCuaHang(QlCuaHang cuaHang) {
        CuaHang x = repository.add(new CuaHang(null, cuaHang.getMa(), cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia()));
        return new QlCuaHang(x.getMa(), x.getTen(), x.getDiaChi(), x.getThanhPho(), x.getQuocGia());

    }

    @Override
    public QlCuaHang updateCuaHang(QlCuaHang cuaHang) {
        CuaHang x = repository.update(new CuaHang(null, cuaHang.getMa(), cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia()));
        return new QlCuaHang(x.getMa(), x.getTen(), x.getDiaChi(), x.getThanhPho(), x.getQuocGia());

    }

    @Override
    public int deleteCuaHang(String ma) {
        return repository.delete(ma);
    }

}
