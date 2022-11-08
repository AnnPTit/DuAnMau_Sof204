/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.DongSp;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IDongSpRepository {

    List<DongSp> getAll();

    DongSp findById(String Id);

    DongSp save(DongSp mau);

    DongSp update(DongSp mau);

    int delete(String ma);

    DongSp findByMa(String ma);

    DongSp findByName(String name);

}
