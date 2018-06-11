package com.lyldemo.entity;

/**
 * Created by KJT-30 on 2018/5/25.
 */
//物流分类
public class TypeInfo {
    private String Name;
    private int SysNo;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public int getSysNo() {
        return SysNo;
    }

    public void setSysNo(int sysNo) {
        SysNo = sysNo;
    }

    @Override
    public String toString() {
        return "TypeInfo{" +
                "name='" + Name + '\'' +
                ", SysNo=" + SysNo +
                '}';
    }
}
