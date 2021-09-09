package cn.woowhales.dynamic.biz.mapper;

import cn.woowhales.dynamic.biz.entity.Student;

import java.util.List;

/**
 * @author woodwhales on 2021-09-09 16:31
 * @description
 */
public interface StudentMapper {

    List<Student> selectAll();

}
