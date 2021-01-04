package com.zss.kernel.aop.transaction.dao;

import com.zss.kernel.aop.transaction.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDaoImpl {

    private JdbcTemplate jdbcTemplate;

    public int insertStudent(Student student){

        String sql = "insert into student(stu_name,stu_age) values (?,?)";
        int effect = jdbcTemplate.update(sql,student.getStuName(),student.getStuAge());

        return effect;
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
