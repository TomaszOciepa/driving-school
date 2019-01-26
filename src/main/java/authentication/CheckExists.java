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
public class CheckExists {

    @Inject
    private ManagerDao managerDao;
    @Inject
    private InstructorDao instructorDao;
    @Inject
    private StudentDao studentDao;

    public boolean checkManagerExists(String email) {

        List<Manager> managersList = managerDao.findAll();
        List<Manager> managersEmail = managersList.stream()
                .filter(m -> m.getManagerEmail().equals(email))
                .collect(Collectors.toList());

        if (!managersEmail.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkInstructorExists(String email) {
        List<Instructor> instructorList = instructorDao.findAll();
        List<Instructor> instructorsEmail = instructorList.stream()
                .filter(i -> i.getInstructorEmail().equals(email))
                .collect(Collectors.toList());

        if (!instructorsEmail.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkStudentExists(String email) {
        List<Student> studentList = studentDao.findAll();
        List<Student> studentsEmail = studentList.stream()
                .filter(s -> s.getStudentEmail().equals(email))
                .collect(Collectors.toList());

        if (!studentsEmail.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


}
