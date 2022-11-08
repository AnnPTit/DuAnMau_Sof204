/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import DomainModels.ChucVu;
import DomainModels.CuaHang;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class QlNhanVien {

    private String id, ma, ten, tenDem, ho, diaChi, sdt, idBaoCao;
    private CuaHang cuaHang;
    private ChucVu chucVu;
    private String gioiTinh;
    private Date ngaySinh;
    private int trangThai;
    private String matKhau;

    public QlNhanVien() {
    }

//    public QlNhanVien(String ma, String ten, String tenDem, String ho, String diaChi, String sdt, String idBaoCao, CuaHang cuaHang, ChucVu chucVu, String gioiTinh, Date ngaySinh, int trangThai, String matKhau) {
//        this.ma = ma;
//        this.ten = ten;
//        this.tenDem = tenDem;
//        this.ho = ho;
//        this.diaChi = diaChi;
//        this.sdt = sdt;
//        this.idBaoCao = idBaoCao;
//        this.cuaHang = cuaHang;
//        this.chucVu = chucVu;
//        this.gioiTinh = gioiTinh;
//        this.ngaySinh = ngaySinh;
//        this.trangThai = trangThai;
//        this.matKhau = matKhau;
//    }
//    public QlNhanVien(String ma, String ten, String tenDem, String ho, String diaChi, String sdt, String matKhau, CuaHang cuaHang, ChucVu chucVu, String gioiTinh, Date ngaySinh, int trangThai) {
//
//        this.ma = ma;
//        this.ten = ten;
//        this.tenDem = tenDem;
//        this.ho = ho;
//        this.diaChi = diaChi;
//        this.sdt = sdt;
//        //  this.idBaoCao = idGuiBC;
//        this.cuaHang = cuaHang;
//        this.chucVu = chucVu;
//        this.gioiTinh = gioiTinh;
//        this.ngaySinh = ngaySinh;
//        this.trangThai = trangThai;
//        this.matKhau = matKhau;
//    }

    public QlNhanVien(String id, String ma, String ten, String tenDem, String ho, String diaChi, String sdt, String idGuiBC, CuaHang cuaHang, ChucVu chucVu, String gioiTinh, Date ngaySinh, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tenDem = tenDem;
        this.ho = ho;
        this.diaChi = diaChi;
        this.sdt = sdt;
        //  this.idBaoCao = idGuiBC;
        this.cuaHang = cuaHang;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.trangThai = trangThai;
        this.matKhau = matKhau;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getIdBaoCao() {
        return idBaoCao;
    }

    public void setIdBaoCao(String idBaoCao) {
        this.idBaoCao = idBaoCao;
    }

    public CuaHang getCuaHang() {
        return cuaHang;
    }

    public void setCuaHang(CuaHang cuaHang) {
        this.cuaHang = cuaHang;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoten() {
        String hoTen = getHo() + " " + getTenDem() + " " + getTen();
        return hoTen;
    }

    @Override
    public String toString() {
        return   ho + " " + tenDem + " " + ten +"-"+ma ;
    }
    

}
