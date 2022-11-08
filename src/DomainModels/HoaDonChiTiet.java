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
public class HoaDonChiTiet {

    private String idHoaDon, idCTSP;
    private int soLuong;
    private float donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String idHoaDon, String idCTSP, int soLuong, float donGia) {
        this.idHoaDon = idHoaDon;
        this.idCTSP = idCTSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
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

}
