/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.ChucVu;
import DomainModels.NSX;
import Service.ImplService.IManagerNSXService;
import InterFaceRepository.INSXRepository;
import Repositories.NSXRepository;
import ViewModels.QlNSX;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerNSXservice implements IManagerNSXService {

    private final INSXRepository iNSXRepository;
    private List<QlNSX> list;

    public ManagerNSXservice() {
        this.iNSXRepository = new NSXRepository();
        list = new ArrayList<>();
    }

    @Override
    public List<QlNSX> getAll() {
        list = new ArrayList<>();
        List<NSX> listNsx = iNSXRepository.getAll();
        for (NSX x : listNsx) {
            list.add(new QlNSX(x.getId(),x.getMa(), x.getTen()));
        }
        return list;
    }

    @Override
    public QlNSX getNSXById(String id) {
        NSX nsx = iNSXRepository.findById(id);
        return new QlNSX(nsx.getMa(), nsx.getTen());
    }

    @Override
    public QlNSX getNSXByMa(String ma) {
        NSX nsx = iNSXRepository.findByMa(ma);
        return new QlNSX(nsx.getMa(), nsx.getTen());
    }

    @Override
    public QlNSX createNewNSX(QlNSX nSX) {
        NSX x = iNSXRepository.save(new NSX(null, nSX.getMa(), nSX.getTen()));
        return nSX;
    }

    @Override
    public QlNSX updateNSX(QlNSX nSX) {
        iNSXRepository.update(new NSX(null, nSX.getMa(), nSX.getTen()));
        return nSX;
    }

    @Override
    public int deleteNSX(String ma) {
        return iNSXRepository.delete(ma);

    }

    @Override
    public QlNSX getNSXByname(String name) {
        NSX nsx = iNSXRepository.findByName(name);
        return new QlNSX(nsx.getMa(), nsx.getTen());
    }

}
