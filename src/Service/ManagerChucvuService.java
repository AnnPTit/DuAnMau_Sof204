/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.ChucVu;
import Service.ImplService.IManagerChucVuService;
import Repositories.ChucVuRepositories;
import InterFaceRepository.IChucVuRepositories;
import ViewModels.QLChucVu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ManagerChucvuService implements IManagerChucVuService {

    private final IChucVuRepositories _iChucVuRepositories;
    private List<QLChucVu> _lstChucVu;

    public ManagerChucvuService() {
        _iChucVuRepositories = new ChucVuRepositories();
        _lstChucVu = new ArrayList<>();
    }

    @Override
    public List<QLChucVu> getChucVu() {
        _lstChucVu = new ArrayList<>();
        List<ChucVu> chucVus = _iChucVuRepositories.findAll();
        for (ChucVu x : chucVus) {
            _lstChucVu.add(new QLChucVu(x.getMa(), x.getTen()));
        }
        return _lstChucVu;
    }

    @Override
    public QLChucVu getChucVuById(String ma) {
        ChucVu x = _iChucVuRepositories.getChucVuById(ma);
        return new QLChucVu(x.getMa(), x.getTen());
    }

    @Override
    public QLChucVu createNewChucVu(QLChucVu chucVu) {

        ChucVu x = _iChucVuRepositories.add(new ChucVu(null, chucVu.getMa(), chucVu.getTen()));
        return new QLChucVu(x.getMa(), x.getTen());
    }

    @Override
    public QLChucVu updateChuVu(QLChucVu chucVu) {
        ChucVu x = _iChucVuRepositories.update(new ChucVu(null, chucVu.getMa(), chucVu.getTen()));
        return new QLChucVu(x.getMa(), x.getTen());
    }

    @Override
    public int deleteChucVu(String ma) {
        return _iChucVuRepositories.delete(ma);
    }

    @Override
    public QLChucVu getChucVuByName(String name) {
        ChucVu x = _iChucVuRepositories.getChucVuByName(name);
        return new QLChucVu(x.getMa(), x.getTen(),x.getId());
    }

}
