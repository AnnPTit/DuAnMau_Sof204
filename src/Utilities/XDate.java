/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class XDate {

    static SimpleDateFormat sdf = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            sdf.applyPattern(pattern);
            return sdf.parse(date);
        } catch (Exception e) {
            System.out.println("Lỗi chuyển chuỗi - date");
            JOptionPane.showMessageDialog(null, "Ngày sai định dạng");
            return null;
        }

    }

    public static String toString(Date date, String pattern) { // chuyển date -> chuỗi
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    public static Date addDay(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

   
}
