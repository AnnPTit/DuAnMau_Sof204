/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import DomainModels.NhanVien;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class QlHoaDon {

    private String id;
    private String maHD;
    private Date ngayTao;
    private String TenNv;
    private int tinhTrang;

    public QlHoaDon() {
    }

    public QlHoaDon(String id, String maHD, Date ngayTao, String TenNv, int tinhTrang) {
        this.id = id;
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.TenNv = TenNv;
        this.tinhTrang = tinhTrang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenNv() {
        return TenNv;
    }

    public void setTenNv(String TenNv) {
        this.TenNv = TenNv;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "QlHoaDon{" + "maHD=" + maHD + ", ngayTao=" + ngayTao + ", TenNv=" + TenNv + ", tinhTrang=" + tinhTrang + '}';
    }

}
