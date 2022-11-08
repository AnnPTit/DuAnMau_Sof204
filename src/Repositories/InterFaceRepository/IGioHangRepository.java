/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.InterFaceRepository;

import InterFaceRepository.*;
import DomainModels.GioHang;
import DomainModels.GioHangChiTiet;
import ViewModels.QlGioHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IGioHangRepository {

    List<QlGioHang> getAll();
    
    GioHang createGioHang(GioHang gioHang);
    
   
}
