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
public class DownloadUserToDatabase {

    @Inject
    private ManagerDao managerDao;
    @Inject
    private InstructorDao instructorDao;

    public Manager downloadManager(String email) {

        List<Manager> managersList = managerDao.findAll();
        List<Manager> managersEmail = managersList.stream()
                .filter(m -> m.getManagerEmail().equals(email))
                .collect(Collectors.toList());

        Manager manager = new Manager();
        return manager = managersEmail.get(0);
    }

    public Instructor downloadInstructor(String email) {
        List<Instructor> instructorList = instructorDao.findAll();
        List<Instructor> instructorsEmail = instructorList.stream()
                .filter(i -> i.getInstructorEmail().equals(email))
                .collect(Collectors.toList());

        Instructor instructor = new Instructor();
        return instructor = instructorsEmail.get(0);
    }
}
