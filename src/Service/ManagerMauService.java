/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.Mau;
import Service.ImplService.IManagerMauService;
import InterFaceRepository.IMauRepository;
import Repositories.MauRepository;
import ViewModels.QlMau;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerMauService implements IManagerMauService {

    private final IMauRepository iMauRepository;
    private List<QlMau> list;

    public ManagerMauService() {
        this.iMauRepository = new MauRepository();
        list = new ArrayList<>();
    }

    @Override
    public List<QlMau> getAll() {
        list = new ArrayList<>();
        List<Mau> listM = iMauRepository.getAll();
        for (Mau x : listM) {
            list.add(new QlMau(x.getId(),x.getMa(), x.getTen()));
        }
        return list;
    }

    @Override
    public QlMau getByID(String id) {
        Mau x = iMauRepository.findById(id);
        return new QlMau(x.getMa(), x.getTen());
    }

    @Override
    public QlMau getByMa(String ma) {
        Mau x = iMauRepository.findByMa(ma);
        return new QlMau(x.getMa(), x.getTen());
    }

    @Override
    public QlMau create(QlMau mau) {
        Mau x = iMauRepository.save(new Mau(null, mau.getMa(), mau.getTen()));
        return mau;
    }

    @Override
    public QlMau update(QlMau mau) {
        Mau x = iMauRepository.update(new Mau(null, mau.getMa(), mau.getTen()));
        return mau;
    }

    @Override
    public int delete(String ma) {
        return iMauRepository.delete(ma);
    }

    @Override
    public QlMau getByName(String name) {
        Mau x = iMauRepository.findByName(name);
        return new QlMau(x.getMa(), x.getTen());
    }

}
