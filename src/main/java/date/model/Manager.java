package date.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "MANAGER")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANAGER_ID")
    private int managerId;

    @Column(name = "MANAGER_Role")
    @NotNull
    private int managerRole;

    @Column(name = "MANAGER_EMAIL")
    private String managerEmail;

    @Column(name = "MANAGER_PASSWORD")
    private String managerPassword;

    @Column(name = "MANAGER_NAME")
    private String managerName;

    @Column(name = "MANAGER_LASTNAME")
    private String managerLastname;

    @Column(name = "MANAGER_DATE_REGISTRATION")
    private LocalDate managerDateRegistration;

    public Manager() {
    }

    public Manager(int managerRole, String managerEmail, String managerPassword, String managerName, String managerLastname, LocalDate managerDateRegistration) {
        this.managerRole = managerRole;
        this.managerEmail = managerEmail;
        this.managerPassword = managerPassword;
        this.managerName = managerName;
        this.managerLastname = managerLastname;
        this.managerDateRegistration = managerDateRegistration;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getManagerRole() {
        return managerRole;
    }

    public void setManagerRole(int managerRole) {
        this.managerRole = managerRole;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }

    public LocalDate getManagerDateRegistration(LocalDate now) {
        return managerDateRegistration;
    }

    public void setManagerDateRegistration(LocalDate managerDateRegistration) {
        this.managerDateRegistration = managerDateRegistration;
    }
}
