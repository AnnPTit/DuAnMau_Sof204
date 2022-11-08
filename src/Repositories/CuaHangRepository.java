/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.CuaHang;
import InterFaceRepository.ICuaHangRepository;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CuaHangRepository implements ICuaHangRepository {

    final String INSERT_SQL = "DECLARE @id uniqueidentifier \n"
            + "set @id = NEWID()\n"
            + "INSERT INTO CUAHANG VALUES (@id,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE CUAHANG SET TEN = ? , DIACHI = ? , THANHPHO = ? , QUOCGIA = ? WHERE ma = ?";
    final String DELETE_SQL = " DELETE CUAHANG WHERE ma = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM CUAHANG ";
    final String SELECT_BY_ID_SQL = "SELECT * FROM CUAHANG WHERE ID = ? ";
    final String SELECT_BY_NAME_SQL = "SELECT * FROM CUAHANG WHERE ten = ? ";

    @Override
    public List<CuaHang> getAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    @Override
    public CuaHang getCuaHangById(String ma) {
        return getSelectSql(SELECT_BY_ID_SQL, ma).get(0);
    }

    private List<CuaHang> getSelectSql(String sql, Object... args) {
        try {
            List<CuaHang> list = new ArrayList<>();
            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                list.add(new CuaHang(
                        rs.getString("id"),
                        rs.getString("ma"),
                        rs.getString("ten"),
                        rs.getString("diachi"),
                        rs.getString("thanhpho"),
                        rs.getString("quocgia")));

            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu bảng cửa hàng");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CuaHang getCuaHangByName(String name) {
        return getSelectSql(SELECT_BY_NAME_SQL, name).get(0);
    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    @Override
    public CuaHang update(CuaHang cuaHang) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL, cuaHang.getTen(), cuaHang.getDiaChi(),
                cuaHang.getThanhPho(), cuaHang.getQuocGia(), cuaHang.getMa());
        return cuaHang;
    }

    @Override
    public CuaHang add(CuaHang cuaHang) {
        DBconnetion.ExcuteUpdate(INSERT_SQL, cuaHang.getMa(), cuaHang.getTen(), cuaHang.getDiaChi(), cuaHang.getThanhPho(), cuaHang.getQuocGia());
        return cuaHang;
    }

}
