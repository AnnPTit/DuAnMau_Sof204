/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.DongSp;
import InterFaceRepository.IDongSpRepository;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DongSpRepository implements IDongSpRepository {

    private List<DongSp> list;
    final String INSERT_SQL = "DECLARE @id  uniqueidentifier \n"
            + "SET @id = NEWID() \n"
            + "INSERT INTO DongSp VALUES(@id,?,?)";
    final String UPDATE_SQL = "UPDATE DongSp SET TEN = ? WHERE MA = ?";
    final String DELETE_SQL = " DELETE DongSp WHERE MA = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM DongSp ";
    final String SELECT_BY_ID = "SELECT * FROM DongSp WHERE ID = ?";
    final String SELECT_BY_MA = "SELECT * FROM DongSp WHERE MA = ?";
    final String SELECT_BY_TEN = "SELECT * FROM DongSp WHERE ten = ?";

    public DongSpRepository() {
        list = new ArrayList<>();
    }

    @Override
    public List<DongSp> getAll() {
        list = new ArrayList<>();
        return getSelectSQl(SELECT_ALL_SQL);
    }

    @Override
    public DongSp findById(String Id) {
        return getSelectSQl(SELECT_BY_ID, Id).get(0);
    }

    @Override
    public DongSp save(DongSp sp) {
        DBconnetion.ExcuteUpdate(INSERT_SQL, sp.getMa(), sp.getTen());
        return sp;
    }

    @Override
    public DongSp update(DongSp sp) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL, sp.getTen(), sp.getMa());
        return sp;
    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    @Override
    public DongSp findByMa(String ma) {
        return getSelectSQl(SELECT_BY_MA, ma).get(0);
    }

    @Override
    public DongSp findByName(String name) {
        return getSelectSQl(SELECT_BY_TEN, name).get(0);
    }

    private List<DongSp> getSelectSQl(String sql, Object... args) {
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                DongSp sp = new DongSp(rs.getString("id"), rs.getString("ma"), rs.getString("ten"));
                list.add(sp);
            }
            return list;

        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu Màu");
            e.printStackTrace();
        }
        return null;
    }

}
