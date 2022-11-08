/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterFaceRepository;

import Repositories.*;
import DomainModels.ChucVu;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IChucVuRepositories {

    List<ChucVu> findAll();

    ChucVu getChucVuById(String id);

    int delete(String ma);

    ChucVu update(ChucVu chucVu);

    ChucVu add(ChucVu chucVu);
    
    ChucVu getChucVuByName(String name);

}
