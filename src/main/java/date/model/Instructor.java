package date.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTRUCTOR_ID")
    private int instructorId;

    @Column(name = "INSTRUCTOR_EMAIL")
    private String instructorEmail;

    @Column(name = "INSTRUCTOR_PASSWORD")
    private String instructorPassword;

    @Column(name = "INSTRUCTOR_SALT")
    private String instructorSalt;

    @Column(name = "INSTRUCTOR_NAME")
    private String instructorName;

    @Column(name = "INSTRUCTOR_LASTNAME")
    private String instructorLastname;

    @Column(name = "INSTRUCTOR_PHONE")
    private String instructorPhone;

    @Column(name = "INSTRUCTOR_STREET")
    private String instructorStreet;

    @Column(name = "INSTRUCTOR_CITY")
    private String instructorCity;

    @Column(name = "INSTRUCTOR_DATE_REGISTRATION")
    private LocalDate instructorDateRegistration;

    public Instructor() {
    }

    public Instructor(String instructorEmail, String instructorPassword, String instructorSalt, String instructorName, String instructorLastname, String instructorPhone, String instructorStreet, String instructorCity, LocalDate instructorDateRegistration) {
        this.instructorEmail = instructorEmail;
        this.instructorPassword = instructorPassword;
        this.instructorSalt = instructorSalt;
        this.instructorName = instructorName;
        this.instructorLastname = instructorLastname;
        this.instructorPhone = instructorPhone;
        this.instructorStreet = instructorStreet;
        this.instructorCity = instructorCity;
        this.instructorDateRegistration = instructorDateRegistration;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorLastname() {
        return instructorLastname;
    }

    public void setInstructorLastname(String instructorLastname) {
        this.instructorLastname = instructorLastname;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorPassword() {
        return instructorPassword;
    }

    public void setInstructorPassword(String instructorPassword) {
        this.instructorPassword = instructorPassword;
    }

    public String getInstructorSalt() {
        return instructorSalt;
    }

    public void setInstructorSalt(String instructorSalt) {
        this.instructorSalt = instructorSalt;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorStreet() {
        return instructorStreet;
    }

    public void setInstructorStreet(String instructorStreet) {
        this.instructorStreet = instructorStreet;
    }

    public String getInstructorCity() {
        return instructorCity;
    }

    public void setInstructorCity(String instructorCity) {
        this.instructorCity = instructorCity;
    }

    public LocalDate getInstructorDateRegistration() {
        return instructorDateRegistration;
    }

    public void setInstructorDateRegistration(LocalDate instructorDateRegistration) {
        this.instructorDateRegistration = instructorDateRegistration;
    }
}
