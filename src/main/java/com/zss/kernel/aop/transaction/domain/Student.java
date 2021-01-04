package com.zss.kernel.aop.transaction.domain;

public class Student {

    /*
    CREATE TABLE STUDENT
(
    ID INT(11) NOT NULL AUTO_INCREMENT,
    STU_NAME  VARCHAR(100)  COMMENT ‘姓名’,
    PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
     */
    private String stuName;
    private int stuAge;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }
}
