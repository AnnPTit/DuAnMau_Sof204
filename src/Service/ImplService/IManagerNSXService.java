/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.ImplService;

import Service.*;
import ViewModels.QlNSX;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IManagerNSXService {

    List<QlNSX> getAll();

    QlNSX getNSXById(String id);

    QlNSX getNSXByMa(String ma);

    QlNSX createNewNSX(QlNSX nSX);

    QlNSX updateNSX(QlNSX nSX);

    int deleteNSX(String ma);

    QlNSX getNSXByname(String name);
}
