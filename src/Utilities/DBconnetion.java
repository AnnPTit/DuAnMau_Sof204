/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class DBconnetion {

    private static String hostName = "JAVA\\SQLEXPRESS";
    private static String acc = "sa";
    private static String pass = "123456";
    private static String dbName = "FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041";
    private static String connectionSql
            = "jdbc:sqlserver://" + hostName + ":1433;databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection conn;

    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Driver error");
            e.printStackTrace();
        }
    }

    // 1. Mở kết nối 
    public static Connection openDbConnetion() {
        try {
            return DriverManager.getConnection(connectionSql, acc, pass);
        } catch (Exception e) {
            System.out.println("Connetion error");
            e.printStackTrace();
            return null;
        }
    }

    // 2. Thực thi truy vấn Thêm , Sửa , Xóa 
    public static int ExcuteUpdate(String sql, Object... args) {
        PreparedStatement pstm = getStmt(sql, args);
        try {
            try {
                return pstm.executeUpdate();
            } finally {
                pstm.close();
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn");
            e.printStackTrace();
            return 0;
        }
    }

    // 3. Trả lại một tập đối tượng  
    public static ResultSet getDataFromQuery(String sql, Object... args) throws SQLException {
        PreparedStatement pstm = getStmt(sql, args);
        return pstm.executeQuery();

    }

    // 4. Chuẩn bị try vấn  trước khi thực thi 
    public static PreparedStatement getStmt(String sql, Object... args) {
        try {
            conn = openDbConnetion();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql); // Dùng để triển khai câu lệnh truy vấn thường
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps;
        } catch (Exception e) {
            System.out.println("Lỗi prepare");
            e.printStackTrace();
            return null;

        }
    }
}
