/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.ChucVu;
import InterFaceRepository.IChucVuRepositories;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChucVuRepositories implements IChucVuRepositories {

    final String INSERT_SQL = "Declare @id uniqueidentifier \n"
            + "set @id = NEWID() \n"
            + "INSERT INTO CHUCVU VALUES(@id,?,?)";
    final String UPDATE_SQL = "UPDATE CHUCVU SET TEN = ? WHERE MA = ?";
    final String DELETE_SQL = "DELETE CHUCVU WHERE MA = ?";
    final String SELECT_BY_SQL = "SELECT * FROM CHUCVU WHERE ID = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM CHUCVU";
    final String SELECT_BY_NAME_SQL = "SELECT * FROM CHUCVU WHERE ten = ?";

    @Override
    public List<ChucVu> findAll() {
        return getSelectSql(SELECT_ALL_SQL);
    }

    @Override
    public ChucVu getChucVuById(String id) {
        return getSelectSql(SELECT_BY_SQL, id).get(0);
    }

    private List<ChucVu> getSelectSql(String sql, Object... args) {
        List<ChucVu> listChucVu = new ArrayList<>();
        try {

            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                listChucVu.add(new ChucVu(rs.getString("id"), rs.getString("ma"), rs.getString("ten")));
            }

            return listChucVu;

        } catch (Exception e) {
            System.out.println("Lỗi getSelectSql");
            e.printStackTrace();

        }
        return listChucVu;

    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    @Override
    public ChucVu update(ChucVu chucVu) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL, chucVu.getTen(), chucVu.getMa());
        return chucVu;
    }

    @Override
    public ChucVu add(ChucVu chucVu) {
        DBconnetion.ExcuteUpdate(INSERT_SQL, chucVu.getMa(), chucVu.getTen());
        return chucVu;
    }

    @Override
    public ChucVu getChucVuByName(String name) {
        return getSelectSql(SELECT_BY_NAME_SQL, name).get(0);
    }

}
