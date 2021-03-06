package authentication;

import date.dao.InstructorDao;
import date.dao.ManagerDao;
import date.dao.StudentDao;
import date.model.Instructor;
import date.model.Manager;
import date.model.Student;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CheckPassword {

    @Inject
    private ManagerDao managerDao;
    @Inject
    private InstructorDao instructorDao;
    @Inject
    private StudentDao studentDao;
    @Inject
    PasswordHashing passwordHashing;

    public boolean checkManagerPassword(String email, String password) {

        List<Manager> managersList = managerDao.findAll();
        List<Manager> managersEmail = managersList.stream()
                .filter(m -> m.getManagerEmail().equals(email))
                .collect(Collectors.toList());
        String passwordHash = managersEmail.get(0).getManagerPassword();

        if (managersEmail.get(0).getManagerEmail().equals(email) && passwordHashing.checkPassword(password, passwordHash)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInstructorPassword(String email, String password) {
        List<Instructor> instructorList = instructorDao.findAll();
        List<Instructor> instructorsEmail = instructorList.stream()
                .filter(i -> i.getInstructorEmail().equals(email))
                .collect(Collectors.toList());

        String passwordHash = instructorsEmail.get(0).getInstructorPassword();
        if (instructorsEmail.get(0).getInstructorEmail().equals(email) && passwordHashing.checkPassword(password, passwordHash)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkStudentPassword(String email, String password) {
        List<Student> studentList = studentDao.findAll();
        List<Student> studentsEmail = studentList.stream()
                .filter(s -> s.getStudentEmail().equals(email))
                .collect(Collectors.toList());
        String passwordHash = studentsEmail.get(0).getStudentPassword();
        if (studentsEmail.get(0).getStudentEmail().equals(email) && passwordHashing.checkPassword(password, passwordHash)) {
            return true;
        } else {
            return false;
        }
    }
}
