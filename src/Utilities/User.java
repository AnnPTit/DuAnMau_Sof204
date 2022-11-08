/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import ViewModels.QlKhachHang;
import ViewModels.QlNhanVien;

/**
 *
 * @author ADMIN
 */
public class User {

    public static String user = null;
    public static String id = null;
    public static boolean roll;

//    public static boolean isLogIn(QlNhanVien nhanVienLogIn) {
//        user = nhanVienLogIn.getMa();
//        id = nhanVienLogIn.getId();
//        return true;
//    }
    public User() {
    }

    public static boolean isRoll() {
        return roll;
    }

    public static void setRoll(boolean roll) {
        User.roll = roll;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        User.user = user;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        User.id = id;
    }

    public static boolean isLogIn(Object... args) {

        user = args[0].toString();
        id = args[1].toString();
     
        return roll;
    }

}
