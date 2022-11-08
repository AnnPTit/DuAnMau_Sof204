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
public class QLChucVu {

    private String ma, ten, id;

    public QLChucVu() {
    }

    public QLChucVu(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public QLChucVu(String ma, String ten, String id) {
        this.ma = ma;
        this.ten = ten;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
