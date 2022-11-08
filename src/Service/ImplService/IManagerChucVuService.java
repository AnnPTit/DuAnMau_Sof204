/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import ViewModels.QLChucVu;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerChucVuService {

    List<QLChucVu> getChucVu();

    QLChucVu getChucVuById(String ma);

    QLChucVu createNewChucVu(QLChucVu chucVu);

    QLChucVu updateChuVu(QLChucVu chucVu);

    int deleteChucVu(String ma);

    QLChucVu getChucVuByName(String name);

}
