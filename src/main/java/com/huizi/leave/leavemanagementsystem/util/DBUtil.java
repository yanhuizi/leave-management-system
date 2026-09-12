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
            if (in == null) {
                throw new IllegalStateException("找不到 db.properties，请确认文件位于 src/main/resources");
            }
            Properties p = new Properties();
            try (InputStream resource = in) {
                p.load(resource);
            }
            url = first(p, "url", "jdbc.url");
            username = first(p, "username", "jdbc.username");
            password = first(p, "password", "jdbc.password");
            Class.forName(first(p, "driver", "jdbc.driver"));
        } catch (Exception e) {
            throw new RuntimeException("读取数据库配置失败", e);
        }
    }

    private static String first(Properties p, String preferred, String legacy) {
        String value = p.getProperty(preferred);
        return value == null ? p.getProperty(legacy) : value;
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
