package date.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private int studentId;

    @Column(name = "STUDENT_ROLE")
    @NotNull
    private int studentRole;

    @Column(name = "STUDENT_EMAIL")
    private String studentEmail;

    @Column(name = "STUDENT_PASSWORD")
    private String studentPassword;

    @Column(name = "STUDENT_SALT")
    private String studentSalt;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Column(name = "STUDENT_LASTNAME")
    private String studentLastname;

    @Column(name = "STUDENT_PESEL")
    private String studentPESEL;

    @Column(name = "STUDENT_PHONE")
    private String studentPhone;

    @Column(name = "STUDENT_STATUS")
    private String studentStatus;

    @Column(name = "STUDENT_CITY")
    private String studentCity;

    @Column(name = "STUDENT_STREET")
    private String studentStreet;

    @Column(name = "STUDENT_DATE_REGISTRATION")
    private LocalDate studentDateRegistration;

    public Student() {
    }

    public Student(int studentRole, String studentEmail, String studentPassword, String studentSalt, String studentName, String studentLastname, String studentPESEL, String studentPhone, String studentStatus, String studentCity, String studentStreet, LocalDate studentDateRegistration) {
        this.studentRole = studentRole;
        this.studentEmail = studentEmail;
        this.studentPassword = studentPassword;
        this.studentSalt = studentSalt;
        this.studentName = studentName;
        this.studentLastname = studentLastname;
        this.studentPESEL = studentPESEL;
        this.studentPhone = studentPhone;
        this.studentStatus = studentStatus;
        this.studentCity = studentCity;
        this.studentStreet = studentStreet;
        this.studentDateRegistration = studentDateRegistration;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(int studentRole) {
        this.studentRole = studentRole;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentSalt() {
        return studentSalt;
    }

    public void setStudentSalt(String studentSalt) {
        this.studentSalt = studentSalt;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastname() {
        return studentLastname;
    }

    public void setStudentLastname(String studentLastname) {
        this.studentLastname = studentLastname;
    }

    public String getStudentPESEL() {
        return studentPESEL;
    }

    public void setStudentPESEL(String studentPESEL) {
        this.studentPESEL = studentPESEL;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

    public String getStudentStreet() {
        return studentStreet;
    }

    public void setStudentStreet(String studentStreet) {
        this.studentStreet = studentStreet;
    }

    public LocalDate getStudentDateRegistration() {
        return studentDateRegistration;
    }

    public void setStudentDateRegistration(LocalDate studentDateRegistration) {
        this.studentDateRegistration = studentDateRegistration;
    }
}
