package com.huizi.leave.leavemanagementsystem;

/** 第一章资料中列表框联动示例使用的系部实体。 */
public class Department {
    private final String code;
    private final String name;

    public Department(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
}
