package cn.woowhales.dynamic.biz.controller;

import cn.woowhales.dynamic.biz.entity.Student;
import cn.woowhales.dynamic.biz.mapper.StudentMapper;
import cn.woowhales.dynamic.biz.service.StudentService2Impl;
import cn.woowhales.dynamic.biz.service.StudentServiceImpl;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author woodwhales on 2021-09-09 21:58
 * @Description
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService2Impl studentService2Impl;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    /**
     * 根据请求头中的标识获取对应的数据源
     * @return
     */
    @GetMapping("/get")
    public Object get() {
        return studentMapper.selectAll();
    }

    /**
     * 根据请求头中的标识获取对应的数据源
     * @return
     */
    @PostMapping("/update")
    public Object update() {
        return studentMapper.selectAll();
    }

    /**
     * 多数据源查询
     * @return
     */
    @GetMapping("/mix")
    public Object mix() {
        List<Student> students1 = studentService2Impl.selectAll();
        List<Student> students2 = studentServiceImpl.selectAll();
        return ListUtils.union(students1, students2);
    }
}
