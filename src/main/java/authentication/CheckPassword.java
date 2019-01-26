package authentication;

import date.dao.InstructorDao;
import date.dao.ManagerDao;
import date.model.Instructor;
import date.model.Manager;

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

    public boolean checkManagerPassword(String email, String password) {

        List<Manager> managersList = managerDao.findAll();
        List<Manager> managersEmail = managersList.stream()
                .filter(m -> m.getManagerEmail().equals(email))
                .collect(Collectors.toList());

        if (managersEmail.get(0).getManagerEmail().equals(email) && managersEmail.get(0).getManagerPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInstructorPassword(String email, String password){
         List<Instructor> instructorList = instructorDao.findAll();
        List<Instructor> instructorsEmail = instructorList.stream()
                .filter(i -> i.getInstructorEmail().equals(email))
                .collect(Collectors.toList());

        if (instructorsEmail.get(0).getInstructorEmail().equals(email) && instructorsEmail.get(0).getInstructorPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
