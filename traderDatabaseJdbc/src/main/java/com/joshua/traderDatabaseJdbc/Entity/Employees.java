package com.joshua.traderDatabaseJdbc.Entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("employees")
public class Employees {

    private String employeeId;
    private String lastName;
    private String firstName;
    private String jobTitle;
    private String birthDate;
    private String hireDate;

    private String cityName;

    private City city;

    private int age;

    private int yearsAtWork;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYearsAtWork() {
        return yearsAtWork;
    }

    public void setYearsAtWork(int yearsAtWork) {
        this.yearsAtWork = yearsAtWork;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeId='" + employeeId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}

