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
public class SanPhamChiTiet {

    private String id;
    private String idSP;
    private String idnsx;
    private String idMau;
    private String dongSp;
    private int namBH;
    private String moTa;
    private int soLuong;
    private double giaNhap, giaBan;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(String id, String idSP, String idnsx, String idMau, String dongSp, int namBH, String moTa, int soLuong, double giaNhap, double giaBan) {
        this.id = id;
        this.idSP = idSP;
        this.idnsx = idnsx;
        this.idMau = idMau;
        this.dongSp = dongSp;
        this.namBH = namBH;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

  



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getIdnsx() {
        return idnsx;
    }

    public void setIdnsx(String idnsx) {
        this.idnsx = idnsx;
    }

    public String getIdMau() {
        return idMau;
    }

    public void setIdMau(String idMau) {
        this.idMau = idMau;
    }

    public String getDongSp() {
        return dongSp;
    }

    public void setDongSp(String dongSp) {
        this.dongSp = dongSp;
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

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
    

}
