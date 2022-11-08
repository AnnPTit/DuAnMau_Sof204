/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import DomainModels.Mau;
import ViewModels.QlMau;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerMauService {

    List<QlMau> getAll();

    QlMau getByID(String id);

    QlMau getByMa(String ma);

    QlMau create(QlMau mau);

    QlMau update(QlMau mau);

    int delete(String ma);

    QlMau getByName(String name);
}
