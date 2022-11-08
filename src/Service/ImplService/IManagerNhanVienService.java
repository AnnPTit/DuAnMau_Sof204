/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import ViewModels.QlNhanVien;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerNhanVienService {

    List<QlNhanVien> getNhanVien(int position, int pageSize);

    QlNhanVien getNhanVienById(String ma);

    QlNhanVien getNhanVienByMa(String ma);

    QlNhanVien createNewNhanVien(QlNhanVien nhanVien);

    QlNhanVien updateNhanVien(QlNhanVien nhanVien);

    int deleteNhanVien(String ma);

    int countAllNhanVien();
    String getMatKhau(String ma);
}
