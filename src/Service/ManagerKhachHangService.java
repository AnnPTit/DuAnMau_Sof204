/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.KhachHang;
import DomainModels.NhanVien;
import Service.ImplService.IManagerKhachHangService;
import InterFaceRepository.IKhachHangRepository;
import Repositories.KhachHangRepository;
import ViewModels.QlKhachHang;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerKhachHangService implements IManagerKhachHangService {

    private final IKhachHangRepository iKhachHangRepository;
    List<QlKhachHang> list;

    public ManagerKhachHangService() {
        this.iKhachHangRepository = new KhachHangRepository();
        list = new ArrayList<>();
    }

    @Override
    public List<QlKhachHang> getAll() {
        list = new ArrayList<>();
        List<KhachHang> khachHangs = iKhachHangRepository.getAll();
        for (KhachHang x : khachHangs) {
            QlKhachHang khachHang = new QlKhachHang(
                    x.getId(),
                    x.getMa(),
                    x.getTen(),
                    x.getTenDem(),
                    x.getHo(),
                    x.getSdt(),
                    x.getDiaChi(),
                    x.getThanhPho(),
                    x.getQuocGia(),
                    x.getMatKhau(),
                    x.getNgaySinh());
            list.add(khachHang);
        }
        return list;
    }

    @Override
    public QlKhachHang getById(String id) {
        KhachHang x = iKhachHangRepository.findById(id);
        return new QlKhachHang(x.getId(),
                x.getMa(),
                x.getTen(),
                x.getTenDem(),
                x.getHo(),
                x.getSdt(),
                x.getDiaChi(),
                x.getThanhPho(),
                x.getQuocGia(),
                x.getMatKhau(),
                x.getNgaySinh());

    }

    @Override
    public QlKhachHang getByMa(String ma) {
        KhachHang x = iKhachHangRepository.findByMa(ma);
        return new QlKhachHang(x.getId(),
                x.getMa(),
                x.getTen(),
                x.getTenDem(),
                x.getHo(),
                x.getSdt(),
                x.getDiaChi(),
                x.getThanhPho(),
                x.getQuocGia(),
                x.getMatKhau(),
                x.getNgaySinh());
    }

    @Override
    public QlKhachHang create(QlKhachHang khachHang) {
        KhachHang x = iKhachHangRepository.save(new KhachHang(
                null,
                khachHang.getMa(),
                khachHang.getTen(),
                khachHang.getTenDem(),
                khachHang.getHo(),
                khachHang.getSdt(),
                khachHang.getDiaChi(),
                khachHang.getThanhPho(),
                khachHang.getQuocGia(),
                khachHang.getMatKhau(),
                khachHang.getNgaySinh()));
        return khachHang;
    }

    @Override
    public QlKhachHang update(QlKhachHang khachHang) {
        KhachHang x = iKhachHangRepository.update(new KhachHang(
                null,
                khachHang.getMa(),
                khachHang.getTen(),
                khachHang.getTenDem(),
                khachHang.getHo(),
                khachHang.getSdt(),
                khachHang.getDiaChi(),
                khachHang.getThanhPho(),
                khachHang.getQuocGia(),
                null,// mat khau k cap nhat 
                khachHang.getNgaySinh()));
        return khachHang;
    }

    @Override
    public int delete(String ma) {
        return iKhachHangRepository.delete(ma);
    }

    @Override
    public int countAll() {
        return iKhachHangRepository.totalCount();
    }

    @Override
    public List<String> getThanhPho() {
        return iKhachHangRepository.getThanhPho();
    }

    @Override
    public List<String> getQuocGia() {
        return iKhachHangRepository.getQuocGia();
    }

    @Override
    public String getMatKhau(String ma) {
        return iKhachHangRepository.getMatKhau(ma);
    }

}
