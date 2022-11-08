/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTiet {

    private String maSP;
    private int soLuong;
    private float donGia;
    private float thanhTien;
    private String idHD;
    private String idSPCT;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maSP, int soLuong, float donGia, float thanhTien, String idHD, String idSPCT) {
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.idHD = idHD;
        this.idSPCT = idSPCT;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(String idSPCT) {
        this.idSPCT = idSPCT;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "maSP=" + maSP + ", soLuong=" + soLuong + ", donGia=" + donGia + ", thanhTien=" + thanhTien + ", idHD=" + idHD + ", idSPCT=" + idSPCT + '}';
    }

}
