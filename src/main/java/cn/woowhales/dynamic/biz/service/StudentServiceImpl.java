package cn.woowhales.dynamic.biz.service;

import cn.woowhales.dynamic.annotation.DataSourceSelector;
import cn.woowhales.dynamic.biz.entity.Student;
import cn.woowhales.dynamic.biz.mapper.StudentMapper;
import cn.woowhales.dynamic.enums.DataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author woodwhales on 2021-09-09 21:59
 * @Description
 */
@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @DataSourceSelector(DataSourceEnum.MASTER)
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }
}
