/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import ViewModels.QLSanPham;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerSanPhamService {
    List<QLSanPham> getAll();

    QLSanPham getByID(String id);

    QLSanPham getByMa(String ma);

    QLSanPham create(QLSanPham sanPham);

    QLSanPham update(QLSanPham sanPham);

    int delete(String ma);

    QLSanPham getByName(String name);
}
