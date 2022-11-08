/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModels;

/**
 *
 * @author ADMIN
 */
public class GioHangChiTiet {
    private String idGioHang ,idChiTietSp;
    private int soLuong;
    private float donGia,donGiaKhiGiam;

    public GioHangChiTiet() {
    }

    public GioHangChiTiet(String idGioHang, String idChiTietSp, int soLuong, float donGia, float donGiaKhiGiam) {
        this.idGioHang = idGioHang;
        this.idChiTietSp = idChiTietSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

    public String getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(String idGioHang) {
        this.idGioHang = idGioHang;
    }

    public String getIdChiTietSp() {
        return idChiTietSp;
    }

    public void setIdChiTietSp(String idChiTietSp) {
        this.idChiTietSp = idChiTietSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getDonGiaKhiGiam() {
        return donGiaKhiGiam;
    }

    public void setDonGiaKhiGiam(float donGiaKhiGiam) {
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

    @Override
    public String toString() {
        return "GioHangChiTiet{" + "idGioHang=" + idGioHang + ", idChiTietSp=" + idChiTietSp + ", soLuong=" + soLuong + ", donGia=" + donGia + ", donGiaKhiGiam=" + donGiaKhiGiam + '}';
    }
    
    
    
}
