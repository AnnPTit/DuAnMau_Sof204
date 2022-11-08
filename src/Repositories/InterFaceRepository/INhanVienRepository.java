/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.NhanVien;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface INhanVienRepository {

    List<NhanVien> getAll(int position, int pageSize);

    NhanVien findByID(String ma);

    NhanVien save(NhanVien nhanVien);

    NhanVien update(NhanVien nhanVien);

    int delete(String ma);

    int totalCount();
    

    NhanVien getNhanVienByMa(String ma);
    
    String getMatKhau(String ma);

    

}
