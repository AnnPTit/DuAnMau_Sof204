/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.SanPham;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ISanPhamReporitory {

    List<SanPham> getAll();

    SanPham findById(String Id);

    SanPham save(SanPham sanPham);

    SanPham update(SanPham sanPham);

    int delete(String ma);

    SanPham findByMa(String ma);

    SanPham findByName(String name);
}
