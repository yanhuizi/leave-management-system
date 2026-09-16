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
        Connection connection = DriverManager.getConnection(url, username, password);
        ensureLeaveTable(connection);
        return connection;
    }

    /** 老师的 qingjia.sql 不包含本项目新增的请假业务表，首次连接时自动补齐。 */
    private static void ensureLeaveTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS leave_request (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "student_id BIGINT NOT NULL, student_num VARCHAR(50) NOT NULL," +
                "student_name VARCHAR(100) NOT NULL, type VARCHAR(20) NOT NULL," +
                "start_time DATETIME NOT NULL, end_time DATETIME NOT NULL," +
                "reason VARCHAR(500) NOT NULL, status VARCHAR(20) NOT NULL DEFAULT '待审批'," +
                "reviewer_id BIGINT NULL, review_comment VARCHAR(500) NULL," +
                "created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "INDEX idx_leave_student(student_id), INDEX idx_leave_status(status))";
        try (Statement statement = connection.createStatement()) { statement.executeUpdate(sql); }
    }

    public static void close(Connection c, Statement s, ResultSet r) {
        try { if (r != null) r.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (s != null) s.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (c != null) c.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}
