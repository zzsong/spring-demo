package com.zss.kernel.aop.transaction.service;

import com.zss.kernel.aop.transaction.dao.StudentDaoImpl;
import com.zss.kernel.aop.transaction.domain.Student;

public class StudentServiceImpl implements StudentService{
    private StudentDaoImpl studentDao;

    public int insertStudent(Student student){
        return studentDao.insertStudent(student);
    }


    public StudentDaoImpl getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDaoImpl studentDao) {
        this.studentDao = studentDao;
    }
}
