/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.NSX;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface INSXRepository {

    List<NSX> getAll();

    NSX findById(String Id);

    NSX save(NSX nsx);

    NSX update(NSX nsx);

    int delete(String ma);

    NSX findByMa(String ma);

    NSX findByName(String name);

}
