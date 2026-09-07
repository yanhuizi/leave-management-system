package com.huizi.leave.leavemanagementsystem.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String url, username, password;

    static {
        try {
            InputStream in = DBUtil.class.getClassLoader()
                    .getResourceAsStream("db.properties");
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("jdbc.url");
            username = p.getProperty("jdbc.username");
            password = p.getProperty("jdbc.password");
            Class.forName(p.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new RuntimeException("读取数据库配置失败", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection c, Statement s, ResultSet r) {
        try { if (r != null) r.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (s != null) s.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (c != null) c.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}
