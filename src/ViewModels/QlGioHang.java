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
public class QlGioHang {

    private String id;
    private String tenKH;
    private String tenNV, ma, ngayTao, tenNguoiNhan, diaChi, sdt;
    private int tinhTrang;

    public QlGioHang() {
    }

    public QlGioHang(String id, String tenKH, String tenNV, String ma, String ngayTao, String tenNguoiNhan, String diaChi, String sdt, int tinhTrang) {
        this.id = id;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.tenNguoiNhan = tenNguoiNhan;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.tinhTrang = tinhTrang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
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

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "QlGioHang{" + "id=" + id + ", tenKH=" + tenKH + ", tenNV=" + tenNV + ", ma=" + ma + ", ngayTao=" + ngayTao + ", tenNguoiNhan=" + tenNguoiNhan + ", diaChi=" + diaChi + ", sdt=" + sdt + ", tinhTrang=" + tinhTrang + '}';
    }
    

}
