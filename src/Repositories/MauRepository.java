/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.Mau;
import DomainModels.NSX;
import InterFaceRepository.IMauRepository;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MauRepository implements IMauRepository {

    private List<Mau> list;
    final String INSERT_SQL = "DECLARE @id  uniqueidentifier \n"
            + "SET @id = NEWID() \n"
            + "INSERT INTO Mausac VALUES(@id,?,?)";
    final String UPDATE_SQL = "UPDATE Mausac SET TEN = ? WHERE MA = ?";
    final String DELETE_SQL = " DELETE Mausac WHERE MA = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM Mausac ";
    final String SELECT_BY_ID = "SELECT * FROM Mausac WHERE ID = ?";
    final String SELECT_BY_MA = "SELECT * FROM Mausac WHERE MA = ?";
    final String SELECT_BY_TEN = "SELECT * FROM Mausac WHERE ten = ?";

    public MauRepository() {
        list = new ArrayList<>();
    }

    @Override
    public Mau findById(String Id) {
        return getSelectSQl(SELECT_BY_ID, Id).get(0);
    }

    @Override
    public Mau save(Mau mau) {
        DBconnetion.ExcuteUpdate(INSERT_SQL, mau.getMa(), mau.getTen());
        return mau;
    }

    @Override
    public Mau update(Mau mau) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL, mau.getTen(), mau.getMa());
        return mau;
    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    @Override
    public Mau findByMa(String ma) {
        return getSelectSQl(SELECT_BY_MA, ma).get(0);
    }

    @Override
    public Mau findByName(String name) {
        return getSelectSQl(SELECT_BY_TEN, name).get(0);
    }

    private List<Mau> getSelectSQl(String sql, Object... args) {
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                Mau mau = new Mau(rs.getString("id"), rs.getString("ma"), rs.getString("ten"));
                list.add(mau);
            }
            return list;

        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu Màu");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Mau> getAll() {
        list = new ArrayList<>();
        return getSelectSQl(SELECT_ALL_SQL);
    }

}
