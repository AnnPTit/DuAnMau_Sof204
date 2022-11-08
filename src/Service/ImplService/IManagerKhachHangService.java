/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import ViewModels.QlKhachHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerKhachHangService {

    List<QlKhachHang> getAll();

    QlKhachHang getById(String id);

    QlKhachHang getByMa(String ma);

    QlKhachHang create(QlKhachHang khachHang);

    QlKhachHang update(QlKhachHang khachHang);

    int delete(String ma);

    int countAll();

    List<String> getThanhPho();

    List<String> getQuocGia();

    String getMatKhau(String ma);

}
