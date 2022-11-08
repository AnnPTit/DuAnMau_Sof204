/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.DongSp;
import Service.ImplService.IManagerDongSpService;
import Repositories.DongSpRepository;
import InterFaceRepository.IDongSpRepository;
import ViewModels.QlDongSp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerDongSpService implements IManagerDongSpService {

    private final IDongSpRepository iDongSpRepository;
    private List<QlDongSp> list;

    public ManagerDongSpService() {
        this.iDongSpRepository = new DongSpRepository();
        list = new ArrayList<>();
    }

    @Override
    public List<QlDongSp> getAll() {
        list = new ArrayList<>();
        List<DongSp> listM = iDongSpRepository.getAll();
        for (DongSp x : listM) {
            list.add(new QlDongSp(x.getId(),x.getMa(), x.getTen()));
        }
        return list;
    }

    @Override
    public QlDongSp getByID(String id) {
        DongSp x = iDongSpRepository.findById(id);
        return new QlDongSp(x.getMa(), x.getTen());
    }

    @Override
    public QlDongSp getByMa(String ma) {
        DongSp x = iDongSpRepository.findByMa(ma);
        return new QlDongSp(x.getMa(), x.getTen());
    }

    @Override
    public QlDongSp create(QlDongSp sp) {
        DongSp x = iDongSpRepository.save(new DongSp(null, sp.getMa(), sp.getTen()));
        return sp;
    }

    @Override
    public QlDongSp update(QlDongSp sp) {
        DongSp x = iDongSpRepository.update(new DongSp(null, sp.getMa(), sp.getTen()));
        return sp;
    }

    @Override
    public int delete(String ma) {
        return iDongSpRepository.delete(ma);
    }

    @Override
    public QlDongSp getByName(String name) {
        DongSp x = iDongSpRepository.findByName(name);
        return new QlDongSp(x.getMa(), x.getTen());
    }

}
