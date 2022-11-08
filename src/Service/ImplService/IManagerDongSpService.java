/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import ViewModels.QlDongSp;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerDongSpService {
      List<QlDongSp> getAll();

    QlDongSp getByID(String id);

    QlDongSp getByMa(String ma);

    QlDongSp create(QlDongSp sp);

    QlDongSp update(QlDongSp sp);

    int delete(String ma);

    QlDongSp getByName(String name);
}
