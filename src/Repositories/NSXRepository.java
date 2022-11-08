/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.NSX;
import InterFaceRepository.INSXRepository;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class NSXRepository implements INSXRepository {

    private List<NSX> list;
    final String INSERT_SQL = "DECLARE @id  uniqueidentifier \n"
            + "SET @id = NEWID() \n"
            + "INSERT INTO NSX VALUES(@id,?,?)";
    final String UPDATE_SQL = "UPDATE NSX SET TEN = ? WHERE MA = ?";
    final String DELETE_SQL = " DELETE NSX WHERE MA = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NSX ";
    final String SELECT_BY_ID = "SELECT * FROM NSX WHERE ID = ?";
    final String SELECT_BY_MA = "SELECT * FROM NSX WHERE MA = ?";
    final String SELECT_BY_TEN = "SELECT * FROM NSX WHERE ten = ?";

    public NSXRepository() {
        list = new ArrayList<>();
    }

    @Override

    public List<NSX> getAll() {
        list = new ArrayList<>();
        return getSelectSQl(SELECT_ALL_SQL);
    }

    @Override
    public NSX findById(String id) {
        return getSelectSQl(SELECT_BY_ID, id).get(0);
    }

    @Override
    public NSX save(NSX nsx) {
        DBconnetion.ExcuteUpdate(INSERT_SQL, nsx.getMa(), nsx.getTen());
        return nsx;
    }

    @Override
    public NSX update(NSX nsx) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL, nsx.getTen(), nsx.getMa());
        return nsx;
    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    private List<NSX> getSelectSQl(String sql, Object... args) {
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                NSX nsx = new NSX(rs.getString("id"), rs.getString("ma"), rs.getString("ten"));
                list.add(nsx);
            }
            return list;

        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu NSX");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NSX findByMa(String ma) {
        return getSelectSQl(SELECT_BY_MA, ma).get(0);
    }

    @Override
    public NSX findByName(String name) {
        return getSelectSQl(SELECT_BY_TEN, name).get(0);
    }

}
