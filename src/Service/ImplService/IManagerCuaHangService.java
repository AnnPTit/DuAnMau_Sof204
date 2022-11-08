/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import DomainModels.CuaHang;
import ViewModels.QlCuaHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerCuaHangService {

    List<QlCuaHang> getAllCuaHang();

    QlCuaHang getCuaHangById(String id);

    QlCuaHang getCuaHangByTen(String ten);

    QlCuaHang createNewCuaHang(QlCuaHang cuaHang);

    QlCuaHang updateCuaHang(QlCuaHang cuaHang);

    int deleteCuaHang(String ma);

}
