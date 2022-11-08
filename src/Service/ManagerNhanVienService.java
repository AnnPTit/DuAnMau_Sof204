/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.NhanVien;
import Service.ImplService.IManagerNhanVienService;
import InterFaceRepository.INhanVienRepository;
import Repositories.NhanVienRepository;
import Utilities.XDate;
import ViewModels.QlNhanVien;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ManagerNhanVienService implements IManagerNhanVienService {

    private final INhanVienRepository iNhanVienRepository;
    private List<QlNhanVien> list;
    XDate xDate;

    public ManagerNhanVienService() {
        this.iNhanVienRepository = new NhanVienRepository();
        list = new ArrayList<>();
        xDate = new XDate();
    }

    @Override
    public List<QlNhanVien> getNhanVien(int position, int pageSize) {
        list = new ArrayList<>();
        List<NhanVien> nhanViens = iNhanVienRepository.getAll(position, pageSize);
        for (NhanVien x : nhanViens) {

            list.add(new QlNhanVien(
                    x.getId(),
                    x.getMa(),
                    x.getTen(),
                    x.getTenDem(),
                    x.getHo(),
                    x.getDiaChi(),
                    x.getSdt(),
                    x.getIdGuiBC(),
                    x.getCuaHang(),
                    x.getChucVu(),
                    x.getGioiTinh(),
                    x.getNgaySinh(),
                    x.getTrangThai()));
        }
        return list;

    }

    @Override
    public QlNhanVien getNhanVienById(String ma) {
        NhanVien x = iNhanVienRepository.findByID(ma);
        return new QlNhanVien(
                x.getId(),
                x.getMa(),
                x.getTen(),
                x.getTenDem(),
                x.getHo(),
                x.getDiaChi(),
                x.getSdt(),
                x.getIdGuiBC(),
                x.getCuaHang(),
                x.getChucVu(),
                x.getGioiTinh(),
                x.getNgaySinh(),
                x.getTrangThai());
    }

    @Override
    public QlNhanVien createNewNhanVien(QlNhanVien nhanVien) {
        //  nhanVien.setMa(null);
        NhanVien x = iNhanVienRepository.save(new NhanVien(
                null,
                nhanVien.getMa(),
                nhanVien.getTen(),
                nhanVien.getTenDem(),
                nhanVien.getHo(),
                nhanVien.getGioiTinh(),
                nhanVien.getDiaChi(),
                nhanVien.getSdt(),
                nhanVien.getMatKhau(),
                null,//                nhanVien.getIdBaoCao(),
                nhanVien.getNgaySinh(),
                nhanVien.getTrangThai(),
                nhanVien.getCuaHang(),
                nhanVien.getChucVu()));
        return nhanVien;

    }

    @Override
    public QlNhanVien updateNhanVien(QlNhanVien nhanVien) {
        NhanVien x = iNhanVienRepository.update(new NhanVien(
                null,
                nhanVien.getMa(),
                nhanVien.getTen(),
                nhanVien.getTenDem(),
                nhanVien.getHo(),
                nhanVien.getGioiTinh(),
                nhanVien.getDiaChi(),
                nhanVien.getSdt(),
                nhanVien.getMatKhau(),
                null,// nhanVien.getIdBaoCao(),
                nhanVien.getNgaySinh(),
                nhanVien.getTrangThai(),
                nhanVien.getCuaHang(),
                nhanVien.getChucVu()));
        return nhanVien;
    }

    @Override
    public int deleteNhanVien(String ma) {
        return iNhanVienRepository.delete(ma);
    }

    @Override
    public int countAllNhanVien() {
        return iNhanVienRepository.totalCount();
    }

    @Override
    public QlNhanVien getNhanVienByMa(String ma) {
        NhanVien x = iNhanVienRepository.getNhanVienByMa(ma);
        return new QlNhanVien(
                x.getId(),
                x.getMa(),
                x.getTen(),
                x.getTenDem(),
                x.getHo(),
                x.getDiaChi(),
                x.getSdt(),
                x.getIdGuiBC(),
                x.getCuaHang(),
                x.getChucVu(),
                x.getGioiTinh(),
                x.getNgaySinh(),
                x.getTrangThai());
    }

    @Override
    public String getMatKhau(String ma) {
        return iNhanVienRepository.getMatKhau(ma);
    }

}
