package com.feiye.service.impl;

import com.feiye.mapper.EmpMapper;
import com.feiye.pojo.Emp;
import com.feiye.pojo.PageBean;
import com.feiye.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.0
 * @Author feiye
 * @Date 2024-07-14 17:22
 * @className EmpServiceImpl
 * @since 1.0
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        // 1. 获取总记录数
        Long count = empMapper.count();

        // 2. 获取分页查询结果列表
        Integer start = (page-1)*pageSize;
        List<Emp> empList = empMapper.page(start, pageSize);

        // 3. 封装PageBean对象
        PageBean pageBean = new PageBean(count, empList);
        return pageBean;
    }*/

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // 1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        // 3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp pageById(Integer id) {
        return empMapper.pageById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
