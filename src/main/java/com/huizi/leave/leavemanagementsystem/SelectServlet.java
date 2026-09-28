package com.huizi.leave.leavemanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 第三章 3.6.9：学院与系部列表框联动。 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<Department> departments = new ArrayList<Department>();
        String option = request.getParameter("option");
        String college = request.getParameter("college");
        setDepartment(departments, college);

        if ("2".equals(option)) {
            response.setContentType("text/plain;charset=UTF-8");
            StringBuilder json = new StringBuilder("{\"code\":200,\"message\":\"添加成功\",\"object\":[");
            for (int i = 0; i < departments.size(); i++) {
                if (i > 0) json.append(',');
                Department d = departments.get(i);
                json.append("{\"code\":\"").append(d.getCode()).append("\",\"name\":\"")
                        .append(escapeJson(d.getName())).append("\"}");
            }
            json.append("]}");
            response.getWriter().write(json.toString());
            return;
        }

        request.setAttribute("cs", college == null ? "" : college);
        request.setAttribute("dl", departments);
        List<String> selected = new ArrayList<String>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String[] values = entry.getValue();
            if (values != null) {
                for (String value : values) selected.add(entry.getKey() + "---->>" + value);
            }
        }
        request.setAttribute("selected", selected);
        getServletContext().getRequestDispatcher("/pages/threechapt/select.jsp").forward(request, response);
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private void setDepartment(List<Department> list, String college) {
        if ("01".equals(college)) {
            list.add(new Department("0101", "古代汉语"));
            list.add(new Department("0102", "现代汉语"));
            list.add(new Department("0103", "汉教育"));
            list.add(new Department("0104", "大众传媒"));
        } else if ("02".equals(college)) {
            list.add(new Department("0201", "计算机科学与应用"));
            list.add(new Department("0202", "数字媒体"));
            list.add(new Department("0203", "电气工程"));
            list.add(new Department("0204", "电子技术"));
        } else if ("03".equals(college)) {
            list.add(new Department("0301", "经典数学"));
            list.add(new Department("0302", "现代数学"));
            list.add(new Department("0303", "计算科学"));
            list.add(new Department("0304", "机械工程"));
        } else if ("04".equals(college)) {
            list.add(new Department("0401", "古代化学"));
            list.add(new Department("0402", "现代化学"));
            list.add(new Department("0403", "化学教育"));
            list.add(new Department("0404", "爆炸控制"));
        } else if ("05".equals(college)) {
            list.add(new Department("0501", "中医学"));
            list.add(new Department("0502", "西医学"));
            list.add(new Department("0503", "临床医学"));
            list.add(new Department("0504", "护理学"));
        }
    }
}
