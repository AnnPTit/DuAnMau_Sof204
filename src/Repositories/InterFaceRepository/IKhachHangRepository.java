/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.KhachHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IKhachHangRepository {

    List<KhachHang> getAll();

    KhachHang findById(String Id);

    KhachHang save(KhachHang kh);

    KhachHang update(KhachHang kh);

    int delete(String ma);

    KhachHang findByMa(String ma);

    KhachHang findByName(String name);

    int totalCount();

    List<String> getThanhPho();

    List<String> getQuocGia();
    
    String getMatKhau(String ma);
}
