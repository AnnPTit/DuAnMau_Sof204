/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.SanPham;
import InterFaceRepository.ISanPhamReporitory;
import Utilities.DBconnetion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SanPhamRepository implements ISanPhamReporitory {

    private List<SanPham> list;
    final String INSERT_SQL = "DECLARE @id  uniqueidentifier \n"
            + "SET @id = NEWID() \n"
            + "INSERT INTO sanpham VALUES(@id,?,?)";
    final String UPDATE_SQL = "UPDATE sanpham SET TEN = ? WHERE MA = ?";
    final String DELETE_SQL = " DELETE sanpham WHERE MA = ?";
    final String SELECT_ALL_SQL = "SELECT id,ma,ten FROM sanpham ";
    final String SELECT_BY_ID = "SELECT id,ma,ten FROM sanpham WHERE ID = ?";
    final String SELECT_BY_MA = "SELECT id,ma,ten FROM sanpham WHERE MA = ?";
    final String SELECT_BY_TEN = "SELECT id,ma,ten FROM sanpham WHERE ten = ?";

    public SanPhamRepository() {
        list = new ArrayList<>();
    }

    @Override
    public List<SanPham> getAll() {
        list = new ArrayList<>();
        return getSelectSQl(SELECT_ALL_SQL);
    }

    @Override
    public SanPham findById(String Id) {
        return getSelectSQl(SELECT_BY_ID, Id).get(0);
    }

    @Override
    public SanPham save(SanPham sanPham) {
        DBconnetion.ExcuteUpdate(INSERT_SQL, sanPham.getMa(), sanPham.getTen());
        return sanPham;
    }

    @Override
    public SanPham update(SanPham sanPham) {
        DBconnetion.ExcuteUpdate(UPDATE_SQL, sanPham.getTen(), sanPham.getMa());
        return sanPham;
    }

    @Override
    public int delete(String ma) {
        return DBconnetion.ExcuteUpdate(DELETE_SQL, ma);
    }

    @Override
    public SanPham findByMa(String ma) {
        return getSelectSQl(SELECT_BY_MA, ma).get(0);
    }

    @Override
    public SanPham findByName(String name) {
        return getSelectSQl(SELECT_BY_TEN, name).get(0);
    }

    private List<SanPham> getSelectSQl(String sql, Object... args) {
        try {
            ResultSet rs = DBconnetion.getDataFromQuery(sql, args);
            while (rs.next()) {
                SanPham mau = new SanPham(rs.getString("id"), rs.getString("ma"), rs.getString("ten"));
                list.add(mau);
            }
            return list;

        } catch (Exception e) {
            System.out.println("Lỗi lấy dữ liệu Màu");
            e.printStackTrace();
        }
        return null;
    }

}
