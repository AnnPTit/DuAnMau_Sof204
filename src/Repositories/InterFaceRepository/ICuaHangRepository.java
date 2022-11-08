/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.CuaHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ICuaHangRepository {

    List<CuaHang> getAll();

    CuaHang getCuaHangById(String ma);

    CuaHang getCuaHangByName(String name);

    int delete(String ma);

    CuaHang update(CuaHang cuaHang);

    CuaHang add(CuaHang cuaHang);

}
