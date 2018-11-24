package com.huju.mycloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by huju on 2018/11/24.
 */

@AllArgsConstructor // 全参构造函数
@NoArgsConstructor  // 空参构造函数
@Data               // get/set
@Accessors(chain = true) // 允许链式编码风格
public class Dept implements Serializable {

    private Long deptno;   //主键
    private String dname;   //部门名称
    private String db_source;//来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

    public Dept(String dname) {
        super();
        this.dname = dname;
    }


    public static void main(String[] args) {
        Dept dept = new Dept();
        dept.setDeptno(11l).setDname("RD").setDb_source("DB001");
        System.out.println(dept);
    }
}
