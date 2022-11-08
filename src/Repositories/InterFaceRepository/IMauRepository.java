/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.Mau;
import DomainModels.NSX;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IMauRepository {

    List<Mau> getAll();

    Mau findById(String Id);

    Mau save(Mau mau);

    Mau update(Mau mau);

    int delete(String ma);

    Mau findByMa(String ma);

    Mau findByName(String name);

}
