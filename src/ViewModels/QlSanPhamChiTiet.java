/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import DomainModels.DongSp;
import DomainModels.Mau;
import DomainModels.NSX;

/**
 *
 * @author ADMIN
 */
public class QlSanPhamChiTiet {
private String maSP,tenSP;
private int namBH;
private String moTa;
private int soLuong;
private float giaNhap;
private float giaBan;
private String id;

    public QlSanPhamChiTiet() {
    }

    public QlSanPhamChiTiet(String maSP, String tenSP, int namBH, String moTa, int soLuong, float giaNhap, float giaBan, String id) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.namBH = namBH;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.id = id;
    }

  

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getNamBH() {
        return namBH;
    }

    public void setNamBH(int namBH) {
        this.namBH = namBH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QlSanPhamChiTiet{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", namBH=" + namBH + ", moTa=" + moTa + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", id=" + id + '}';
    }
   

    


    
    
    
}
    