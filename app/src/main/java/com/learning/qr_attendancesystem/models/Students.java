package com.learning.qr_attendancesystem.models;

public class Students {



    private String studentName;

    private String mobileNo;

    private String studentMail;

    private String course;
    private String batch;

    public Students(String studentName, String mobileNo, String studentMail, String course, String batch) {
        this.studentName = studentName;
        this.mobileNo = mobileNo;
        this.studentMail = studentMail;
        this.course = course;
        this.batch = batch;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}

