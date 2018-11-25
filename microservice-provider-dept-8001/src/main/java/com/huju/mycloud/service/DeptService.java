package com.huju.mycloud.service;

import com.huju.mycloud.entities.Dept;

import java.util.List;

/**
 * Created by huju on 2018/11/25.
 */
public interface DeptService {

    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();

}
